package com.sbm.module.onlineleasing.file.download.biz;

import com.sbm.module.onlineleasing.base.fileuploaddetail.domain.TOLFileUploadDetail;

import javax.servlet.http.HttpServletResponse;

public interface IDownloadMethodService {

	/**
	 * 下载具体实现
	 *
	 * @param detail
	 * @param response
	 */
	void downloadMethod(TOLFileUploadDetail detail, HttpServletResponse response);

}
