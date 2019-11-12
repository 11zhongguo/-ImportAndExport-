package com.zkdj.urlCheck.spring_boot_1.main.java.controller;



import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.zkdj.urlCheck.spring_boot_1.main.java.model.GatherRulesSource;
import com.zkdj.urlCheck.spring_boot_1.main.java.model.GovernmentUnit;
import com.zkdj.urlCheck.spring_boot_1.main.java.service.GovernmentUnitService;
import com.zkdj.urlCheck.spring_boot_1.main.java.utils.BaseController;
import com.zkdj.urlCheck.spring_boot_1.main.java.utils.JSONResponse;
import com.zkdj.urlCheck.spring_boot_1.main.java.utils.PagingInfo;

@Controller

@RequestMapping("/gather")
public class GatherRulesSourceController extends BaseController{
	@Autowired
    private  GovernmentUnitService governmentUnitService;

	
	@RequestMapping(value = {"login"})
    public String login() {
		return "login";
//		return "addClueList";
    } 
	
	

	@RequestMapping(value = {"findBaMain"})
	@ResponseBody
	public  JSONResponse findBaMain(Integer pageIndex,Integer pageSize) {
		PagingInfo pagingInfo = new PagingInfo();
		pagingInfo.setPageSize(pageSize);
		pagingInfo.setPageIndex(pageIndex);
		PagingInfo findGovernmentUnit = governmentUnitService.findBaMain(pagingInfo);
		return this.success(findGovernmentUnit);
	} 
	
	
	@RequestMapping(value = {"findData"})
	@ResponseBody
	public  JSONResponse findData(@RequestBody PagingInfo pagingInfo) {
		PagingInfo findGovernmentUnit = governmentUnitService.findGovernmentUnit(pagingInfo);
		return this.success(findGovernmentUnit);
	} 
	
	
	
	//拷贝单位名称到source表
	@RequestMapping(value = {"findGover"})
	public  String findGover() {
		List<GatherRulesSource> listGa = new ArrayList<>();
		List<GovernmentUnit> findGovernmentUnit = governmentUnitService.findGovernmentUnit();
		List<String> list = new ArrayList<>();
		for (GovernmentUnit governmentUnit : findGovernmentUnit) {
			GatherRulesSource grs = new GatherRulesSource();
			grs.setCompetentUnits(governmentUnit.getCompetentUnits());
			grs.setIndexUrl(governmentUnit.getBriefUrl());
			grs.setLevel(governmentUnit.getLevel());
			grs.setRegularName(governmentUnit.getWebsite());
			listGa.add(grs);
		}
		governmentUnitService.insertRulesSource(listGa);
		return "login";
	} 
	
	
	/**t_gather_rules_source
	  * 提取url中的根域名
	  * 
	  * @param url
	  * @return
	  */
	 public static String getUrlRoot(String url) {
		 String host ="";
		 if (url.contains("http://")||url.contains("https://")) {
			 try {
				 url = url.replace("?", "");
				   URL u = new URL(url.toLowerCase());
				    host = u.getHost();
//				   StringBuffer root = new StringBuffer();
//				   String[] rootMembers = host.split("\\.");
//
//				   // 一般情况下，取host的最后2个，如www.abc.com取abc.com
//				   int n = 2;
//
//				   // 判断TLD后缀是否超过1个，比如edu.cn，如果是，则判断域名倒数第2个后缀是否是.com等（.com.cn形式）
//				   if (rootMembers.length > 2) {
//				    String secondLastMember = rootMembers[rootMembers.length - 2];
//				    if (secondLastMember.matches("^com$|^net$|^gov$|^edu$|^co$|^org$"))
//				     n = 3;
//				   }
//
//				   // 确定了n的取数后，将结果保存到StringBuffer中，保存顺序是自左至右
//				   for (int i = n; i > 0; i--) {
//				    try {
//				     root.append(rootMembers[host.split("\\.").length - i]);
//				     if ((i - 1) > 0)
//				      root.append(".");
//				    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
//				     System.out.println("Wrong Url:" + url);
//				    }
//				   }
				   return host;
				  } catch (Exception e) {
				   e.printStackTrace();
				  }
		}
	  return url;
	 }
	
   public static void main(String[] args) {
	   
} 
}
