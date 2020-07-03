/**
 * 
 */
package org.yelong.support.orm;

import org.yelong.commons.util.PropertiesUtils;
import org.yelong.core.jdbc.DataSourceProperties;
import org.yelong.support.properties.wired.PropertiesWiredProcessorBuilder;

/**
 * 读取自定义的配置创建DataSource
 * 
 * @author PengFei
 */
public final class DataSourcePropertiesFactory {

	private DataSourcePropertiesFactory() {
	}

	public static final String DEFAULT_DATASOURCE_PREFIX = "datasource";

	/**
	 * 根据配置创建数据库配置
	 * 
	 * @param configLocation 配置文件路径
	 * @return 数据库配置属性
	 * @see #createDataSourceBuilder(String, String)
	 * @throws Exception
	 * @since 1.0.4
	 */
	public static DataSourceProperties create(String configLocation) throws Exception {
		return create(configLocation, DEFAULT_DATASOURCE_PREFIX);
	}

	/**
	 * 根据配置创建数据库配置
	 * 
	 * createDataSourceBuilder("database.yelong.properties","datasource.yelong");
	 * 
	 * database.yelong.properties配置文件属性如下： datasource.yelong.url = ...
	 * datasource.yelong.username=... datasource.yelong.password=...
	 * datasource.yelong.driverClassName=...
	 * 
	 * @param configLocation 配置文件路径
	 * @param prefix         配置文件中数据库配置的前缀
	 * @return 数据库配置
	 * @throws Exception
	 * @since 1.0.4
	 */
	public static DataSourceProperties create(String configLocation, String prefix) throws Exception {
		return PropertiesWiredProcessorBuilder
				.builder(DataSourceProperties.class, PropertiesUtils.load(configLocation), prefix).wiredObj();
	}

}
