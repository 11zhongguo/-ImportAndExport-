package com.zkdj.urlCheck.spring_boot_1.main.java.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;


/**
 * 网站主管部门
 * @Author si
 */
@Repository
public interface EntityMapper {
	
	List<String> findWordName();
}
