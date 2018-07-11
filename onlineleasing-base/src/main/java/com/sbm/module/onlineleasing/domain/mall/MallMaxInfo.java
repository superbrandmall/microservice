package com.sbm.module.onlineleasing.domain.mall;

import com.sbm.module.onlineleasing.base.mall.domain.TOLMall;
import com.sbm.module.onlineleasing.domain.base.info.mall.MallTraffic;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class MallMaxInfo extends Mall {

	@ApiModelProperty(value = "描述")
	private String description;

	@ApiModelProperty(value = "描述（英文）")
	private String descriptionEng;

	@ApiModelProperty(value = "建筑面积")
	private BigDecimal grossFloorArea;

	@ApiModelProperty(value = "租赁面积")
	private BigDecimal leasingArea;

	@ApiModelProperty(value = "视频")
	private String video;

	@ApiModelProperty(value = "交通")
	List<MallTraffic> traffic;

	public MallMaxInfo() {
	}

	public MallMaxInfo(String mallCode, String mallName, String mallNameEng, String location, String locationEng, Integer position, String img, String phone, String hdState, String description, String descriptionEng, BigDecimal grossFloorArea, BigDecimal leasingArea, String video) {
		super(mallCode, mallName, mallNameEng, location, locationEng, position, img, phone, hdState);
		this.description = description;
		this.descriptionEng = descriptionEng;
		this.grossFloorArea = grossFloorArea;
		this.leasingArea = leasingArea;
		this.video = video;
	}

	public static MallMaxInfo convert(TOLMall e) {
		return new MallMaxInfo(e.getCode(), e.getMallName(), e.getMallNameEng(),
				e.getLocation(), e.getLocationEng(), e.getPosition(), e.getImg(), e.getPhone(),
				e.getHdState(),
				e.getDescription(), e.getDescriptionEng(), e.getGrossFloorArea(), e.getLeasingArea(), e.getVideo());
	}

}
