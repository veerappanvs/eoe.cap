package edu.mass.doe.cap.dataservice.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.OrderBy;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "CP060GOP_GOAL_PLAN")
@AttributeOverrides({
@AttributeOverride(name = "createDate", column = @Column(name = "GOP_CREATE_DATE")),
@AttributeOverride(name = "createUser", column = @Column(name = "GOP_CREATED_BY")),
@AttributeOverride(name = "modifyDate", column = @Column(name = "GOP_UPDATE_DATE")),
@AttributeOverride(name = "modifyUser", column = @Column(name = "GOP_UPDATED_BY"))
})
public class GoalPlan extends BaseJPAEntity  {

	@Id
	@Column(name="GOP_GOAL_PLAN_ID")
	@SequenceGenerator(name = "GOP_PK_GENERATOR", sequenceName = "CP060GOP_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GOP_PK_GENERATOR")
	private Long planId;
	
	@Column(name="GOP_GOAL_PLAN_TYPE_CODE")
	private String typeCode;
	
	@Column(name="GOP_PPG_GOAL_DESC")
	private String goalDesc;
	
	@Column(name="GOP_PPG_IMPORTANCE")
	private String importance;
	
	@Column(name="GOP_PPG_ACTION")
	private String action;
	
	
	@Column(name="GOP_PPG_SUPPORT")
	private String support;
	
	@Column(name="GOP_LEARNING_MEASURE")
	private String learningMeasure;
	
	
	@Column(name="GOP_SMART_SKILLS_ACQUIRED")
	private String acquired;
	
	@Column(name="GOP_SMART_SUCCESS_EVAL")
	private String successEval;
	
	@Column(name="GOP_SMART_PROGRESS_MEASURE")
	private String progressMeasure;
	
	@Column(name="GOP_SMART_REALISTIC_MEASURE")
	private String realisticMeasure;
	
	@Column(name="GOP_SMART_ACHIEVEMENT_TIME")
	private String achievementTime;
	
	@Column(name="GOP_FEEDBACK")
	private String feedback;
	
	@Column(name="GOP_COMPLETE")
	private Character complete;
	
	@Column(name="GOP_EXP_DATE")
	private Date expDate;
	
	@Column(name="GOP_EFF_DATE")
	private Date effDate;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GOP_CYCLE_ID", insertable = true, updatable = false,nullable=false)
	private CapCycle capCycle;	
	

	@OneToMany(mappedBy = "goalPlan", cascade = CascadeType.PERSIST,fetch=FetchType.LAZY)
	@Where(clause = "GFE_EXP_DATE is null")
	private List<GoalPlanElement > goalPlanElements;
	
	@OneToMany(mappedBy = "goalPlan", cascade = CascadeType.PERSIST,fetch=FetchType.LAZY)
	@Where(clause = "GPA_EXP_DATE is null")
	@OrderBy(clause="GPA_ACTION_NUMBER")
	private List<GoalPlanImplementation > goalPlanImplementations;
	
	@OneToMany(mappedBy = "goalPlan", cascade = CascadeType.PERSIST,fetch=FetchType.LAZY)
	@Where(clause = "GLM_EXP_DATE is null")
	private List<GoalLearningMeasure > GoalLearningMeasure;

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

	public String getAcquired() {
		return acquired;
	}

	public void setAcquired(String acquired) {
		this.acquired = acquired;
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

	public List<GoalPlanElement> getGoalPlanElements() {
		return goalPlanElements;
	}

	public void setGoalPlanElements(List<GoalPlanElement> goalPlanElements) {
		this.goalPlanElements = goalPlanElements;
	}

	
	public List<GoalPlanImplementation> getGoalPlanImplementations() {
		return goalPlanImplementations;
	}

	public void setGoalPlanImplementations(List<GoalPlanImplementation> goalPlanImplementations) {
		this.goalPlanImplementations = goalPlanImplementations;
	}

	public List<GoalLearningMeasure> getGoalLearningMeasure() {
		return GoalLearningMeasure;
	}

	public void setGoalLearningMeasure(List<GoalLearningMeasure> goalLearningMeasure) {
		GoalLearningMeasure = goalLearningMeasure;
	}

	public Long getPlanId() {
		return planId;
	}

	public void setPlanId(Long planId) {
		this.planId = planId;
	}

	public CapCycle getCapCycle() {
		return capCycle;
	}

	public void setCapCycle(CapCycle capCycle) {
		this.capCycle = capCycle;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((GoalLearningMeasure == null) ? 0 : GoalLearningMeasure.hashCode());
		result = prime * result + ((achievementTime == null) ? 0 : achievementTime.hashCode());
		result = prime * result + ((acquired == null) ? 0 : acquired.hashCode());
		result = prime * result + ((action == null) ? 0 : action.hashCode());
		result = prime * result + ((capCycle == null) ? 0 : capCycle.hashCode());
		result = prime * result + ((complete == null) ? 0 : complete.hashCode());
		result = prime * result + ((effDate == null) ? 0 : effDate.hashCode());
		result = prime * result + ((expDate == null) ? 0 : expDate.hashCode());
		result = prime * result + ((feedback == null) ? 0 : feedback.hashCode());
		result = prime * result + ((goalDesc == null) ? 0 : goalDesc.hashCode());
		result = prime * result + ((goalPlanElements == null) ? 0 : goalPlanElements.hashCode());
		result = prime * result + ((goalPlanImplementations == null) ? 0 : goalPlanImplementations.hashCode());
		result = prime * result + ((importance == null) ? 0 : importance.hashCode());
		result = prime * result + ((learningMeasure == null) ? 0 : learningMeasure.hashCode());
		result = prime * result + ((planId == null) ? 0 : planId.hashCode());
		result = prime * result + ((progressMeasure == null) ? 0 : progressMeasure.hashCode());
		result = prime * result + ((realisticMeasure == null) ? 0 : realisticMeasure.hashCode());
		result = prime * result + ((successEval == null) ? 0 : successEval.hashCode());
		result = prime * result + ((support == null) ? 0 : support.hashCode());
		result = prime * result + ((typeCode == null) ? 0 : typeCode.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof GoalPlan))
			return false;
		GoalPlan other = (GoalPlan) obj;
		if (GoalLearningMeasure == null) {
			if (other.GoalLearningMeasure != null)
				return false;
		} else if (!GoalLearningMeasure.equals(other.GoalLearningMeasure))
			return false;
		if (achievementTime == null) {
			if (other.achievementTime != null)
				return false;
		} else if (!achievementTime.equals(other.achievementTime))
			return false;
		if (acquired == null) {
			if (other.acquired != null)
				return false;
		} else if (!acquired.equals(other.acquired))
			return false;
		if (action == null) {
			if (other.action != null)
				return false;
		} else if (!action.equals(other.action))
			return false;
		if (capCycle == null) {
			if (other.capCycle != null)
				return false;
		} else if (!capCycle.equals(other.capCycle))
			return false;
		if (complete == null) {
			if (other.complete != null)
				return false;
		} else if (!complete.equals(other.complete))
			return false;
		if (effDate == null) {
			if (other.effDate != null)
				return false;
		} else if (!effDate.equals(other.effDate))
			return false;
		if (expDate == null) {
			if (other.expDate != null)
				return false;
		} else if (!expDate.equals(other.expDate))
			return false;
		if (feedback == null) {
			if (other.feedback != null)
				return false;
		} else if (!feedback.equals(other.feedback))
			return false;
		if (goalDesc == null) {
			if (other.goalDesc != null)
				return false;
		} else if (!goalDesc.equals(other.goalDesc))
			return false;
		if (goalPlanElements == null) {
			if (other.goalPlanElements != null)
				return false;
		} else if (!goalPlanElements.equals(other.goalPlanElements))
			return false;
		if (goalPlanImplementations == null) {
			if (other.goalPlanImplementations != null)
				return false;
		} else if (!goalPlanImplementations.equals(other.goalPlanImplementations))
			return false;
		if (importance == null) {
			if (other.importance != null)
				return false;
		} else if (!importance.equals(other.importance))
			return false;
		if (learningMeasure == null) {
			if (other.learningMeasure != null)
				return false;
		} else if (!learningMeasure.equals(other.learningMeasure))
			return false;
		if (planId == null) {
			if (other.planId != null)
				return false;
		} else if (!planId.equals(other.planId))
			return false;
		if (progressMeasure == null) {
			if (other.progressMeasure != null)
				return false;
		} else if (!progressMeasure.equals(other.progressMeasure))
			return false;
		if (realisticMeasure == null) {
			if (other.realisticMeasure != null)
				return false;
		} else if (!realisticMeasure.equals(other.realisticMeasure))
			return false;
		if (successEval == null) {
			if (other.successEval != null)
				return false;
		} else if (!successEval.equals(other.successEval))
			return false;
		if (support == null) {
			if (other.support != null)
				return false;
		} else if (!support.equals(other.support))
			return false;
		if (typeCode == null) {
			if (other.typeCode != null)
				return false;
		} else if (!typeCode.equals(other.typeCode))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "GoalPlan [planId=" + planId + ", typeCode=" + typeCode + ", goalDesc=" + goalDesc + ", importance="
				+ importance + ", action=" + action + ", support=" + support + ", learningMeasure=" + learningMeasure
				+ ", acquired=" + acquired + ", successEval=" + successEval + ", progressMeasure=" + progressMeasure
				+ ", realisticMeasure=" + realisticMeasure + ", achievementTime=" + achievementTime + ", feedback="
				+ feedback + ", complete=" + complete + ", expDate=" + expDate + ", effDate=" + effDate + ", capCycle="
				+ capCycle + ", goalPlanElements=" + goalPlanElements + ", goalPlanImplementations="
				+ goalPlanImplementations + ", GoalLearningMeasure=" + GoalLearningMeasure + "]";
	}

	 
}
