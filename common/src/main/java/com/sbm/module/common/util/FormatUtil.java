package com.sbm.module.common.util;

import java.text.MessageFormat;

public class FormatUtil {

	/**
	 * 格式化参数
	 *
	 * @param objArr
	 * @return
	 */
	public static String messageFormat(String message, Object[] objArr) {
		if (null == objArr) {
			return message;
		}
		Object[] temp = new Object[objArr.length];
		for (int i = 0; i < objArr.length; i++) {
			// 因为坑爹的MessageFormat会自动格式化数字，所以转成String不格式化
			if (objArr[i] instanceof Integer || objArr[i] instanceof Long) {
				temp[i] = String.valueOf(objArr[i]);
			} else {
				temp[i] = objArr[i];
			}
		}
		return MessageFormat.format(message, temp);
	}

}
