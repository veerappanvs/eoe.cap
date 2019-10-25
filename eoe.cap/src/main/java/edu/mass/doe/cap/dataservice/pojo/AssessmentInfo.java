package edu.mass.doe.cap.dataservice.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class AssessmentInfo {

	private Long assessmentId;

	private Long cycleId;

	private String typeCode;
	
	private String typeDesc;

	private Character locked;

	private Character completed='N';

	private String calFeedback;

	private Character addlObservationRequired;

	private String reinFeedback;

	private String refineFeedback;

	private String evidenceFeedback;

	private String recommendedfocus;

	private Character spApproved='N';

	private Date spApprovedDate;

	private Character psApproved='N';

	private Date psApprovedDate;
	
	private ObservationInfo announcedObservation1;
	private ObservationInfo announcedObservation2;
	private ObservationInfo unAnnouncedObservation;
	private ObservationInfo unAnnouncedObservation2;

	
	private List<Attribute> reinAttributes;
	private List<Attribute> refineAttributes;
	
	private List<ElementInfo> elements = new ArrayList<ElementInfo>();
	
	private boolean canSignOff;
	
	private boolean assignmentSigned;
	
	private Character readyToTeach;
	
	private String activeElementCode="01";

	private List<SelfAssessmentAttributeInfo> strengths=new ArrayList<SelfAssessmentAttributeInfo>();

	private List<SelfAssessmentAttributeInfo> deletedStrengths=new ArrayList<SelfAssessmentAttributeInfo>();

	private List<SelfAssessmentAttributeInfo> growths=new ArrayList<SelfAssessmentAttributeInfo>();
	
	private List<SelfAssessmentAttributeInfo> deletedGrowths=new ArrayList<SelfAssessmentAttributeInfo>();
	
	private GoalPlanInfo goalPlanInfo;
	
	private boolean completeSelfAssessment;
	private boolean enableCompletion;
	
	private boolean canAddStrengths;
	
	private boolean canAddGrowths;


	public Long getAssessmentId() {
		return assessmentId;
	}

	public void setAssessmentId(Long assessmentId) {
		this.assessmentId = assessmentId;
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

	public Character getLocked() {
		return locked;
	}

	public void setLocked(Character locked) {
		this.locked = locked;
	}

	public Character getCompleted() {
		return completed;
	}

	public void setCompleted(Character completed) {
		this.completed = completed;
	}

	public String getCalFeedback() {
		return calFeedback;
	}

	public void setCalFeedback(String calFeedback) {
		this.calFeedback = calFeedback;
	}

	public Character getAddlObservationRequired() {
		return addlObservationRequired;
	}

	public void setAddlObservationRequired(Character addlObservationRequired) {
		this.addlObservationRequired = addlObservationRequired;
	}

	public String getReinFeedback() {
		return reinFeedback;
	}

	public void setReinFeedback(String reinFeedback) {
		this.reinFeedback = reinFeedback;
	}

	public String getRefineFeedback() {
		return refineFeedback;
	}

	public void setRefineFeedback(String refineFeedback) {
		this.refineFeedback = refineFeedback;
	}

	public String getEvidenceFeedback() {
		return evidenceFeedback;
	}

	public void setEvidenceFeedback(String evidenceFeedback) {
		this.evidenceFeedback = evidenceFeedback;
	}

	public String getRecommendedfocus() {
		return recommendedfocus;
	}

	public void setRecommendedfocus(String recommendedfocus) {
		this.recommendedfocus = recommendedfocus;
	}

	public Character getSpApproved() {
		return spApproved;
	}

	public void setSpApproved(Character spApproved) {
		this.spApproved = spApproved;
	}

	public Date getSpApprovedDate() {
		return spApprovedDate;
	}

	public void setSpApprovedDate(Date spApprovedDate) {
		this.spApprovedDate = spApprovedDate;
	}

	public Character getPsApproved() {
		return psApproved;
	}

	public void setPsApproved(Character psApproved) {
		this.psApproved = psApproved;
	}

	public Date getPsApprovedDate() {
		return psApprovedDate;
	}

	public void setPsApprovedDate(Date psApprovedDate) {
		this.psApprovedDate = psApprovedDate;
	}

	public List<ElementInfo> getElements() {
		return elements;
	}

	public void setElements(List<ElementInfo> elements) {
		this.elements = elements;
	}

	public String getTypeDesc() {
		return typeDesc;
	}

	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
	}

	public ObservationInfo getAnnouncedObservation1() {
		return announcedObservation1;
	}

	public void setAnnouncedObservation1(ObservationInfo announcedObservation1) {
		this.announcedObservation1 = announcedObservation1;
	}

	public ObservationInfo getAnnouncedObservation2() {
		return announcedObservation2;
	}

	public void setAnnouncedObservation2(ObservationInfo announcedObservation2) {
		this.announcedObservation2 = announcedObservation2;
	}

	public ObservationInfo getUnAnnouncedObservation() {
		return unAnnouncedObservation;
	}

	public void setUnAnnouncedObservation(ObservationInfo unAnnouncedObservation) {
		this.unAnnouncedObservation = unAnnouncedObservation;
	}

	public List<Attribute> getRefineAttributes() {
		return refineAttributes;
	}

	public void setRefineAttributes(List<Attribute> refineAttributes) {
		this.refineAttributes = refineAttributes;
	}

	public List<Attribute> getReinAttributes() {
		return reinAttributes;
	}

	public void setReinAttributes(List<Attribute> reinAttributes) {
		this.reinAttributes = reinAttributes;
	}

	public boolean isCanSignOff() {
		return canSignOff;
	}

	public void setCanSignOff(boolean canSignOff) {
		this.canSignOff = canSignOff;
	}

	public boolean isAssignmentSigned() {
		return assignmentSigned;
	}

	public void setAssignmentSigned(boolean assignmentSigned) {
		this.assignmentSigned = assignmentSigned;
	}

	public ObservationInfo getUnAnnouncedObservation2() {
		return unAnnouncedObservation2;
	}

	public void setUnAnnouncedObservation2(ObservationInfo unAnnouncedObservation2) {
		this.unAnnouncedObservation2 = unAnnouncedObservation2;
	}

	public Character getReadyToTeach() {
		return readyToTeach;
	}

	public void setReadyToTeach(Character readyToTeach) {
		this.readyToTeach = readyToTeach;
	}

	public String getActiveElementCode() {
		return activeElementCode;
	}

	public void setActiveElementCode(String activeElementCode) {
		this.activeElementCode = activeElementCode;
	}

	public List<SelfAssessmentAttributeInfo> getStrengths() {
		return strengths;
	}

	public void setStrengths(List<SelfAssessmentAttributeInfo> strengths) {
		this.strengths = strengths;
	}

	public List<SelfAssessmentAttributeInfo> getGrowths() {
		return growths;
	}

	public void setGrowths(List<SelfAssessmentAttributeInfo> growths) {
		this.growths = growths;
	}

	public List<SelfAssessmentAttributeInfo> getDeletedStrengths() {
		return deletedStrengths;
	}

	public void setDeletedStrengths(List<SelfAssessmentAttributeInfo> deletedStrengths) {
		this.deletedStrengths = deletedStrengths;
	}

	public List<SelfAssessmentAttributeInfo> getDeletedGrowths() {
		return deletedGrowths;
	}

	public void setDeletedGrowths(List<SelfAssessmentAttributeInfo> deletedGrowths) {
		this.deletedGrowths = deletedGrowths;
	}

	public GoalPlanInfo getGoalPlanInfo() {
		return goalPlanInfo;
	}

	public void setGoalPlanInfo(GoalPlanInfo goalPlanInfo) {
		this.goalPlanInfo = goalPlanInfo;
	}

	public boolean isCompleteSelfAssessment() {
		return completeSelfAssessment;
	}

	public void setCompleteSelfAssessment(boolean completeSelfAssessment) {
		this.completeSelfAssessment = completeSelfAssessment;
	}

	public boolean isEnableCompletion() {
		return enableCompletion;
	}

	public void setEnableCompletion(boolean enableCompletion) {
		this.enableCompletion = enableCompletion;
	}

	public boolean isCanAddStrengths() {
		return canAddStrengths;
	}

	public void setCanAddStrengths(boolean canAddStrengths) {
		this.canAddStrengths = canAddStrengths;
	}

	public boolean isCanAddGrowths() {
		return canAddGrowths;
	}

	public void setCanAddGrowths(boolean canAddGrowths) {
		this.canAddGrowths = canAddGrowths;
	}

	
	
	
	
	
	
	

}
