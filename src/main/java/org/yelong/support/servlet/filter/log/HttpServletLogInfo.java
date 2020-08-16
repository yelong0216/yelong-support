/**
 * 
 */
package org.yelong.support.servlet.filter.log;

import java.util.Date;
import java.util.Map;

/**
 * @since 1.0
 */
public class HttpServletLogInfo {

	private Date startTime;

	private Date endTime;

	private Map<String, String[]> requestParams;

	private byte[] requestBody;

	private byte[] responseResult;

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

	public byte[] getResponseResult() {
		return responseResult;
	}

	public void setResponseResult(byte[] responseResult) {
		this.responseResult = responseResult;
	}

	/**
	 * 操作时间
	 * 
	 * @return 操作时间 ms
	 */
	public long getOperationTime() {
		return endTime.getTime() - startTime.getTime();
	}

	/**
	 * 请求消息体
	 * 
	 * @return request body
	 */
	public byte[] getRequestBody() {
		return requestBody;
	}

	public void setRequestBody(byte[] requestBody) {
		this.requestBody = requestBody;
	}

	/**
	 * 请求参数
	 * 
	 * @return request params
	 */
	public Map<String, String[]> getRequestParams() {
		return requestParams;
	}

	public void setRequestParams(Map<String, String[]> requestParams) {
		this.requestParams = requestParams;
	}

}
