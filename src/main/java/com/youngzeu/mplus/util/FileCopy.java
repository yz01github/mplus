package com.youngzeu.mplus.util;
import java.io.File;
import java.io.IOException;
import java.util.Collection;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.SuffixFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;
public class FileCopy {
	
	// 源目录,或源文件
	private String sourceDir = "D:\\eclipse_workspace\\shxi\\library";
	// 目标目录
	private String targetDir = "D:\\eclipse_workspace\\shxi\\library\\all_jar_bycode";
	
	public static void main(String[] args) {
		// SuffixFileFilter suffixFileFilter = new SuffixFileFilter(".jar");
		FileCopy fileCfg = new FileCopy();
		
		File file = new File(fileCfg.getSourceDir());
		SuffixFileFilter filter = new SuffixFileFilter(".jar");
		File fileDir = new File(fileCfg.getTargetDir());
		/**
         * targetDir：不要为 null、不要是文件、不要不存在
         * 第二个参数 文件过滤
         *      1）SuffixFileFilter：为文件名后缀过滤器
         *      2）SuffixFileFilter 构造器参数可以是 String、List<String>、String[] 等
         *      3）如果参数为空，则表示不进行过滤，等同于 TrueFileFilter.INSTANCE
         *
         * 第三个参数 目录过滤
         *      TrueFileFilter.INSTANCE：表示迭代获取所有子孙目录
         *      FalseFileFilter.FALSE：表示只获取目标目录下一级，不进行迭代
         */
		Collection<File> listFiles = FileUtils.listFiles(file, filter, TrueFileFilter.INSTANCE);
		
		// 循环copy文件 
		listFiles.forEach(f -> {
			try {
				String filePath = f.getPath() + f.getName();
				System.out.println(filePath + " copy begin...");
				FileUtils.copyFileToDirectory(f, fileDir);
				System.out.println(filePath + " copy end...");
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		System.out.println("copy over!");
	}
	
	public String getSourceDir() {
		return sourceDir;
	}

	public void setSourceDir(String sourceDir) {
		this.sourceDir = sourceDir;
	}

	public String getTargetDir() {
		return targetDir;
	}

	public void setTargetDir(String targetDir) {
		this.targetDir = targetDir;
	}
}
