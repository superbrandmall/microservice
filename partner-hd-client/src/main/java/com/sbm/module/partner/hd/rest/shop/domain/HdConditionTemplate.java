package com.sbm.module.partner.hd.rest.shop.domain;

import lombok.Data;

import java.util.List;

@Data
public class HdConditionTemplate {

	private String code;

	private String name;

	private List<HdProjectCondition> conditions;

}
