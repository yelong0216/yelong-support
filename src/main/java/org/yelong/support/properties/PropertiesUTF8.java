/**
 * 
 */
package org.yelong.support.properties;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 * 重写获取proeprty方法。获取的所有值将进行转换为utf-8格式
 * @author PengFei
 */
public class PropertiesUTF8 extends Properties{
	
	private static final long serialVersionUID = -3925335530308637498L;

	private static final String ENCODING = "UTF-8";
	
	@Override
	public String getProperty(String key) {
		String value = super.getProperty(key);
		if( null == value ) {
			return null;
		}
		try {
			return new String(value.getBytes("ISO-8859-1"),ENCODING);
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}
	
}
