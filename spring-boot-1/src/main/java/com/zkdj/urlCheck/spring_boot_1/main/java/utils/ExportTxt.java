package com.zkdj.urlCheck.spring_boot_1.main.java.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Iterator;
import java.util.List;

public class ExportTxt {

	public static void exportTxt(List<String> list) {
//		String ss ="";
		try {
    		File f=new File("D:/test/piliangInsertciku/local/quchonghou_enword.txt");
    		if (!f.exists()) {
    			f.createNewFile();
    		}
    		FileOutputStream fos1=new FileOutputStream(f);
    		OutputStreamWriter dos1=new OutputStreamWriter(fos1);
    		Iterator<String> iterator = list.iterator();
	    	while (iterator.hasNext()) {
	    		String trim = iterator.next().trim();
//	    		ss +=trim+",";
	    		dos1.write(trim+"\r\n");
	    	}
	    	
	    	dos1.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
