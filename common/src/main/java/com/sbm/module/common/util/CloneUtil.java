package com.sbm.module.common.util;

import com.alibaba.fastjson.JSON;

public class CloneUtil {

	public static Object jsonClone(Object t, Class clazz) {
		Object obj = JSON.parseObject(JSON.toJSONString(t), clazz);
		return obj;
	}

}
