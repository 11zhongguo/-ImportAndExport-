package com.zkdj.urlCheck.spring_boot_1.main.java.utils;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.data.domain.Page;

/**
 * Created by gq
 * 将Page转为可序列化的VO对象
 */
public class PageVO<T> {

    public PageVO(Page page) {
        this.rows = (T) page.getContent();
        this.total = page.getTotalElements();
        this.pageNumber = page.getNumber();
        this.pageSize = page.getSize();
        this.totalPage = page.getTotalPages();
    }

    public PageVO() {

    }

    private T rows;

    private long total;

    private int totalPage;

    private Integer pageNumber;

    private Integer pageSize;

    private String orderBy;

    private String orderWay;

    public T getRows() {
        return rows;
    }

    public void setRows(T rows) {
        this.rows = rows;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getOrderWay() {
        return orderWay;
    }

    public void setOrderWay(String orderWay) {
        this.orderWay = orderWay;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
