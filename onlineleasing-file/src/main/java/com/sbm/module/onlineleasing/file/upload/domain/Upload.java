package com.sbm.module.onlineleasing.file.upload.domain;

import com.sbm.module.onlineleasing.base.fileuploaddetail.domain.TOLFileUploadDetail;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Data
public class Upload {

	private TOLFileUploadDetail vo;

	private MultipartFile[] files;

	private List<TOLFileUploadDetail> details = new ArrayList<>();

}
