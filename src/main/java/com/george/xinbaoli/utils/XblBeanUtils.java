package com.george.xinbaoli.utils;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;


/**
 * 
 * <PRE>
 * Filename:    XblBeanUtils.java
 * Description: 对象转换工具类
 * </PRE>
 * @author      GONGZHAO
 * @version     1.0
 * <PRE>
 * Create at:   2018年12月18日 下午2:11:23
 * Modification History:
 * Date         Author      Version     Description
 * ------------------------------------------------------------------
 * 2018年12月18日      GONGZHAO     1.0         新建
 * </PRE>
 */
public class XblBeanUtils {

	/**
	 * 
	 * @Description: Map对象转换为JavaBean对象,通过org.apache.commons.beanutils进行转换
	 * @author GONGZHAO 
	 * @Created 2018年12月18日下午2:18:48
	 * @since 1.0
	 * @param map
	 * @param beanClass
	 * @return
	 */
	public static Object mapToObjectByApacheUtils(Map<String,Object> map, Class<?> beanClass) {
		if(map == null)
			return null;
		Object	obj = null; 
		try {
			obj = beanClass.newInstance();
			org.apache.commons.beanutils.BeanUtils.populate(obj, map);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return obj;
	}
	/**
	 * 
	 * @Description: JavaBean对象转换为Map对象,通过org.apache.commons.beanutils进行转换
	 * @author GONGZHAO 
	 * @Created 2018年12月18日下午2:23:03
	 * @since 1.0
	 * @param obj
	 * @return
	 */
	public static Map<?,?> objToMapByApacheUtils(Object obj){
		if(obj == null)
			return null;
		return new org.apache.commons.beanutils.BeanMap(obj);
	}
	
	/**
	 * 
	 * @Description: Map对象转换为JavaBean对象,通过Introspector进行转换
	 * @author GONGZHAO 
	 * @Created 2018年12月18日下午2:49:36
	 * @since 1.0
	 * @param map
	 * @param beanClass
	 * @return
	 */
	public static Object mapToObjectByIntrospector(Map<String,Object> map ,Class<?> beanClass) {
		if(map == null)
			return null;
		Object obj = null;
		try {
			obj = beanClass.newInstance();
			java.beans.BeanInfo beaninfo = Introspector.getBeanInfo(obj.getClass());
			PropertyDescriptor[] propertyDescriptors = beaninfo.getPropertyDescriptors();
			for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
				Method setter = propertyDescriptor.getWriteMethod();
				if(setter != null)
					setter.invoke(obj, map.get(propertyDescriptor.getName()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 

		return obj;
	}
	
	/**
	 * 
	 * @Description: JavaBean对象转换为Map对象,通过Introspector进行转换
	 * @author GONGZHAO 
	 * @Created 2018年12月18日下午2:50:13
	 * @since 1.0
	 * @param obj
	 * @return
	 */
	public static Map<?,?> objToMapByIntrospector(Object obj){
		if(obj == null)
			return null;
		Map<String,Object> map = new HashMap<>();
		try {
			java.beans.BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
				String key = propertyDescriptor.getName();
				if(key.compareToIgnoreCase("class") == 0)
					continue;
				Method getter = propertyDescriptor.getReadMethod();
				Object  value = getter == null ? null : getter.invoke(obj);
				map.put(key, value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 
	 * @Description: Map对象转换为JavaBean对象,通过Reflect进行转换
	 * @author GONGZHAO 
	 * @Created 2018年12月18日下午3:15:12
	 * @since 1.0
	 * @param map
	 * @param beanClass
	 * @return
	 */
	public static Object mapToObjectByReflect(Map<String,Object> map,Class<?> beanClass) {
		if(map == null)
			return null;
		Object obj = null;
		try {
			obj = beanClass.newInstance();
			Field[] fields = beanClass.getDeclaredFields();
			for (Field field : fields) {
				int mod = field.getModifiers();
				if(Modifier.isStatic(mod) || Modifier.isFinal(mod))
					continue;
				field.setAccessible(true);
				field.set(obj, map.get(field.getName()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 	
		return obj;
	}
	
	/**
	 * 
	 * @Description: JavaBean对象转换为Map对象,通过Rflect进行转换
	 * @author GONGZHAO 
	 * @Created 2018年12月18日下午3:15:37
	 * @since 1.0
	 * @param obj
	 * @return
	 */
	public static Map<?,?> objToMapByReflect(Object obj){
		if(obj == null)
			return null;
		Map<String,Object> map = new HashMap<>();
		Field[] fields = obj.getClass().getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			try {
				map.put(field.getName(), field.get(obj));
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return map;
	}
}