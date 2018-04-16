package org.ifsim.vairline.common.util;

import java.lang.reflect.Field;

/**
 * @Description: 字节流解包赋值到对象
 * @author shentong
 * @date 2017年12月6日 上午11:18:04
 * @version V1.0
 */
public class UnpackUtil {
	private int startIndex = 0;

	/**
	 * @Description: 解包，可以选择从第几个属性开始解包
	 * @param data 要解包的对象
	 * @param bytes 字节数组
	 * @param startFieldIndex 开始解包位置
	 */
	public void unpack(Object data, byte[] bytes, int startFieldIndex) {
		Class<? extends Object> dataClass = data.getClass();
		Field fields[] = dataClass.getDeclaredFields();

		try {
			for (int i = startFieldIndex; i < fields.length; i++) {
				Field field = fields[i];
				field.setAccessible(true);
				if (field.getType() == String.class) {

					String str = (String) field.get(data);
					String strNew = "";
					for (int j = 0; j < str.length(); j++) {
						Character c = (Character) judgeTypeClass(Character.class, bytes);
						strNew += c;
					}
					field.set(data, strNew);
				} else {
					Class<?> typeClass = field.getType();
					Object num = judgeTypeClass(typeClass, bytes);
					field.set(data, num);
				}
			}

		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} finally {
			startIndex = 0;
		}
	}

	/**
	 * @Description: 判断类型并预留规定的字节长度
	 */
	private Object judgeTypeClass(Class<?> typeClass, byte[] bytes) {
		Object obj = null;
		byte[] targetBytes = null;
		if (typeClass == Float.class) {
			targetBytes = getBytesArray(bytes, 4);
			obj = ByteTranserUtil.getFloat(targetBytes);
		} else if (typeClass == Double.class) {
			targetBytes = getBytesArray(bytes, 8);
			obj = ByteTranserUtil.getDouble(targetBytes);
		} else if (typeClass == Character.class) {
			targetBytes = getBytesArray(bytes, 1);
			obj = ByteTranserUtil.getChar(targetBytes);
		}
		return obj;
	}

	/**
	 * @Description: 获取 字节数组
	 */
	private byte[] getBytesArray(byte[] bytes, int length) {
		byte[] targetBytes = new byte[length];
		for (int i = 0; i < length; i++) {
			targetBytes[i] = bytes[startIndex + i];
		}
		startIndex += length;
		return targetBytes;
	}

	public static byte[] ObjectToByte(Object obj) {
		byte[] bytes = new byte[0];
		try {
			for (Field field : obj.getClass().getFields()) {
				byte[] b = { field.getByte(obj) };
				CommonUtil.concat(bytes, b);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bytes;
	}

}
