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
@Table(name = "CP057SAA_SELF_ASMT_ATTRIBUTES")
@AttributeOverrides({
@AttributeOverride(name = "createDate", column = @Column(name = "SAA_CREATE_DATE")),
@AttributeOverride(name = "createUser", column = @Column(name = "SAA_CREATED_BY")),
@AttributeOverride(name = "modifyDate", column = @Column(name = "SAA_UPDATE_DATE")),
@AttributeOverride(name = "modifyUser", column = @Column(name = "SAA_UPDATED_BY"))
})
public class SelfAssessmentAttribute extends BaseJPAEntity  {

	@Id
	@Column(name="SAA_ATTRIBUTE_ID")
	@SequenceGenerator(name = "SAA_PK_GENERATOR", sequenceName = "CP057SAA_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SAA_PK_GENERATOR")
	private Long id;
	
	@Column(name="SAA_ATTRIBUTE_TYPE_CODE")	
	private String typeCode;
	
	@Column(name="SAA_ATTRIBUTE_AREA")	
	private String area;
	
	@Column(name="SAA_ATTRIBUTE_RATIONALE")	
	private String rationale;
	
	@Column(name="SAA_EXP_DATE")	
	private Date expDate;	

	@Column(name="SAA_EFF_DATE")	
	private Date effDate;
	
	@Column(name="SAA_ATTRIBUTE_NUMBER")	
	private Long attributeNumber;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SAA_ASSESSMENT_ID", insertable = true, updatable = false,nullable=false)
	private Assessment assessment;
	
	@OneToMany(mappedBy = "selfAssessmentAttribute", cascade = CascadeType.PERSIST,fetch=FetchType.LAZY)
	@Where(clause = "SEL_EXP_DATE is null")
	private List<SelfassessmentElement > selfassessmentElements;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
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

	public String getRationale() {
		return rationale;
	}

	public void setRationale(String rationale) {
		this.rationale = rationale;
	}

	public Assessment getAssessment() {
		return assessment;
	}

	public void setAssessment(Assessment assessment) {
		this.assessment = assessment;
	}

	public List<SelfassessmentElement> getSelfassessmentElements() {
		return selfassessmentElements;
	}

	public void setSelfassessmentElements(List<SelfassessmentElement> selfassessmentElements) {
		this.selfassessmentElements = selfassessmentElements;
	}

	public Long getAttributeNumber() {
		return attributeNumber;
	}

	public void setAttributeNumber(Long attributeNumber) {
		this.attributeNumber = attributeNumber;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((area == null) ? 0 : area.hashCode());
		result = prime * result + ((assessment == null) ? 0 : assessment.hashCode());
		result = prime * result + ((attributeNumber == null) ? 0 : attributeNumber.hashCode());
		result = prime * result + ((effDate == null) ? 0 : effDate.hashCode());
		result = prime * result + ((expDate == null) ? 0 : expDate.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((rationale == null) ? 0 : rationale.hashCode());
		result = prime * result + ((selfassessmentElements == null) ? 0 : selfassessmentElements.hashCode());
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
		if (!(obj instanceof SelfAssessmentAttribute))
			return false;
		SelfAssessmentAttribute other = (SelfAssessmentAttribute) obj;
		if (area == null) {
			if (other.area != null)
				return false;
		} else if (!area.equals(other.area))
			return false;
		if (assessment == null) {
			if (other.assessment != null)
				return false;
		} else if (!assessment.equals(other.assessment))
			return false;
		if (attributeNumber == null) {
			if (other.attributeNumber != null)
				return false;
		} else if (!attributeNumber.equals(other.attributeNumber))
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (rationale == null) {
			if (other.rationale != null)
				return false;
		} else if (!rationale.equals(other.rationale))
			return false;
		if (selfassessmentElements == null) {
			if (other.selfassessmentElements != null)
				return false;
		} else if (!selfassessmentElements.equals(other.selfassessmentElements))
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
		return "SelfAssessmentAttribute [id=" + id + ", typeCode=" + typeCode + ", area=" + area + ", rationale="
				+ rationale + ", expDate=" + expDate + ", effDate=" + effDate + ", attributeNumber=" + attributeNumber
				+ ", assessment=" + assessment + ", selfassessmentElements=" + selfassessmentElements + "]";
	}
	
	
	
}
