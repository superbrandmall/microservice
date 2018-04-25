package com.sbm.module.sync.hd.api.brand.biz.impl;

import com.sbm.module.common.biz.impl.SyncServiceImpl;
import com.sbm.module.common.domain.SyncResult;
import com.sbm.module.onlineleasing.base.brand.biz.ITOLBrandService;
import com.sbm.module.onlineleasing.base.brand.constant.BrandConstant;
import com.sbm.module.onlineleasing.base.brand.domain.TOLBrand;
import com.sbm.module.onlineleasing.base.tempparam.biz.ITOLTempParamService;
import com.sbm.module.onlineleasing.base.tempparam.constant.TempParamConstant;
import com.sbm.module.partner.hd.rest.base.domain.HdQueryFilter;
import com.sbm.module.partner.hd.rest.brand.client.IHdBrandClient;
import com.sbm.module.partner.hd.rest.brand.domain.HdBrand;
import com.sbm.module.sync.hd.api.brand.biz.IBrandService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class BrandServiceImpl extends SyncServiceImpl<TOLBrand, HdBrand, HdQueryFilter> implements IBrandService {

	@Autowired
	private IHdBrandClient hdBrandClient;
	@Autowired
	private ITOLBrandService brandService;
	@Autowired
	private ITOLTempParamService tempParamService;

	private static final Integer LENGTH = 8;
	private static final String BIZTYPE_MESSAGE = "biztype is missing, brandUuid: {}";
	private static final String BIZTYPE_LENGTH_MESSAGE = "biztype.length != {}, brandUuid: {}, biztype: {}";

	private static final String ERROR_MESSAGE = "品牌同步异常";

	@Override
	@Scheduled(cron = "${sync.cron.brand}")
	public void refresh() {
		HdQueryFilter filter = new HdQueryFilter();
		try {
			execute(filter, e -> newInstance(e));
		} catch (Exception ex) {
			log.error(ERROR_MESSAGE, ex);
		}
	}

	public TOLBrand newInstance(HdBrand e) {
		TOLBrand po = brandService.findOneByHdUuid(e.getUuid());
		if (null == po) {
			po = brandService.newInstance();
		}
		// 海鼎uuid
		po.setHdUuid(e.getUuid());
		// 海鼎编码
		po.setHdCode(e.getCode());
		// 名称
		po.setName(e.getName());
		// 英文名称
		po.setNameEng(e.getForeignName());
		// 海鼎状态
		po.setHdState(e.getState());
		// 业态3
		if (null != e.getBizType()) {
			if (StringUtils.isNotBlank(e.getBizType().getCode()) && e.getBizType().getCode().length() == LENGTH) {
				po.setModality_1(e.getBizType().getCode().substring(0, 4));
				po.setModality_2(e.getBizType().getCode().substring(0, 6));
				po.setModality_3(e.getBizType().getCode());
			} else {
				log.warn(BIZTYPE_LENGTH_MESSAGE, LENGTH, e.getUuid(), e.getBizType().getCode());
			}
		} else {
			log.warn(BIZTYPE_MESSAGE, e.getUuid());
		}
		// 城市
		po.setCity(e.getLocal());

		if (null != e.getProperties()) {
			// 品牌属性
			po.setAttribute(tempParamService.findKeyByParamAndValue(TempParamConstant.attribute, e.getProperties().getIntroductions(), e.getUuid()));
			// 品牌价位
			po.setBrandClass(tempParamService.findKeyByParamAndValue(TempParamConstant.brandClass, e.getProperties().getBrandGrade(), e.getUuid()));
			// 标准店面积
			po.setStandardArea(tempParamService.findKeyByParamAndValue(TempParamConstant.standardArea, e.getProperties().getAreaLow(), e.getUuid()));
			// 主要客户群
			po.setTarget(tempParamService.findKeyByParamAndValue(TempParamConstant.target, e.getProperties().getTarget(), e.getUuid()));
			// 开店区域
			po.setLocation(tempParamService.findKeyByParamAndValue(TempParamConstant.location, e.getProperties().getLocation(), e.getUuid()));
			// 当前已开店数
			po.setShopAmount(tempParamService.findKeyByParamAndValue(TempParamConstant.shopAmount, e.getProperties().getShopCount(), e.getUuid()));
			// 品牌发展历史
			po.setHistory(tempParamService.findKeyByParamAndValue(TempParamConstant.history, e.getProperties().getHistory(), e.getUuid()));
			// 口碑
			po.setReputation(tempParamService.findKeyByParamAndValue(TempParamConstant.reputation, e.getProperties().getReputation(), e.getUuid()));
			// 是否有旗下品牌已入驻
			po.setJoined(tempParamService.findKeyByParamAndValue(TempParamConstant.joined, e.getProperties().getJoined(), e.getUuid()));
			// 是否有意进驻正大其它门店
			po.setJoinOtherMall(tempParamService.findKeyByParamAndValue(TempParamConstant.joinOtherMall, e.getProperties().getJoin_other_mall(), e.getUuid()));
			// 月均销售额坪效
			po.setCompare(tempParamService.findKeyByParamAndValue(TempParamConstant.compare, e.getProperties().getCompare(), e.getUuid()));
			// 品牌信息来源
			po.setSource(tempParamService.findKeyByParamAndValue(TempParamConstant.source, e.getProperties().getSource(), e.getUuid()));
			// 客单价
			po.setAverageUnitPrice(e.getProperties().getPriceLow());
		}
		// 品牌状态 同步过来的品牌默认已准入
		po.setStatus(BrandConstant.ADMITTANCE);
		return po;
	}

	@Override
	protected void save(List<TOLBrand> pos) {
		brandService.save(pos);
	}

	@Override
	protected SyncResult<HdBrand> getResult(HdQueryFilter filter) {
		return hdBrandClient.query(filter).getBody().toSyncResult();
	}

	@Override
	protected void doAfter(HdQueryFilter filter) {
		filter.addOne();
	}

	@Override
	protected boolean whileCondition(HdQueryFilter filter, SyncResult<HdBrand> result) {
		return filter.getPage() < result.getPageCount();
	}
}
