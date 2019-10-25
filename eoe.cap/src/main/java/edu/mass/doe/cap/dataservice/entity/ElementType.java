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
@Table(name = "CP004RET_RUBRIC_ELEMENT_TYP")
@AttributeOverrides({
@AttributeOverride(name = "createDate", column = @Column(name = "RET_CREATE_DATE")),
@AttributeOverride(name = "createUser", column = @Column(name = "RET_CREATED_BY")),
@AttributeOverride(name = "modifyDate", column = @Column(name = "RET_UPDATE_DATE")),
@AttributeOverride(name = "modifyUser", column = @Column(name = "RET_UPDATED_BY"))
})
public class ElementType extends BaseJPAEntity {
	
	
	@Id
	@Column(name="RET_ELEMENT_CODE")	
	private String elementCode;
	
	@Column(name="RET_ELEMENT_LABEL")	
	private String label;
	
	@Column(name="RET_ELEMENT_LONG_DESC")	
	private String longDesc;
	
	@Column(name="RET_ELEMENT_ALT_LONG_DESC")	
	private String altDesc;
	
	
	@Column(name="RET_EXP_DATE")	
	private Date expDate;	
	
	@Column(name="RET_EFF_DATE")	
	private Date effDate;
	
	
	public String getElementCode() {
		return elementCode;
	}

	public void setElementCode(String elementCode) {
		this.elementCode = elementCode;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getLongDesc() {
		return longDesc;
	}

	public void setLongDesc(String longDesc) {
		this.longDesc = longDesc;
	}

	public String getAltDesc() {
		return altDesc;
	}

	public void setAltDesc(String altDesc) {
		this.altDesc = altDesc;
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
		result = prime * result + ((altDesc == null) ? 0 : altDesc.hashCode());
		result = prime * result + ((effDate == null) ? 0 : effDate.hashCode());
		result = prime * result + ((elementCode == null) ? 0 : elementCode.hashCode());
		result = prime * result + ((expDate == null) ? 0 : expDate.hashCode());
		result = prime * result + ((label == null) ? 0 : label.hashCode());
		result = prime * result + ((longDesc == null) ? 0 : longDesc.hashCode());
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
		if (!(obj instanceof ElementType))
			return false;
		ElementType other = (ElementType) obj;
		if (altDesc == null) {
			if (other.altDesc != null)
				return false;
		} else if (!altDesc.equals(other.altDesc))
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
		if (label == null) {
			if (other.label != null)
				return false;
		} else if (!label.equals(other.label))
			return false;
		if (longDesc == null) {
			if (other.longDesc != null)
				return false;
		} else if (!longDesc.equals(other.longDesc))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ElementType [elementCode=" + elementCode + ", label=" + label + ", longDesc=" + longDesc + ", altDesc="
				+ altDesc + ", expDate=" + expDate + ", effDate=" + effDate + "]";
	}

	

		
	

}
