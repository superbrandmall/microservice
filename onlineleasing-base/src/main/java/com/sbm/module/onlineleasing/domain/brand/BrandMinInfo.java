package com.sbm.module.onlineleasing.domain.brand;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

@Data
public class BrandMinInfo {

	@ApiModelProperty(value = "品牌编号")
	private String code;

	@ApiModelProperty(value = "品牌名称")
	@NotBlank
	private String name;

	@ApiModelProperty(value = "二级业态")
	private String modality_1;

	@ApiModelProperty(value = "三级业态")
	private String modality_2;

	@ApiModelProperty(value = "四级业态")
	private String modality_3;

	@ApiModelProperty(value = "英文名称")
	private String nameEng;

	@ApiModelProperty(value = "状态")
	private Integer status;

	private String hdState;

	public BrandMinInfo() {
	}

	public BrandMinInfo(String code, String name, String modality_1, String modality_2, String modality_3, String nameEng, Integer status, String hdState) {
		this.code = code;
		this.name = name;
		this.modality_1 = modality_1;
		this.modality_2 = modality_2;
		this.modality_3 = modality_3;
		this.nameEng = nameEng;
		this.status = status;
		this.hdState = hdState;
	}
}
