package com.sbm.module.onlineleasing.file.upload.biz;

import com.sbm.module.onlineleasing.base.fileuploaddetail.domain.TOLFileUploadDetail;
import org.springframework.web.multipart.MultipartFile;

public interface IUploadMethodService {

	/**
	 * 上传数据具体实现方法
	 *
	 * @param detail
	 * @param file
	 */
	void uploadMethod(TOLFileUploadDetail detail, MultipartFile file);


	void setFileInfo(TOLFileUploadDetail detail);

}
