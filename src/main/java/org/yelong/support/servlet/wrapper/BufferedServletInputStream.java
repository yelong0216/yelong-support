/**
 * 
 */
package org.yelong.support.servlet.wrapper;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;

/**
 * @since 1.0
 */
public class BufferedServletInputStream extends ServletInputStream {

	private ByteArrayInputStream inputStream;

	public BufferedServletInputStream(byte[] buffer) {
		this.inputStream = new ByteArrayInputStream(buffer);
	}

	@Override
	public int available() throws IOException {
		return inputStream.available();
	}

	@Override
	public boolean isFinished() {
		return inputStream.available() == 0;
	}

	@Override
	public boolean isReady() {
		return true;
	}

	@Override
	public void setReadListener(ReadListener readListener) {

	}

	@Override
	public int read() throws IOException {
		return inputStream.read();
	}

}
