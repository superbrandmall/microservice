package com.sbm.module.onlineleasing.file.media.caller;

import com.caucho.burlap.client.BurlapProxyFactory;
import com.sbm.module.onlineleasing.file.media.constant.MediaSConstants;
import com.sbm.module.onlineleasing.file.media.service.IFileProcessService;

public class BurlapServiceCaller {

	public static IFileProcessService getFileProcessService() throws Exception {
		try {
			BurlapProxyFactory factory = new BurlapProxyFactory();
			String url = getUrl("FileProcessService");
			IFileProcessService service = (IFileProcessService) factory.create(IFileProcessService.class, url);
			return service;
		} catch (Exception e) {
			throw e;
		}
	}

	public static String getUrl(String urlPattern) {
		String url = MediaSConstants.URL;
		if (!url.endsWith("/"))
			url = url + "/";
		url = url + urlPattern;
		return url;
	}


}
