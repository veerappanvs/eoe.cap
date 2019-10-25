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

import org.hibernate.annotations.Where;

@Entity
@Table(name = "CP006RDT_RUBRIC_DIMENSION_TYP")
@AttributeOverrides({
@AttributeOverride(name = "createDate", column = @Column(name = "RDT_CREATE_DATE")),
@AttributeOverride(name = "createUser", column = @Column(name = "RDT_CREATED_BY")),
@AttributeOverride(name = "modifyDate", column = @Column(name = "RDT_UPDATE_DATE")),
@AttributeOverride(name = "modifyUser", column = @Column(name = "RDT_UPDATED_BY"))
})
public class DimensionType extends BaseJPAEntity {
	

	
	
	@Id
	@Column(name="RDT_RUBRIC_DIMENSION_CODE")	
	private String dimensionCode;
	
	@Column(name="RDT_DIMENSION_DESC")	
	private String desc;
	
	@Column(name="RDT_DIMENSION_SHORT_DESC")	
	private String shortDesc;
	
	@Column(name="RDT_LONG_DESC")	
	private String longDesc;
	
	
	@Column(name="RDT_EXP_DATE")	
	private Date expDate;	
	
	@Column(name="RDT_EFF_DATE")	
	private Date effDate;
	

	public String getDimensionCode() {
		return dimensionCode;
	}

	public void setDimensionCode(String dimensionCode) {
		this.dimensionCode = dimensionCode;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getShortDesc() {
		return shortDesc;
	}

	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
	}

	public String getLongDesc() {
		return longDesc;
	}

	public void setLongDesc(String longDesc) {
		this.longDesc = longDesc;
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
		result = prime * result + ((desc == null) ? 0 : desc.hashCode());
		result = prime * result + ((dimensionCode == null) ? 0 : dimensionCode.hashCode());
		result = prime * result + ((effDate == null) ? 0 : effDate.hashCode());
		result = prime * result + ((expDate == null) ? 0 : expDate.hashCode());
		result = prime * result + ((longDesc == null) ? 0 : longDesc.hashCode());
		result = prime * result + ((shortDesc == null) ? 0 : shortDesc.hashCode());
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
		if (!(obj instanceof DimensionType))
			return false;
		DimensionType other = (DimensionType) obj;
		if (desc == null) {
			if (other.desc != null)
				return false;
		} else if (!desc.equals(other.desc))
			return false;
		if (dimensionCode == null) {
			if (other.dimensionCode != null)
				return false;
		} else if (!dimensionCode.equals(other.dimensionCode))
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
		if (longDesc == null) {
			if (other.longDesc != null)
				return false;
		} else if (!longDesc.equals(other.longDesc))
			return false;
		if (shortDesc == null) {
			if (other.shortDesc != null)
				return false;
		} else if (!shortDesc.equals(other.shortDesc))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DimensionType [dimensionCode=" + dimensionCode + ", desc=" + desc + ", shortDesc=" + shortDesc
				+ ", longDesc=" + longDesc + ", expDate=" + expDate + ", effDate=" + effDate + "]";
	}
	


}
