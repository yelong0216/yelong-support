/**
 * 
 */
package org.yelong.support.servlet.filter.log;

import java.util.Date;
import java.util.Map;

/**
 * @author 彭飞
 * @date 2019年9月19日下午3:24:35
 * @version 1.2
 */
public class HttpServletLogInfo {

	private Date startTime;
	
	private Date endTime;

	private Map<String,String[]> requestParams;
	
	private byte [] requestBody;
	
	private byte [] responseResult;

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public byte [] getResponseResult() {
		return responseResult;
	}

	public void setResponseResult(byte [] responseResult) {
		this.responseResult = responseResult;
	}
	
	/**
	 * 操作时间
	 * @author 彭飞
	 * @date 2019年9月23日下午4:33:06
	 * @version 1.2
	 * @return
	 */
	public long getOperationTime() {
		return endTime.getTime() - startTime.getTime();
	}

	/**
	 * 请求消息体
	 * @author 彭飞
	 * @date 2019年9月23日下午5:18:55
	 * @version 1.2
	 * @return
	 */
	public byte[] getRequestBody() {
		return requestBody;
	}

	public void setRequestBody(byte[] requestBody) {
		this.requestBody = requestBody;
	}

	/**
	 * 请求参数
	 * @author 彭飞
	 * @date 2019年9月23日下午5:18:47
	 * @version 1.2
	 * @return
	 */
	public Map<String, String[]> getRequestParams() {
		return requestParams;
	}

	public void setRequestParams(Map<String, String[]> requestParams) {
		this.requestParams = requestParams;
	}
	
	
	
}
