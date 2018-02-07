package com.sbm.module.partner.hd.rest.base.domain;

import lombok.Data;

import java.util.List;

@Data
public class HdResultBody<T> {

	private Integer pageSize;

	private Integer page;

	private Integer pageCount;

	private Integer recordCount;

	private List<T> records;

}
