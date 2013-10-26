package com.desilvas.volunteerapp.domain.TO;

import java.util.List;

public class PageTO {

	public int getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}
	public int getStartFrom() {
		return startFrom;
	}
	public void setStartFrom(int startFrom) {
		this.startFrom = startFrom;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalNumberOfPages() {
		return totalNumberOfPages;
	}
	public void setTotalNumberOfPages(int totalNumberOfPages) {
		this.totalNumberOfPages = totalNumberOfPages;
	}
	private int totalRecords;
	private int startFrom;
	private int pageSize;
	private int totalNumberOfPages;
	private List<?> records;
	public List<?> getRecords() {
		return records;
	}
	public void setRecords(List<?> records) {
		this.records = records;
	}
	
}
