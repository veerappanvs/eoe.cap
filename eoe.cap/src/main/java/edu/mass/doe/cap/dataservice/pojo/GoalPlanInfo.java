package edu.mass.doe.cap.dataservice.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GoalPlanInfo {
	
	private Long id;
	
	private Long cycleId;
	
	private String typeCode;
	
	private String goalDesc;

	private String importance;
	
	private String action;
	
	private String support;
	
	private String learningMeasure;

	private String skillsAcquired;

	private String successEval;
	
	private String progressMeasure;

	private String realisticMeasure;

	private String achievementTime;

	private String feedback;
	
	private Character complete;	
	
	private Date completeDate;
	
	private List<GoalElementInfo> elements;
	
	private List<ImplementationPlanInfo> implementationPlanInfos;
	
	private List<ImplementationPlanInfo> deletedImplementationPlanInfos=new ArrayList<ImplementationPlanInfo>();
	;
	
	private List<GoalLearningMeasureInfo> goalLearningMeasureInfos;
	
	private GoalPlanInfo prelimGoalPlanInfo;
	
	
	private boolean completeGoal;
	private boolean enableCompletion;
	private boolean canAddAction;

	
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getGoalDesc() {
		return goalDesc;
	}

	public void setGoalDesc(String goalDesc) {
		this.goalDesc = goalDesc;
	}

	public String getImportance() {
		return importance;
	}

	public void setImportance(String importance) {
		this.importance = importance;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getSupport() {
		return support;
	}

	public void setSupport(String support) {
		this.support = support;
	}

	public String getLearningMeasure() {
		return learningMeasure;
	}

	public void setLearningMeasure(String learningMeasure) {
		this.learningMeasure = learningMeasure;
	}

	public String getSkillsAcquired() {
		return skillsAcquired;
	}

	public void setSkillsAcquired(String skillsAcquired) {
		this.skillsAcquired = skillsAcquired;
	}

	public String getSuccessEval() {
		return successEval;
	}

	public void setSuccessEval(String successEval) {
		this.successEval = successEval;
	}

	public String getProgressMeasure() {
		return progressMeasure;
	}

	public void setProgressMeasure(String progressMeasure) {
		this.progressMeasure = progressMeasure;
	}

	public String getRealisticMeasure() {
		return realisticMeasure;
	}

	public void setRealisticMeasure(String realisticMeasure) {
		this.realisticMeasure = realisticMeasure;
	}

	public String getAchievementTime() {
		return achievementTime;
	}

	public void setAchievementTime(String achievementTime) {
		this.achievementTime = achievementTime;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public Character getComplete() {
		return complete;
	}

	public void setComplete(Character complete) {
		this.complete = complete;
	}

	public Date getCompleteDate() {
		return completeDate;
	}

	public void setCompleteDate(Date completeDate) {
		this.completeDate = completeDate;
	}

	public List<GoalElementInfo> getElements() {
		return elements;
	}

	public void setElements(List<GoalElementInfo> elements) {
		this.elements = elements;
	}

	public List<ImplementationPlanInfo> getImplementationPlanInfos() {
		return implementationPlanInfos;
	}

	public void setImplementationPlanInfos(List<ImplementationPlanInfo> implementationPlanInfos) {
		this.implementationPlanInfos = implementationPlanInfos;
	}

	public List<GoalLearningMeasureInfo> getGoalLearningMeasureInfos() {
		return goalLearningMeasureInfos;
	}

	public void setGoalLearningMeasureInfos(List<GoalLearningMeasureInfo> goalLearningMeasureInfos) {
		this.goalLearningMeasureInfos = goalLearningMeasureInfos;
	}

	public GoalPlanInfo getPrelimGoalPlanInfo() {
		return prelimGoalPlanInfo;
	}

	public void setPrelimGoalPlanInfo(GoalPlanInfo prelimGoalPlanInfo) {
		this.prelimGoalPlanInfo = prelimGoalPlanInfo;
	}

	public boolean isCompleteGoal() {
		return completeGoal;
	}

	public void setCompleteGoal(boolean completeGoal) {
		this.completeGoal = completeGoal;
	}

	public boolean isEnableCompletion() {
		return enableCompletion;
	}

	public void setEnableCompletion(boolean enableCompletion) {
		this.enableCompletion = enableCompletion;
	}

	public List<ImplementationPlanInfo> getDeletedImplementationPlanInfos() {
		return deletedImplementationPlanInfos;
	}

	public void setDeletedImplementationPlanInfos(List<ImplementationPlanInfo> deletedImplementationPlanInfos) {
		this.deletedImplementationPlanInfos = deletedImplementationPlanInfos;
	}

	public boolean isCanAddAction() {
		return canAddAction;
	}

	public void setCanAddAction(boolean canAddAction) {
		this.canAddAction = canAddAction;
	}



}
