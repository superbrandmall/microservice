package com.sbm.module.onlineleasing.customer.merchant.biz.impl;

import com.sbm.module.common.biz.impl.CommonServiceImpl;
import com.sbm.module.onlineleasing.base.merchant.biz.ITOLMerchantService;
import com.sbm.module.onlineleasing.base.merchantaddress.biz.ITOLMerchantAddressService;
import com.sbm.module.onlineleasing.base.merchantbankaccount.biz.ITOLMerchantBankAccountService;
import com.sbm.module.onlineleasing.base.merchantbusinesslicense.biz.ITOLMerchantBusinessLicenseService;
import com.sbm.module.onlineleasing.customer.merchant.biz.IMerchantService;
import com.sbm.module.onlineleasing.domain.merchant.Merchant;
import com.sbm.module.onlineleasing.domain.merchant.MerchantAddress;
import com.sbm.module.onlineleasing.domain.merchant.MerchantBankAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	@Override
	public Merchant findOneByCode(String code) {
		return mapOneIfNotNull(merchantService.findOneByCode(code), e -> new Merchant(e.getCode(), e.getName(), e.getType(), e.getCapital(),
				e.getShareholder(), e.getUscc(), e.getAuthState(), e.getBusinessScope(), e.getTianyanchaId(),
				// 营业执照
				mapOneIfNotNull(merchantBusinessLicenseService.findOneByCode(code), s -> s.getBusinessLicense()),
				// 地址
				mapOneIfNotNull(merchantAddressService.findOneByCode(code), s -> new MerchantAddress(s.getProvince(), s.getCity(), s.getPostalCode(), s.getStreetAddress(), s.getMailingAddress(), s.getFax())),
				// 银行账号
				map(merchantBankAccountService.findAllByCode(code), s -> new MerchantBankAccount(s.getBankAccount(), s.getBankAccountDesc()))
		));
	}
}
