package edu.mass.doe.cap.dataservice.pojo;

import java.util.Date;

public class AnnouncementInfo {

	private Long announcementId;

	private String text;
	
	private String prevText;
	
	private Date postedDate;

	public Long getAnnouncementId() {
		return announcementId;
	}

	public void setAnnouncementId(Long announcementId) {
		this.announcementId = announcementId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getPostedDate() {
		return postedDate;
	}

	public void setPostedDate(Date postedDate) {
		this.postedDate = postedDate;
	}

	public String getPrevText() {
		return prevText;
	}

	public void setPrevText(String prevText) {
		this.prevText = prevText;
	}

}
