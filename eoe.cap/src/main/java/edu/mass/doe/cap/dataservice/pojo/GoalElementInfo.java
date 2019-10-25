package edu.mass.doe.cap.dataservice.pojo;

import java.util.Date;

public class GoalElementInfo {
	
	
	private Long id;
	
	private String elementCode;

	private Date expDate;	
	
	private Date effDate;
	
	
	private String code;
	private boolean selected;
	private String label;
	private String desc;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getElementCode() {
		return elementCode;
	}
	public void setElementCode(String elementCode) {
		this.elementCode = elementCode;
	}
	public Date getExpDate() {
		return expDate;
	}
	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}
	public Date getEffDate() {
		return effDate;
	}
	public void setEffDate(Date effDate) {
		this.effDate = effDate;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}

}
