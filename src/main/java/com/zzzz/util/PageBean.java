package com.zzzz.util;

import java.util.List;

public class PageBean<T>  {

	// 当前页
	private int currPage = 1;
	// 每页条数
	private int pageSize = 10;
	// 总条数
	private int totalNum;
	// 总页数
	private int totalPage;
	// 开始索引
	private int startIndex;
	// 是否有下一页
	private boolean isMore;
	// 分页结果
	private List<T> page;
	
	public PageBean() {
		super();
	}
	
	public PageBean(int currPage, int pageSize, int totalNum) {
		super();
		this.currPage = currPage;
		this.pageSize = pageSize;
		this.totalNum = totalNum;
		this.totalPage = (this.totalNum + this.pageSize - 1) / this.pageSize;
		this.startIndex = (this.currPage - 1) * this.pageSize;
		this.isMore = this.currPage >= this.totalPage;
	}
	
	public int getCurrPage() {
		return currPage;
	}
	
	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}
	
	public int getPageSize() {
		return pageSize;
	}
	
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public int getTotalNum() {
		return totalNum;
	}
	
	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}
	
	public int getTotalPage() {
		return totalPage;
	}
	
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	
	public int getStartIndex() {
		return startIndex;
	}
	
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	
	public boolean getIsMore() {
		return isMore;
	}
	
	public void setIsMore(boolean isMore) {
		this.isMore = isMore;
	}
	
	public List<T> getPage() {
		return page;
	}
	
	public void setPage(List<T> page) {
		this.page = page;
	}
}
