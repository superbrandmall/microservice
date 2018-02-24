package com.sbm.module.onlineleasing.base.fileuploaddetail.domain;

import com.sbm.module.common.data.domain.DataEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_ol_file_upload_detail")
@Data
public class TOLFileUploadDetail extends DataEntity {

	@ApiModelProperty(value = "用户编码")
	private String userCode;

	@ApiModelProperty(value = "uri")
	@Column(columnDefinition = "text")
	private String uri;

	@ApiModelProperty(value = "容器名称")
	private String containerName;

	@ApiModelProperty(value = "前缀")
	private String prefix;

	@ApiModelProperty(value = "文件id")
	private String fileId;

	@ApiModelProperty(value = "大小")
	private Long size;

	@ApiModelProperty(value = "原始名称")
	private String originalFilename;

	@ApiModelProperty(value = "后缀")
	private String suffix;

}
