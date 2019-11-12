package com.zkdj.urlCheck.spring_boot_1.main.java.model;

import java.sql.Connection;
import java.sql.DriverManager;

import com.zkdj.urlCheck.spring_boot_1.main.java.utils.ConnectionPool;

public class ConnectionClass {

	private Connection con=null;
	private boolean busy;

//	public ConnectionClass()
//	{
//	try
//	{
//	Class.forName("com.mysql.jdbc.Driver");
//	con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/thesaurus222?characterEncoding=utf8&allowMultiQueries=true&useSSL=false","root","root");
//	}catch(Exception e)
//	{
//	e.printStackTrace();
//	}
//	}
	/***
	 * construct function
	 * @param url
	 * @param user
	 * @param password
	 */
	public ConnectionClass(String jdbcDriver,String url,String user,String password)
	{
	this.busy=false;
	try
	{
	Class.forName(jdbcDriver);
	con=DriverManager.getConnection(url, user, password);
	}catch(Exception e)
	{
	e.printStackTrace();
	}
	}


	public Connection getCon(){
		return con;
	}
	public boolean isBusy() {
		return busy;
	}
	public synchronized  void setBusy(boolean busy) {
	this.busy = busy;
	}

	private static ConnectionPool cpool=null;
	public  static ConnectionPool getConnectionPool(){
	if(cpool==null){
//		cpool = new ConnectionPool("jdbc:mysql://127.0.0.1:3306/thesaurus222?characterEncoding=utf8&allowMultiQueries=true&useSSL=false","root","root");
		cpool = new ConnectionPool("jdbc:mysql://116.62.153.127:3306/thesaurus?characterEncoding=utf8&allowMultiQueries=true&useSSL=false","root","zkdj1234.");
		return cpool;
	}
	else
		return cpool;
	}
	
}
