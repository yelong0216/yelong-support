package org.yelong.support.servlet.resource.response.responder.file;

import java.util.Objects;

import org.yelong.support.resource.freemarker.TemplateResourceHandler;

/**
 * 抽象实现
 * 
 * @since 2.2
 */
public abstract class AbstractTemplateFileResourceResponder extends AbstractFileResourceResponder
		implements TemplateFileResourceResponder {

	protected TemplateResourceHandler templateResourceHandler;

	public AbstractTemplateFileResourceResponder(TemplateResourceHandler templateResourceHandler) {
		this.templateResourceHandler = Objects.requireNonNull(templateResourceHandler);
	}

	@Override
	public TemplateResourceHandler getTemplateResourceHandler() {
		return templateResourceHandler;
	}

}
