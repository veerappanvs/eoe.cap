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

import org.hibernate.annotations.Where;

@Entity
@Table(name = "CP056AST_ASSESSMENT")
@AttributeOverrides({
@AttributeOverride(name = "createDate", column = @Column(name = "AST_CREATE_DATE")),
@AttributeOverride(name = "createUser", column = @Column(name = "AST_CREATED_BY")),
@AttributeOverride(name = "modifyDate", column = @Column(name = "AST_UPDATE_DATE")),
@AttributeOverride(name = "modifyUser", column = @Column(name = "AST_UPDATED_BY"))
})
public class Assessment extends BaseJPAEntity {
	
	
	
	@Id
	@Column(name="AST_ASSESSMENT_ID")
	@SequenceGenerator(name = "AST_PK_GENERATOR", sequenceName = "CP056AST_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AST_PK_GENERATOR")
	private Long assessmentId;
	

	@Column(name="AST_ASMT_TYPE_CODE")
	private String typeCode;
	
	@Column(name="AST_LOCKED")
	private Character locked;
	
	@Column(name="AST_COMPLETED")
	private Character completed;
	
	@Column(name="AST_CALIBRATED_FEEDBACK")
	private String calFeedback;
	
	@Column(name="AST_ADDL_OBS_REQ")
	private Character addlObservationRequired;
	
	@Column(name="AST_REIN_FEEDBACK")
	private String reinFeedback;
	
	@Column(name="AST_REFINE_FEEDBACK")
	private String refineFeedback;
	
	@Column(name="AST_EVIDENCE_FEEDBACK")
	private String evidenceFeedback;
	
	@Column(name="AST_RECOMENDED_FOCUS")
	private String recommendedfocus;
	
	@Column(name="AST_SP_APPROVED")
	private Character spApproved;
	
	@Column(name="AST_SP_APPROVE_DATE")
	private Date spApprovedDate;
	
	@Column(name="AST_PS_APPROVED")
	private Character psApproved;
	
	@Column(name="AST_PS_APPROVE_DATE")
	private Date psApprovedDate;
	
	@Column(name="AST_EXP_DATE")
	private Date expDate;
	
	@Column(name="AST_EFF_DATE")
	private Date effdate;
	
	
	@OneToMany(mappedBy = "assessment", cascade = CascadeType.PERSIST,fetch=FetchType.LAZY)
	@Where(clause = "RUM_EXP_DATE is null")
	private List<RubricMap> rubrics;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AST_CYCLE_ID", insertable = true, updatable = false,nullable=false)
	private CapCycle capCycle;
	
	@OneToMany(mappedBy = "assessment", cascade = CascadeType.PERSIST,fetch=FetchType.LAZY)
	@Where(clause = "AEL_EXP_DATE is null")
	private List<AssessmentElementLink> assessmentElementLinks;
	
	
	@OneToMany(mappedBy = "assessment", cascade = CascadeType.PERSIST,fetch=FetchType.LAZY)
	@Where(clause = "REI_EXP_DATE is null")
	private List<RubricElement > rubricElements;
	
	@OneToMany(mappedBy = "assessment", cascade = CascadeType.PERSIST,fetch=FetchType.LAZY)
	@Where(clause = "SAA_EXP_DATE is null")
	private List<SelfAssessmentAttribute > selfAssessmentAttributes;
	
	

	public Long getAssessmentId() {
		return assessmentId;
	}

	public void setAssessmentId(Long assessmentId) {
		this.assessmentId = assessmentId;
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

	public Date getExpDate() {
		return expDate;
	}

	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}

	public Date getEffdate() {
		return effdate;
	}

	public void setEffdate(Date effdate) {
		this.effdate = effdate;
	}

	public List<RubricMap> getRubrics() {
		return rubrics;
	}

	public void setRubrics(List<RubricMap> rubrics) {
		this.rubrics = rubrics;
	}

	public CapCycle getCapCycle() {
		return capCycle;
	}

	public void setCapCycle(CapCycle capCycle) {
		this.capCycle = capCycle;
	}

	public List<AssessmentElementLink> getAssessmentElementLinks() {
		return assessmentElementLinks;
	}

	public void setAssessmentElementLinks(List<AssessmentElementLink> assessmentElementLinks) {
		this.assessmentElementLinks = assessmentElementLinks;
	}

	public List<RubricElement> getRubricElements() {
		return rubricElements;
	}

	public void setRubricElements(List<RubricElement> rubricElements) {
		this.rubricElements = rubricElements;
	}

	public List<SelfAssessmentAttribute> getSelfAssessmentAttributes() {
		return selfAssessmentAttributes;
	}

	public void setSelfAssessmentAttributes(List<SelfAssessmentAttribute> selfAssessmentAttributes) {
		this.selfAssessmentAttributes = selfAssessmentAttributes;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((addlObservationRequired == null) ? 0 : addlObservationRequired.hashCode());
		result = prime * result + ((assessmentElementLinks == null) ? 0 : assessmentElementLinks.hashCode());
		result = prime * result + ((assessmentId == null) ? 0 : assessmentId.hashCode());
		result = prime * result + ((calFeedback == null) ? 0 : calFeedback.hashCode());
		result = prime * result + ((capCycle == null) ? 0 : capCycle.hashCode());
		result = prime * result + ((completed == null) ? 0 : completed.hashCode());
		result = prime * result + ((effdate == null) ? 0 : effdate.hashCode());
		result = prime * result + ((evidenceFeedback == null) ? 0 : evidenceFeedback.hashCode());
		result = prime * result + ((expDate == null) ? 0 : expDate.hashCode());
		result = prime * result + ((locked == null) ? 0 : locked.hashCode());
		result = prime * result + ((psApproved == null) ? 0 : psApproved.hashCode());
		result = prime * result + ((psApprovedDate == null) ? 0 : psApprovedDate.hashCode());
		result = prime * result + ((recommendedfocus == null) ? 0 : recommendedfocus.hashCode());
		result = prime * result + ((refineFeedback == null) ? 0 : refineFeedback.hashCode());
		result = prime * result + ((reinFeedback == null) ? 0 : reinFeedback.hashCode());
		result = prime * result + ((rubricElements == null) ? 0 : rubricElements.hashCode());
		result = prime * result + ((rubrics == null) ? 0 : rubrics.hashCode());
		result = prime * result + ((selfAssessmentAttributes == null) ? 0 : selfAssessmentAttributes.hashCode());
		result = prime * result + ((spApproved == null) ? 0 : spApproved.hashCode());
		result = prime * result + ((spApprovedDate == null) ? 0 : spApprovedDate.hashCode());
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
		if (!(obj instanceof Assessment))
			return false;
		Assessment other = (Assessment) obj;
		if (addlObservationRequired == null) {
			if (other.addlObservationRequired != null)
				return false;
		} else if (!addlObservationRequired.equals(other.addlObservationRequired))
			return false;
		if (assessmentElementLinks == null) {
			if (other.assessmentElementLinks != null)
				return false;
		} else if (!assessmentElementLinks.equals(other.assessmentElementLinks))
			return false;
		if (assessmentId == null) {
			if (other.assessmentId != null)
				return false;
		} else if (!assessmentId.equals(other.assessmentId))
			return false;
		if (calFeedback == null) {
			if (other.calFeedback != null)
				return false;
		} else if (!calFeedback.equals(other.calFeedback))
			return false;
		if (capCycle == null) {
			if (other.capCycle != null)
				return false;
		} else if (!capCycle.equals(other.capCycle))
			return false;
		if (completed == null) {
			if (other.completed != null)
				return false;
		} else if (!completed.equals(other.completed))
			return false;
		if (effdate == null) {
			if (other.effdate != null)
				return false;
		} else if (!effdate.equals(other.effdate))
			return false;
		if (evidenceFeedback == null) {
			if (other.evidenceFeedback != null)
				return false;
		} else if (!evidenceFeedback.equals(other.evidenceFeedback))
			return false;
		if (expDate == null) {
			if (other.expDate != null)
				return false;
		} else if (!expDate.equals(other.expDate))
			return false;
		if (locked == null) {
			if (other.locked != null)
				return false;
		} else if (!locked.equals(other.locked))
			return false;
		if (psApproved == null) {
			if (other.psApproved != null)
				return false;
		} else if (!psApproved.equals(other.psApproved))
			return false;
		if (psApprovedDate == null) {
			if (other.psApprovedDate != null)
				return false;
		} else if (!psApprovedDate.equals(other.psApprovedDate))
			return false;
		if (recommendedfocus == null) {
			if (other.recommendedfocus != null)
				return false;
		} else if (!recommendedfocus.equals(other.recommendedfocus))
			return false;
		if (refineFeedback == null) {
			if (other.refineFeedback != null)
				return false;
		} else if (!refineFeedback.equals(other.refineFeedback))
			return false;
		if (reinFeedback == null) {
			if (other.reinFeedback != null)
				return false;
		} else if (!reinFeedback.equals(other.reinFeedback))
			return false;
		if (rubricElements == null) {
			if (other.rubricElements != null)
				return false;
		} else if (!rubricElements.equals(other.rubricElements))
			return false;
		if (rubrics == null) {
			if (other.rubrics != null)
				return false;
		} else if (!rubrics.equals(other.rubrics))
			return false;
		if (selfAssessmentAttributes == null) {
			if (other.selfAssessmentAttributes != null)
				return false;
		} else if (!selfAssessmentAttributes.equals(other.selfAssessmentAttributes))
			return false;
		if (spApproved == null) {
			if (other.spApproved != null)
				return false;
		} else if (!spApproved.equals(other.spApproved))
			return false;
		if (spApprovedDate == null) {
			if (other.spApprovedDate != null)
				return false;
		} else if (!spApprovedDate.equals(other.spApprovedDate))
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
		return "Assessment [assessmentId=" + assessmentId + ", typeCode=" + typeCode + ", locked=" + locked
				+ ", completed=" + completed + ", calFeedback=" + calFeedback + ", addlObservationRequired="
				+ addlObservationRequired + ", reinFeedback=" + reinFeedback + ", refineFeedback=" + refineFeedback
				+ ", evidenceFeedback=" + evidenceFeedback + ", recommendedfocus=" + recommendedfocus + ", spApproved="
				+ spApproved + ", spApprovedDate=" + spApprovedDate + ", psApproved=" + psApproved + ", psApprovedDate="
				+ psApprovedDate + ", expDate=" + expDate + ", effdate=" + effdate + ", rubrics=" + rubrics
				+ ", capCycle=" + capCycle + ", assessmentElementLinks=" + assessmentElementLinks + ", rubricElements="
				+ rubricElements + ", selfAssessmentAttributes=" + selfAssessmentAttributes + "]";
	}
		

	
	
	
}
