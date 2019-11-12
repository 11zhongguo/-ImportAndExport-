package com.zkdj.urlCheck.spring_boot_1.main.java.utils;


/**   
 * Created by fan on 1/8/19.
 */
public class BaseController {
	
	/**
	 * 成功 返回的数据
	 * 
	 * @param data
	 * @return
	 */
	public JSONResponse success(Object data) {
		return JSONResponse.Create(true,"OK").setData(data);
	}
	
	/**
	 * 成功 返回的数据
	 * 
	 * @param data
	 * @return
	 */
	public JSONResponse success(String data) {
		return JSONResponse.Create(true,"OK").setData(data);
	}

	/**
	 * 失败 返回的消息
	 *
	 * @param msg
	 * @return
	 */
	public JSONResponse error(String msg) {
		return JSONResponse.Create(false, msg).setStatus(1);
	}
	
}
