/**
 * 
 */
package org.yelong.support.properties;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import org.yelong.commons.util.PropertiesUtils;

/**
 * @author PengFei
 */
public class PropertiesWrapper {

	private Properties properties;

	public PropertiesWrapper(Properties properties) {
		if (null == properties)
			throw new NullPointerException();
		this.properties = properties;
	}

	public PropertiesWrapper(String properties) {
		this(load(properties));
	}

	/**
	 * @see PropertiesUtils#load(String)
	 */
	public static Properties load(String properties) {
		return PropertiesUtils.load(properties);
	}

	/**
	 * @see PropertiesUtils#load(Properties, String)
	 */
	public static Properties load(Properties props, String properties) {
		return PropertiesUtils.load(props, properties);
	}

	/**
	 * @return 属性
	 */
	public Properties getProperties() {
		return properties;
	}

	/**
	 * @param key key
	 * @return Properties.getProperty(String key)
	 */
	public String get(String key) {
		return properties.getProperty(key);
	}

	/**
	 * 获取值并根据指定格式进行转换
	 * 
	 * @param key         key
	 * @param charsetName 字符串编码
	 * @return 转换为指定编码的值
	 * @throws UnsupportedEncodingException 编码异常
	 */
	public String get(String key, String charsetName) throws UnsupportedEncodingException {
		String value = properties.getProperty(key);
		if (null == value) {
			return null;
		}
		return new String(value.getBytes("ISO-8859-1"), charsetName);
	}

	/**
	 * 获取值并转换为utf-8格式
	 * 
	 * @param key key
	 * @return UTF-8格式的值
	 */
	public String getUTF8(String key) {
		try {
			return get(key, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}

	/**
	 * 获取值并转换为gbk格式
	 * 
	 * @param key key
	 * @return GBK格式的值
	 */
	public String getGBK(String key) {
		try {
			return get(key, "GBK");
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}

}
