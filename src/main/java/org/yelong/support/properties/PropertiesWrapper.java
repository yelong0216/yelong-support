/**
 * 
 */
package org.yelong.support.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import org.yelong.commons.util.PropertiesUtils;

/**
 * @author 彭飞
 * @date 2019年9月11日上午11:05:42
 * @version 1.2
 */
public class PropertiesWrapper {

	private Properties properties;
	
	public PropertiesWrapper(Properties properties) {
		if( null == properties) 
			throw new NullPointerException();
		this.properties = properties;
	}
	
	public PropertiesWrapper(String properties) {
		this(load(properties));
	}
	
	
	/**
	 * 加载properties
	 * @author 彭飞
	 * @date 2019年9月10日下午5:57:14
	 * @version 1.2
	 * @param properties
	 * @return
	 */
	public static Properties load(String properties){
		return load(new Properties(), properties);
	}
	
	/**
	 * 加载properties
	 * @author 彭飞
	 * @date 2019年9月10日下午5:57:14
	 * @version 1.2
	 * @param props
	 * @param properties
	 * @return
	 */
	public static Properties load(Properties props , String properties){
		Class<?> c = PropertiesUtils.class;
		try {
			InputStream inStream = null;
			if(properties.contains(":"))
				inStream = new FileInputStream(new File(properties));
			if(null == inStream)
				inStream = c.getResourceAsStream(properties);
			if(null == inStream)
				inStream = ClassLoader.getSystemResourceAsStream(properties);
			if(null == inStream)
				inStream = c.getClassLoader().getResourceAsStream(properties);
			if(null == inStream)
				inStream = c.getClassLoader().getResourceAsStream("resources/"+properties);
			if(null == inStream)
				inStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(properties);
			if(null != inStream){
				props.load(inStream);
				inStream.close();
			}else{
				System.out.println(properties+"文件没有找到！");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return props;
	}
	
	/**
	 * @author 彭飞
	 * @date 2019年9月11日下午2:24:36
	 * @version 1.2
	 * @return 属性
	 */
	public Properties getProperties() {
		return properties;
	}
	
	/**
	 * @author 彭飞
	 * @date 2019年9月11日下午2:25:41
	 * @version 1.2
	 * @param key
	 * @return Properties.getProperty(String key)
	 */
	public String get(String key) {
		return properties.getProperty(key);
	}
	
	/**
	 * 获取值并根据指定格式进行转换
	 * @author 彭飞
	 * @date 2019年9月11日下午2:24:21
	 * @version 1.2
	 * @param key
	 * @param charsetName
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String get(String key , String charsetName) throws UnsupportedEncodingException {
		String value = properties.getProperty(key);
		if( null == value ) {
			return null;
		}
		return new String(value.getBytes("ISO-8859-1"),charsetName);
	}
	
	/**
	 * 获取值并转换为utf-8格式
	 * @author 彭飞
	 * @date 2019年9月11日下午2:24:04
	 * @version 1.2
	 * @param key
	 * @return
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
	 * @author 彭飞
	 * @date 2019年9月11日下午2:23:48
	 * @version 1.2
	 * @param key
	 * @return
	 */
	public String getGBK(String key) {
		try {
			return get(key, "GBK");
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}
	
	
}
