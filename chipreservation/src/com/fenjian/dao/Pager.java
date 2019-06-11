package com.fenjian.dao;

import java.util.List;

public class Pager<E> {
	//数据总条�?
	private int totalRecord;
	//每一页显示的数据条数
	private int pageSize;
	//页码�?
	private int pageIndex;
	//总页�?
	private int totalPage;
	//每一页显示的数据的起始下�? , 为后面做准备
	
	//查询到的数据的集�?
	private List<E> datas;

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public List<E> getDatas() {
		return datas;
	}

	public void setDatas(List<E> datas) {
		this.datas = datas;
	}
	
	
}
