package org.sang.bean;

import java.sql.Timestamp;

public class Date {
    private Timestamp starttime;
    private Timestamp endtime;
	public Timestamp getStarttime() {
		return starttime;
	}
	public void setStarttime(Timestamp starttime) {
		this.starttime = starttime;
	}
	public Timestamp getEndtime() {
		return endtime;
	}
	public void setEndtime(Timestamp endtime) {
		this.endtime = endtime;
	}
	public Date(Timestamp starttime, Timestamp endtime) {
		super();
		this.starttime = starttime;
		this.endtime = endtime;
	}
	@Override
	public String toString() {
		return "Date [starttime=" + starttime + ", endtime=" + endtime + "]";
	}
	
}
