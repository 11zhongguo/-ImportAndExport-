package com.zkdj.urlCheck.spring_boot_1.main.java.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import com.zkdj.urlCheck.spring_boot_1.main.java.model.ConnectionClass;

public class EntityThread implements Runnable {

	private int begin;
	private int end;
	private int id;
	private List<Integer> list;
	
	public EntityThread(int i,int num,List<Integer> wordList,Integer entityId){
		begin=i*num;
		end=begin+num; //need to solve how many insert 
		list=wordList;
		id=entityId;
	}
	
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
		PreparedStatement pst= (PreparedStatement)con.prepareStatement(sql);
		for(int i=begin;i<end;i++){
			pst.setInt(1, list.get(i));
			pst.setInt(2, id);
			pst.setInt(3, 1);
			pst.setString(4, null);
			pst.addBatch();
		}
		pst.executeBatch();
		con.commit();
		connection.setBusy(false);
		} catch (SQLException e) {
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
