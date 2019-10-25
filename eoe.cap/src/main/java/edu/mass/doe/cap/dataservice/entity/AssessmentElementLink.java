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
@Table(name = "CP059AEL_ASMT_ELEMENT_LINK")
@AttributeOverrides({
@AttributeOverride(name = "createDate", column = @Column(name = "AEL_CREATE_DATE")),
@AttributeOverride(name = "createUser", column = @Column(name = "AEL_CREATED_BY")),
@AttributeOverride(name = "modifyDate", column = @Column(name = "AEL_UPDATE_DATE")),
@AttributeOverride(name = "modifyUser", column = @Column(name = "AEL_UPDATED_BY"))
})
public class AssessmentElementLink extends BaseJPAEntity  {
	
	@Id
	@Column(name="AEL_ELEMENT_LINK_ID")
	@SequenceGenerator(name = "AEL_PK_GENERATOR", sequenceName = "CP059AEL_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AEL_PK_GENERATOR")
	private Long linkId;
	
	@Column(name="AEL_AAT_ATTRIBUTE_TYPE")
	private String attributeType;
	
	@Column(name="AEL_RET_ELEMENT_CODE")
	private String elementCode;

	
	@Column(name="AEL_EXP_DATE")
	private Date expDate;
	
	@Column(name="AEL_EFF_DATE")
	private Date effdate;
	

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AEL_AAT_ASSESSMENT_ID", insertable = true, updatable = false,nullable=false)
	private Assessment assessment;
	

	public Long getLinkId() {
		return linkId;
	}

	public void setLinkId(Long linkId) {
		this.linkId = linkId;
	}

	public String getAttributeType() {
		return attributeType;
	}

	public void setAttributeType(String attributeType) {
		this.attributeType = attributeType;
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

	public Assessment getAssessment() {
		return assessment;
	}

	public void setAssessment(Assessment assessment) {
		this.assessment = assessment;
	}

	public String getElementCode() {
		return elementCode;
	}

	public void setElementCode(String elementCode) {
		this.elementCode = elementCode;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((assessment == null) ? 0 : assessment.hashCode());
		result = prime * result + ((attributeType == null) ? 0 : attributeType.hashCode());
		result = prime * result + ((effdate == null) ? 0 : effdate.hashCode());
		result = prime * result + ((elementCode == null) ? 0 : elementCode.hashCode());
		result = prime * result + ((expDate == null) ? 0 : expDate.hashCode());
		result = prime * result + ((linkId == null) ? 0 : linkId.hashCode());
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
		if (!(obj instanceof AssessmentElementLink))
			return false;
		AssessmentElementLink other = (AssessmentElementLink) obj;
		if (assessment == null) {
			if (other.assessment != null)
				return false;
		} else if (!assessment.equals(other.assessment))
			return false;
		if (attributeType == null) {
			if (other.attributeType != null)
				return false;
		} else if (!attributeType.equals(other.attributeType))
			return false;
		if (effdate == null) {
			if (other.effdate != null)
				return false;
		} else if (!effdate.equals(other.effdate))
			return false;
		if (elementCode == null) {
			if (other.elementCode != null)
				return false;
		} else if (!elementCode.equals(other.elementCode))
			return false;
		if (expDate == null) {
			if (other.expDate != null)
				return false;
		} else if (!expDate.equals(other.expDate))
			return false;
		if (linkId == null) {
			if (other.linkId != null)
				return false;
		} else if (!linkId.equals(other.linkId))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AssessmentElementLink [linkId=" + linkId + ", attributeType=" + attributeType + ", elementCode="
				+ elementCode + ", expDate=" + expDate + ", effdate=" + effdate + ", assessment=" + assessment + "]";
	}

	
	
}
