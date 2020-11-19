package org.yelong.support.servlet.resource.response.responder.file;

import java.util.ArrayList;
import java.util.List;

/**
 * 抽象实现
 * 
 * @since 2.2
 */
public abstract class AbstractFileResourceResponder implements FileResourceResponder {

	private List<String> supportResourceTypes = new ArrayList<>();

	public AbstractFileResourceResponder(String... supportResourceTypes) {
		setSupportResourceTypes(supportResourceTypes);
	}

	@Override
	public String[] getSupportResourceTypes() {
		return supportResourceTypes.toArray(new String[supportResourceTypes.size()]);
	}

	/**
	 * 添加支持的资源类型
	 * 
	 * @param supportResourceTypes 支持的资源类型
	 */
	public void addSupportResourceTypes(String... supportResourceTypes) {
		if (null != supportResourceTypes) {
			for (String supportResourceType : supportResourceTypes) {
				this.supportResourceTypes.add(supportResourceType);
			}
		}
	}

	/**
	 * 删除支持的资源类型
	 * 
	 * @param supportResourceTypes 支持的资源类型
	 */
	public void removeSupportResourceTypes(String... supportResourceTypes) {
		if (null != supportResourceTypes) {
			for (String supportResourceType : supportResourceTypes) {
				this.supportResourceTypes.remove(supportResourceType);
			}
		}
	}

	/**
	 * 设置支持的资源类型，这会覆盖原本已经存在资源类型
	 * 
	 * @param supportResourceTypes 支持的资源类型
	 */
	public void setSupportResourceTypes(String... supportResourceTypes) {
		this.supportResourceTypes.clear();
		addSupportResourceTypes(supportResourceTypes);
	}

}
