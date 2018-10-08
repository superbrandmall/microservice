package com.sbm.module.sync.hd.api.merchant.biz.impl;

import com.sbm.module.common.biz.impl.SyncServiceImpl;
import com.sbm.module.common.domain.SyncResult;
import com.sbm.module.onlineleasing.base.merchant.biz.ITOLMerchantService;
import com.sbm.module.onlineleasing.base.merchant.domain.TOLMerchant;
import com.sbm.module.onlineleasing.base.merchantaddress.biz.ITOLMerchantAddressService;
import com.sbm.module.onlineleasing.base.merchantaddress.domain.TOLMerchantAddress;
import com.sbm.module.onlineleasing.base.merchantbankaccount.biz.ITOLMerchantBankAccountService;
import com.sbm.module.onlineleasing.base.merchantbankaccount.domain.TOLMerchantBankAccount;
import com.sbm.module.onlineleasing.base.tempparam.biz.ITOLTempParamService;
import com.sbm.module.onlineleasing.base.tempparam.constant.TempParamConstant;
import com.sbm.module.partner.hd.rest.base.domain.HdQueryFilter;
import com.sbm.module.partner.hd.rest.merchant.client.IHdMerchantClient;
import com.sbm.module.partner.hd.rest.merchant.domain.HdBank;
import com.sbm.module.partner.hd.rest.merchant.domain.HdMerchant;
import com.sbm.module.sync.hd.api.merchant.biz.IMerchantService;
import com.sbm.module.sync.hd.api.merchant.domain.SyncMerchant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class MerchantServiceImpl extends SyncServiceImpl<SyncMerchant, HdMerchant, HdQueryFilter> implements IMerchantService {

	@Autowired
	private IHdMerchantClient hdMerchantClient;
	@Autowired
	private ITOLMerchantService merchantService;
	@Autowired
	private ITOLMerchantAddressService merchantAddressService;
	@Autowired
	private ITOLMerchantBankAccountService merchantBankAccountService;

	@Autowired
	private ITOLTempParamService tempParamService;

	private static final String ERROR_MESSAGE = "商户同步异常";

	@Override
	@Scheduled(cron = "${sync.cron.merchant}")
	public void refresh() {
		HdQueryFilter filter = new HdQueryFilter();
		filter.getFilter().put("type", "merchant");
		try {
			execute(filter, e -> newInstance(e));
		} catch (Exception ex) {
			log.error(ERROR_MESSAGE, ex);
		}
	}

	public SyncMerchant newInstance(HdMerchant e) {
		SyncMerchant sync = new SyncMerchant();
		// 添加商户
		sync.setMerchant(convert2Merchant(e));
		// 添加商户地址
		sync.setMerchantAddress(convert2MerchantAddress(sync.getMerchant().getCode(), e));
		// 添加商户银行账号
		sync.setMerchantBankAccounts(convert2MerchantBankAccount(sync.getMerchant().getCode(), e));
		return sync;
	}

	/**
	 * 商户
	 *
	 * @param e
	 * @return
	 */
	private TOLMerchant convert2Merchant(HdMerchant e) {
		TOLMerchant po = merchantService.findOneByHdUuid(e.getUuid());
		if (null == po) {
			po = merchantService.newInstance();
		}
		// 海鼎uuid
		po.setHdUuid(e.getUuid());
		// 海鼎编码
		po.setHdCode(e.getCode());
		// 海鼎状态
		po.setHdState(e.getState());
		// 名称
		po.setName(StringUtils.trim(e.getName()));
		// 类型
		po.setType(tempParamService.findKeyByParamAndValue(TempParamConstant.brandDealerType, e.getBrandDealerType(), e.getUuid()));
		// 理论上properties不能为空，但是海鼎数据存在问题，为了避免错误，做非空判断
		if (null != e.getProperties()) {
			// 注册资金
			po.setCapital(StringUtils.trim(e.getProperties().getRegCapital()));
			// 股东结构
			po.setShareholder(StringUtils.trim(e.getProperties().getShareholder()));
			// 统一社会信用代码
			po.setUscc(StringUtils.trim(e.getProperties().getUscc()));
			// 经营范围
			po.setBusinessScope(StringUtils.trim(e.getProperties().getBusiness_scope()));
			// 天眼查id 不需要
		}
		return po;
	}

	/**
	 * 商户地址
	 *
	 * @param code
	 * @param e
	 * @return
	 */
	private TOLMerchantAddress convert2MerchantAddress(String code, HdMerchant e) {
		TOLMerchantAddress po = merchantAddressService.findOneByCode(code);
		if (null == po) {
			po = new TOLMerchantAddress();
			po.setCode(code);
		}
		if (null != e.getProperties()) {
			// 注册地址
			po.setStreetAddress(e.getProperties().getRegisterAddress());
			// 邮寄地址
			po.setMailingAddress(e.getProperties().getPostAddress());
		}
		return po;
	}

	/**
	 * 商户银行账号
	 *
	 * @param code
	 * @param e
	 * @return
	 */
	private List<TOLMerchantBankAccount> convert2MerchantBankAccount(String code, HdMerchant e) {
		List<HdBank> vos = e.getBanks();
		List<TOLMerchantBankAccount> pos = merchantBankAccountService.findAllByCode(code);
		return mergeAndSetDeleteFlag(pos, vos, (po, vo) -> convert2MerchantBankAccount(code, po, vo), TOLMerchantBankAccount.class);
	}

	private TOLMerchantBankAccount convert2MerchantBankAccount(String code, TOLMerchantBankAccount po, HdBank vo) {
		po.setCode(code);
		// 银行账号
		po.setBankAccount(vo.getAccount());
		// 银行名称
		po.setBankAccountDesc(vo.getName());
		return po;
	}

	@Override
	protected void save(List<SyncMerchant> pos) {
		for (SyncMerchant po : pos) {
			// 商户
			merchantService.save(po.getMerchant());
			// 商户地址
			merchantAddressService.save(po.getMerchantAddress());
			// 商户银行账号
			if (!po.getMerchantBankAccounts().isEmpty()) {
				merchantBankAccountService.saveOrDelete(po.getMerchantBankAccounts());
			}
		}
	}

	@Override
	protected SyncResult<HdMerchant> getResult(HdQueryFilter filter) {
		return hdMerchantClient.query(filter).getBody().toSyncResult();
	}

	@Override
	protected void doAfter(HdQueryFilter filter) {
		filter.addOne();
	}

	@Override
	protected boolean whileCondition(HdQueryFilter filter, SyncResult<HdMerchant> result) {
		return filter.getPage() < result.getPageCount();
	}

}
