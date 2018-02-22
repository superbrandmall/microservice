package com.sbm.module.partner.hd.rest.base.domain;

import com.sbm.module.common.domain.SyncResult;
import lombok.Data;

import java.util.List;

@Data
public class HdResultBody<T> {

	private Integer pageSize;

	private Integer page;

	private Integer pageCount;

	private Integer recordCount;

	private List<T> records;

	public SyncResult<T> toSyncResult() {
		return new SyncResult<>(page, pageSize, pageCount, recordCount, records);
	}

}
