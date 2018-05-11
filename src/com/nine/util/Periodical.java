package com.nine.util;

import java.sql.Date;

public class Periodical {
	String periodicalsID;
	String periodicalName;
	Date buyDate;
	String press;
	public String getPeriodicalsID() {
		return periodicalsID;
	}
	public void setPeriodicalsID(String periodicalsID) {
		this.periodicalsID = periodicalsID;
	}
	public String getPeriodicalName() {
		return periodicalName;
	}
	public void setPeriodicalName(String periodicalName) {
		this.periodicalName = periodicalName;
	}
	public String getPress() {
		return press;
	}
	public void setPress(String press) {
		this.press = press;
	}
}
