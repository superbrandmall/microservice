package com.sbm.module.onlineleasing.file.upload.biz;

import com.sbm.module.onlineleasing.base.fileuploaddetail.domain.TOLFileUploadDetail;
import com.sbm.module.onlineleasing.file.upload.domain.Upload;
import com.sbm.module.onlineleasing.file.upload.domain.UploadDetail;

import java.util.List;

public interface IUploadService {

	/**
	 * 上传数据
	 *
	 * @param vo
	 * @return
	 */
	List<TOLFileUploadDetail> upload(Upload vo);

	/**
	 * 保存上传信息
	 *
	 * @param vo
	 * @return
	 */
	String saveUploadDetail(UploadDetail vo);

	/**
	 * 保存上传信息
	 *
	 * @param detail
	 */
	void saveUploadDetail(TOLFileUploadDetail detail);

}
