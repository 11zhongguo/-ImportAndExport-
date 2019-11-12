package com.zkdj.urlCheck.spring_boot_1.main.java.controller;



import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.csvreader.CsvReader;
import com.demo.analysis_.Analysis;
import com.zkdj.urlCheck.spring_boot_1.main.java.mapper.GovernmentUnitMapper;
import com.zkdj.urlCheck.spring_boot_1.main.java.model.GatherRulesSource;
import com.zkdj.urlCheck.spring_boot_1.main.java.model.GovernmentUnit;
import com.zkdj.urlCheck.spring_boot_1.main.java.model.SearchEngineSource;
import com.zkdj.urlCheck.spring_boot_1.main.java.service.GovernmentUnitService;

@Controller

@RequestMapping("/api/government")
public class GovernmentUnitController {
	@Autowired
    private  GovernmentUnitService governmentUnitService;
	@Autowired
    private  GovernmentUnitMapper governmentUnitMapper;


	@RequestMapping(value = {"insert"})
    public  String login() {
		List<GovernmentUnit> list = new ArrayList<>();
		try {
			InputStream in=null;
			String path = "D:/test/webzipover/"; 
			
	    	File file = new File(path); 
	    	File[] files = file.listFiles(); 
	    	for (File govenfile : files) {
			
				in = new FileInputStream(govenfile);
				CsvReader cr = new CsvReader(in, ',', Charset.forName("GBK")); 
	    		cr.readHeaders(); 
	    		String[] headers = cr.getHeaders(); 
	    		System.out.println(headers.length); 
	    		if(headers.length != 3){ 
	    			System.out.println("错误"); 
	    			} 
	    		while(cr.readRecord()){ 
	    			GovernmentUnit governmentUnit = new GovernmentUnit();
	    			governmentUnit.setWebsite(cr.get(2));
	    			governmentUnit.setHomePageUrl(cr.get(3));
	    			governmentUnit.setUnit(cr.get(4));
	    			governmentUnit.setWebState(cr.get(5));
	    			String urlRoot = getUrlRoot(cr.get(3));
	    			governmentUnit.setBriefUrl(urlRoot);
	    			String name = govenfile.getName();
	    			name= name.split("\\.")[0].replace("-", "").replaceAll("\\d+", "");
	    			governmentUnit.setUnitCity(name);
	    			list.add(governmentUnit);
	    			} 
    		
			}
	    	in.close();
			governmentUnitService.insertGovernmentUnit(list);
		} catch (Exception e) {
			e.printStackTrace();
		} 
			
		return "login";
    } 
	
	@RequestMapping(value = {"importdata"})
	public  String importdata() {
		try {
			InputStream in=null;
			String path = "D:/test/webnametest/import/"; 
			File file = new File(path); 
			File[] files = file.listFiles(); 
			for (File govenfile : files) {
				
				in = new FileInputStream(govenfile);
				CsvReader cr = new CsvReader(in, ',', Charset.forName("GBK")); 
				cr.readHeaders(); 
				String[] headers = cr.getHeaders(); 
				System.out.println(headers.length); 
				if(headers.length != 3){ 
					System.out.println("错误"); 
				} 
				String oldname = "";
				while(cr.readRecord()){ 
					oldname=cr.get(4);
				} 
				in.close();
				int indexOf = oldname.indexOf("州");
				String name = govenfile.getName();
				String wei="";
				String newname="";
				String addname="";
				if (indexOf>0) {
					 if(name.indexOf(".")>=0)
					  {
						 addname = oldname.substring(0,(oldname.indexOf("州")+1));
						 newname = name.substring(0,name.lastIndexOf("."));
						 wei = name.substring(name.lastIndexOf("."),name.length());
					  }
					 File newFile = new File(file,newname+addname+wei);
					 govenfile.renameTo(newFile);
				}else if(oldname.indexOf("旗")>0){
					 if(name.indexOf(".")>=0)
					  {
						 addname = oldname.substring(0,(oldname.indexOf("旗")+1));
						 newname = name.substring(0,name.lastIndexOf("."));
						 wei = name.substring(name.lastIndexOf("."),name.length());
					  }
					 File newFile = new File(file,newname+addname+wei);
					 govenfile.renameTo(newFile);
				}else if(oldname.indexOf("县")>0){
					 if(name.indexOf(".")>=0)
					  {
						 addname = oldname.substring(0,(oldname.indexOf("县")+1));
						 newname = name.substring(0,name.lastIndexOf("."));
						 wei = name.substring(name.lastIndexOf("."),name.length());
					  }
					 File newFile = new File(file,newname+addname+wei);
					 govenfile.renameTo(newFile);
				}else if(oldname.indexOf("盟")>0){
					 if(name.indexOf(".")>=0)
					  {
						 addname = oldname.substring(0,(oldname.indexOf("盟")+1));
						 newname = name.substring(0,name.lastIndexOf("."));
						 wei = name.substring(name.lastIndexOf("."),name.length());
					  }
					 File newFile = new File(file,newname+addname+wei);
					 govenfile.renameTo(newFile);
				}else if(oldname.indexOf("区")>0){
					 if(name.indexOf(".")>=0)
					  {
						 addname = oldname.substring(0,(oldname.indexOf("区")+1));
						 newname = name.substring(0,name.lastIndexOf("."));
						 wei = name.substring(name.lastIndexOf("."),name.length());
					  }
					 File newFile = new File(file,newname+addname+wei);
					 govenfile.renameTo(newFile);
				}else if(oldname.indexOf("市")>0){
					 if(name.indexOf(".")>=0)
					  {
						 addname = oldname.substring(0,(oldname.indexOf("市")+1));
						 newname = name.substring(0,name.lastIndexOf("."));
						 wei = name.substring(name.lastIndexOf("."),name.length());
					  }
					 File newFile = new File(file,newname+addname+wei);
					 govenfile.renameTo(newFile);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return "login";
	} 
	
	
	@RequestMapping(value = {"findGover"})
	public  String findGover() {
		List<GovernmentUnit> findGovernmentUnit = governmentUnitService.findGovernmentUnit();
		List<String> list = new ArrayList<>();
		for (GovernmentUnit governmentUnit : findGovernmentUnit) {
			String urlRoot = getUrlRoot(governmentUnit.getHomePageUrl());
			list.add(urlRoot);
		}
		return "login";
	} 
	
	@RequestMapping(value = {"replaceGoverUnit"})
	public  String replaceGoverUnit() {
		List<GovernmentUnit> findGovernmentUnit = governmentUnitService.findGovernmentUnitAll();
//		List<String> list = new ArrayList<>();
		for (GovernmentUnit governmentUnit : findGovernmentUnit) {
			String unit = governmentUnit.getUnitName();
			int length = unit.length();
//			if (unit.indexOf("州")>0) {
//				unit=unit.substring(unit.indexOf("州")+1,length);
//			}else if (unit.indexOf("旗")>0) {
//				unit=unit.substring(unit.indexOf("旗")+1,length);
//			}else if (unit.indexOf("县")>0) {
//				unit=unit.substring(unit.indexOf("县")+1,length);
//			}else if (unit.indexOf("盟")>0) {
//				unit=unit.substring(unit.indexOf("盟")+1,length);
//			}else if (unit.indexOf("区")>0) {
//				unit=unit.substring(unit.indexOf("区")+1,length);
//			}else if (unit.indexOf("市")>0) {
//				unit=unit.substring(unit.indexOf("市")+1,length);
//			}
			if (unit.indexOf("开发区")==-1) {
				if (unit.indexOf("区")>=0) {
					unit=unit.substring(unit.indexOf("区")+1,length);
				}
			}
			governmentUnit.setUnitName(unit);
			
		}
		governmentUnitMapper.GovernmentUnit(findGovernmentUnit);
		return "login";
	} 
	
	/**
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
	
	 /**根据属地信息定位属地信息*/
	 @RequestMapping(value = {"findSource"})
		public  String findSource() {
			List<GatherRulesSource> fsList = governmentUnitService.findGatherRulesSource();
			for (GatherRulesSource fs : fsList) {
				if (fs.getAttributionInformation() !=null&&fs.getAttributionInformation() !="全国") {
					
					Map<String, String> address_ = Analysis.address_(fs.getAttributionInformation());
					if (address_.get("province") !=null) {
						String province=address_.get("province");
						fs.setProvince(province);
					}
					if (address_.get("city") !=null) {
						String city=address_.get("city");
						fs.setCity(city);
					}
					if (address_.get("county") !=null) {
						String district =address_.get("county");
						fs.setDistrict(district);
					}
					governmentUnitMapper.updateINRulesSource(fs);
				}
			}
/*			for (GatherRulesSource fs : fsList) {
				if (fs.getRegularName() !=null) {
					
					Map<String, String> address_ = Analysis.address_(fs.getRegularName());
					String defaultAddress = "全国";
					if (address_.get("province") !=null) {
						defaultAddress=address_.get("province");
					}
					if (address_.get("city") !=null) {
						defaultAddress +=address_.get("city");
					}
					if (address_.get("county") !=null) {
						defaultAddress +=address_.get("county");
					}
					fs.setAttributionInformation(defaultAddress);
					governmentUnitMapper.updateINRulesSource(fs);
				}
			}
*///			governmentUnitMapper.updateRulesSource(fsList);
			try {
//				List<GatherRulesSource> list = getMap(fs,3);
				//批量插入
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "login";
		} 
	 /**根据网站名导入属地和省市县*/
	 @RequestMapping(value = {"updateSourceAddress"})
	 public  String updateSourceAddress() {
		 List<GatherRulesSource> fsList = governmentUnitService.findGatherRulesSource();
		 for (GatherRulesSource fs : fsList) {
			 if (fs.getRegularName() !=null&&fs.getRegularName() !="") {
				 String s ="";
				 Map<String, String> address_ = Analysis.address_(fs.getRegularName());
				 if (address_.get("province") !=null) {
					 String province=address_.get("province");
					 fs.setProvince(province);
					 s +=province;
				 }
				 if (address_.get("city") !=null) {
					 String city=address_.get("city");
					 fs.setCity(city);
					 s +=city;
				 }
				 if (address_.get("county") !=null) {
					 String district =address_.get("county");
					 fs.setDistrict(district);
					 s +=district;
				 }
				 fs.setAttributionInformation(s);
				 governmentUnitMapper.updateINRulesSource(fs);
			 }
		 }
		 return "login";
	 } 
	 //根据网站名导入属地和省市县
	 @RequestMapping(value = {"updateSearchSourceAddress"})
	 public  String updateSearchSourceAddress() {
		 List<SearchEngineSource> fsList = governmentUnitService.findSearchEngineSource();
		 for (SearchEngineSource fs : fsList) {
			 if (fs.getRegularName() !=null&&fs.getRegularName() !="") {
				 String s ="";
				 Map<String, String> address_ = Analysis.address_(fs.getRegularName());
				 if (address_.get("province") !=null) {
					 String province=address_.get("province");
					 fs.setProvince(province);
					 s +=province;
				 }
				 if (address_.get("city") !=null) {
					 String city=address_.get("city");
					 fs.setCity(city);
					 s +=city;
				 }
				 if (address_.get("county") !=null) {
					 String district =address_.get("county");
					 fs.setDistrict(district);
					 s +=district;
				 }
				 fs.setAttributionInformation(s);
				 governmentUnitMapper.updateINSearchSource(fs);
			 }
		 }
		 /*			for (GatherRulesSource fs : fsList) {
				if (fs.getRegularName() !=null) {
					
					Map<String, String> address_ = Analysis.address_(fs.getRegularName());
					String defaultAddress = "全国";
					if (address_.get("province") !=null) {
						defaultAddress=address_.get("province");
					}
					if (address_.get("city") !=null) {
						defaultAddress +=address_.get("city");
					}
					if (address_.get("county") !=null) {
						defaultAddress +=address_.get("county");
					}
					fs.setAttributionInformation(defaultAddress);
					governmentUnitMapper.updateINRulesSource(fs);
				}
			}
		  *///			governmentUnitMapper.updateRulesSource(fsList);
		 try {
//				List<GatherRulesSource> list = getMap(fs,3);
			 //批量插入
		 } catch (Exception e) {
			 // TODO Auto-generated catch block
			 e.printStackTrace();
		 }
		 return "login";
	 } 
	 
	 public static List<GatherRulesSource> getMap(List<GatherRulesSource> list, final int nThreads)
	            throws Exception {
	        if (list == null || list.isEmpty()) {
	            return null;
	        }
//	        StringBuffer ret = new StringBuffer();
//			List<Map<String,Object>> npklist = new ArrayList<>();
	        List<GatherRulesSource> grsList = new ArrayList<>();
	        int size = list.size();
	        ExecutorService executorService = Executors.newFixedThreadPool(nThreads);
	        List<Future<GatherRulesSource>> futures = new ArrayList<Future<GatherRulesSource>>(nThreads);
	        for (int i = 0; i < nThreads; i++) {
	            final List<GatherRulesSource> subList = list.subList(size / nThreads * i, size/ nThreads * (i + 1));
	            Callable<GatherRulesSource> task = new Callable<GatherRulesSource>() {
					@Override
					public GatherRulesSource call() throws Exception {
						GatherRulesSource grs = new GatherRulesSource();
						for (GatherRulesSource gs : subList) {
							if (gs.getRegularName() !="" && gs.getRegularName() != null) {
								Map<String, String> address_ = Analysis.address_(gs.getRegularName());
								//{province=浙江省, city=台州市, county=null}
								String defaultAddress = "全国";
								if (address_.get("province") !=null) {
									defaultAddress=address_.get("province");
								}
								if (address_.get("city") !=null) {
									defaultAddress +=address_.get("city");
								}
								if (address_.get("county") !=null) {
									defaultAddress +=address_.get("county");
								}
								grs.setRegularName(gs.getRegularName());
								grs.setAttributionInformation(defaultAddress);
								grs.setCompetentUnits(gs.getCompetentUnits());
								grs.setConfigurationNumber(gs.getConfigurationNumber());
								grs.setId(gs.getId());
								grs.setIndexUrl(gs.getIndexUrl());
								grs.setLevel(gs.getLevel());
								grs.setMaintenanceTime(gs.getMaintenanceTime());
								grs.setWebsiteNature(gs.getWebsiteNature());
								grs.setCarrierID(gs.getCarrierID());
								grs.setContryId(gs.getContryId());
							}
							
	                    }
						return grs;
					}
	            };
	            futures.add(executorService.submit(task));
	        }

	        for (Future<GatherRulesSource> future : futures) {
	        	grsList.add(future.get());
	        }
	        executorService.shutdown();

	        return grsList;
	    }
	 
	 
   public static void main(String[] args) {
	   String unit = "北京市海淀区人民政府办公室";
	   String mm = "";
	   int length = unit.length();
	   if (unit.indexOf("区")>0) {
		   mm = unit.substring(unit.indexOf("区")+1,length);
		   System.out.println(mm);
	   }
//	   Map<String, String> address_ = Analysis.address_("浙江杭州新闻网");
//	   System.out.println(address_);
//	   String ss="云南省-甘孜县公安局.csv";
//	   int indexOf = ss.indexOf("州");
//	   System.out.println(indexOf);
//	   String substring = ss.substring(0,ss.lastIndexOf("."));
//	   String sb = ss.substring(ss.lastIndexOf("."),ss.length());
//	   System.out.println("sb="+sb);
//	   System.out.println("substring="+substring);
//	   String result = ss.split("\\.")[0].replace("-", "").replaceAll("\\d+", "");
//	   System.out.println(result);
//	   
//	   File folder = new File("D:/test/webnametest/data/");
//		//获取该目录下所有文件的File数组
//		File[] fileArray = folder.listFiles();
//		for(File f : fileArray){
//			//System.out.println(f);
//			String name = f.getName();
//			int index = name.indexOf("_");
//			String newName = name.substring(index+1, name.length());
//			//System.out.println(newName);
//			File newFile = new File(folder,"666.csv");
//			f.renameTo(newFile);
//		}
	   
} 
}
