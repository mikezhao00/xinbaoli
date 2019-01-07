package com.george.xinbaoli.utils;

import java.util.UUID;

/**
 * 
 * <PRE>
 * Filename:    IdGenerateUtil.java
 * Description: 主键生成工具类
 * </PRE>
 * @author      GONGZHAO
 * @version     1.0
 * <PRE>
 * Create at:   2018年12月11日 下午5:18:58
 * Modification History:
 * Date         Author      Version     Description
 * ------------------------------------------------------------------
 * 2018年12月11日      GONGZHAO     1.0         新建
 * </PRE>
 */
public class IdGenerateUtil {

	/**
	 * 
	 * @Description: 封装java.util.UUID生成策略
	 * @author GONGZHAO 
	 * @Created 2018年12月11日下午5:20:35
	 * @since 1.0
	 * @return
	 */
	public static String uuidGenerate() {
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		return uuid;
	}
}
