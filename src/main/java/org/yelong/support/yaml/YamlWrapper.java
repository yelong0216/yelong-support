/**
 * 
 */
package org.yelong.support.yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.yaml.snakeyaml.Yaml;

/**
 * @author pengfei<yl1430834495@163.com>
 * @date 2019年11月14日上午9:31:49
 * @version 1.2
 */
public class YamlWrapper {

	private final Yaml yaml;

	public YamlWrapper() {
		this(new Yaml());
	}

	public YamlWrapper(Yaml yaml) {
		this.yaml = yaml;
	}


	public YamlProperties load(String yaml) throws FileNotFoundException {
		return load(new File(yaml));
	}

	public YamlProperties load(File file) throws FileNotFoundException {
		return new DefaultYamlProperties(file.getName(), yaml.load(new FileInputStream(file)));
	}
	
	public YamlProperties load(InputStream is) {
		return new DefaultYamlProperties( yaml.load(is)) ;
	}

	public Yaml getYaml() {
		return yaml;
	}





}
