package edu.mass.doe.cap.dataservice.entity;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CP005ATP_ASSESMENT_TYPE")
@AttributeOverrides({
@AttributeOverride(name = "createDate", column = @Column(name = "AST_CREATE_DATE")),
@AttributeOverride(name = "createUser", column = @Column(name = "AST_CREATED_BY")),
@AttributeOverride(name = "modifyDate", column = @Column(name = "AST_UPDATE_DATE")),
@AttributeOverride(name = "modifyUser", column = @Column(name = "AST_UPDATED_BY"))
})
public class AssessmentType  extends BaseJPAEntity {


	@Id
	@Column(name="ATP_ASMT_TYPE_CODE")	
	private String typeCode;
	
	@Column(name="ATP_ASMT_TYPE_DESC")	
	private String typeDesc;
	
	@Column(name="ATP_ASMT_REPORTABLE")	
	private Character reportable;
		
	@Column(name="AST_EXP_DATE")	
	private Date expDate;	
	
	@Column(name="AST_EFF_DATE")	
	private Date effDate;

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getTypeDesc() {
		return typeDesc;
	}

	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
	}

	public Character getReportable() {
		return reportable;
	}

	public void setReportable(Character reportable) {
		this.reportable = reportable;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((effDate == null) ? 0 : effDate.hashCode());
		result = prime * result + ((expDate == null) ? 0 : expDate.hashCode());
		result = prime * result + ((reportable == null) ? 0 : reportable.hashCode());
		result = prime * result + ((typeCode == null) ? 0 : typeCode.hashCode());
		result = prime * result + ((typeDesc == null) ? 0 : typeDesc.hashCode());
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
		if (!(obj instanceof AssessmentType))
			return false;
		AssessmentType other = (AssessmentType) obj;
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
		if (reportable == null) {
			if (other.reportable != null)
				return false;
		} else if (!reportable.equals(other.reportable))
			return false;
		if (typeCode == null) {
			if (other.typeCode != null)
				return false;
		} else if (!typeCode.equals(other.typeCode))
			return false;
		if (typeDesc == null) {
			if (other.typeDesc != null)
				return false;
		} else if (!typeDesc.equals(other.typeDesc))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AssessmentType [typeCode=" + typeCode + ", typeDesc=" + typeDesc + ", reportable=" + reportable
				+ ", expDate=" + expDate + ", effDate=" + effDate + "]";
	}
	
	
	
	
}
