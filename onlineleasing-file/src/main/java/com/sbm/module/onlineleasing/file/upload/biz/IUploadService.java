package com.sbm.module.onlineleasing.file.upload.biz;

import com.sbm.module.onlineleasing.base.fileuploaddetail.domain.TOLFileUploadDetail;
import com.sbm.module.onlineleasing.file.upload.domain.Upload;

public interface IUploadService {

	/**
	 * 上传数据
	 *
	 * @param vo
	 */
	void upload(Upload vo);

	/**
	 * 保存上传信息
	 *
	 * @param fileId
	 * @param containerName
	 * @param prefix
	 * @return
	 */
	String saveFileUploadDetail(String fileId, String containerName, String prefix);

	/**
	 * 保存上传信息
	 *
	 * @param detail
	 */
	void saveFileUploadDetail(TOLFileUploadDetail detail);

	/**
	 * 获取前缀
	 *
	 * @param userCode
	 * @param type
	 * @param use
	 * @return
	 */
	String getPrefix(String userCode, String type, String use);

}
