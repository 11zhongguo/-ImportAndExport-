package com.zkdj.urlCheck.spring_boot_1.main.java.model;

import java.util.Date;

import org.springframework.data.annotation.Transient;

/**
 * @author sixiujun
 *
 */
public class BaMain {

	private Integer id;
	/**备案许可证号 */
	private String 备案许可证号;
	/**主办单位名称 */
	private String 主办单位名称;
	/**主办单位性质 */
	private String 主办单位性质;
	/**审核通过时间 */
	private Date 审核通过时间;
	/**createtime */
	private Date createtime;
	/**guid */
	private String guid;
	/**网站名称 */
	@Transient  
	private   String 网站名称;
	/**网站首页网址 */
	@Transient  
	private   String 网站首页网址;
	/**网站负责人姓名 */
	@Transient  
	private   String 网站负责人姓名;
	/**网站域名 */
	@Transient  
	private   String 网站域名;
	/**网站备案许可证号*/
	@Transient  
	private   Integer 网站备案许可证号;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String get备案许可证号() {
		return 备案许可证号;
	}
	public void set备案许可证号(String 备案许可证号) {
		this.备案许可证号 = 备案许可证号;
	}
	public String get主办单位名称() {
		return 主办单位名称;
	}
	public void set主办单位名称(String 主办单位名称) {
		this.主办单位名称 = 主办单位名称;
	}
	public String get主办单位性质() {
		return 主办单位性质;
	}
	public void set主办单位性质(String 主办单位性质) {
		this.主办单位性质 = 主办单位性质;
	}
	public Date get审核通过时间() {
		return 审核通过时间;
	}
	public void set审核通过时间(Date 审核通过时间) {
		this.审核通过时间 = 审核通过时间;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public String getGuid() {
		return guid;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}
	public String get网站名称() {
		return 网站名称;
	}
	public void set网站名称(String 网站名称) {
		this.网站名称 = 网站名称;
	}
	public String get网站首页网址() {
		return 网站首页网址;
	}
	public void set网站首页网址(String 网站首页网址) {
		this.网站首页网址 = 网站首页网址;
	}
	public String get网站负责人姓名() {
		return 网站负责人姓名;
	}
	public void set网站负责人姓名(String 网站负责人姓名) {
		this.网站负责人姓名 = 网站负责人姓名;
	}
	public String get网站域名() {
		return 网站域名;
	}
	public void set网站域名(String 网站域名) {
		this.网站域名 = 网站域名;
	}
	public Integer get网站备案许可证号() {
		return 网站备案许可证号;
	}
	public void set网站备案许可证号(Integer 网站备案许可证号) {
		this.网站备案许可证号 = 网站备案许可证号;
	}
	


	
}
