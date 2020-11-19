package org.yelong.support.servlet.resource;

/**
 * 资源控制器配置参数
 * 
 * @since 2.2
 */
public class ResourceServletProperties {

	private String encoding;

	protected ResourceServletProperties() {
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}
	
	public String getEncoding() {
		return encoding;
	}

}
