/**
 * 
 */
package org.yelong.support.properties;

import java.util.ResourceBundle;

/**
 * @author PengFei
 */
public class ResourceBundleBuilder {
	
	/**
	 * 获取pack包下的propertiesName配置文件对象。propertiesName(不用带.properties结尾)
	 * 
	 * @param pack 包
	 * @param propertiesName 配置文件名称
	 * @return 资源绑定
	 */
	public static ResourceBundle build(Package pack,String propertiesName) {
		String packName = pack.getName();
		packName = packName.replace(".", "/");
		ResourceBundle resource = ResourceBundle.getBundle(packName+"/"+propertiesName);
		return resource;
	}
	
	/**
	 * 获取classs同包下的propertiesName配置文件对象。propertiesName(不用带.properties结尾)
	 * 
	 * @param pack 包
	 * @param propertiesName 属性名称
	 * @return 资源绑定
	 */
	public static ResourceBundle build(Class<?> classs,String propertiesName) {
		return build(classs.getPackage(), propertiesName);
	}
	
}
