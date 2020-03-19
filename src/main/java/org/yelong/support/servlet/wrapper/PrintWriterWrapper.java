/**
 * 
 */
package org.yelong.support.servlet.wrapper;

import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * PrintWriter包装器
 * 内部使用 {@link StringBuilder}来存储数据
 * @author 彭飞
 * @date 2019年9月19日下午3:47:40
 * @version 1.2
 */
public class PrintWriterWrapper extends PrintWriter{

	private StringBuilder stringBuilder;

	public PrintWriterWrapper(OutputStream out) {
		super(out);
		stringBuilder = new StringBuilder();
	}

	public void write(char[] buf, int off, int len) {
		super.write(buf, off, len);
		char[] dest = new char[len];
		System.arraycopy(buf, off, dest, 0, len);
		stringBuilder.append(dest);
	}

	@Override
	public void write(char[] buf) {
		super.write(buf);
	}

	@Override
	public void write(int c) {
		super.write(c);
	}

	@Override
	public void write(String s, int off, int len) {
		super.write(s, off, len);
		stringBuilder.append(s);
	}

	@Override
	public void write(String s) {
		super.write(s);
	}

	public String getContent(){
		return stringBuilder.toString();
	}

	/**
	 * 重置打印的内容
	 * @author 彭飞
	 * @date 2019年11月4日上午11:11:15
	 * @version 1.2
	 */
	public void reset() {
		stringBuilder.setLength(0);
	}

}
