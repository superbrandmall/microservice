package com.sbm.module.common.domain;

import lombok.Data;

import java.util.List;

@Data
public class SyncResult<K> {

	private Integer page;

	private Integer pageSize;

	private Integer pageCount;

	private Integer recordCount;

	private List<K> records;

	public SyncResult(Integer page, Integer pageSize, Integer pageCount, Integer recordCount, List<K> records) {
		this.page = page;
		this.pageSize = pageSize;
		this.pageCount = pageCount;
		this.recordCount = recordCount;
		this.records = records;
	}

	public SyncResult() {

	}
}
