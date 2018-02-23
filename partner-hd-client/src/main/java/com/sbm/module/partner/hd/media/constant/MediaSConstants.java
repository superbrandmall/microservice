package com.sbm.module.partner.hd.media.constant;

import com.sbm.module.partner.hd.media.caller.BurlapServiceCaller;
import com.sbm.module.partner.hd.media.service.IFileProcessService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MediaSConstants {

	public static String URL;

	@Value("${hd.media.url}")
	public void setURL(String url) {
		URL = url;
	}

	public static String CALLER_CLASS = "default";

	private static IFileProcessService caller = null;

	public static IFileProcessService getCaller() throws Exception {
		if ((URL == null) || ("".equals(URL)))
			throw new Exception("未设置参数URL.");
		if ((CALLER_CLASS == null) || ("".equals(CALLER_CLASS))) {
			throw new Exception("请配置参数CALLER_CLASS，值为default或实类名.");
		}
		if ("default".equalsIgnoreCase(CALLER_CLASS)) {
			caller = BurlapServiceCaller.getFileProcessService();
		}
		if (caller == null) {
			try {
				caller = (IFileProcessService) Class.forName(CALLER_CLASS).newInstance();
			} catch (Exception e) {
				throw new Exception("无法实例化" + CALLER_CLASS + ".");
			}
		}
		return caller;
	}

}
