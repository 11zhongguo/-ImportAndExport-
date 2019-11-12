package com.zkdj.urlCheck.spring_boot_1.main.java.controller;

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

@Controller

@RequestMapping("/api/check")
public class UrlCheckController {
	@Autowired
    private UrlCheckService importService;


	@RequestMapping(value = {"login"})
    public String login() {
		return "addClueList";
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
        int rowIndex = 0;
        ExcelDate data = new ExcelDate();
        data.setName("hello");
        List<String> titles = new ArrayList();
        titles.add(list.get(0).get(0));
        titles.add(list.get(0).get(1));
        titles.add(list.get(0).get(2));
        titles.add(list.get(0).get(3));
        titles.add(list.get(0).get(4));
        titles.add(list.get(0).get(5));
        titles.add(list.get(0).get(6));
        titles.add("状态");
        data.setTitles(titles);
 
        List<List<Object>> rows = new ArrayList();
        for(int i = 0, length = list.size();i<length;i++){
        }
        
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
			    	 List<Object> row = new ArrayList();
			    	 for (String cell : lo) {
			             row.add(cell);
					}
			    	 row.add("有效");
			    	 rows.add(row);
			     }
			     if (state == 403) {
			    	 System.out.println("异常！");
			    	 List<Object> row = new ArrayList();
			    	 for (String cell : lo) {
			             row.add(cell);
					}
			    	 row.add("异常");
			    	 rows.add(row);
			     }
			     if (state == 404 || state==410||state==301||state==302||state==304||state==400||state==401||state==500||state==501||state==502||state==503) {
			    	 System.out.println("URL不可用！");
			    	 List<Object> row = new ArrayList();
			    	 for (String cell : lo) {
			             row.add(cell);
					}
			    	 row.add("无效");
			    	 rows.add(row);
			     }
			     break;
			    }catch (Exception ex) {
			     counts++; 
			     System.out.println("URL不可用，连接第 "+counts+" 次");
			     if (counts==5) {
			    	 System.out.println(urlStr+" ==>异常");
			    	 List<Object> row = new ArrayList();
			    	 for (String cell : lo) {
			             row.add(cell);
					}
			    	 row.add("异常");
			    	 rows.add(row);
						ex.printStackTrace();
				}
			     continue;
			    }
			   }
            //---------------------------end-----------------------
        }
        data.setRows(rows);
        try{
            ExcelUtils.exportExcel(response,name,data);
        }catch (Exception e){
            e.printStackTrace();
        }
        long endTime=System.currentTimeMillis(); //获取结束时间
        System.out.println("程序运行时间："+(endTime-startTime)+"ms");
        return null;
    }
    
    
}
