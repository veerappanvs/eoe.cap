package edu.mass.doe.cap.dataservice.entity;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "CP006CST_CYCLE_STATREASON_TYP")
@AttributeOverrides({
@AttributeOverride(name = "createDate", column = @Column(name = "CST_CREATE_DATE")),
@AttributeOverride(name = "createUser", column = @Column(name = "CST_CREATED_BY")),
@AttributeOverride(name = "modifyDate", column = @Column(name = "CST_UPDATE_DATE")),
@AttributeOverride(name = "modifyUser", column = @Column(name = "CST_UPDATED_BY"))
})

public class StatusReasonType extends BaseJPAEntity  {
	
	@Id
	@Column(name="CST_STATUS_CODE")	
	private String typeCode;
	
	@Column(name="CST_STATUS_DESC")	
	private String typeDesc;
	
	@Column(name="CST_EXP_DATE")	
	private Date expDate;
	
	@Column(name="CST_EFF_DATE")	
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
		if (!(obj instanceof StatusReasonType))
			return false;
		StatusReasonType other = (StatusReasonType) obj;
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
		return "StatusReasonType [typeCode=" + typeCode + ", typeDesc=" + typeDesc + ", expDate=" + expDate
				+ ", effDate=" + effDate + "]";
	}
	
	
	

}
