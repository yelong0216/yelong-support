/**
 * 
 */
package org.yelong.support.security.sm3;

import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.util.encoders.Hex;

/**
 * @author 彭飞
 * @date 2019年4月11日上午10:01:52
 */
public class SM3Utils {

	/**
	 * 通过sm3加密数据
	 * @author 彭飞
	 * @date 2019年4月11日上午8:46:34
	 * @param str 需要加密的字符串
	 * @return 加密生成的字符串
	 */
	public static String encrypt(String str){
		if(StringUtils.isEmpty(str))return null;
		SM3Digest sm3 = new SM3Digest();
		byte [] b = str.getBytes();
		byte[] c = new byte[32];
		sm3.update(b, 0, b.length);
		sm3.doFinal(c, 0);
		return new String(Hex.encode(c));
	}

	/**
	 * 验证该两者是否是同一个数据
	 * @author 彭飞
	 * @date 2019年4月11日上午10:04:03
	 * @param str 原数据
	 * @param sm3HexStr 通过sm3加密后的数据
	 * @return 是否相同;true:相同
	 */
	public static boolean verify(String str,String sm3HexStr) {
		if(StringUtils.isEmpty(str)&&StringUtils.isEmpty(sm3HexStr))
			throw new NullPointerException("param str and sm3HexStr can not be blank");
		return encrypt(str).equals(sm3HexStr);
	}

}
