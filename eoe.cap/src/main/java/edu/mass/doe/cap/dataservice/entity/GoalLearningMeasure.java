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
@Table(name = "CP063GLM_GOAL_LEARNING_MEASURE")
@AttributeOverrides({
@AttributeOverride(name = "createDate", column = @Column(name = "GLM_CREATE_DATE")),
@AttributeOverride(name = "createUser", column = @Column(name = "GLM_CREATED_BY")),
@AttributeOverride(name = "modifyDate", column = @Column(name = "GLM_UPDATE_DATE")),
@AttributeOverride(name = "modifyUser", column = @Column(name = "GLM_UPDATED_BY"))
})
public class GoalLearningMeasure  extends BaseJPAEntity {
	
	
	
	@Id
	@Column(name="GLM_GOAL_LEARNING_MEASURE_ID")
	@SequenceGenerator(name = "GLM_PK_GENERATOR", sequenceName = "CP063GLM_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GLM_PK_GENERATOR")
	private Long id;
	
	@Column(name="GLM_IMPACT_CODE")
	private String impactCode;
	
	@Column(name="GLM_PARAMETER")
	private String parameter;
	
	
	@Column(name="GLM_EXP_DATE")	
	private Date expDate;	
	
	@Column(name="GLM_EFF_DATE")	
	private Date effDate;
	

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GLM_GOAL_PLAN_ID", insertable = true, updatable = false,nullable=false)
	private GoalPlan goalPlan;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getImpactCode() {
		return impactCode;
	}


	public void setImpactCode(String impactCode) {
		this.impactCode = impactCode;
	}


	public String getParameter() {
		return parameter;
	}


	public void setParameter(String parameter) {
		this.parameter = parameter;
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


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((effDate == null) ? 0 : effDate.hashCode());
		result = prime * result + ((expDate == null) ? 0 : expDate.hashCode());
		result = prime * result + ((goalPlan == null) ? 0 : goalPlan.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((impactCode == null) ? 0 : impactCode.hashCode());
		result = prime * result + ((parameter == null) ? 0 : parameter.hashCode());
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
		if (!(obj instanceof GoalLearningMeasure))
			return false;
		GoalLearningMeasure other = (GoalLearningMeasure) obj;
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
		if (impactCode == null) {
			if (other.impactCode != null)
				return false;
		} else if (!impactCode.equals(other.impactCode))
			return false;
		if (parameter == null) {
			if (other.parameter != null)
				return false;
		} else if (!parameter.equals(other.parameter))
			return false;
		return true;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "GoalLearningMeasure [id=" + id + ", impactCode=" + impactCode + ", parameter=" + parameter
				+ ", expDate=" + expDate + ", effDate=" + effDate + ", goalPlan=" + goalPlan + "]";
	}

	
	

}
