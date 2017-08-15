package com.pm.order.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class PoiExcelBaseBean {
/**
 * 按字段取
 * @title get
 * @description TODO
 * @author ccc-cbf02
 * @create_date 2014-12-24
 * @param fieldName
 * @param bean
 * @return
 * @throws Exception
 */
public String get(String fieldName,PoiExcelBaseBean bean) throws Exception{
	String o=null;
	Class<?> clazz=getClazz();
	o=getFieldValue(bean, clazz, fieldName);
	return o;
}

	/**
	 * 获取类对象
	 * 
	 * @title getClazz
	 * @description TODO
	 * @author ccc-cbf02
	 * @create_date 2014-12-16
	 * @return
	 */
	private Class<?> getClazz() {
		Class<?> clazz = null;
		try {
			clazz = this.getClass();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return clazz;
	}

	/**
	 * 获取字段值
	 * 
	 * @title getFieldValue
	 * @description TODO
	 * @author ccc-cbf02
	 * @create_date 2014-12-16
	 * @param bean
	 * @param clazz
	 * @param fieldName
	 * @return
	 * @throws NoSuchMethodException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetExceptio
	 */
	private String getFieldValue(PoiExcelBaseBean bean, Class<?> clazz,
			String fieldName) throws NoSuchMethodException,
			IllegalArgumentException, IllegalAccessException,
			InvocationTargetException {
		String o;
		Method method = clazz.getMethod("get"
				+ fieldName.substring(0, 1).toUpperCase()
				+ (fieldName.length() > 1 ? fieldName.substring(1) : ""));
		o = (String) method.invoke(bean);
		return o;
	}

	/**
	 * 根据索引获取，从1开始，按定义字段的排列顺序序列
	 * 
	 * @title get
	 * @description TODO
	 * @author ccc-cbf02
	 * @create_date 2014-12-18
	 * @param index
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	public String get(int index, PoiExcelBaseBean bean) throws Exception {
		if (index <= 0||index > size()) {
			throw new ArrayIndexOutOfBoundsException();
		}
		String sValue = null;
		// 获取类对象
		Class<?> clazz = getClazz();
		// 获取公共的字段
		Field field = clazz.getDeclaredFields()[index-1];
		// 获取字段名称
		String fieldName = field.getName();
		// 获取文件字段
		sValue = getFieldValue(bean, clazz, fieldName);
		return sValue;
	}

	/**
	 * 用反射获取字段
	 * @title size
	 * @description TODO
	 * @author ccc-cbf02
	 * @create_date 2014-12-18
	 * @return
	 */
	public int size() {
		// 获取某个类
		Class<?> clazz = getClazz();
		// 获取某个公共类的字段的长度
		return clazz.getDeclaredFields().length;
	}
}
