package com.tianhe.statesave.master;

import java.io.Serializable;
import java.lang.reflect.Field;

import android.os.Bundle;
import android.os.Parcelable;

@SuppressWarnings("rawtypes")
public class StateUtils {

	/**
	 * ����context�����SaveStateע��ĳ�Ա����
	 * @param context ��Ҫ�����Ա�����Ķ���
	 * @param outState ���ڱ�����ݵ�Bundle
	 */
	public static void onSaveState(Object context, Bundle outState) {
		Class clazz = context.getClass();
		Field[] fields = clazz.getDeclaredFields();
		for (Field f : fields) {
			boolean accessible = f.isAccessible();
			if (!accessible) {
				f.setAccessible(true);
			}
			if (f.getAnnotation(SaveState.class) != null) {
				try {
					Class type = f.getType();
					if (type == boolean.class) {
						outState.putBoolean(f.getName(), f.getBoolean(context));
						continue;
					} else if (type == int.class) {
						outState.putInt(f.getName(), f.getInt(context));
						continue;
					} else if (type == float.class) {
						outState.putFloat(f.getName(), f.getFloat(context));
						continue;
					} else if (type == double.class) {
						outState.putDouble(f.getName(), f.getDouble(context));
						continue;
					} else if (type == long.class) {
						outState.putLong(f.getName(), f.getLong(context));
						continue;
					} else if (type == short.class) {
						outState.putShort(f.getName(), f.getShort(context));
						continue;
					} else if (type == char.class) {
						outState.putChar(f.getName(), f.getChar(context));
						continue;
					} else if (type == byte.class) {
						outState.putByte(f.getName(), f.getByte(context));
						continue;
					} else if (isImplementSerializable(type)){
						outState.putSerializable(f.getName(),(Serializable) f.get(context));
						continue;
					} else if(isImplementParcelable(type)){
						outState.putParcelable(f.getName(),(Parcelable) f.get(context));
						continue;
						}else{
							throw new IllegalArgumentException(f.getName()+"���ɱ����л����޷����浽Bundle�У�");
					}
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				}
			}
			f.setAccessible(accessible);
		}
	}

	/**
	 * �ָ����������SaveStateע��ĳ�Ա������ֵ
	 * @param context ��Ҫ�����Ա�����Ķ���
	 * @param savedInstanceState ���ڱ�����ݵ�Bundle
	 */
	public static void onRestoreState(Object context, Bundle savedInstanceState) {
		Class clazz = context.getClass();
		Field[] fields = clazz.getDeclaredFields();
		for (Field f : fields) {
			boolean accessible = f.isAccessible();
			if (!accessible) {
				f.setAccessible(true);
			}
			if (f.getAnnotation(SaveState.class) != null) {
				try {
					f.set(context, savedInstanceState.get(f.getName()));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			f.setAccessible(accessible);
		}
	}

	/**
	 * �жϳ�Ա�����Ƿ�ʵ����Serializable
	 * @param clazz ��Ա������������
	 * @return
	 */
	private static boolean isImplementSerializable(Class clazz) {
		Class[] interfaces = clazz.getInterfaces();
		if (interfaces.length == 0) {
			return false;
		}
		for (Class i : interfaces) {
			if (i == Serializable.class) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * �жϳ�Ա�����Ƿ�ʵ����Parcelable
	 * @param clazz ��Ա������������
	 * @return
	 */
	private static boolean isImplementParcelable(Class clazz) {
		Class[] interfaces = clazz.getInterfaces();
		if (interfaces.length == 0) {
			return false;
		}
		for (Class i : interfaces) {
			if (i == Parcelable.class) {
				return true;
			}
		}
		return false;
	}
}
