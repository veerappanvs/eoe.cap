package edu.mass.doe.cap.dataservice.entity;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CP018EEV_ELEMENT_EVIDENCE")
@AttributeOverrides({
@AttributeOverride(name = "createDate", column = @Column(name = "EEV_CREATE_DATE")),
@AttributeOverride(name = "createUser", column = @Column(name = "EEV_CREATED_BY")),
@AttributeOverride(name = "modifyDate", column = @Column(name = "EEV_UPDATE_DATE")),
@AttributeOverride(name = "modifyUser", column = @Column(name = "EEV_UPDATED_BY"))
})
public class EvidenceSourceFocus {
	
	@Id
	@Column(name="EEV_ID")	
	private Long eevId;
	
	@Column(name="EEV_EVT_EVIDENCE_CODE")	
	private String evidenceCode;
	
		
	@Column(name="EEV_RET_ELEMENT_CODE")	
	private String elementCode;
	
	@Column(name="EEV_EXP_DATE")	
	private Date expDate;
	
	@Column(name="EEV_EFF_DATE")	
	private Date effDate;

	public Long getEevId() {
		return eevId;
	}

	public void setEevId(Long eevId) {
		this.eevId = eevId;
	}

	public String getEvidenceCode() {
		return evidenceCode;
	}

	public void setEvidenceCode(String evidenceCode) {
		this.evidenceCode = evidenceCode;
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
		result = prime * result + ((eevId == null) ? 0 : eevId.hashCode());
		result = prime * result + ((effDate == null) ? 0 : effDate.hashCode());
		result = prime * result + ((elementCode == null) ? 0 : elementCode.hashCode());
		result = prime * result + ((evidenceCode == null) ? 0 : evidenceCode.hashCode());
		result = prime * result + ((expDate == null) ? 0 : expDate.hashCode());
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
		if (!(obj instanceof EvidenceSourceFocus))
			return false;
		EvidenceSourceFocus other = (EvidenceSourceFocus) obj;
		if (eevId == null) {
			if (other.eevId != null)
				return false;
		} else if (!eevId.equals(other.eevId))
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
		if (evidenceCode == null) {
			if (other.evidenceCode != null)
				return false;
		} else if (!evidenceCode.equals(other.evidenceCode))
			return false;
		if (expDate == null) {
			if (other.expDate != null)
				return false;
		} else if (!expDate.equals(other.expDate))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "EvidenceSourceFocus [eevId=" + eevId + ", evidenceCode=" + evidenceCode + ", elementCode=" + elementCode
				+ ", expDate=" + expDate + ", effDate=" + effDate + "]";
	}

	

	

}
