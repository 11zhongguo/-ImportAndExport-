package com.zkdj.urlCheck.spring_boot_1.main.java.utils;

import java.util.ArrayList;

import com.zkdj.urlCheck.spring_boot_1.main.java.model.ConnectionClass;

public class ConnectionPool {

	private String user="";
	private String password="";
	final static String jdbcDriver="com.mysql.jdbc.Driver";
	private String url="";
	private int maxConnection=10;
	private ArrayList<ConnectionClass> connections=null;
	public ConnectionPool(String url,String user,String password)
	{
	this.setUrl(url);
	this.setUser(user);
	this.setPassword(password);
	createConnections();
	}
	/***
	 * create connections in connection pool
	 */
	private void createConnections()
	{
	if(connections!=null)
	return;
	else
	{
	connections = new ArrayList<ConnectionClass>();
	for(int i=0;i<maxConnection;i++)
	{
	ConnectionClass t=new ConnectionClass(jdbcDriver,url,user,password);
	if(t!=null)
	{
	connections.add(t);
	}
	}

	}
	}
	
	/**
	 * get free connection
	 * @return
	 */
	public synchronized ConnectionClass findFreeConnection() // 线程每次获取空闲连接是要实现同步
	{
	ConnectionClass t=null;
	int i=0;
	for(i=0;i<maxConnection;i++)
	{
	t=connections.get(i);
	if(t.isBusy()==false)
	break;
	}
	if(i<maxConnection)
	t.setBusy(true);
	return t;
	}
	public int getMaxConnection() {
	return maxConnection;
	}
	public void setMaxConnection(int maxConnection) {
	this.maxConnection = maxConnection;
	}
	public ArrayList<ConnectionClass> getConnections() {
	return connections;
	}
	public void setConnections(ArrayList<ConnectionClass> connections) {
	this.connections = connections;
	}
	public String getUrl() {
	return url;
	}
	public void setUrl(String url) {
	this.url = url;
	}
	public String getPassword() {
	return password;
	}
	public void setPassword(String password) {
	this.password = password;
	}
	public String getUser() {
	return user;
	}
	public void setUser(String user) {
	this.user = user;
	}
}
