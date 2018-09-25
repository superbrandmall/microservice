package com.sbm.module.onlineleasing.domain.base.modality;

import lombok.Data;

@Data
public class ModalityMaxInfo extends Modality {

	private String hdUuid;

	private String hdLevelid;

	public ModalityMaxInfo(String code, String name, String lv, String remark, String hdUuid, String hdLevelid) {
		super(code, name, lv, remark);
		this.hdUuid = hdUuid;
		this.hdLevelid = hdLevelid;
	}

	public ModalityMaxInfo() {

	}
}
