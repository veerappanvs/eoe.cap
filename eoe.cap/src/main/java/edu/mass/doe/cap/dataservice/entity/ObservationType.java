package edu.mass.doe.cap.dataservice.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "CP002OBT_OSBSERVATION_TYP")
@AttributeOverrides({
@AttributeOverride(name = "createDate", column = @Column(name = "OBT_CREATE_DATE")),
@AttributeOverride(name = "createUser", column = @Column(name = "OBT_CREATED_BY")),
@AttributeOverride(name = "modifyDate", column = @Column(name = "OBT_UPDATE_DATE")),
@AttributeOverride(name = "modifyUser", column = @Column(name = "OBT_UPDATED_BY"))
})
public class ObservationType extends BaseJPAEntity {
	
	@Id
	@Column(name="OBT_OBSERVATION_TYPE_CODE")	
	private String typeCode;
	
	@Column(name="OBT_OBSERVATION_TYPE_DESC")	
	private String typeDesc;
	
	
	@Column(name="OBT_EXP_DATE")	
	private Date expDate;
	
	@Column(name="OBT_EFF_DATE")	
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
		if (!(obj instanceof ObservationType))
			return false;
		ObservationType other = (ObservationType) obj;
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
		return "ObservationType [typeCode=" + typeCode + ", typeDesc=" + typeDesc + ", expDate=" + expDate
				+ ", effDate=" + effDate + "]";
	}
	

}
