package com.sbm.module.onlineleasing.file.download.biz;

import com.sbm.module.onlineleasing.file.download.domain.Download;

import javax.servlet.http.HttpServletResponse;

public interface IDownloadService {

	/**
	 * 下载预处理
	 *
	 * @param uri
	 * @return
	 */
	Download preDownload(String uri);

	/**
	 * 下载文件
	 *
	 * @param key
	 * @param type
	 * @param response
	 */
	void getFile(String key, String type, HttpServletResponse response);

}
