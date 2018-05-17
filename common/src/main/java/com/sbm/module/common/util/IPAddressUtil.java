package com.sbm.module.common.util;

import lombok.SneakyThrows;

import javax.servlet.http.HttpServletRequest;

public class IPAddressUtil {

	private static final String UNKNOW = "unknown";
	private static final String X_Forwarded_For = "X-Forwarded-For";
	private static final String Proxy_Client_IP = "Proxy-Client-IP";
	private static final String WL_Proxy_Client_IP = "WL-Proxy-Client-IP";
	private static final String HTTP_CLIENT_IP = "HTTP_CLIENT_IP";
	private static final String HTTP_X_FORWARDED_FOR = "HTTP_X_FORWARDED_FOR";
	private static final String COMMA = ",";

	/**
	 * 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址
	 *
	 * @param request
	 * @return
	 */
	@SneakyThrows
	public final static String getIpAddress(HttpServletRequest request) {
		// 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址

		String ip = request.getHeader(X_Forwarded_For);
		if (ip == null || ip.length() == 0 || UNKNOW.equalsIgnoreCase(ip)) {
			if (ip == null || ip.length() == 0 || UNKNOW.equalsIgnoreCase(ip)) {
				ip = request.getHeader(Proxy_Client_IP);
			}
			if (ip == null || ip.length() == 0 || UNKNOW.equalsIgnoreCase(ip)) {
				ip = request.getHeader(WL_Proxy_Client_IP);
			}
			if (ip == null || ip.length() == 0 || UNKNOW.equalsIgnoreCase(ip)) {
				ip = request.getHeader(HTTP_CLIENT_IP);
			}
			if (ip == null || ip.length() == 0 || UNKNOW.equalsIgnoreCase(ip)) {
				ip = request.getHeader(HTTP_X_FORWARDED_FOR);
			}
			if (ip == null || ip.length() == 0 || UNKNOW.equalsIgnoreCase(ip)) {
				ip = request.getRemoteAddr();
			}
		} else if (ip.length() > 15) {
			String[] ips = ip.split(COMMA);
			for (int index = 0; index < ips.length; index++) {
				String strIp = (String) ips[index];
				if (!(UNKNOW.equalsIgnoreCase(strIp))) {
					ip = strIp;
					break;
				}
			}
		}
		return ip;
	}

}
