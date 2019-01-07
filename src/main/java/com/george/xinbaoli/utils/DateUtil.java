package com.george.xinbaoli.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <PRE>
 * Filename:    DateUtil.java
 * Description: 日期格式类
 * </PRE>
 * @author      GONGZHAO
 * @version     1.0
 * <PRE>
 * Create at:   2018年11月8日 下午2:19:56
 * Modification History:
 * Date         Author      Version     Description
 * ------------------------------------------------------------------
 * 2018年11月8日      GONGZHAO     1.0         新建
 * </PRE>
 */
public class DateUtil {

	public static final String DateTime24h = "yyyy-MM-dd HH:mm:ss";
	
	public static final String DateTime12h = "yyyy-MM-dd HH:mm:ss";
	
	public static final String dateFormat = "yyyy-MM-dd";

	public static final String timeFormat = "HH:mm:ss";


	/**
	 * 
	 * @Description: 格式化date to yyyy-MM-dd HH:mm:ss
	 * @author GONGZHAO 
	 * @Created 2018年11月8日下午2:23:29
	 * @since 1.0
	 * @param dataTime
	 * @return
	 */
	public static String dateTimeToString(Date dataTime) {
		DateFormat df = new SimpleDateFormat(DateTime24h);
		return df.format(dataTime);
	}
	
	public static Date stringToDateTime(String dateTime) throws ParseException {
		DateFormat df = new SimpleDateFormat(DateTime24h);
		return df.parse(dateTime);
	}
}
