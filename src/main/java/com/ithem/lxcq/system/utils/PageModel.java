package com.ithem.lxcq.system.utils;

import java.util.List;
import com.ithem.lxcq.system.domain.course;

//一个页面总的记录数，因为sql的limit ？（num-1）*（Record），？（Record）

public class PageModel {
	private List<course> list;//每页商品内容
	private int Records=10;//每页取的记录数
	private int totalpagenum;//总页数
	private int startPage;//开始页数
	private int endPage;//结尾页数
	private int currentPageNum;//目前页数
	private int prePageNum;
	private int nextPageNum;
	private int PageIndex;
	private String url;
	//计算初始页数
	public PageModel(int num,int totalRecords,int Records){
		this.currentPageNum=num;
		this.prePageNum=currentPageNum-1;
		this.nextPageNum=currentPageNum+1;
		PageIndex=(num-1)*Records;
		//限制分页一次显示9页
		startPage=currentPageNum-4;
		endPage=currentPageNum+4;
		totalpagenum=totalRecords%Records==0?(totalRecords/Records):(totalRecords/Records+1);
		if(totalpagenum>9){
			if(startPage<0){
				startPage=1;
				endPage=startPage+8;
			}
			if(endPage>totalpagenum){
				endPage=totalpagenum;
				startPage=endPage-8;
			}
		}else{
			startPage=1;
			endPage=totalpagenum;
		}
		
	}
	
	
	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public List<course> getList() {
		return list;
	}
	public void setList(List<course> list) {
		this.list = list;
	}
	public int getTotalpagenum() {
		return totalpagenum;
	}
	public void setTotalpagenum(int totalpagenum) {
		this.totalpagenum = totalpagenum;
	}
	public int getStartPage() {
		return startPage;
	}
	public int getRecords() {
		return Records;
	}

	public void setRecords(int records) {
		Records = records;
	}

	public int getPageIndex() {
		return PageIndex;
	}

	public void setPageIndex(int pageIndex) {
		PageIndex = pageIndex;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public int getCurrentPageNum() {
		return currentPageNum;
	}
	public void setCurrentPageNum(int currentPageNum) {
		this.currentPageNum = currentPageNum;
	}
	public int getPrePageNum() {
		return prePageNum;
	}
	public void setPrePageNum(int prePageNum) {
		this.prePageNum = prePageNum;
	}
	public int getNextPageNum() {
		return nextPageNum;
	}
	public void setNextPageNum(int nextPageNum) {
		this.nextPageNum = nextPageNum;
	}

	
}
