package com.sbm.module.onlineleasing.domain.merchant;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class Merchant {

	@ApiModelProperty(value = "编号")
	private String code;

	@ApiModelProperty(value = "名称")
	private String name;

	@ApiModelProperty(value = "类型")
	private Integer type;

	@ApiModelProperty(value = "注册资金")
	private String capital;

	@ApiModelProperty(value = "股东结构")
	private String shareholder;

	@ApiModelProperty(value = "社会统一信用代码")
	private String uscc;

	@ApiModelProperty(value = "商户状态（已认证，未认证等）")
	private Integer authState;

	@ApiModelProperty(value = "经营范围")
	private String businessScope;

	@ApiModelProperty(value = "天眼查ID")
	private Long tianyanchaId;

	@ApiModelProperty(value = "营业执照")
	private String businessLicense;

	@ApiModelProperty(value = "地址")
	private MerchantAddress address = new MerchantAddress();

	@ApiModelProperty(value = "银行账号")
	private List<MerchantBankAccount> bankAccounts;

	private String hdUuid;

	private String hdCode;

	private String hdState;

	public Merchant(String code, String name, Integer type, String capital, String shareholder, String uscc, Integer authState, String businessScope, Long tianyanchaId, String businessLicense, MerchantAddress address, List<MerchantBankAccount> bankAccounts, String hdUuid, String hdCode, String hdState) {
		this.code = code;
		this.name = name;
		this.type = type;
		this.capital = capital;
		this.shareholder = shareholder;
		this.uscc = uscc;
		this.authState = authState;
		this.businessScope = businessScope;
		this.tianyanchaId = tianyanchaId;
		this.businessLicense = businessLicense;
		if (null != address) this.address = address;
		this.bankAccounts = bankAccounts;
		this.hdUuid = hdUuid;
		this.hdCode = hdCode;
		this.hdState = hdState;
	}

	public Merchant() {

	}
}
