package com.zkdj.urlCheck.spring_boot_1.main.java.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PagingInfo {

    public static final int DEFAULT_PAGE_SIZE = 10;

    private int pageIndex = 0;

    private int pageSize = DEFAULT_PAGE_SIZE;

    private int totalCount;

    private int pageNum;

    private List<?> list;

    private Map<String, Object> query = new HashMap<String, Object>();

    public Map<String, Object> getQuery() {
        return this.query;
    }

    public void setQuery(Map<String, Object> query ) {
        this.query = query;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public List<?> getList() {
		return list;
	}

	public void setList(List<?> list) {
		this.list = list;
	}

    public int getPageIndex() {
        if(pageIndex<0){
            return 0;
        }
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        if(pageSize<0){
            return 10;
        }
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getStartIndex() {
        if(pageIndex<0){
            return 0;
        }
        return pageIndex;
    }

    public int getEndIndex() {
        if(pageIndex<0){
            return  pageSize + 1;
        }
        return pageIndex + pageSize + 1;
    }
}
