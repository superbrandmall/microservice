package com.sbm.module.partner.hd.rest.shop.domain;

import com.sbm.module.partner.hd.rest.base.domain.HdBizType;
import com.sbm.module.partner.hd.rest.base.domain.HdMediaFile;
import com.sbm.module.partner.hd.rest.base.domain.HdUCN;
import com.sbm.module.partner.hd.rest.brand.domain.HdBrandProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class HdShop {

	private String uuid;

	private String code;

	private String name;

	private String state;

	private HdUCN floor;

	private HdUCN building;

	private HdUCN store;

	private BigDecimal rentArea;

	private HdBizType modality;

	private Date contract_expire_date;

	private List<HdUCN> currentBrand;

	private BigDecimal dead_rent;

	private BigDecimal floating_rental_rate;

	private List<HdMediaFile> mediaFiles;

	private List<HdConditionTemplate> templates;

	private String subType;

}
