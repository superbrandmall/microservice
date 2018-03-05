package com.sbm.module.onlineleasing.constant;

import com.sbm.module.common.util.FormatUtil;

public class RedisConstant {

	/**
	 * 楼层信息
	 */
	public static final String FLOOR_INFO = "FLOOR_INFO_{0}_{1}";


	public static String getKey(String key, Object... args) {
		return FormatUtil.messageFormat(key, args);
	}

}
