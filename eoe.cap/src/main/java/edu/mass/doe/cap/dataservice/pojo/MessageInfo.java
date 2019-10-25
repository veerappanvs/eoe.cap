package edu.mass.doe.cap.dataservice.pojo;

import java.util.Date;

import javax.persistence.Column;

public class MessageInfo {

	private Long messageId;
	
	private Long cycleId;
	
	private String  typeCode;
	
	private Long daPersonId;
	
	private String message;
	
	private Date messageDate;
	
	private String messageFrom;
	
	private String bgColor;

	public Long getMessageId() {
		return messageId;
	}

	public void setMessageId(Long messageId) {
		this.messageId = messageId;
	}

	public Long getCycleId() {
		return cycleId;
	}

	public void setCycleId(Long cycleId) {
		this.cycleId = cycleId;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public Long getDaPersonId() {
		return daPersonId;
	}

	public void setDaPersonId(Long daPersonId) {
		this.daPersonId = daPersonId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getMessageDate() {
		return messageDate;
	}

	public void setMessageDate(Date messageDate) {
		this.messageDate = messageDate;
	}

	public String getMessageFrom() {
		return messageFrom;
	}

	public void setMessageFrom(String messageFrom) {
		this.messageFrom = messageFrom;
	}

	public String getBgColor() {
		return bgColor;
	}

	public void setBgColor(String bgColor) {
		this.bgColor = bgColor;
	}
}
