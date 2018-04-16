package org.ifsim.vairline.common.util;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**   
* @Description: 关于shiro的工具类
* @author shentong  
* @date 2017年11月12日 下午5:06:16 
* @version V1.0   
*/
public class ShiroUtil {
	
	/** 
	* @Description: 通过默认加密次数（1024）获取MD5字符串 
	*/
	public static String getMD5BySalt(String credentials,String salt) {
		String hashAlgorithmName = "MD5";
		// 加密次数
		int hashIterations = 1024;
		SimpleHash result = new SimpleHash(hashAlgorithmName, credentials, ByteSource.Util.bytes(salt), hashIterations);
		return result.toString();
	}
	
	/** 
	* @Description: 通过自定义加密次数获取MD5字符串  
	*/
	public static String getMD5BySalt(String credentials,String salt,int time) {
		String hashAlgorithmName = "MD5";
		// 加密次数
		int hashIterations = time;
		SimpleHash result = new SimpleHash(hashAlgorithmName, credentials, ByteSource.Util.bytes(salt), hashIterations);
		return result.toString();
	}
	
}
