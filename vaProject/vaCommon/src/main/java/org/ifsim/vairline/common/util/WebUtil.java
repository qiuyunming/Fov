package org.ifsim.vairline.common.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * @author shentong web常用工具
 */
public class WebUtil {

	/**
	 * @Description: 添加带时间的cookie
	 */
	public static void addCookie(HttpServletResponse response, String name, String value, int age) {
		Cookie cookie = new Cookie(name, value);
		cookie.setMaxAge(age);
		response.addCookie(cookie);
	}

	/**
	 * @Description: 添加不带时间的cookie
	 */
	public static void addCookie(HttpServletResponse response, String name, Object value) {
		Cookie cookie = new Cookie(name, String.valueOf(value));
		response.addCookie(cookie);
	}

	/**
	 * @Description: 发送对象到网页
	 */
	public static void sendMessage(HttpServletResponse response, Object o) {
		response.setContentLength(o.toString().length());

		PrintWriter w = null;
		try {
			w = response.getWriter();
			System.out.println("发送的信息： " + o);
			w.write(o.toString());
			w.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			w.close();
		}
	}

	/**
	 * @Description: 发送字节
	 */
	public static void sendByte(HttpServletResponse response, byte[] bytes) {
		ServletOutputStream sos = null;
		try {
			sos = response.getOutputStream();
			sos.write(bytes);
			sos.flush();
			sos.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * @Description: 转义HTML特殊字符
	 */
	public static String encodeHtml(String strUserInput) {
		strUserInput = strUserInput.replace("&", "&amp;");
		strUserInput = strUserInput.replace("'", "&apos;");
		strUserInput = strUserInput.replace("\"", "&quot;");
		strUserInput = strUserInput.replace(" ", "&nbsp;");
		strUserInput = strUserInput.replace("<", "&lt;");
		strUserInput = strUserInput.replace(">", "&gt;");
		strUserInput = strUserInput.replace("\n", "<br>");
		return strUserInput.trim();
	}

	/**
	 * @Description: 反转义HTML特殊字符
	 */
	public static String unEncodeHtml(String strUserInput) {
		strUserInput = strUserInput.replace("&amp;", "&");
		strUserInput = strUserInput.replace("&apos;", "'");
		strUserInput = strUserInput.replace("&quot;", "\"");
		strUserInput = strUserInput.replace("&nbsp;", " ");
		strUserInput = strUserInput.replace("&lt;", "<");
		strUserInput = strUserInput.replace("&gt;", ">");
		strUserInput = strUserInput.replace("<br>", "\n");
		return strUserInput.trim();
	}

	/**
	 * @Description: 转义HTML特殊字符 （做字符串用）
	 */
	public static String unEncodeHtml2(String strUserInput) {
		strUserInput = strUserInput.replace("&amp;", "&");
		strUserInput = strUserInput.replace("&apos;", "\'");
		strUserInput = strUserInput.replace("&quot;", "\\\"");
		strUserInput = strUserInput.replace("&nbsp;", " ");
		strUserInput = strUserInput.replace("&lt;", "<");
		strUserInput = strUserInput.replace("&gt;", ">");
		strUserInput = strUserInput.replace("<br>", "\n");

		return delHtmlTag(strUserInput.trim());
	}

	/**
	 * @Description: 去掉html标签
	 */
	public static String delHtmlTag(String str) {
		String newstr = "";
		newstr = str.replaceAll("<[.[^>]]*>", "");
		newstr = newstr.replaceAll(" ", "");
		return newstr;
	}
	
	public static String delSpecialChar(String input) {
		input = input.replace("<", "");
		input = input.replace(">", "");
		input = input.replace("%", "");
		input = input.replace("&", "");
		input = input.replace("$", "");
		input = input.replace("@", "");
		input = input.replace("#", "");
		input = input.replace("*", "");
		input = input.replace("_", "");
		return input.trim();
	}

}
