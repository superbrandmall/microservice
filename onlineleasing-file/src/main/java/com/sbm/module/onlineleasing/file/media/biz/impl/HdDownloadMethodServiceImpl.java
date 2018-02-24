package com.sbm.module.onlineleasing.file.media.biz.impl;


import com.sbm.module.onlineleasing.base.fileuploaddetail.domain.TOLFileUploadDetail;
import com.sbm.module.onlineleasing.file.download.biz.IDownloadMethodService;
import com.sbm.module.onlineleasing.file.media.caller.BurlapServiceCaller;
import com.sbm.module.onlineleasing.file.media.service.IFileProcessService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

@Service("hdDownloadMethodServiceImpl")
public class HdDownloadMethodServiceImpl implements IDownloadMethodService {

	public void downloadMethod(TOLFileUploadDetail detail, HttpServletResponse response) {
		try {
			IFileProcessService service = BurlapServiceCaller.getFileProcessService();
			byte[] bytes = service.getFile(detail.getFileId());
			response.getOutputStream().write(bytes);
		} catch (Exception e) {
			throw new RuntimeException();
			// TODO throw new BusinessException(BusinessCode.C2100, e);
		}
	}
}

