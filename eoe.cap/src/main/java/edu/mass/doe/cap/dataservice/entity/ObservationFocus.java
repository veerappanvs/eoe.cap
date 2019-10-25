package edu.mass.doe.cap.dataservice.entity;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CP0054OFE_OBS_FOCUS_ELEMENT")
@AttributeOverrides({
@AttributeOverride(name = "createDate", column = @Column(name = "OFE_CREATE_DATE")),
@AttributeOverride(name = "createUser", column = @Column(name = "OFE_CREATED_BY")),
@AttributeOverride(name = "modifyDate", column = @Column(name = "OFE_UPDATE_DATE")),
@AttributeOverride(name = "modifyUser", column = @Column(name = "OFE_UPDATED_BY"))
})
public class ObservationFocus {
	
	@Id
	@Column(name="OFE_FOCUS_ELEMENT_ID")	
	private Long focusId;
	
	@Column(name="OFE_OBSERVATION_TYPE_CODE")	
	private String typeCode;
	
	@Column(name="OFE_OBSERVATION_NUMBER")	
	private Long observationNumber;
	
	@Column(name="OFE_ELEMENT_CODE")	
	private String elementCode;
	
	@Column(name="OFE_EXP_DATE")	
	private Date expDate;
	
	@Column(name="OFE_EFF_DATE")	
	private Date effDate;

	public Long getFocusId() {
		return focusId;
	}

	public void setFocusId(Long focusId) {
		this.focusId = focusId;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public Long getObservationNumber() {
		return observationNumber;
	}

	public void setObservationNumber(Long observationNumber) {
		this.observationNumber = observationNumber;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((effDate == null) ? 0 : effDate.hashCode());
		result = prime * result + ((elementCode == null) ? 0 : elementCode.hashCode());
		result = prime * result + ((expDate == null) ? 0 : expDate.hashCode());
		result = prime * result + ((focusId == null) ? 0 : focusId.hashCode());
		result = prime * result + ((observationNumber == null) ? 0 : observationNumber.hashCode());
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
		if (obj == null)
			return false;
		if (!(obj instanceof ObservationFocus))
			return false;
		ObservationFocus other = (ObservationFocus) obj;
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
		if (focusId == null) {
			if (other.focusId != null)
				return false;
		} else if (!focusId.equals(other.focusId))
			return false;
		if (observationNumber == null) {
			if (other.observationNumber != null)
				return false;
		} else if (!observationNumber.equals(other.observationNumber))
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
		return "ObservationFocus [focusId=" + focusId + ", typeCode=" + typeCode + ", observationNumber="
				+ observationNumber + ", elementCode=" + elementCode + ", expDate=" + expDate + ", effDate=" + effDate
				+ "]";
	}

	

}
