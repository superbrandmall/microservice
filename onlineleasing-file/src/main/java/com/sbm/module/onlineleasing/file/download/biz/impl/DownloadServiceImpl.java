package com.sbm.module.onlineleasing.file.download.biz.impl;

import com.alibaba.fastjson.JSON;
import com.sbm.module.common.biz.impl.CommonServiceImpl;
import com.sbm.module.common.redis.biz.IRedisService;
import com.sbm.module.common.redis.constant.RedisConstant;
import com.sbm.module.onlineleasing.base.fileuploaddetail.biz.ITOLFileUploadDetailService;
import com.sbm.module.onlineleasing.base.fileuploaddetail.domain.TOLFileUploadDetail;
import com.sbm.module.onlineleasing.file.download.biz.IDownloadMethodService;
import com.sbm.module.onlineleasing.file.download.biz.IDownloadService;
import com.sbm.module.onlineleasing.file.download.domain.Download;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.net.URLConnection;
import java.text.MessageFormat;

@Service
public class DownloadServiceImpl extends CommonServiceImpl implements IDownloadService {

	@Autowired
	private IRedisService redisService;

	@Autowired
	private ITOLFileUploadDetailService fileUploadDetailService;
	@Autowired
	@Qualifier("hdDownloadMethodServiceImpl")
	private IDownloadMethodService downloadMethodService;

	private static final String INLINE = "inline";

	private static final String STEAM = "application/octet-stream";

	private static final String CONTENT_DISPOSITION = "Content-Disposition";

	private static final String CONTENT_DISPOSITION_VALUE = "{0}; filename=\"{1}\"";

	/********************************************************************/
	// 下载预处理
	public Download preDownload(String uri) {
		Download download = new Download();
		TOLFileUploadDetail detail = fileUploadDetailService.findOneByUri(uri);
		// 获取指定文件失败，指定文件不存在
		if (null == detail) {
			throw new RuntimeException();
			// TODO throw new BusinessException(BusinessCode.C1101, new Object[]{download.getDetail().getUri()}, null);
		}
		download.setDetail(detail);
		// 设置key
		setKey(download);
		// 加入缓存
		redisService.set2Redis(download.getKey(), JSON.toJSONString(download.getDetail()));
		return download;
	}

	/**
	 * 设置key
	 *
	 * @param download
	 */
	private void setKey(Download download) {
		download.setKey(download.getDetail().getSuffix() + RedisConstant.UNDER_LINE + getUUID());
	}

	/********************************************************************/
	// 下载文件
	public void getFile(String key, String type, HttpServletResponse response) {
		// 设置下载属性
		Download download = getDownload(key, type);
		// 获取文件详情
		TOLFileUploadDetail detail = getDetail(download);
		// 下载前设置
		beforeDownload(detail, download, response);
		// 下载文件
		downloadMethodService.downloadMethod(detail, response);

	}

	/**
	 * 设置下载属性
	 *
	 * @param key
	 * @param type
	 * @return
	 */
	private Download getDownload(String key, String type) {
		if (StringUtils.isBlank(key)) {
			throw new RuntimeException();
			// TODO throw new BusinessException(BusinessCode.C1103, null);
		}
		Download download = new Download();
		download.setKey(key);
		download.setType(type);
		// 放入本地线程
		// TODO save2RequestBody(download);
		return download;
	}

	/**
	 * 获取文件详情
	 *
	 * @param download
	 * @return
	 */
	private TOLFileUploadDetail getDetail(Download download) {
		String valuer = (String) redisService.get(download.getKey());
		if (StringUtils.isBlank(valuer)) {
			throw new RuntimeException();
			// TODO throw new BusinessException(BusinessCode.C1102, new Object[]{download.getKey()}, null);
		}
		TOLFileUploadDetail detail = JSON.parseObject(valuer, TOLFileUploadDetail.class);
		return detail;
	}

	/**
	 * 下载前设置
	 *
	 * @param detail
	 * @param download
	 * @param response
	 * @return
	 */
	private TOLFileUploadDetail beforeDownload(TOLFileUploadDetail detail, Download download,
											   HttpServletResponse response) {
		String mimeType = URLConnection.guessContentTypeFromName(detail.getOriginalFilename());
		if (mimeType == null) {
			mimeType = STEAM;
		}
		response.setContentType(mimeType);
		if (StringUtils.isBlank(download.getType())) {
			download.setType(INLINE);
		}
		response.setHeader(CONTENT_DISPOSITION,
				MessageFormat.format(CONTENT_DISPOSITION_VALUE, download.getType(), detail.getOriginalFilename()));
		response.setContentLength(detail.getSize().intValue());
		return detail;
	}

}
