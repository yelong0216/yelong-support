/**
 * 
 */
package org.yelong.support.tools.zip.ant;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Expand;
import org.apache.tools.ant.taskdefs.Zip;
import org.apache.tools.ant.types.FileSet;

/**
 * @author PengFei
 * @since 1.0.3
 */
public class ZipUtils {
	
	/**
	 * 压缩文件
	 * @param filePathList 文件路径集合 。支持目录和文件
	 * @param zipFilePath 压缩包文件路径
	 */
	public static void zipFileP(List<String> filePaths , String zipFilePath) {
		List<File> files = filePaths.stream().map(File::new).collect(Collectors.toList());
		zipFile(files, zipFilePath);
	}
	
	/**
	 * 压缩文件
	 * @param files 文件集合
	 * @param zipFilePath 压缩文件路径
	 */
	public static void zipFile(List<File> files , String zipFilePath) {
		if( null == files || files.isEmpty() || StringUtils.isEmpty(zipFilePath)) {
			throw new NullPointerException();
		}
		Project prj = new Project();
		Zip zip = new Zip();
		zip.setProject(prj);
		zip.setDestFile(new File(zipFilePath));
		for (File file : files) {
			if( !file.exists() ) {
				continue;
			}
			FileSet fileSet = new FileSet();
			fileSet.setProject(prj);
			if( file.isDirectory() ) {
				fileSet.setDir(file);
			} else {
				fileSet.setFile(file);
			}
			zip.addFileset(fileSet);
		}
		zip.execute();
	}

	/**
	 * 解压zipFile文件。解压生成的文件在zipFile同级
	 * @param zipFile
	 * @throws FileNotFoundException
	 */
	public static void unZipFile(File zipFile) throws FileNotFoundException {
		if( null == zipFile ) {
			throw new NullPointerException();
		}
		if( !zipFile.exists() ) {
			throw new FileNotFoundException();
		}
		unZipFile(zipFile, zipFile.getParentFile().getAbsolutePath());	
	}

	/**
	 * 解.zip类型的压缩文件
	 * @param zipFile zip文件路径
	 * @param unFilePath 解压到的路径位置
	 * @throws FileNotFoundException 如果zipFilePath文件不存在
	 */
	public static void unZipFile(File zipFile , String unFilePath) throws FileNotFoundException {
		if( null == zipFile || StringUtils.isEmpty(unFilePath)) {
			throw new NullPointerException();
		}
		if( !zipFile.exists() ) {
			throw new FileNotFoundException();
		}
		Project project = new Project();
		Expand expand = new Expand();
		expand.setProject(project);
		expand.setSrc(zipFile);
		expand.setDest(new File(unFilePath));
		expand.execute();
	}

	public static void main(String[] args) throws FileNotFoundException {
		zipFileP(Arrays.asList("D:\\labbol\\工作日志"), "D:\\labbol\\工作日志.zip");
	}
	
}
