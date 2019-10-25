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
@Table(name = "CP062GFE_GOAL_FOCUS_ELEMENT")
@AttributeOverrides({
@AttributeOverride(name = "createDate", column = @Column(name = "GFE_CREATE_DATE")),
@AttributeOverride(name = "createUser", column = @Column(name = "GFE_CREATED_BY")),
@AttributeOverride(name = "modifyDate", column = @Column(name = "GFE_UPDATE_DATE")),
@AttributeOverride(name = "modifyUser", column = @Column(name = "GFE_UPDATED_BY"))
})
public class GoalPlanElement extends BaseJPAEntity {
	
	@Id
	@Column(name="GFE_GOAL_FOCUS_ELEMENT_ID")
	@SequenceGenerator(name = "GFE_PK_GENERATOR", sequenceName = "CP062GFE_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GFE_PK_GENERATOR")
	private Long id;
	
	
	@Column(name="GFE_ELEMENT_CODE")	
	private String elementCode;

	@Column(name="GFE_EXP_DATE")	
	private Date expDate;	
	
	@Column(name="GFE_EFF_DATE")	
	private Date effDate;
	
	

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GFE_GOAL_PLAN_ID", insertable = true, updatable = false,nullable=false)
	private GoalPlan goalPlan;
	

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

	public GoalPlan getGoalPlan() {
		return goalPlan;
	}

	public void setGoalPlan(GoalPlan goalPlan) {
		this.goalPlan = goalPlan;
	}



}
