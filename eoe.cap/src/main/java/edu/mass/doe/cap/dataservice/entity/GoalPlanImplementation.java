package edu.mass.doe.cap.dataservice.entity;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "CP061GPA_GOAL_PLAN_ACTION")
@AttributeOverrides({
@AttributeOverride(name = "createDate", column = @Column(name = "GPA_CREATE_DATE")),
@AttributeOverride(name = "createUser", column = @Column(name = "GPA_CREATED_BY")),
@AttributeOverride(name = "modifyDate", column = @Column(name = "GPA_UPDATE_DATE")),
@AttributeOverride(name = "modifyUser", column = @Column(name = "GPA_UPDATED_BY"))
})
public class GoalPlanImplementation  extends BaseJPAEntity {
	
	
	
	@Id
	@Column(name="GPA_GOAL_PLAN_ACTION_ID")
	@SequenceGenerator(name = "GPA_PK_GENERATOR", sequenceName = "CP061GPA_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GPA_PK_GENERATOR")
	private Long id;
	
	@Column(name="GPA_ACTION_EVIDENCE")
	private String actionEvidence;
	
	@Column(name="GPA_ACTION_SUPPORT")
	private String actionSupport;
	
	@Column(name="GPA_ACTION_TIMELINE")
	private String actionTimeline;
	
	@Column(name="GPA_ACTION_DESC")
	private String actionDesc;
	
	
	@Column(name="GPA_EXP_DATE")	
	private Date expDate;	
	
	@Column(name="GPA_EFF_DATE")	
	private Date effDate;
	
	@Column(name="GPA_ACTION_NUMBER")	
	private Long actionNumber;
	
	

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GPA_GOAL_PLAN_ID", insertable = true, updatable = false,nullable=false)
	private GoalPlan goalPlan;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getActionEvidence() {
		return actionEvidence;
	}

	public void setActionEvidence(String actionEvidence) {
		this.actionEvidence = actionEvidence;
	}

	public String getActionSupport() {
		return actionSupport;
	}

	public void setActionSupport(String actionSupport) {
		this.actionSupport = actionSupport;
	}

	public String getActionTimeline() {
		return actionTimeline;
	}

	public void setActionTimeline(String actionTimeline) {
		this.actionTimeline = actionTimeline;
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

	public GoalPlan getGoalPlan() {
		return goalPlan;
	}

	public void setGoalPlan(GoalPlan goalPlan) {
		this.goalPlan = goalPlan;
	}

	public String getActionDesc() {
		return actionDesc;
	}

	public void setActionDesc(String actionDesc) {
		this.actionDesc = actionDesc;
	}

	public Long getActionNumber() {
		return actionNumber;
	}

	public void setActionNumber(Long actionNumber) {
		this.actionNumber = actionNumber;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((actionDesc == null) ? 0 : actionDesc.hashCode());
		result = prime * result + ((actionEvidence == null) ? 0 : actionEvidence.hashCode());
		result = prime * result + ((actionNumber == null) ? 0 : actionNumber.hashCode());
		result = prime * result + ((actionSupport == null) ? 0 : actionSupport.hashCode());
		result = prime * result + ((actionTimeline == null) ? 0 : actionTimeline.hashCode());
		result = prime * result + ((effDate == null) ? 0 : effDate.hashCode());
		result = prime * result + ((expDate == null) ? 0 : expDate.hashCode());
		result = prime * result + ((goalPlan == null) ? 0 : goalPlan.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		if (!(obj instanceof GoalPlanImplementation))
			return false;
		GoalPlanImplementation other = (GoalPlanImplementation) obj;
		if (actionDesc == null) {
			if (other.actionDesc != null)
				return false;
		} else if (!actionDesc.equals(other.actionDesc))
			return false;
		if (actionEvidence == null) {
			if (other.actionEvidence != null)
				return false;
		} else if (!actionEvidence.equals(other.actionEvidence))
			return false;
		if (actionNumber == null) {
			if (other.actionNumber != null)
				return false;
		} else if (!actionNumber.equals(other.actionNumber))
			return false;
		if (actionSupport == null) {
			if (other.actionSupport != null)
				return false;
		} else if (!actionSupport.equals(other.actionSupport))
			return false;
		if (actionTimeline == null) {
			if (other.actionTimeline != null)
				return false;
		} else if (!actionTimeline.equals(other.actionTimeline))
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
		if (goalPlan == null) {
			if (other.goalPlan != null)
				return false;
		} else if (!goalPlan.equals(other.goalPlan))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "GoalPlanImplementation [id=" + id + ", actionEvidence=" + actionEvidence + ", actionSupport="
				+ actionSupport + ", actionTimeline=" + actionTimeline + ", actionDesc=" + actionDesc + ", expDate="
				+ expDate + ", effDate=" + effDate + ", actionNumber=" + actionNumber + ", goalPlan=" + goalPlan + "]";
	}
	

}
