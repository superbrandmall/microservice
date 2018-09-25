package com.sbm.module.onlineleasing.customer.merchant.biz.impl;

import com.sbm.module.common.biz.impl.CommonServiceImpl;
import com.sbm.module.common.exception.BusinessException;
import com.sbm.module.onlineleasing.base.merchant.biz.ITOLMerchantService;
import com.sbm.module.onlineleasing.base.merchant.domain.TOLMerchant;
import com.sbm.module.onlineleasing.base.merchantaddress.biz.ITOLMerchantAddressService;
import com.sbm.module.onlineleasing.base.merchantbankaccount.biz.ITOLMerchantBankAccountService;
import com.sbm.module.onlineleasing.base.merchantbrand.biz.ITOLMerchantBrandService;
import com.sbm.module.onlineleasing.base.merchantbusinesslicense.biz.ITOLMerchantBusinessLicenseService;
import com.sbm.module.onlineleasing.base.merchantbusinesslicense.domain.TOLMerchantBusinessLicense;
import com.sbm.module.onlineleasing.customer.merchant.biz.IMerchantService;
import com.sbm.module.onlineleasing.domain.merchant.Merchant;
import com.sbm.module.onlineleasing.domain.merchant.MerchantAddress;
import com.sbm.module.onlineleasing.domain.merchant.MerchantBankAccount;
import com.sbm.module.onlineleasing.exception.OnlineleasingCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MerchantServiceImpl extends CommonServiceImpl implements IMerchantService {

	@Autowired
	private ITOLMerchantService merchantService;
	@Autowired
	private ITOLMerchantAddressService merchantAddressService;
	@Autowired
	private ITOLMerchantBankAccountService merchantBankAccountService;
	@Autowired
	private ITOLMerchantBusinessLicenseService merchantBusinessLicenseService;
	@Autowired
	private ITOLMerchantBrandService merchantBrandService;

	@Override
	public Merchant findOneByCode(String code) {
		return mapOneIfNotNull(merchantService.findOneByCode(code), e -> convert(e));
	}

	@Override
	public Merchant findOneByUscc(String uscc) {
		return mapOneIfNotNull(merchantService.findOneByUscc(uscc), e -> convert(e));
	}

	private Merchant convert(TOLMerchant e) {
		return new Merchant(e.getCode(), e.getName(), e.getType(), e.getCapital(),
				e.getShareholder(), e.getUscc(), e.getAuthState(), e.getBusinessScope(), e.getTianyanchaId(),
				// 营业执照
				mapOneIfNotNull(merchantBusinessLicenseService.findOneByCode(e.getCode()), s -> s.getBusinessLicense()),
				// 地址
				mapOneIfNotNull(merchantAddressService.findOneByCode(e.getCode()), s -> new MerchantAddress(s.getProvince(), s.getCity(), s.getPostalCode(), s.getStreetAddress(), s.getMailingAddress(), s.getFax())),
				// 银行账号
				map(merchantBankAccountService.findAllByCode(e.getCode()), s -> new MerchantBankAccount(s.getBankAccount(), s.getBankAccountDesc())),
				e.getHdUuid(), e.getHdCode(), e.getHdState()
		);
	}

	@Override
	public Integer findBrandCountByMerchantCode(String code) {
		return merchantBrandService.findAllByMerchantCode(code).size();
	}

	@Override
	@Transactional
	public void updateType(String code, Integer type) {
		TOLMerchant po = checkIfNullThrowException(merchantService.findOneByCode(code),
				new BusinessException(OnlineleasingCode.M0002, new Object[]{code}));
		po.setType(type);
		merchantService.save(po);
	}

	@Override
	@Transactional
	public void updateBusinessLicense(String code, String businessLicense) {
		TOLMerchantBusinessLicense po = checkIfNullNewInstance(merchantBusinessLicenseService.findOneByCode(code),
				e -> new TOLMerchantBusinessLicense(code));
		po.setBusinessLicense(businessLicense);
		merchantBusinessLicenseService.save(po);
	}

	@Override
	@Transactional
	public void save(Merchant merchant) {
		TOLMerchant po = merchantService.newInstance();
		po.setName(merchant.getName());
		po.setTianyanchaId(merchant.getTianyanchaId());
		po.setUscc(merchant.getUscc());
		po.setHdUuid(merchant.getHdUuid());
		po.setHdCode(merchant.getHdCode());
		po.setHdState(merchant.getHdState());
		merchantService.save(po);

		merchant.setCode(po.getCode());
	}
}
