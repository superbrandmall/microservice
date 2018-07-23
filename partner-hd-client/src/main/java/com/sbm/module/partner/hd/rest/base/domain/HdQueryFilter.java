package com.sbm.module.partner.hd.rest.base.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class HdQueryFilter {

	@ApiModelProperty(value = "每页最大显示记录数，0表示包含全部结果集")
	private Integer pageSize = 50;

	@ApiModelProperty(value = "当前页号，从0计数")
	private Integer page = 0;

	@ApiModelProperty(value = "过滤条件")
	private Map<String, Object> filter = new HashMap<String, Object>();

	public void addOne(){
		page = page + 1;
	}

	public void reset(){
		page = 0;
	}
}
