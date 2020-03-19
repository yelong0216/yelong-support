/**
 * 
 */
package org.yelong.support.servlet.mvc;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.yelong.core.annotation.Nullable;

/**
 * @author pengfei<yl1430834495@163.com>
 * @date 2019年11月11日上午9:02:03
 * @version 1.2
 */
public interface Controller {
	
	/**
	 * @date 2019年11月11日上午9:59:24
	 * @version 1.2
	 * @return
	 */
	HttpServletRequest getRequest();
	
	/**
	 * @date 2019年11月11日上午10:00:30
	 * @version 1.2
	 * @return HttpServletResponse
	 */
	HttpServletResponse getResponse();
	
	/**
	 * 响应一个文本
	 * @date 2019年11月11日上午10:00:34
	 * @version 1.2
	 * @param text 内容
	 */
	void responseText(String text)  throws IOException ;
	
	/**
	 * 响应一个文件
	 * @date 2019年11月11日上午10:00:46
	 * @version 1.2
	 * @param file 文件
	 */
	void responseFile(File file)  throws IOException ;
	
	/**
	 * 响应一个文件并指定文件的名称
	 * @date 2019年12月11日下午5:35:51
	 * @param file 文件
	 * @param filename 响应的文件名称。默认为 {@link File#getName()}
	 * @throws IOException
	 */
	void responseFile(File file,@Nullable String filename) throws IOException;
	
	/**
	 * 响应文件
	 * @param fileByteArray 文件流
	 * @param fileName 文件名称
	 * @throws IOException
	 */
	void responseFile( String fileName , byte [] fileBytsArray) throws IOException;
	
	/**
	 * 设置响应内容
	 * @date 2019年11月11日上午10:00:49
	 * @version 1.2
	 * @param content 内容字节数组
	 */
	void responseContent(byte [] content)  throws IOException ;

}
