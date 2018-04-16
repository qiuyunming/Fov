package org.ifsim.vairline.common.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

/**
 * @author shentong
 *
 */
public class CommonUtil {

	public static String getUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	/*
	 * 阻塞时间
	 */
	public static void sleep(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
	}

	/*
	 * 字符转字节数组
	 */
	public static byte[] getBytes(String name) {
		byte[] bytes = name.getBytes();
		return bytes;
	}

	/*
	 * 字符转字节数组
	 */
	public static String getString(char[] bytes) {
		String string = String.valueOf(bytes);
		return string;
	}

	/*
	 * 字符转字节数组
	 */
	public static String getString(Character[] bytes) {
		String string = String.valueOf(bytes);
		return string;
	}

	/* 获取一定范围内的随机数 */
	public static int getRandom(int min, int max) {
		return (int) (min + Math.random() * (max - min));
	}

	/* 获取一定范围内的随机数 */
	public static float getRandom(float min, float max) {
		return (float) (min + Math.random() * (max - min));
	}

	/* 获取一定范围内的随机数 */
	public static double getRandom(double min, double max) {
		return min + Math.random() * (max - min);
	}

	/* 融合两个数组 */
	public static byte[] concat(byte[] first, byte[] second) {
		byte[] bytes = Arrays.copyOf(first, first.length + second.length);
		System.arraycopy(second, 0, bytes, first.length, second.length);
		return bytes;
	}

	/**
	 * @Description: 获取文件字节流
	 */
	public static byte[] getFileBytes(File file) {
		byte[] ret = null;
		try {
			if (file == null) {
				return null;
			}
			FileInputStream in = new FileInputStream(file);
			ByteArrayOutputStream out = new ByteArrayOutputStream(4096);
			byte[] b = new byte[4096];
			int n;
			while ((n = in.read(b)) != -1) {
				out.write(b, 0, n);
			}
			in.close();
			out.close();
			ret = out.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ret;
	}

	/**
	 * @Description: 比较两个时间
	 * @return Integer 相差的秒数
	 */
	public static Long compareTime(Date time1, Date time2) {
		return (time2.getTime() - time1.getTime()) / 1000;
	}

	/**
	 * @Description: 获取两地距离
	 */
	public static double getDistance(double longitude1, double latitude1, double longitude2, double latitude2) {

		// 纬度
		double Lat1 = rad(latitude1);

		double Lat2 = rad(latitude2);

		// 两点纬度之差
		double a = Lat1 - Lat2;
		// 经度之差
		double b = rad(longitude1) - rad(longitude2);
		// 计算两点距离的公式
		double s = 2 * Math.asin(Math

				.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(Lat1) * Math.cos(Lat2) * Math.pow(Math.sin(b / 2), 2)));
		// 弧长乘地球半径（半径为米）
		s = s * 6378137.0;
		// 精确距离的数值
		s = Math.round(s * 10000d) / 10000d;

		return s;

	}

	/**
	 * @Description: //角度转换成弧度
	 */
	private static double rad(double d) {

		return d * Math.PI / 180.00;

	}
}
