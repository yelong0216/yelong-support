/**
 * 
 */
package org.yelong.support.ognl;

import ognl.Ognl;
import ognl.OgnlContext;
import ognl.OgnlException;

/**
 * Ognl包装器
 * 
 * @author PengFei
 */
public class OgnlWrapper {

	private OgnlContext ognlContext = new OgnlContext();

	public OgnlWrapper() {
	}

	public OgnlWrapper(Object root) {
		setRoot(root);
	}

	/**
	 * 添加对象
	 * 
	 * @param key   对象对应的key
	 * @param value 对象
	 */
	public void put(String key, Object value) {
		ognlContext.put(key, value);
	}

	/**
	 * 获取表达式的值
	 * 
	 * @param expression 表达式
	 * @return value
	 * @throws OgnlException
	 */
	public Object getValue(String expression) throws OgnlException {
		return Ognl.getValue(expression, ognlContext, ognlContext.getRoot());
	}

	/**
	 * 设置表达式的属性值
	 * 
	 * @param expression 表达式
	 * @param value      值
	 * @throws OgnlException
	 */
	public void setValue(String expression, Object value) throws OgnlException {
		Ognl.setValue(expression, ognlContext, ognlContext.getRoot(), value);
	}

	/**
	 * @param root 根对象
	 */
	public void setRoot(Object root) {
		ognlContext.setRoot(root);
	}

	/**
	 * @return 根对象
	 */
	public Object getRoot() {
		return ognlContext.getRoot();
	}

}
