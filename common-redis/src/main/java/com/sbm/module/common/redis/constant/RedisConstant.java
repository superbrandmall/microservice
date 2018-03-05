package com.sbm.module.common.redis.constant;

public class RedisConstant {

	public static final String UNDER_LINE = "_";

	public static String getKey(Class clazz, Object... args) {
		StringBuffer sb = new StringBuffer(clazz.getName());
		for (int i = 0; i < args.length; i++) {
			sb.append(args[i]);
			if (i < args.length - 1) {
				sb.append(UNDER_LINE);
			}
		}
		return sb.toString();
	}

}
