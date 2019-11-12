package com.zkdj.urlCheck.spring_boot_1.main.java.controller;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.csvreader.CsvReader;
import com.zkdj.urlCheck.spring_boot_1.main.java.service.UrlCheckService;

@Controller

@RequestMapping("/api/province")
public class ProvinceController {
	@Autowired
    private UrlCheckService importService;

	@RequestMapping(value = {"inserttxt"})
    public  String login() {
		try {
			InputStream in=null;
			String path = "D:\\test\\piliangInsertciku\\newfile"; 
			
//			File f=new File("D:\\test\\a.txt");
//	        if (!f.exists()) {
//	            f.mkdirs();
//	        }
//	        FileOutputStream fos1=new FileOutputStream(f);
//			OutputStreamWriter dos1=new OutputStreamWriter(fos1);
			
	    	File file = new File(path); 
	    	File[] files = file.listFiles(); 
	    	Set<String> set = new LinkedHashSet<String>();
	    	
	    	File f=new File("D:\\test\\piliangInsertciku\\newfile\\all.txt");
	    	if (!f.exists()) {
	    		f.createNewFile();
	    	}
	    	FileOutputStream fos1=new FileOutputStream(f);
	    	OutputStreamWriter dos1=new OutputStreamWriter(fos1);
	    	for (File govenfile : files) {
	    		
				in = new FileInputStream(govenfile);
		        List<List<String>> list = importService.getBankListByExcel(in, govenfile.getName());
		        for (int i = 0; i < list.size(); i++) {
		        	List<String> lo = list.get(i);
		        	System.out.println(lo.get(0));
		        	try {
		        		set.add(lo.get(0));
		        		set.add(lo.get(1));
					} catch (Exception e) {
						System.out.println(govenfile.getName());
					}
				}
			}
	    	Iterator<String> iterator = set.iterator();
	    	while (iterator.hasNext()) {
	    		String trim = iterator.next().trim();
	    		if (!trim.contains("LEN")) {
	    			dos1.write(trim+"\r\n");
				}
	    		
	    	}
	    	
	    	dos1.close();
	    	in.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
			
		return "login";
    } 
	
	 
	 
   public static void main(String[] args) {
	   Set<String> list1 = new LinkedHashSet<String>();
	   String filePath  = "D:/test/piliangInsertciku/local/enname.txt" ;
 		String flag="";
 		try { 
           File dest = new File(filePath);
 			InputStreamReader inputReader = new InputStreamReader(new FileInputStream(dest),"UTF-8");
 			BufferedReader bf = new BufferedReader(inputReader);
 			// 按行读取字符串
 			String str;
 			while ((str = bf.readLine()) != null&&str != "") {
 				list1.add(str);
 			}
 			bf.close();
 			inputReader.close();
 		} catch (Exception e) {
 			e.printStackTrace();
 		}
 		String ss = "";
    	try {
    		File f=new File("D:/test/piliangInsertciku/local/new_enword.txt");
    		if (!f.exists()) {
    			f.createNewFile();
    		}
    		FileOutputStream fos1=new FileOutputStream(f);
    		OutputStreamWriter dos1=new OutputStreamWriter(fos1);
    		Iterator<String> iterator = list1.iterator();
	    	while (iterator.hasNext()) {
	    		String trim = iterator.next().trim();
	    		ss +=trim+",";
	    	}
	    	dos1.write(ss+"\r\n");
	    	
	    	dos1.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
    	
    	for (String string : list1) {
			
		}
} 
}
