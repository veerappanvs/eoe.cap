package edu.mass.doe.cap.dataservice.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;

import org.apache.commons.lang.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;

public class ObservationInfo {
	
	
	private Long observationId;
	private Long cycleId;
	private String observationTypecode;
	private String observationTypeDesc;
	private Integer observationNumber;
	private Character psspCompleted;
	private Character completed;
	
	private String statusDesc;
	
	@DateTimeFormat(pattern = "MM/dd/yyyy") 
	private Date observationDate;
	
	@DateTimeFormat(pattern = "hh:mm a") 
	private Date startTime;
	
	@DateTimeFormat(pattern = "hh:mm a") 
	private Date endTime;
	
	private List<String> groupCode;
	private String topicObjective;
	private String spRefineFeedback;
	private String psRefineFeedback;
	private String calRefineFeedback;
	private String psReinFeedback;
	private String spReinFeedback;
	private String calReinFeedback;
	
	private boolean supervisor;
	private boolean practitioner;
	private boolean viewOnly;
	
	private boolean completeObservation;
	private boolean enableCompletion;
	
	private Date CompleteDate;
	
	private Long completedBy;
	
	
	List<EvidenceInfo> evidences = new ArrayList<EvidenceInfo>();
	
	private String groupCodeAsString;
	
	
	
	public String getObservationTypecode() {
		return observationTypecode;
	}
	public void setObservationTypecode(String observationTypecode) {
		this.observationTypecode = observationTypecode;
	}
	public Integer getObservationNumber() {
		return observationNumber;
	}
	public void setObservationNumber(Integer observationNumber) {
		this.observationNumber = observationNumber;
	}
	public Character getPsspCompleted() {
		return psspCompleted;
	}
	public void setPsspCompleted(Character psspCompleted) {
		this.psspCompleted = psspCompleted;
	}
	public Character getCompleted() {
		return completed;
	}
	public void setCompleted(Character completed) {
		this.completed = completed;
	}
	public Date getObservationDate() {
		return observationDate;
	}
	public void setObservationDate(Date observationDate) {
		this.observationDate = observationDate;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public List<String> getGroupCode() {
		return groupCode;
	}
	
	public void setGroupCode(List<String> groupCode) {
		this.groupCode = groupCode;
	}
	
	public void  setGroupCodeAsString(String  groupCodeAsString) {
		this.groupCodeAsString = groupCodeAsString;	
	}
	
	public String getGroupCodeAsString() {
		return StringUtils.join(groupCode,", ");
	}
	
	public String getTopicObjective() {
		return topicObjective;
	}
	public void setTopicObjective(String topicObjective) {
		this.topicObjective = topicObjective;
	}
	public String getSpRefineFeedback() {
		return spRefineFeedback;
	}
	public void setSpRefineFeedback(String spRefineFeedback) {
		this.spRefineFeedback = spRefineFeedback;
	}
	public String getPsRefineFeedback() {
		return psRefineFeedback;
	}
	public void setPsRefineFeedback(String psRefineFeedback) {
		this.psRefineFeedback = psRefineFeedback;
	}
	public String getCalRefineFeedback() {
		return calRefineFeedback;
	}
	public void setCalRefineFeedback(String calRefineFeedback) {
		this.calRefineFeedback = calRefineFeedback;
	}
	public String getPsReinFeedback() {
		return psReinFeedback;
	}
	public void setPsReinFeedback(String psReinFeedback) {
		this.psReinFeedback = psReinFeedback;
	}
	public String getSpReinFeedback() {
		return spReinFeedback;
	}
	public void setSpReinFeedback(String spReinFeedback) {
		this.spReinFeedback = spReinFeedback;
	}
	public String getCalReinFeedback() {
		return calReinFeedback;
	}
	public void setCalReinFeedback(String calReinFeedback) {
		this.calReinFeedback = calReinFeedback;
	}
	public List<EvidenceInfo> getEvidences() {
		return evidences;
	}
	public void setEvidences(List<EvidenceInfo> evidences) {
		this.evidences = evidences;
	}
	public Long getObservationId() {
		return observationId;
	}
	public void setObservationId(Long observationId) {
		this.observationId = observationId;
	}
	public Long getCycleId() {
		return cycleId;
	}
	public void setCycleId(Long cycleId) {
		this.cycleId = cycleId;
	}
	public boolean isSupervisor() {
		return supervisor;
	}
	public void setSupervisor(boolean supervisor) {
		this.supervisor = supervisor;
	}
	public boolean isPractitioner() {
		return practitioner;
	}
	public void setPractitioner(boolean practitioner) {
		this.practitioner = practitioner;
	}
	public boolean isViewOnly() {
		return viewOnly;
	}
	public void setViewOnly(boolean viewOnly) {
		this.viewOnly = viewOnly;
	}
	public String getObservationTypeDesc() {
		return observationTypeDesc;
	}
	public void setObservationTypeDesc(String observationTypeDesc) {
		this.observationTypeDesc = observationTypeDesc;
	}
	public String getStatusDesc() {
		return statusDesc;
	}
	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
	public boolean isCompleteObservation() {
		return completeObservation;
	}
	public void setCompleteObservation(boolean completeObservation) {
		this.completeObservation = completeObservation;
	}
	public boolean isEnableCompletion() {
		return enableCompletion;
	}
	public void setEnableCompletion(boolean enableCompletion) {
		this.enableCompletion = enableCompletion;
	}
	public Date getCompleteDate() {
		return CompleteDate;
	}
	public void setCompleteDate(Date completeDate) {
		CompleteDate = completeDate;
	}
	public Long getCompletedBy() {
		return completedBy;
	}
	public void setCompletedBy(Long completedBy) {
		this.completedBy = completedBy;
	}

	
	
}
