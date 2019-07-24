package com.youngzeu.file;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

public class TraverseDemo {

	private String path = "E:/qycache";
	
	private List<FileInfo> list = new ArrayList<>();

	@Test
	public void test() {
		traverseTest(path);
		FileInfo[] array = list.toArray(new FileInfo[list.size()]);
		FileInfo temp;
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array.length - 1 - i; j++) {
				if (needSwap(array[j], array[j + 1])) {
					temp = array[j];
					array[j] = array[j + 1];
					array[j + 1] = temp;
				}
			}
		}
		print(array);
	}
	
	private void print(FileInfo[] array) {
		for (FileInfo fileInfo : array) {
			System.out.println(fileInfo);
		}
	}

	public void traverseTest(String filePath) {
		File file = new File(filePath);
		if (!file.exists()) {
			return;
		}
		File[] files = file.listFiles();
		if (null == files || files.length == 0) {
			return;
		}
		
		for (File f : files) {
			if (f.isDirectory()) {
				traverseTest(f.getAbsolutePath());
			} else {
				Date date = new Date(f.lastModified());
				list.add(new FileInfo(f.getAbsolutePath(), date));
			}
		}
	}
	
	public boolean needSwap(FileInfo f1, FileInfo f2) {
		//f1 < f2 ==> 返回-1
		int needSwap = f1.getFileUpdateTime().compareTo(f2.getFileUpdateTime());
		return needSwap == -1;
	}
}

class FileInfo {
	private String filePath; 
	private Date fileUpdateTime;
	
	public FileInfo(String filePath, Date fileUpdateTime) {
		super();
		this.filePath = filePath;
		this.fileUpdateTime = fileUpdateTime;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public Date getFileUpdateTime() {
		return fileUpdateTime;
	}
	public void setFileUpdateTime(Date fileUpdateTime) {
		this.fileUpdateTime = fileUpdateTime;
	}
	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = sdf.format(fileUpdateTime);
		return "FileInfo [filePath=" + filePath + ", fileUpdateTime=" + date + "]";
	} 
	
}