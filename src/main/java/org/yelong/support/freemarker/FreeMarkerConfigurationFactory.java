/**
 * 
 */
package org.yelong.support.freemarker;

import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;

import freemarker.template.Configuration;

/**
 * 配置工厂
 * 
 * @since 1.1
 */
public final class FreeMarkerConfigurationFactory {

	private FreeMarkerConfigurationFactory() {
	}

	/**
	 * private static final Configuration configuration =
	 * FreeMarkerConfigurationFactory.createConfigurationByClass(DictTemplate.class);
	 * 
	 * 创建模板在 c 类同包下的 {@link Configuration}
	 * 
	 * @param c 与模板在同一包的类
	 * @return {@link Configuration}
	 */
	public static final Configuration createConfigurationByClass(Class<?> c) {
		return createConfigurationByPackage(c.getPackage());
	}

	/**
	 * 创建模板在 p 包下的 {@link Configuration}
	 * 
	 * @param p 存放模板的包
	 * @return {@link Configuration}
	 */
	public static final Configuration createConfigurationByPackage(Package p) {
		return createConfigurationByPackageName(p.getName());
	}

	/**
	 * 
	 * private static final Configuration configuration =
	 * FreeMarkerConfigurationFactory.createConfigurationByPackageName("org.yelong.dict.tpl");
	 * 
	 * 创建模板在 packageName 包下的 {@link Configuration}
	 * 
	 * @param packageName 存放模板的包
	 * @return {@link Configuration}
	 */
	public static final Configuration createConfigurationByPackageName(String packageName) {
		FreeMarkerConfigurationFactoryBean factory = new FreeMarkerConfigurationFactoryBean();
		factory.setTemplateLoaderPath(packageName.replace(".", "/"));
		try {
			return factory.createConfiguration();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}