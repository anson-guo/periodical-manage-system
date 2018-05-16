package com.nine.util;

import java.sql.Date;

public class WillBorrow {
	private String periodicalID;
	private String issue;
	private String periodicalName;
	private String borrowed;
	public String getPeriodicalID() {
		return periodicalID;
	}
	public void setPeriodicalID(String periodicalID) {
		this.periodicalID = periodicalID;
	}
	public String getIssue() {
		return issue;
	}
	public void setIssue(String issue) {
		this.issue = issue;
	}
	public String getPeriodicalName() {
		return periodicalName;
	}
	public void setPeriodicalName(String periodicalName) {
		this.periodicalName = periodicalName;
	}
	public String getBorrowed() {
		return borrowed;
	}
	public void setBorrowed(String borrowed) {
		this.borrowed = borrowed;
	}
}
