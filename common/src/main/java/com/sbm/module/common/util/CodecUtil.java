package com.sbm.module.common.util;

import org.apache.commons.codec.digest.DigestUtils;

public class CodecUtil {

	/**
	 * 生成MD5
	 *
	 * @param str
	 * @return
	 */
	public static String md5Hex(String str) {
		return DigestUtils.md5Hex(str);
	}

	/**
	 * 生成SHA1
	 *
	 * @param str
	 * @return
	 */
	public static String sha1Hex(String str) {
		return DigestUtils.sha1Hex(str);
	}


}
