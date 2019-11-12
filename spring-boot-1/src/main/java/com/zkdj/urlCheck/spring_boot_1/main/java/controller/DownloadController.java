package com.zkdj.urlCheck.spring_boot_1.main.java.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.zkdj.urlCheck.spring_boot_1.main.java.model.ExcelDate;
import com.zkdj.urlCheck.spring_boot_1.main.java.service.UrlCheckService;
import com.zkdj.urlCheck.spring_boot_1.main.java.utils.ExcelUtils;
import com.zkdj.urlCheck.spring_boot_1.main.java.utils.FileUtils;
import com.zkdj.urlCheck.spring_boot_1.main.java.utils.UnZipFile;

@Controller

@RequestMapping("/api/download")
public class DownloadController {
	@Autowired
    private UrlCheckService importService;


	@RequestMapping(value = {"login"})
    public String login() {
		return "login";
    } 
	
	/**
	 * 检测、判断、Excel下载
	 * @param file
	 * @param request
	 * @return
	 * @throws Exception
	 */
    @SuppressWarnings("unchecked")
    @RequestMapping("upload")
    public String uploadExcel(@RequestParam("file") MultipartFile file, HttpServletRequest request,HttpServletResponse response) throws Exception {
    	
    	long startTime=System.currentTimeMillis();   //获取开始时间
    	URL url;
    	HttpURLConnection con;
    	int state = -1;
    	
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

        String urlStr = "";
        if (file.isEmpty()) {
            return "文件不能为空";
        }
        InputStream inputStream = file.getInputStream();
        String originalFilename = file.getOriginalFilename();
        String[] split = originalFilename.split("\\.");
        String name = split[0];
        List<List<String>> list = importService.getBankListByExcel(inputStream, originalFilename);
        inputStream.close();

        /////////////////////////////////////////////////////////////////////
        List<List<Object>> rows = new ArrayList();
        for(int i = 0, length = list.size();i<length;i++){
        }
        
        for (int i = 1; i < list.size(); i++) {
            List<String> lo = list.get(i);
            //-------------------------start------------------------
            urlStr=lo.get(0);
            String xian =lo.get(1);
            String sheng =lo.get(2);
            String filename = "";
            if (xian.isEmpty()) {
            	xian =xian+i;
			}
            filename=sheng+"-"+xian+".zip";
            FileUtils.downLoadFromUrl(urlStr, filename, "D:/test/webDownload/");
        }
        long endTime=System.currentTimeMillis(); //获取结束时间
        System.out.println("程序运行时间："+(endTime-startTime)+"ms");
        return null;
    }
    
    
    //解压文件夹中的zip文件
    @RequestMapping("unZipFile")
    public String unZipFile(){
    	String str = "";
    	String path = "D:/test/webDownload3/"; 
    	File file = new File(path); 
    	File[] files = file.listFiles(); 
    	if(files.length == 0){ 
    		System.out.println("This package is empty"); 
    		}else{
    			StringBuilder stb  = new StringBuilder();
    			for(File zip:files){ 
    						System.out.println(zip.getName()); 
    						try {
								String unZip = UnZipFile.unZip(zip, "D:/test/webzipover/", "");
								stb.append(unZip);
								str=stb.toString();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
    				} 
    			}
    	System.out.println(str);
		return str;
    }
    
}
