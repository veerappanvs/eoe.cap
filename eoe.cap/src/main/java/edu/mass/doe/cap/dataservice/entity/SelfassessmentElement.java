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
@Table(name = "CP0058SEL_SELFASSESS_ELEMENT")
@AttributeOverrides({
@AttributeOverride(name = "createDate", column = @Column(name = "SEL_CREATE_DATE")),
@AttributeOverride(name = "createUser", column = @Column(name = "SEL_CREATED_BY")),
@AttributeOverride(name = "modifyDate", column = @Column(name = "SEL_UPDATE_DATE")),
@AttributeOverride(name = "modifyUser", column = @Column(name = "SEL_UPDATED_BY"))
})
public class SelfassessmentElement extends BaseJPAEntity  {
	
	@Id
	@Column(name="SEL_ASSMT_ELEMENT_ID")
	@SequenceGenerator(name = "SEL_PK_GENERATOR", sequenceName = "CP0058SEL_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEL_PK_GENERATOR")
	private Long id;
	
	
	
	@Column(name="SEL_ELEMENT_CODE")	
	private String elementCode;

	@Column(name="SEL_EXP_DATE")	
	private Date expDate;	
	
	@Column(name="SEL_EFF_DATE")	
	private Date effDate;
	
	

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SEL_ATTRIBUTE_ID", insertable = true, updatable = false,nullable=false)
	private SelfAssessmentAttribute selfAssessmentAttribute;

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

	public SelfAssessmentAttribute getSelfAssessmentAttribute() {
		return selfAssessmentAttribute;
	}

	public void setSelfAssessmentAttribute(SelfAssessmentAttribute selfAssessmentAttribute) {
		this.selfAssessmentAttribute = selfAssessmentAttribute;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((effDate == null) ? 0 : effDate.hashCode());
		result = prime * result + ((elementCode == null) ? 0 : elementCode.hashCode());
		result = prime * result + ((expDate == null) ? 0 : expDate.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((selfAssessmentAttribute == null) ? 0 : selfAssessmentAttribute.hashCode());
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
		if (!(obj instanceof SelfassessmentElement))
			return false;
		SelfassessmentElement other = (SelfassessmentElement) obj;
		if (effDate == null) {
			if (other.effDate != null)
				return false;
		} else if (!effDate.equals(other.effDate))
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (selfAssessmentAttribute == null) {
			if (other.selfAssessmentAttribute != null)
				return false;
		} else if (!selfAssessmentAttribute.equals(other.selfAssessmentAttribute))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SelfassessmentElement [id=" + id + ", elementCode=" + elementCode + ", expDate=" + expDate
				+ ", effDate=" + effDate + ", selfAssessmentAttribute=" + selfAssessmentAttribute + "]";
	}


}
