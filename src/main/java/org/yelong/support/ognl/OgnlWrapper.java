/**
 * 
 */
package org.yelong.support.ognl;

import ognl.Ognl;
import ognl.OgnlContext;
import ognl.OgnlException;

/**
 * @author 彭飞
 * @date 2019年9月10日下午5:47:24
 * @version 1.2
 */
public class OgnlWrapper {

	private OgnlContext ognlContext = new OgnlContext();
	
	/**
	 * 添加对象
	 * @author 彭飞
	 * @date 2019年9月10日下午5:49:51
	 * @version 1.2
	 * @param key
	 * @param value
	 */
	public void put(String key,Object value) {
		ognlContext.put(key, value);
	}
	
	/**
	 * 获取表达式的值
	 * @author 彭飞
	 * @date 2019年9月10日下午5:49:45
	 * @version 1.2
	 * @param expression
	 * @return
	 * @throws OgnlException
	 */
	public Object getValue(String expression) throws OgnlException {
		return Ognl.getValue(expression, ognlContext, ognlContext.getRoot());
	}
	
	/**
	 * 设置表达式的属性值
	 * @author 彭飞
	 * @date 2019年9月10日下午5:50:40
	 * @version 1.2
	 * @param expression 表达式
	 * @param value 值
	 * @throws OgnlException
	 */
	public void setValue(String expression,Object value) throws OgnlException {
		Ognl.setValue(expression, ognlContext, ognlContext.getRoot(), value);
	}
	
	/**
	 * @author 彭飞
	 * @date 2019年9月11日上午10:01:31
	 * @version 1.2
	 * @param root
	 */
	public void setRoot(Object root) {
		ognlContext.setRoot(root);
	}
	
	/**
	 * @date 2019年11月14日上午11:28:18
	 * @version 1.2
	 * @return
	 */
	public Object getRoot() {
		return ognlContext.getRoot();
	}
	
	
}
