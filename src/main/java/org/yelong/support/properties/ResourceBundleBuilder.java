/**
 * 
 */
package org.yelong.support.properties;

import java.util.ResourceBundle;

/**
 * @author 彭飞
 * @date 2019年4月26日下午1:12:16
 */
public class ResourceBundleBuilder {
	
	
	/**
	 * 获取pack包下的propertiesName配置文件对象。propertiesName(不用带.properties结尾)
	 * @author 彭飞
	 * @date 2019年4月19日下午2:28:44
	 * @param pack
	 * @param propertiesName
	 * @return
	 */
	public static ResourceBundle build(Package pack,String propertiesName) {
		String packName = pack.getName();
		packName = packName.replace(".", "/");
		ResourceBundle resource = ResourceBundle.getBundle(packName+"/"+propertiesName);
		return resource;
	}
	
	
	/**
	 * 获取classs同包下的propertiesName配置文件对象。propertiesName(不用带.properties结尾)
	 * @author 彭飞
	 * @date 2019年4月19日下午2:28:44
	 * @param pack
	 * @param propertiesName
	 * @return
	 */
	public static ResourceBundle build(Class<?> classs,String propertiesName) {
		return build(classs.getPackage(), propertiesName);
	}
	
	
	
	
}
