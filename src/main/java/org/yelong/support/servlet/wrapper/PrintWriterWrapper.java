/**
 * 
 */
package org.yelong.support.servlet.wrapper;

import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * PrintWriter包装器
 * 内部使用 {@link StringBuilder}来存储数据
 * @author PengFei
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
	 */
	public void reset() {
		stringBuilder.setLength(0);
	}

}
