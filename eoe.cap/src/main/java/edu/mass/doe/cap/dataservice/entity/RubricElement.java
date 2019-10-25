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
@Table(name = "CP0064REI_RUBRIC_ELEMENT_INFO")
@AttributeOverrides({
@AttributeOverride(name = "createDate", column = @Column(name = "REI_CREATE_DATE")),
@AttributeOverride(name = "createUser", column = @Column(name = "REI_CREATED_BY")),
@AttributeOverride(name = "modifyDate", column = @Column(name = "REI_UPDATE_DATE")),
@AttributeOverride(name = "modifyUser", column = @Column(name = "REI_UPDATED_BY"))
})
public class RubricElement extends BaseJPAEntity  {
	
	@Id
	@Column(name="REI_ASSESS_ELEMENT_ID")
	@SequenceGenerator(name = "REI_PK_GENERATOR", sequenceName = "CP0064REI_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REI_PK_GENERATOR")
	private Long id;
		
	@Column(name="REI_ELEMENT_CODE")	
	private String elementCode;
	
	
	@Column(name="REI_RATIONALE")
	private String rationale;
	
	@Column(name="REI_MET_THRESHOLD")
	private Character metThreshold;
	
	@Column(name="REI_RUBRIC_COMPLETE")
	private Character complete;
	
	@Column(name="REI_EXP_DATE")	
	private Date expDate;	
	
	@Column(name="REI_EFF_DATE")	
	private Date effDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REI_ASSESSMENT_ID", insertable = true, updatable = false,nullable=false)
	private Assessment assessment;

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

	public String getRationale() {
		return rationale;
	}

	public void setRationale(String rationale) {
		this.rationale = rationale;
	}

	public Character getMetThreshold() {
		return metThreshold;
	}

	public void setMetThreshold(Character metThreshold) {
		this.metThreshold = metThreshold;
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

	public Assessment getAssessment() {
		return assessment;
	}

	public void setAssessment(Assessment assessment) {
		this.assessment = assessment;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((assessment == null) ? 0 : assessment.hashCode());
		result = prime * result + ((complete == null) ? 0 : complete.hashCode());
		result = prime * result + ((effDate == null) ? 0 : effDate.hashCode());
		result = prime * result + ((elementCode == null) ? 0 : elementCode.hashCode());
		result = prime * result + ((expDate == null) ? 0 : expDate.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((metThreshold == null) ? 0 : metThreshold.hashCode());
		result = prime * result + ((rationale == null) ? 0 : rationale.hashCode());
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
		if (!(obj instanceof RubricElement))
			return false;
		RubricElement other = (RubricElement) obj;
		if (assessment == null) {
			if (other.assessment != null)
				return false;
		} else if (!assessment.equals(other.assessment))
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
		if (metThreshold == null) {
			if (other.metThreshold != null)
				return false;
		} else if (!metThreshold.equals(other.metThreshold))
			return false;
		if (rationale == null) {
			if (other.rationale != null)
				return false;
		} else if (!rationale.equals(other.rationale))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RubricElement [id=" + id + ", elementCode=" + elementCode + ", rationale=" + rationale
				+ ", metThreshold=" + metThreshold + ", complete=" + complete + ", expDate=" + expDate + ", effDate="
				+ effDate + ", assessment=" + assessment + "]";
	}
	
	

}
