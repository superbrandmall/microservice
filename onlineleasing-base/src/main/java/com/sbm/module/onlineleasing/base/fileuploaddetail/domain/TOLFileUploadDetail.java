package com.sbm.module.onlineleasing.base.fileuploaddetail.domain;

import com.sbm.module.common.data.domain.DataEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_ol_file_upload_detail")
@Data
public class TOLFileUploadDetail extends DataEntity {

	private String userCode;

	@Column(columnDefinition = "text")
	private String uri;

	private String containerName;

	private String prefix;

	private String fileId;

	private Long size;

	private String originalFilename;

	private String suffix;

	public TOLFileUploadDetail() {
	}

	public TOLFileUploadDetail(String userCode, String containerName, String prefix) {
		this.userCode = userCode;
		this.containerName = containerName;
		this.prefix = prefix;
	}
}
