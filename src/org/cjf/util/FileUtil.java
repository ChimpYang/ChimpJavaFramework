package org.cjf.util;

import java.io.File;

public class FileUtil {
	
	/**
	 * 删除文件
	 * @param path 含路径的文件名称
	 */
	public static void deleteFile(String path) {
		File file = new File(path);
		if(file.exists()){
			file.delete();
		}
	}
	
	/**
	 * 得到文件名，不包括扩展名
	 * @param filename 没有路径的文件名，含扩展名
	 * @return
	 */
	public static String getFileNameOnly(String filename) {  
        int dot = filename.lastIndexOf('.');   
        if ((dot > -1) && (dot < (filename.length() - 1))) {   
            return filename.substring(0, dot);   
        }   
        
        return "";
    }
	
	/**
	 * 得到扩展名，不包括扩展名，也不包括.
	 * @param filename 没有路径的文件名，含扩展名
	 * @return
	 */
	public static String getExtensionName(String filename) {  
        int dot = filename.lastIndexOf('.');   
        if ((dot > -1) && (dot < (filename.length() - 1))) {   
            return filename.substring(dot+1);   
        }   
        
        return "";
    }  
	
	/**
	 * 得到文件名（含扩展名）
	 * @param path 带路径的文件名
	 * @return
	 */
	public static String getFileNameFromFullPath(String path) {
		String newPath = path.replace("\\", "/");

		int flag = newPath.lastIndexOf('/');
		if ((flag > -1) && (flag < (path.length() - 1))) {
			newPath = path.substring(flag+1);
		} 

		return newPath;
	}
}
