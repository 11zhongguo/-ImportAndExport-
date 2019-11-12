package com.zkdj.urlCheck.spring_boot_1.main.java.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.apache.commons.codec.binary.Base64;


public class UnZipFile {

	

	/**
	 * 解压文件到指定目录 解压后的文件名，和之前一致
	 * 
	 * @param zipFile
	 *            待解压的zip文件
	 * @param descDir
	 *            指定目录
	 */
	public static String unZip(File zipFile, String descDir, String tiff2Jpg) throws IOException {
		String sb = "";
		List<BenFile> list = new ArrayList<BenFile>();
		ZipFile zip = null;
		try {
			zip = new ZipFile(zipFile, Charset.forName("GBK"));// 解决中文文件夹乱码
			File pathFile = new File(descDir);
			if (!pathFile.exists()) {
				pathFile.mkdirs();
			}
			ZipEntry entry = null;
			String zipFileName = null;
			String newname = null;
			String newpath = null;
			for (Enumeration<? extends ZipEntry> entries = zip.entries(); entries.hasMoreElements();) {

				entry = null;
				try {
					entry = (ZipEntry) entries.nextElement();
					InputStream in = zip.getInputStream(entry);
					if (entry.isDirectory()) {
						continue;
					}
					zipFileName = entry.getName();
					zipFileName = zipFileName.trim().toLowerCase();
					if (!isSuperFileFmt(zipFileName)) {
						continue;
					}
					newname =  zipFileName;
					newname =  zipFile.getName().split("\\.")[0]+".csv";
					newpath = descDir + newname;
					// 输出文件路径信息
					FileOutputStream os = null;
					File out = new File(newpath);
					try {
						if (!out.exists()) {
							if (!out.getParentFile().exists()) {// 相对路径可能多级，可能需要创建父目录.
								out.getParentFile().mkdirs();
							}
							out.createNewFile();
						}
						os = new FileOutputStream(newpath);
						byte[] buf1 = new byte[1024];
						int len;
						while ((len = in.read(buf1)) > 0) {
							os.write(buf1, 0, len);
						}
						
						if (in != null)
							in.close();
						if (os != null)
							os.close();
						//tif格式文件
						if(newname.endsWith(".tif")){
							try{
								ConvertTifToJpg(newpath,newpath.replace(".tif", ".jpg"),tiff2Jpg);
								newname=newname.replace(".tif", ".jpg");
								zipFileName=newname;
								out.delete();
							}catch(Exception ex){
								ex.printStackTrace();
							}
						}
						list.add(new BenFile(zipFileName, newname , list.size()+1));
					} catch (Exception ex) {
						System.err.println("******************解压********************" + zipFile.getName());
						ex.printStackTrace();
					} finally {
						if (in != null)
							in.close();
						if (os != null)
							os.close();
					}

				} catch (Exception ex) {
					System.err.println("******************解压********************" + zipFile.getName());
					ex.printStackTrace();
				}
			}
		} catch (Exception ex) {
			sb=zipFile.getName();
			System.err.println("******************解压********************" + zipFile.getName());
			ex.printStackTrace();
		}finally {
			
			if (zip != null)
				zip.close();
		}
		System.out.println("******************解压完毕********************" + zipFile.getName());
		return sb;
	}
	 

	public static boolean isSuperFileFmt(String file) {
		file = file.toLowerCase().trim();
		if (file.endsWith(".jpg") ||
				file.endsWith(".jpeg") ||
				file.endsWith(".gif") ||
				file.endsWith(".bmp") ||
				file.endsWith(".tif") ||
				file.endsWith(".csv") ||
				file.endsWith(".png") ||
				file.endsWith(".pdf") ) {
			return true;
		}
		return false;
	}

	/**
	 * 将图片转换成Base64编码
	 * 
	 * @param imgFile
	 *            待处理图片
	 * @return
	 */
	public static String getImgStr(String imgFile) {
		// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
		InputStream in = null;
		byte[] data = null;
		// 读取图片字节数组
		try {
			in = new FileInputStream(imgFile);
			data = new byte[in.available()];
			in.read(data);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new String(Base64.encodeBase64(data));
	}
	
	/**
	 * 将tif转换为jpg
	 * @param ogrFile
	 * @param newFile
	 * @throws Exception
	 */
	public static void ConvertTifToJpg(String ogrFile,String newFile, String tiff2Jpg) throws Exception{

			if(ogrFile.endsWith(".tif")){
		        Runtime run = Runtime.getRuntime();
		        try {
		            String parm=tiff2Jpg+" \"" + ogrFile+"\" \""+newFile+"\" true";
		            Process process = run.exec(parm);
		            process.waitFor();
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
			}
			
		
		
	}
	
	
	/**
	 * 解压结果
	 * @author gq
	 *
	 */
	public static class BenFile {
		public BenFile(String orgFile, String newFile, Integer pageNum) {
			this.orgFile = orgFile;
			this.newFile = newFile;
			this.pageNum = pageNum;
		}

		public BenFile() {
		}
		/**
		 * 原文件
		 */
		private String orgFile;
		/**
		 * 新文件名
		 */
		private String newFile;
		
		/**
		 * 索引
		 * @return
		 */
		private Integer pageNum;
		
		/**
		 * dossierId
		 */
		
		/**
		 * 卷宗id
		 * @return
		 */
		private Long dossierId;
		
		public Integer getPageNum() {
			return pageNum;
		}

		public void setPageNum(Integer pageNum) {
			pageNum = pageNum;
		}

		public String getOrgFile() {
			return orgFile;
		}

		public void setOrgFile(String orgFile) {
			this.orgFile = orgFile;
		}

		public String getNewFile() {
			return newFile;
		}

		public void setNewFile(String newFile) {
			this.newFile = newFile;
		}

	}
	
	// 测试
	/*public static void main(String[] args) {
		try {
			List<BenFile> list = UnZipFile.unZipFiles("D:\\标准的卷内文书目录\\picture\\测试卷宗\\结果\\二级文件.zip", "D:\\标准的卷内文书目录\\picture\\测试卷宗\\结果5","");
			for (BenFile benFile : list) {
				System.out.println("----"+benFile.getNewFile());
				System.out.println("----"+benFile.getOrgFile());
			}
			
		} catch (Exception e) {
			e.printStackTrace(); 
		}
		
	}*/
	
//	public static void getFile(){
//		/*File file = new File("D:\\标准的卷内文书目录\\picture\\测试卷宗\\结果");
//		List<File> list = Arrays.asList(file.listFiles());
//		  
//		Collections.sort(list, new Comparator<File>() {
//		    @Override
//		    public int compare(File o1, File o2) {
//		        if (o1.isDirectory() && o2.isFile())
//		            return -1;
//		        if (o1.isFile() && o2.isDirectory())
//		            return 1;
//		        return o2.getName().compareTo(o1.getName())>0?-1:1;
//		    }
//		});
//		for (File file2 : list) {
//			System.out.println("结果："+file2.getName());
//		}*/
//		
//		int i = "侦查卷宗_页面".compareTo("检察卷宗_目录");
//		System.out.println(i);
//		 
//	}
	
	
}