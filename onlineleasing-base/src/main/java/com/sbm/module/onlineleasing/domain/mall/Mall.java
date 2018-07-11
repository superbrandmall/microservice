package com.sbm.module.onlineleasing.domain.mall;

import com.sbm.module.onlineleasing.domain.base.info.mall.MallMinInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Mall extends MallMinInfo {

	@ApiModelProperty(value = "海鼎状态")
	private String hdState;

	public Mall(String mallCode, String mallName, String mallNameEng, String location, String locationEng, Integer position, String img, String phone, String hdState) {
		super(mallCode, mallName, mallNameEng, location, locationEng, position, img, phone);
		this.hdState = hdState;
	}

	public Mall() {

	}
}
