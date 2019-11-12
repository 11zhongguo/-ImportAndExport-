package com.zkdj.urlCheck.spring_boot_1.main.java.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zkdj.urlCheck.spring_boot_1.main.java.service.GovernmentUnitService;
import com.zkdj.urlCheck.spring_boot_1.main.java.utils.BaseController;
import com.zkdj.urlCheck.spring_boot_1.main.java.utils.ExportTxt;
import com.zkdj.urlCheck.spring_boot_1.main.java.utils.JSONResponse;
import com.zkdj.urlCheck.spring_boot_1.main.java.utils.WorkThread;
/**
 * @author Administrator
   *   批量导入word表（导入实体词前步）
 */
@Controller
@RequestMapping("/api/insert")
public class WordController  extends BaseController{

	@Autowired
    private   GovernmentUnitService governmentUnitService;
	
	@RequestMapping(value = {"insertWord"})
	@ResponseBody
	public JSONResponse insertWord() {
		Set<String> list1 = new LinkedHashSet<String>();
		Long startTime=System.currentTimeMillis();
  		String filePath  = "D:/test/piliangInsertciku/local/port-1.txt" ;
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
  		
  		//wordList ：保存词库中不存在的词  不存在则保存入词库
  		List<String> wordList = new ArrayList<>();
  		List<String> findWordName =new ArrayList<>();
  		try {
			
  			 findWordName = governmentUnitService.findWordName();
		} catch (Exception e) {
			e.printStackTrace();
		}
  		for (String word : list1) {
			if (!findWordName.contains(word)) {
				wordList.add(word);
			}
		}
  		if (wordList.size()<1) {
  			return this.success("数据为空！");
		}
  		//导出去重后词名文件
  		ExportTxt.exportTxt(wordList);
  		
		List<Thread> list=new ArrayList<Thread>();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss:SS");   
		TimeZone t = sdf.getTimeZone();   
		t.setRawOffset(0);   
		sdf.setTimeZone(t); 
		for(int i=0;i<10;i++){
			Thread g=new Thread(new WorkThread(i,wordList.size()/10,wordList)," "+i);
			g.start();
			list.add(g);
		}
		try{
			for(Thread thread:list)
			thread.join();
		}catch(InterruptedException e){
			e.printStackTrace();
		}
			Long endTime=System.currentTimeMillis();
			System.out.println("总共用时: "+sdf.format(new Date(endTime-startTime)));
			return this.success("操作成功！");
	}
		
}
