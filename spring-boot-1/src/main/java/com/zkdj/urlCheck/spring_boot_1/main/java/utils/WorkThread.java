package com.zkdj.urlCheck.spring_boot_1.main.java.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import com.zkdj.urlCheck.spring_boot_1.main.java.model.ConnectionClass;

public class WorkThread implements Runnable {

	private int begin;
	private int end;
	private List<String> list;
	
	public WorkThread(int i,int num,List<String> wordlist){
		begin=i*num;
		end=begin+num; //need to solve how many insert 
		list=wordlist;
	}
	
	@SuppressWarnings("null")
	@Override
	public void run() {

		ConnectionClass connection=this.getConn();
		Connection con=connection.getCon();
		if(con!=null)
		{
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss:SS");   
		TimeZone t = sdf.getTimeZone();   
		t.setRawOffset(0);   
		sdf.setTimeZone(t);   
		Long startTime = System.currentTimeMillis(); 
		try {
		con.setAutoCommit(false);
		String sql = "INSERT INTO relation_word_entity (word_id, entity_id, status, children) " + 
				"        VALUES (?,?,?,?)";
		String sql1 = "insert  into " + 
				"		word " + 
				"		(name,user_id,audit_time,status,source,create_time,type,gj_category_id,provider_name) " + 
				"		values (?,?,?,?,?,?,?,?,?)	";
		PreparedStatement pst= (PreparedStatement)con.prepareStatement(sql1);
		for(int i=begin;i<end;i++){
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
			Date date = new Date();
			String format = df.format(date);
			pst.setString(1, list.get(i));
			pst.setInt(2, 1);
			pst.setString(3, format);
			pst.setInt(4, 1);
			pst.setInt(5, 0);
			pst.setString(6, format);
			pst.setInt(7, 0);
			pst.setString(8,null);
			pst.setString(9,  null);
			pst.addBatch();
		}
		pst.executeBatch();
		con.commit();
		connection.setBusy(false);
		} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		Long endTime = System.currentTimeMillis();
		System.out.println(Thread.currentThread().getName()+":startTime= "+sdf.format(new Date(startTime))+",endTime= "+sdf.format(new Date(endTime))
		+" 用时："+sdf.format(new Date(endTime - startTime)));

		}

	}
	
	private ConnectionClass getConn()
	{
	ConnectionClass con=null;
	ConnectionPool pool=ConnectionClass.getConnectionPool();
	con=pool.findFreeConnection();
	return con;
	}

}
