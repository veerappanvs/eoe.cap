package edu.mass.doe.cap.dataservice.pojo;

import java.util.Date;

public class CapYear {
	
	private int schoolYear;
	private String desc;
	private Date startDate;
	private Date endDate;
	private boolean active;
	
	public int getSchoolYear() {
		return schoolYear;
	}
	public void setSchoolYear(int schoolYear) {
		this.schoolYear = schoolYear;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	

}
