package com.sbm.module.onlineleasing.domain.merchant;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MerchantAddress {

	@ApiModelProperty(value = "省份")
	private String province;

	@ApiModelProperty(value = "城市")
	private String city;

	@ApiModelProperty(value = "邮编")
	private String postalCode;

	@ApiModelProperty(value = "注册地址")
	private String streetAddress;

	@ApiModelProperty(value = "邮寄地址")
	private String mailingAddress;

	@ApiModelProperty(value = "传真")
	private String fax;

	public MerchantAddress(String province, String city, String postalCode, String streetAddress, String mailingAddress, String fax) {
		this.province = province;
		this.city = city;
		this.postalCode = postalCode;
		this.streetAddress = streetAddress;
		this.mailingAddress = mailingAddress;
		this.fax = fax;
	}

	public MerchantAddress() {

	}
}
