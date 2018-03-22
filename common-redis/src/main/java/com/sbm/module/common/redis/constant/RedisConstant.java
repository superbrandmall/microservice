package com.sbm.module.common.redis.constant;

public class RedisConstant {

	public static final String UNDER_LINE = "_";

	public static final String LIST = "LIST";

	public static String getKey(Class clazz, Object... args) {
		StringBuffer sb = new StringBuffer(clazz.getName());
		for (int i = 0; i < args.length; i++) {
			sb.append(UNDER_LINE);
			sb.append(args[i]);
		}
		return sb.toString();
	}

}
