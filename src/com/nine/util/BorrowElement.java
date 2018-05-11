package com.nine.util;

import java.sql.Date;

public class BorrowElement {
	private String periodicalsID;
	private String periodicalName;
	private Date beginDate;
	private Date endDate;
	private String remarks;
	public String getPeriodicalName() {
		return periodicalName;
	}
	public void setPeriodicalName(String periodicalName) {
		this.periodicalName = periodicalName;
	}
	public String getReaderID() {
		return periodicalName;
	}
	public void setReaderID(String periodicalName) {
		this.periodicalName = periodicalName;
	}
	public String getPeriodicalsID() {
		return periodicalsID;
	}
	public void setPeriodicalsID(String periodicalsID) {
		this.periodicalsID = periodicalsID;
	}
	public Date getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}
