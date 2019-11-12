package com.zkdj.urlCheck.spring_boot_1.main.java.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.zkdj.urlCheck.spring_boot_1.main.java.service.UrlCheckService;

@Controller

@RequestMapping("check")
public class UrlCheckController2 {
	@Autowired
    private UrlCheckService importService;


	@RequestMapping(value = {"login"})
    public String login() {
		return "login";
    } 
	@RequestMapping(value = {"down"})
	public String down() {
		return "download";
	} 
	
	/**
	 * 上传
	 * @param file
	 * @param request
	 * @return
	 * @throws Exception
	 */
    @SuppressWarnings("unchecked")
    @RequestMapping("upload")
    public String uploadExcel(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws Exception {
    	
    	URL url;
    	HttpURLConnection con;
    	int state = -1;
    	
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

        String urlStr = "";
        if (file.isEmpty()) {
            return "文件不能为空";
        }
        InputStream inputStream = file.getInputStream();
        List<List<String>> list = importService.getBankListByExcel(inputStream, file.getOriginalFilename());
        inputStream.close();

        File f=new File("D:\\test\\a.txt");
        if (!f.exists()) {
            f.mkdirs();
        }
		FileOutputStream fos1=new FileOutputStream(f);
		OutputStreamWriter dos1=new OutputStreamWriter(fos1);
		File f2=new File("D:\\test\\b.txt");
		if (!f2.exists()) {
			f2.mkdirs();
		}
		FileOutputStream fos2=new FileOutputStream(f2);
		OutputStreamWriter dos2=new OutputStreamWriter(fos2);
		File f3=new File("D:\\test\\c.txt");
		if (!f3.exists()) {
			f3.mkdirs();
		}
		FileOutputStream fos3=new FileOutputStream(f3);
		OutputStreamWriter dos3=new OutputStreamWriter(fos3);
        for (int i = 1; i < list.size(); i++) {
            List<String> lo = list.get(i);
            //-------------------------start------------------------
            urlStr=lo.get(6);
            int counts = 0;
			   if (urlStr == null || urlStr.length() <= 0) {                       
			    return null;                 
			   }
			   while (counts < 5) {
			    try {
			     url = new URL(urlStr);
			     urlStr=i+"="+lo.get(1)+url;
			     con = (HttpURLConnection) url.openConnection();
			     state = con.getResponseCode();
			     System.out.println(counts +"= "+state);
			     if (state == 200) {
				      System.out.println("URL可用！");
				      dos1.write(urlStr+" ==>true"+"\n");
			     }
			     if (state == 403) {
			    	 dos3.write(urlStr+" ==>异常"+"\n");
			     }
			     if (state == 404 || state==410||state==301||state==302||state==304||state==400||state==401||state==500||state==501||state==502||state==503) {
			    	 System.out.println("URL不可用！");
			    	 dos2.write(urlStr+" ==>false"+"\n");
			     }
			     break;
			    }catch (Exception ex) {
			     counts++; 
			     System.out.println("URL不可用，连接第 "+counts+" 次");
			     if (counts==5) {
			    	 System.out.println(urlStr+" ==>异常");
						ex.printStackTrace();
						dos3.write(urlStr+" ==>异常"+"\n");
				}
			     continue;
			    }
			   }
            //---------------------------end-----------------------
        }
        dos1.close();
        dos2.close();
        dos3.close();
        return "download";
    }
    
    
    /**
     * 文件下载
     * @param request
     * @param response
     * @param fileName
     * @param file
     */
        @RequestMapping("downloadFile")
        public static void downloadFile(HttpServletRequest request, HttpServletResponse response,String fileName) {
            BufferedInputStream bis = null;
            BufferedOutputStream bos = null;
            String localpath = "D:/test";
            String path="";
            	 path= localpath + "/" + fileName;
            try {
            	File file = ResourceUtils.getFile(path);
                if (!file.exists()) {
                    throw new RuntimeException("文件不存在！");
                }
                if (!file.isFile()) {
                    throw new RuntimeException("非文件类型！");
                }

                String encodedFileName = new String(fileName.getBytes(), "ISO-8859-1");
                if (isIE(request)) {
                    encodedFileName = URLEncoder.encode(fileName, "UTF-8");
                }
                response.reset();
                response.setContentType("application/octet-stream");
                response.setHeader("content-disposition", "attachment; filename=" + encodedFileName);

                byte[] buffer = new byte[4096];
                bis = new BufferedInputStream(new FileInputStream(file));
                bos = new BufferedOutputStream(response.getOutputStream());
                int length = -1;
                while ((length = bis.read(buffer, 0, buffer.length)) != -1) {
                    bos.write(buffer, 0, length);
                }
                bos.flush();

            }catch (IOException e){
                e.printStackTrace();
                try{ //request.getCOntextPath()获取项目名，这个必须加上，不然找不到对应path的方法。 
                	response.sendRedirect(request.getContextPath()+"/check/login"); 
                }catch(IOException e1){ 
                	e1.printStackTrace();
                }
            }finally {
            }
            close(bis);
            close(bos);
        }

        private static void close(InputStream bis) {
            try {
                if(bis != null){
                    bis.close();
                }
            } catch (IOException e) {
            	e.printStackTrace();
            }
        }
    
        private static void close(OutputStream out) {
            try {
                if(out != null){
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        /**
         * 判断是否是IE浏览器
         *
         * @param request
         * @return
         */
        private static  boolean isIE(HttpServletRequest request) {
            String userAgent = request.getHeader("User-Agent").toUpperCase();
            if (userAgent.indexOf("MSIE") != -1) {
                return true;
            } else if (userAgent.indexOf("GECKO") != -1 && userAgent.indexOf("RV:11") != -1) {
                //IE11 浏览器
                return true;
            } else if (userAgent.indexOf("EDGE") != -1) {
                //微软的EDGE浏览器
                return true;
            } else {
                return false;
            }
        }
}
