package com.george.xinbaoli.utils;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * <PRE>
 * Filename:    ParamUtil.java
 * Description: 参数转换
 * </PRE>
 * @author      GONGZHAO
 * @version     1.0
 * <PRE>
 * Create at:   2018年11月8日 下午2:30:24
 * Modification History:
 * Date         Author      Version     Description
 * ------------------------------------------------------------------
 * 2018年11月8日      GONGZHAO     1.0         新建
 * </PRE>
 */
public class ParamUtil {

	public static Map<String, Object> handleServiceParameter(HttpServletRequest request){
		
		Map<String, String[]> parameter = request.getParameterMap();
		Map<String, Object> rParams  = new HashMap<>();
		for (Map.Entry<String, String[]> m : parameter.entrySet()) {
			String key = m.getKey();
			String[] obj = parameter.get(key);
			rParams.put(key, (obj.length>1 )? obj : obj[0]);
		}
		return rParams;
	}
	
}
