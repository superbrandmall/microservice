package com.sbm.module.onlineleasing.domain.user;

import com.sbm.module.common.authorization.api.user.domain.User;
import com.sbm.module.common.authorization.api.user.domain.UserSettings;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class UserSimple extends User {

	@ApiModelProperty(value = "公司名称")
	private String merchantName;

	@ApiModelProperty(value = "品牌名称")
	private String brandName;

	@ApiModelProperty(value = "业态")
	private String modality;

	@ApiModelProperty(value = "官网")
	private String website;

	@ApiModelProperty(value = "文件")
	private String file;

	public UserSimple() {
	}

	public UserSimple(String code, String email, String mobile, String password, Date lastLogin, Integer emailVerified, Integer mobileVerified, UserSettings settings, String merchantName, String brandName, String modality, String website, String file) {
		super(code, email, mobile, password, lastLogin, emailVerified, mobileVerified, settings);
		this.merchantName = merchantName;
		this.brandName = brandName;
		this.modality = modality;
		this.website = website;
		this.file = file;
	}
}
