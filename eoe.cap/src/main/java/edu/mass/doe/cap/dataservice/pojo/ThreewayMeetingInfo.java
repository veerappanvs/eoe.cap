package edu.mass.doe.cap.dataservice.pojo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;


public class ThreewayMeetingInfo {

	
	
	private Long meetingId1;
	
	private Long meetingId2;
	
	private Long meetingId3;
	
	private Long capCycleId;	
	
	private Long meetingNumber1;
	private String meetingNotes1;
	
	private Long meetingNumber2;
	private String meetingNotes2;
	
	
	private Long meetingNumber3;
	private String meetingNotes3;
	
	@DateTimeFormat(pattern = "MM/dd/yyyy") 
	private Date meetingDate1;

	@DateTimeFormat(pattern = "MM/dd/yyyy") 
	private Date meetingDate2;
	
	@DateTimeFormat(pattern = "MM/dd/yyyy") 
	private Date meetingDate3;

	public Long getMeetingId1() {
		return meetingId1;
	}

	public void setMeetingId1(Long meetingId1) {
		this.meetingId1 = meetingId1;
	}

	public Long getMeetingId2() {
		return meetingId2;
	}

	public void setMeetingId2(Long meetingId2) {
		this.meetingId2 = meetingId2;
	}

	public Long getMeetingId3() {
		return meetingId3;
	}

	public void setMeetingId3(Long meetingId3) {
		this.meetingId3 = meetingId3;
	}

	public Long getCapCycleId() {
		return capCycleId;
	}

	public void setCapCycleId(Long capCycleId) {
		this.capCycleId = capCycleId;
	}

	public Long getMeetingNumber1() {
		return meetingNumber1;
	}

	public void setMeetingNumber1(Long meetingNumber1) {
		this.meetingNumber1 = meetingNumber1;
	}

	public Long getMeetingNumber2() {
		return meetingNumber2;
	}

	public void setMeetingNumber2(Long meetingNumber2) {
		this.meetingNumber2 = meetingNumber2;
	}

	public Long getMeetingNumber3() {
		return meetingNumber3;
	}

	public void setMeetingNumber3(Long meetingNumber3) {
		this.meetingNumber3 = meetingNumber3;
	}

	public Date getMeetingDate1() {
		return meetingDate1;
	}

	public void setMeetingDate1(Date meetingDate1) {
		this.meetingDate1 = meetingDate1;
	}

	public Date getMeetingDate2() {
		return meetingDate2;
	}

	public void setMeetingDate2(Date meetingDate2) {
		this.meetingDate2 = meetingDate2;
	}

	public Date getMeetingDate3() {
		return meetingDate3;
	}

	public void setMeetingDate3(Date meetingDate3) {
		this.meetingDate3 = meetingDate3;
	}

	public String getMeetingNotes1() {
		return meetingNotes1;
	}

	public void setMeetingNotes1(String meetingNotes1) {
		this.meetingNotes1 = meetingNotes1;
	}

	public String getMeetingNotes2() {
		return meetingNotes2;
	}

	public void setMeetingNotes2(String meetingNotes2) {
		this.meetingNotes2 = meetingNotes2;
	}

	public String getMeetingNotes3() {
		return meetingNotes3;
	}

	public void setMeetingNotes3(String meetingNotes3) {
		this.meetingNotes3 = meetingNotes3;
	}
	

	
	
}
