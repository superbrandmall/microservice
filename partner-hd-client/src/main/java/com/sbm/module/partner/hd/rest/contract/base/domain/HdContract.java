package com.sbm.module.partner.hd.rest.contract.base.domain;

import com.sbm.module.partner.hd.rest.base.domain.HdBizType;
import com.sbm.module.partner.hd.rest.base.domain.HdUCN;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
public class HdContract {

	@ApiModelProperty(value = "模板名称")
	private String template;

	@ApiModelProperty(value = "OL合同id")
	private String olContractId;

	@ApiModelProperty(value = "合同类型")
	private String contractCategory;

	@ApiModelProperty(value = "合作方式")
	private String coopMode;

	@ApiModelProperty(value = "结算方式")
	private String accType;

	@ApiModelProperty(value = "法务异议")
	private String legal_objection;

	@ApiModelProperty(value = "有效期天数")
	private Integer effective_Days;

	@ApiModelProperty(value = "装修免租期")
	private Integer free_days;

	@ApiModelProperty(value = "商户")
	private HdUCN tenant;

	@ApiModelProperty(value = "品牌")
	private HdUCN brand;

	@ApiModelProperty(value = "核算楼层 为空则默认取铺位的楼层")
	private HdUCN floor;

	@ApiModelProperty(value = "业态")
	private HdBizType bizType = new HdBizType();

	@ApiModelProperty(value = "计租面积 为空则默认取铺位的计租面积")
	private BigDecimal rentArea;

	@ApiModelProperty(value = "店招 为空则默认取品牌名称")
	private String signboard;

	@ApiModelProperty(value = "合同开始时间")
	private Date beginDate;

	@ApiModelProperty(value = "合同结束时间")
	private Date endDate;

	@ApiModelProperty(value = "招商人员")
	private HdUCN employee;

	@ApiModelProperty(value = "收银方式 unuse(\"不用POS自收银\"), useRent(\"租用POS自收银\"), usePublic(\"公用POS代收银\");")
	private String posMode;

	@ApiModelProperty(value = "铺位")
	private HdUCN position;

	@ApiModelProperty(value = "月固定条款列表")
	private List<HdMonthFixedTerm> monthFixeds = new ArrayList<>();

	@ApiModelProperty(value = "账款按科目取最大值条款列表")
	private List<HdMaxSubjectTerm> maxSubjects = new ArrayList<>();

	@ApiModelProperty(value = "商品类别销售固定比例提成条款列表")
	private List<HdNormalCategorySaleRateTerm> categorySaleRates = new ArrayList<>();

	@ApiModelProperty(value = "销售固定比例提成条款列表")
	private List<HdNormalSaleRateTerm> normalSaleRates = new ArrayList<>();

	@ApiModelProperty(value = "免租条款列表")
	private List<HdFreeAccountTerm> freeAccountTerms = new ArrayList<>();

	@ApiModelProperty(value = "预存款条款列表")
	private List<HdDepositTerm> depositTerms = new ArrayList<>();

	@ApiModelProperty(value = "进场条款")
	private HdEnteryTerm enteryTerm = new HdEnteryTerm();

	@ApiModelProperty(value = "自定义属性 供自定义字段使用，例如： Properties：｛ “olContractId”:”000sdf05848sdg48484” ｝")
	private Map<String, String> properties;

}
