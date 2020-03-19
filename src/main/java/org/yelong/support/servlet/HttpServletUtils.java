/**
 * 
 */
package org.yelong.support.servlet;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

/**
 * @author 彭飞
 * @date 2019年9月17日下午1:51:29
 * @version 1.2
 */
public class HttpServletUtils {

	/**
	 * 读取请求消息体
	 * @author 彭飞
	 * @date 2019年9月17日下午2:23:57
	 * @version 1.2
	 * @param request
	 * @return
	 */
	public static String readerBodyStr(HttpServletRequest request) {
		StringBuilder sb = new StringBuilder(); 
		try(BufferedReader reader = request.getReader();) { 
			char[]buff = new char[1024]; 
			int len; 
			while((len = reader.read(buff)) != -1) { 
				sb.append(buff,0, len); 
			} 
		}catch (IOException e) { 
			e.printStackTrace(); 
		} 
		return sb.toString(); 
	}

	/**
	 * 读取请求消息体
	 * @author 彭飞
	 * @date 2019年9月23日下午5:22:38
	 * @version 1.2
	 * @param request
	 * @return
	 * @throws IOException
	 */
	public static byte [] readerBody(HttpServletRequest request) throws IOException {
		InputStream is = request.getInputStream();
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        byte[] tmp = new byte[1024];
        int read;
        while ((read = is.read(tmp)) > 0) {
            os.write(tmp, 0, read);
        }
        return os.toByteArray();
	}
	
	
	/**
	 * 获取request的请求参数。
	 * 格式：
	 * @author 彭飞
	 * @date 2019年9月23日下午4:43:18
	 * @version 1.2
	 * @param request
	 * @return
	 */
	public static String getRequestParamsStr(HttpServletRequest request) {
		Map<String,String []> requestParams = request.getParameterMap();
		if( requestParams.isEmpty() ) {
			return "";
		}
		StringBuilder requestParamStr = new StringBuilder();
		requestParams.forEach((key,value)->{
			String valueStr = "";
			if( value.length == 1 ) {
				valueStr = value[0];
			} else {
				for (int i = 0; i < value.length; i++) {
					valueStr += (value[i]+",");
				}
				valueStr = valueStr.substring(0, valueStr.length()-1);
			}
			requestParamStr.append(key+"="+valueStr+"&");
		});
		requestParamStr.deleteCharAt(requestParamStr.length()-1);
		return requestParamStr.toString();
	}



	/**
	 * 获取请求ip<br/>
	 * 这个请求应该是被Nginx反向代理的<br/>
	 * 以上是获取ip代码，另外Nginx还需要配置正确，如果Nginx反向代理有多台，那么在最外层的反向代理服务器加上
	 * proxy_set_header X-Real-IP $remote_addr;
	 * proxy_set_header X-Forwarded-For $remote_addr;
	 * 内层的反向代理服务器加上
	 * proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
	 * (！注意不要加X-Real-IP配置)
	 * 配置好后重启Nginx即可
	 * 这样java中获取的ip就是真实的ip了（不考虑用户使用代理或者VPN），即使用户伪造请求头，我们获取到的仍然是真实ip。如果Nginx不想配置X-Real-IP，那么也要删除掉java中对应的获取X-Real-IP代码
	 * @author 彭飞
	 * @date 2019年8月18日下午3:19:40
	 * @version 1.0
	 * @param request
	 * @return 请求的ip
	 */
	public static String getIpAddrByNginxReverseProxy(HttpServletRequest request) throws UnknownHostException {
		//从Nginx中X-Real-IP获取真实ip
		String ipAddress = request.getHeader("X-Real-IP");
		if( StringUtils.isNotEmpty(ipAddress) && "unknown".equalsIgnoreCase(ipAddress) ) {
			return ipAddress;
		}
		// 从Nginx中x-forwarded-for获取真实ip
		ipAddress = request.getHeader("x-forwarded-for");
		if (ipAddress != null && ipAddress.length() > 0 && !"unknown".equalsIgnoreCase(ipAddress)) {
			// 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
			int index = ipAddress.indexOf(",");
			if (index > 0) {
				ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
			}
			return ipAddress;
		}
		ipAddress = request.getRemoteAddr();
		if ("127.0.0.1".equals(ipAddress) || "0:0:0:0:0:0:0:1".equals(ipAddress)) {
			// 根据网卡取本机配置的IP
			ipAddress = InetAddress.getLocalHost().getHostAddress();
		}
		return ipAddress;
	}

	

}
