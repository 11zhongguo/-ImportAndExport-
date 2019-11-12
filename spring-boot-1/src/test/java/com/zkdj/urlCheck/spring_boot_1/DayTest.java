package com.zkdj.urlCheck.spring_boot_1;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DayTest {

	public static void main(String[] args) {
		String str1 = "中国 语言*a";
		String str2 = "chanise lagurage bei jing cheng ";
		boolean containChinese = isContainChinese(str1);
		boolean English = isContainChinese(str2);
		boolean countWords = countWords(str2);
		boolean countWords2 = countWords(str1);
		boolean english2 = isEnglish(str2);
		boolean english3 = isEnglish(str1);
		System.out.println("2="+countWords);
		System.out.println("1="+countWords2);
		System.out.println("E2="+english2+"E1="+english3);
		System.out.println("是否中="+containChinese+"英文="+English);
	}
	
	/**
	22      * 1.判断字节是否是中文
	23      * 
	24      * CJK的意思是“Chinese，Japanese，Korea”的简写 ，实际上就是指中日韩三国的象形文字的Unicode编码 
	25      * Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS             ：4E00-9FBF：CJK 统一表意符号 
	26      * Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS       ：F900-FAFF：CJK 兼容象形文字
	27      * Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A ：3400-4DBF：CJK 统一表意符号扩展 A 
	28      * Character.UnicodeBlock.GENERAL_PUNCTUATION                ：2000-206F：常用标点 
	29      * Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION        ：3000-303F：CJK 符号和标点 
	30      * Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS      ：FF00-FFEF：半角及全角形式
	31      * 
	32      */
	     public static boolean isChinese(char c) {
	         Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
	         if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
	                 || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
	                 || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
	                 || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
	                 || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
	                 || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
	             return true;
	         }
	         return false;
	     }
	     //2.检测是否包含英文
	     public static boolean isEnglish(String charaString) {
	         return charaString.matches("^[a-zA-Z]*");
	     }
	     //3.检测是否包含中文
	     public static boolean isContainChinese(String str) {
	         String regEx = "[\\u4E00-\\u9FA5]+";
	         Pattern p = Pattern.compile(regEx);
	         Matcher m = p.matcher(str);
	         if (m.find()) {
	             return true;
	         } else {
	           return false;
	         }
	     }
	     public static boolean countWords(String str){

	         Map<String, Integer> map=new HashMap<String, Integer>();        
	         Pattern p=Pattern.compile("\\b[a-zA-Z-]+\\b");//正则表达式
	         Matcher m=p.matcher(str);
	         while(m.find()){
	             String mstr=m.group();
	             if(map.containsKey(mstr)){
	                 map.put(mstr,map.get(mstr)+1);
	             }else{
	                 map.put(mstr, 1);
	             }
	         }
	         if (map.size()>5) {
				return true;
			}else {
				return false;
			}
//	         System.out.println("ge="+map.size());
//	         Set<Entry<String, Integer>> entrySet = map.entrySet();
//	         Iterator<Entry<String,Integer>> it=entrySet.iterator();
//	         while(it.hasNext()){
//	             Entry<String, Integer> next = it.next();
//	             System.out.println(next.getKey()+" 个数:"+next.getValue());
//	         }

	     }
}
