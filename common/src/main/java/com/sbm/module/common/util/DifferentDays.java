package com.sbm.module.common.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DifferentDays {

	/**
	 * date2比date1多的天数
	 *
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int differentDays(Date date1, Date date2) {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);

		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		int day1 = cal1.get(Calendar.DAY_OF_YEAR);
		int day2 = cal2.get(Calendar.DAY_OF_YEAR);

		int year1 = cal1.get(Calendar.YEAR);
		int year2 = cal2.get(Calendar.YEAR);
		// 不同年
		if (year1 != year2) {
			int timeDistance = 0;
			for (int i = year1; i < year2; i++) {
				// 闰年
				if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0) {
					timeDistance += 366;
				}
				// 不是闰年
				else {
					timeDistance += 365;
				}
			}
			return timeDistance + (day2 - day1);
		}
		// 同一年
		else {
			// System.out.println("判断day2 - day1 : " + (day2 - day1));
			return day2 - day1;
		}
	}

	/**
	 * addNdays:加N天
	 *
	 * @param beginDate
	 * @param days
	 * @return
	 * @author junkai.zhang
	 */
	public static Date addNdays(Date beginDate, Integer days) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(beginDate);
		calendar.add(Calendar.DATE, days);
		return calendar.getTime();
	}

}
