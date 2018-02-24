package com.sbm.module.onlineleasing.file.upload.domain;

import com.sbm.module.onlineleasing.base.fileuploaddetail.domain.TOLFileUploadDetail;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Data
public class Upload {

	@ApiModelProperty(value="模板名称")
	private TOLFileUploadDetail vo;

	@ApiModelProperty(value="模板名称")
	private MultipartFile[] files;

	@ApiModelProperty(value="模板名称")
	private List<TOLFileUploadDetail> details = new ArrayList<>();

}
