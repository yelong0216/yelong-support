/**
 * 
 */
package org.yelong.support.servlet.mvc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletOutputStream;

/**
 * @author pengfei<yl1430834495@163.com>
 * @date 2019年11月11日上午10:36:33
 * @version 1.2
 */
public abstract class AbstractController implements Controller{
	
	
	@Override
	public void responseText(String text) throws IOException {
		getResponse().setContentType("text/html;charset=UTF-8");
		getResponse().getWriter().write(text);
	}
	
	@Override
	public void responseFile(File file) throws IOException {
		if( !file.exists() ) {
			throw new FileNotFoundException(file.getAbsolutePath()+"文件不存在！");
		}
		responseFile(file, file.getName());
	}
	
	@Override
	public void responseFile(File file, String filename) throws IOException {
		if( !file.exists() ) {
			throw new FileNotFoundException(file.getAbsolutePath()+"文件不存在！");
		}
		FileInputStream fis = new FileInputStream(file);
		String fileName = URLEncoder.encode(filename, "UTF-8");
		byte [] b = new byte[fis.available()];
		fis.read(b);
		getResponse().setCharacterEncoding("UTF-8");
		getResponse().setHeader("Content-Disposition", "attachment;fileName="+fileName+"");
		ServletOutputStream out = getResponse().getOutputStream();
		out.write(b);
		out.flush();
		out.close();
		fis.close();
	}
	
	@Override
	public void responseFile( String fileName , byte [] fileBytsArray) throws IOException {
		getResponse().setCharacterEncoding("UTF-8");
		getResponse().setHeader("Content-Disposition", "attachment;fileName="+fileName+"");
		ServletOutputStream out = getResponse().getOutputStream();
		out.write(fileBytsArray);
		out.flush();
		out.close();
	}
	
	
	@Override
	public void responseContent(byte[] content) throws IOException {
		getResponse().getOutputStream().write(content);
	}

}
