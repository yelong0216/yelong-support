/**
 * 
 */
package org.yelong.support.xml.dom;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * 文档工具类
 * 
 * @since 2.1.2
 */
public final class Documents {

	private Documents() {
	}

	/**
	 * 解析XML文档
	 * 
	 * @param file XML文件
	 * @return XML文档对象
	 */
	public static Document parseDocument(File file) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		return db.parse(file);
	}

	/**
	 * 解析XML文档
	 * 
	 * @param file XML文件
	 * @return XML文档对象
	 */
	public static Document parseDocument(InputStream is)
			throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		return db.parse(new InputSource(is));
	}

}
