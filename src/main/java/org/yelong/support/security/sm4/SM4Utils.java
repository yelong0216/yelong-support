/**
 * 
 */
package org.yelong.support.security.sm4;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.yelong.commons.util.HexStrConvert;

/**
 * sm4工具
 * @author PengFei
 */
public class SM4Utils {

	private static final String ENCODING = "UTF-8";

	/**
	 * 加密
	 * @param input 需要加密的字符串 
	 * @param key 加密的密钥
	 * @return 加密后的字符串。这个字符串是base64格式的
	 * @throws IOException
	 */
	public static String encodeByBase64(String input,String key) throws IOException{
		return encodeByBase64(input, key.getBytes(ENCODING));
	}

	/**
	 * 加密
	 * @param input 需要加密的字符串
	 * @param key 秘钥
	 * @return 这个字符串是base64格式的
	 * @throws UnsupportedEncodingException
	 */
	public static String encodeByBase64(String input,byte [] key) throws UnsupportedEncodingException {
		return new sun.misc.BASE64Encoder().encode(encode(input.getBytes(ENCODING), key));
	}

	/**
	 * 解密
	 * @param input 加密的字符串。这个字符串是base64格式的
	 * @param key 密钥
	 * @return 解密后的字符串
	 * @throws IOException
	 */
	public static String decodeByBase64(String input,String key) throws IOException{
		return decodeByBase64(input, key.getBytes(ENCODING));
	}

	/**
	 * 解密
	 * @param input 加密的字符串。这个字符串是base64格式的
	 * @param key 密钥
	 * @return 解密后的字符串
	 * @throws IOException 
	 */
	public static String decodeByBase64(String input,byte [] key) throws IOException {
		return new String(decode(new sun.misc.BASE64Decoder().decodeBuffer(input), key),ENCODING).trim();
	}

	/**
	 * 加密
	 * @param input 需要加密的字符串 
	 * @param key 加密的密钥
	 * @return 加密后的字符串。这个字符串是16进制格式的
	 * @throws IOException
	 */
	public static String encodeByHexStr(String input,String key) throws IOException{
		return encodeByHexStr(input, key.getBytes(ENCODING));
	}

	/**
	 * 加密
	 * @param input 需要加密的字符串
	 * @param key 秘钥
	 * @return 这个字符串是16进制格式的
	 * @throws UnsupportedEncodingException
	 */
	public static String encodeByHexStr(String input,byte [] key) throws UnsupportedEncodingException {
		return 	HexStrConvert.bytesToHexString(encode(input.getBytes(ENCODING), key));
	}

	/**
	 * 解密
	 * @param input 加密的字符串。这个字符串是16进制格式的
	 * @param key 密钥
	 * @return 解密后的字符串
	 * @throws IOException
	 */
	public static String decodeByHexStr(String input,String key) throws IOException{
		return decodeByHexStr(input, key.getBytes(ENCODING));
	}

	/**
	 * 解密
	 * @param input 加密的字符串。这个字符串是16进制格式的
	 * @param key 密钥
	 * @return 
	 * @throws IOException 
	 */
	public static String decodeByHexStr(String input,byte [] key) throws IOException {
		return new String(decode(HexStrConvert.hexStringToBytes(input), key),ENCODING).trim();
	}

	/**
	 * 加密
	 * @param input 需要加密的数据
	 * @param key 秘钥
	 * @return 加密后的数据
	 * @throws UnsupportedEncodingException
	 */
	public static byte[] encode(byte [] input,byte [] key) throws UnsupportedEncodingException {
		return new SM4().encode(input, key);
	}

	/**
	 * 解密
	 * @param input 加密的数据
	 * @param key 秘钥
	 * @return 解析后的数据
	 * @throws IOException
	 */
	public static byte [] decode(byte [] input,byte [] key) throws IOException {
		return new SM4().decode(input, key);
	}

}
