package com.ahxd.lingyuangou.utils;

import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.DecimalFormat;

/**
 * @fileName FileUtils.java
 * @package com.immomo.momo.android.util
 * @description 文件工具类
 * @author
 * @email 86930007@qq.com
 * @version 1.0
 */
public class FileUtils {
	/**
	 * 判断SD是否可以
	 * 
	 * @return
	 */
	public static boolean isSdcardExist() {
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			return true;
		}
		return false;
	}

	/**
	 * 创建根目录
	 * 
	 * @param path
	 *            目录路径
	 */
	public static void createDirFile(String path) {
		File dir = new File(path);
		if (!dir.exists()) {
			dir.mkdirs();
		}
	}

	/**
	 * 创建文件
	 * 
	 * @param path
	 *            文件路径
	 * @return 创建的文件
	 */
	public static File createNewFile(String path) {
		File file = new File(path);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				return null;
			}
		}
		return file;
	}

	/**
	 * 删除文件夹
	 * 
	 * @param folderPath
	 *            文件夹的路径
	 */
	public static void delFolder(String folderPath) {
		delAllFile(folderPath);
		String filePath = folderPath;
		filePath = filePath.toString();
		File myFilePath = new File(filePath);
		myFilePath.delete();
	}

	/**
	 * 删除文件
	 * 
	 * @param path
	 *            文件的路径
	 */
	public static void delAllFile(String path) {
		File file = new File(path);
		if (!file.exists()) {
			return;
		}
		if (!file.isDirectory()) {
			return;
		}
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (path.endsWith(File.separator)) {
				temp = new File(path + tempList[i]);
			} else {
				temp = new File(path + File.separator + tempList[i]);
			}
			if (temp.isFile()) {
				temp.delete();
			}
			if (temp.isDirectory()) {
				delAllFile(path + "/" + tempList[i]);
				delFolder(path + "/" + tempList[i]);
			}
		}
	}

	/**
	 * 获取文件的Uri
	 * 
	 * @param path
	 *            文件的路径
	 * @return
	 */
	public static Uri getUriFromFile(String path) {
		File file = new File(path);
		return Uri.fromFile(file);
	}

	/**
	 * 换算文件大小
	 * 
	 * @param
	 * @return
	 */
	public static String formatFileSize(long size) {
		DecimalFormat df = new DecimalFormat("#.00");
		String fileSizeString = "未知大小";
		if (size < 1024) {
			fileSizeString="0.00B";
//			fileSizeString = df.format((double) size) + "B";
		} else if (size < 1048576) {
			fileSizeString = df.format((double) size / 1024) + "K";
		} else if (size < 1073741824) {
			fileSizeString = df.format((double) size / 1048576) + "M";
		} else {
			fileSizeString = df.format((double) size / 1073741824) + "G";
		}
		return fileSizeString;
	}

	/* 上传文件至Server的方法 */
	public static StringBuffer uploadFile(String uploadFile) {
		String actionUrl = "http://img.mingjing.cn/UploadHandler.ashx";
		// String actionUrl ="http://192.168.123.112/UploadHandler.ashx";
		StringBuffer b = null;
		String newName = "image.jpg";
		String end = "\r\n";
		String twoHyphens = "--";
		String boundary = "*****";
		try {
			URL url = new URL(actionUrl);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			/* 允许Input、Output，不使用Cache */
			con.setDoInput(true);
			con.setDoOutput(true);
			con.setUseCaches(false);
			/* 设置传送的method=POST */
			con.setRequestMethod("POST");
			/* setRequestProperty */
			// enctype="multipart/form-data"
			con.setRequestProperty("Connection", "Keep-Alive");
			con.setRequestProperty("Charset", "UTF-8");
			con.setRequestProperty("Content-Type",
					"multipart/form-data;boundary=" + boundary);
			/* 设置DataOutputStream */
			DataOutputStream ds = new DataOutputStream(con.getOutputStream());
			ds.writeBytes(twoHyphens + boundary + end);
			ds.writeBytes("Content-Disposition: form-data; "
					+ "name=\"file1\";filename=\"" + newName + "\"" + end);
			ds.writeBytes(end);
			/* 取得文件的FileInputStream */
			FileInputStream fStream = new FileInputStream(uploadFile);
			/* 设置每次写入1024bytes */
			int bufferSize = 1024;
			byte[] buffer = new byte[bufferSize];
			int length = -1;
			/* 从文件读取数据至缓冲区 */
			while ((length = fStream.read(buffer)) != -1) {
				/* 将资料写入DataOutputStream中 */
				ds.write(buffer, 0, length);
			}
			ds.writeBytes(end);
			ds.writeBytes(twoHyphens + boundary + twoHyphens + end);
			/* close streams */
			fStream.close();
			ds.flush();
			/* 取得Response内容 */
			InputStream is = con.getInputStream();
			int ch;
			b = new StringBuffer();
			while ((ch = is.read()) != -1) {
				b.append((char) ch);
			}
			/* 将Response显示于Dialog, ImageUrl|ImageWidth|ImageHeight|ImageMd5 */
			// showDialog("上传成功"+b.toString().trim());
			/* 关闭DataOutputStream */
			ds.close();
			is.close();
			con.disconnect();
			ds.close();
			url=null;
			buffer=null;
		} catch (Exception e) {
			e.printStackTrace();
			// showDialog("上传失败"+e);
		}
		return b;
	}

//	public static long getFileSizes(File f) throws Exception {
//		long s = 0;
//		if (f.exists()) {
//			FileInputStream fis = null;
//			fis = new FileInputStream(f);
//			s = fis.available();
//		} else {
//			f.createNewFile();
//			System.out.println("文件不存在");
//		}
//		return s;
//	}
	/*** 获取文件夹大小 ***/
	public static long getFileSize(File f) throws Exception {
	long size = 0; 
	File flist[] = f.listFiles(); 
	for (int i = 0; i < flist.length; i++) { 
	if (flist[i].isDirectory()) { 
	size = size + getFileSize(flist[i]); 
	} else { 
	size = size + flist[i].length(); 
	} 
	}
	return size;
	} 
	
	
	public static boolean deleteDirectory(String filePath) {
	    boolean flag = false;
	        //如果filePath不以文件分隔符结尾，自动添加文件分隔符
	        if (!filePath.endsWith(File.separator)) {
	            filePath = filePath + File.separator;
	        }
	        File dirFile = new File(filePath);
	        if (!dirFile.exists() || !dirFile.isDirectory()) {
	            return false;
	        }
	        flag = true;
	        File[] files = dirFile.listFiles();
	        //遍历删除文件夹下的所有文件(包括子目录)
	        for (int i = 0; i < files.length; i++) {
	            if (files[i].isFile()) {
	            //删除子文件
	                flag = deleteFile(files[i].getAbsolutePath());
	                if (!flag) break;
	            } else {
	            //删除子目录
	                flag = deleteDirectory(files[i].getAbsolutePath());
	                if (!flag) break;
	            }
	        }
	        if (!flag) return false;
	        //删除当前空目录
	        return dirFile.delete();
	    }
	  public static boolean deleteFile(String filePath) {
		    File file = new File(filePath);
		        if (file.isFile() && file.exists()) {
		        return file.delete();
		        }
		        return false;
		    }
	  /**
	     * 清除WebView缓存
	     */ 
//	    public static void clearWebViewCache(String cacheDirPath){ 
//	        //清理Webview缓存数据库 
//	        try { 
//	        	EasyReportApplication.getInstance().deleteDatabase("webview.db");  
//	        	EasyReportApplication.getInstance().deleteDatabase("webviewCache.db"); 
//	        } catch (Exception e) { 
//	            e.printStackTrace(); 
//	        } 
//	           
//	        //WebView 缓存文件 
//	        File appCacheDir = new File(EasyReportApplication.getInstance().getFilesDir().getAbsolutePath()+"/webviewCache"); 
//	        Log.e("sss", "appCacheDir path="+appCacheDir.getAbsolutePath()); 
//	           
//	        File webviewCacheDir = new File(EasyReportApplication.getInstance().getCacheDir().getAbsolutePath()+"/webviewCache"); 
//	        Log.e("ss", "webviewCacheDir path="+webviewCacheDir.getAbsolutePath());
//	        
//	           
//	        //删除webview 缓存目录 
//	        if(webviewCacheDir.exists()){ 
//	            deleteFile(webviewCacheDir); 
//	        } 
//	        //删除webview 缓存 缓存目录 
//	        if(appCacheDir.exists()){ 
//	            deleteFile(appCacheDir); 
//	        } 
//	    }
	    /**
	     * 递归删除 文件/文件夹
	     * 
	     * @param file
	     */ 
	    public static void deleteFile(File file) { 
	   

	        if (file.exists()) { 
	            if (file.isFile()) { 
	                file.delete(); 
	            } else if (file.isDirectory()) { 
	                File files[] = file.listFiles(); 
	                for (int i = 0; i < files.length; i++) { 
	                    deleteFile(files[i]); 
	                } 
	            } 
	            file.delete(); 
	        } else { 
	        }
	    }
	public static String getPath(final Uri uri) {
		File file= null;
		try {
			file = new File(new URI(uri.toString()));
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return file.getPath();
	}
}
