package com.sinlov.toolsclass.reflectclass;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;


/**
 * This class is used to myOwn reflect method
 * @author erZheng
 * @date Oct 8, 2014 11:00:57 AM
 */
	
public class ReflectClass {
	/**
	 * this method is used input a Constructor<?>[] to reflect constructor methods.
	 * After reflect return a String by "\n".
	 * @param cConstructors
	 * @return
	 */
	public String getConstrutorsToString(Constructor<?>[] cConstructors) {
		StringBuffer sb = new StringBuffer();
		for (Constructor<?> constructor : cConstructors) {
			Class<?>[] classType = constructor.getParameterTypes();
			int classModifiers = constructor.getModifiers();
			String classMod = Modifier.toString(classModifiers);
			
			sb.append(classMod + " " + constructor.getName());
			sb.append(" (");
			if (classType.length != 0) {
				for (int i = 0; i < classType.length; i++) {
					Class<?> classTypeName = classType[i];
					String buff = classTypeName.getName();
					String[] buffst = buff.split("\\.");
					sb.append(buffst[buffst.length-1]);
					sb.append(" agr"+i);
					if (i != classType.length-1) {
						sb.append(", ");
					}
				}
			}
			sb.append(") { }" + "\n");
		}
		String result = sb.toString();
		return result;
	}
	
	/**
	 * this method is used input a Method[] to reflect methods.
	 * After reflect return a String by "\n".
	 * @param cMethods
	 * @return
	 */
	public String getMethodsToString(Method[] cMethods){
		StringBuffer sb = new StringBuffer();
		for (Method method : cMethods) {
			//get type of Return and Modifiers name
			Class<?> classReturnType = method.getReturnType();
			int classModifiers = method.getModifiers();
			String classMod = Modifier.toString(classModifiers);
			sb.append(classMod + " " +classReturnType.getName() + method.getName());
			//get type of parameter
			Class<?>[] classType = method.getParameterTypes();
			sb.append(" (");
			if (classType.length != 0) {
				for (int i = 0; i < classType.length; i++) {
					Class<?> classTypeName = classType[i];
					String buff = classTypeName.getName();
					String[] buffst = buff.split("\\.");
					sb.append(buffst[buffst.length-1]);
					if (i != classType.length-1) {
						sb.append(", ");
					}
				}
			}
			sb.append(") ");
			//get Exception list
			Class<?>[] classException = method.getExceptionTypes();
			if (classException.length != 0) {
				sb.append("throws ");
				for (int i = 0; i < classException.length; i++) {
					Class<?> classExceptionName = classException[i];
					String buff = classExceptionName.getName();
					String[] buffst = buff.split("\\.");
					sb.append(buffst[buffst.length-1]);
					if (i != classException.length-1) {
						sb.append(",");
					}
				}
			}
			sb.append(" { }" + "\n");
		}
		String result = sb.toString();
		return result;
	}
}
