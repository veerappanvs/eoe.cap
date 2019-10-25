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
@Table(name = "CP053OBE_OBSERVATION_EVIDENCE")
@AttributeOverrides({
@AttributeOverride(name = "createDate", column = @Column(name = "OBE_CREATE_DATE")),
@AttributeOverride(name = "createUser", column = @Column(name = "OBE_CREATED_BY")),
@AttributeOverride(name = "modifyDate", column = @Column(name = "OBE_UPDATE_DATE")),
@AttributeOverride(name = "modifyUser", column = @Column(name = "OBE_UPDATED_BY"))
})
public class Evidence  extends BaseJPAEntity{
	
	@Id
	@Column(name="OBE_OBSERVATION_EVIDENCE_ID")
	@SequenceGenerator(name = "OBE_PK_GENERATOR", sequenceName = "CP053OBE_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OBE_PK_GENERATOR")
	private Long evidenceId;
	
	@Column(name="OBE_OBS_ELEMENT_CODE")
	private String elementCode;
	
	@Column(name="OBE_PS_EVIDENCE")
	private String psEvidence;

	@Column(name="OBE_SP_EVIDENCE")
	private String spEvidence;
	
	@Column(name="OBE_CAL_EVIDENCE")
	private String calEvidence;
	
	@Column(name="OBE_PS_REFINE_EVIDENCE")
	private String psRefineEvidence;
	
	@Column(name="OBE_SP_REFINE_EVIDENCE")
	private String spRefineEvidence;
	
	@Column(name="OBE_CAL_REFINE_EVIDENCE")
	private String calRefineEvidence;
	
	
	@Column(name="OBE_PS_REIN_EVIDENCE")
	private String psReinEvidence;

	@Column(name="OBE_SP_REIN_EVIDENCE")
	private String spReinEvidence;
	
	@Column(name="OBE_CAL_REIN_EVIDENCE")
	private String calReinEvidence;
	
	@Column(name="OBE_EXP_DATE")
	private String expDate;
	
	@Column(name="OBE_EFF_DATE")
	private Date effDate;

	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OBE_OBSERVATION_ID", insertable = true, updatable = false,nullable=false)
	private Observation observation;
	
	public Long getEvidenceId() {
		return evidenceId;
	}

	public void setEvidenceId(Long evidenceId) {
		this.evidenceId = evidenceId;
	}

	public String getElementCode() {
		return elementCode;
	}

	public void setElementCode(String elementCode) {
		this.elementCode = elementCode;
	}

	public String getPsEvidence() {
		return psEvidence;
	}

	public void setPsEvidence(String psEvidence) {
		this.psEvidence = psEvidence;
	}

	public String getSpEvidence() {
		return spEvidence;
	}

	public void setSpEvidence(String spEvidence) {
		this.spEvidence = spEvidence;
	}

	public String getCalEvidence() {
		return calEvidence;
	}

	public void setCalEvidence(String calEvidence) {
		this.calEvidence = calEvidence;
	}

	public String getPsRefineEvidence() {
		return psRefineEvidence;
	}

	public void setPsRefineEvidence(String psRefineEvidence) {
		this.psRefineEvidence = psRefineEvidence;
	}

	public String getSpRefineEvidence() {
		return spRefineEvidence;
	}

	public void setSpRefineEvidence(String spRefineEvidence) {
		this.spRefineEvidence = spRefineEvidence;
	}

	public String getCalRefineEvidence() {
		return calRefineEvidence;
	}

	public void setCalRefineEvidence(String calRefineEvidence) {
		this.calRefineEvidence = calRefineEvidence;
	}

	public String getPsReinEvidence() {
		return psReinEvidence;
	}

	public void setPsReinEvidence(String psReinEvidence) {
		this.psReinEvidence = psReinEvidence;
	}

	public String getSpReinEvidence() {
		return spReinEvidence;
	}

	public void setSpReinEvidence(String spReinEvidence) {
		this.spReinEvidence = spReinEvidence;
	}

	public String getCalReinEvidence() {
		return calReinEvidence;
	}

	public void setCalReinEvidence(String calReinEvidence) {
		this.calReinEvidence = calReinEvidence;
	}

	public String getExpDate() {
		return expDate;
	}

	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}

	public Date getEffDate() {
		return effDate;
	}

	public void setEffDate(Date effDate) {
		this.effDate = effDate;
	}

	public Observation getObservation() {
		return observation;
	}

	public void setObservation(Observation observation) {
		this.observation = observation;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((calEvidence == null) ? 0 : calEvidence.hashCode());
		result = prime * result + ((calRefineEvidence == null) ? 0 : calRefineEvidence.hashCode());
		result = prime * result + ((calReinEvidence == null) ? 0 : calReinEvidence.hashCode());
		result = prime * result + ((effDate == null) ? 0 : effDate.hashCode());
		result = prime * result + ((elementCode == null) ? 0 : elementCode.hashCode());
		result = prime * result + ((evidenceId == null) ? 0 : evidenceId.hashCode());
		result = prime * result + ((expDate == null) ? 0 : expDate.hashCode());
		result = prime * result + ((observation == null) ? 0 : observation.hashCode());
		result = prime * result + ((psEvidence == null) ? 0 : psEvidence.hashCode());
		result = prime * result + ((psRefineEvidence == null) ? 0 : psRefineEvidence.hashCode());
		result = prime * result + ((psReinEvidence == null) ? 0 : psReinEvidence.hashCode());
		result = prime * result + ((spEvidence == null) ? 0 : spEvidence.hashCode());
		result = prime * result + ((spRefineEvidence == null) ? 0 : spRefineEvidence.hashCode());
		result = prime * result + ((spReinEvidence == null) ? 0 : spReinEvidence.hashCode());
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
		if (!(obj instanceof Evidence))
			return false;
		Evidence other = (Evidence) obj;
		if (calEvidence == null) {
			if (other.calEvidence != null)
				return false;
		} else if (!calEvidence.equals(other.calEvidence))
			return false;
		if (calRefineEvidence == null) {
			if (other.calRefineEvidence != null)
				return false;
		} else if (!calRefineEvidence.equals(other.calRefineEvidence))
			return false;
		if (calReinEvidence == null) {
			if (other.calReinEvidence != null)
				return false;
		} else if (!calReinEvidence.equals(other.calReinEvidence))
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
		if (evidenceId == null) {
			if (other.evidenceId != null)
				return false;
		} else if (!evidenceId.equals(other.evidenceId))
			return false;
		if (expDate == null) {
			if (other.expDate != null)
				return false;
		} else if (!expDate.equals(other.expDate))
			return false;
		if (observation == null) {
			if (other.observation != null)
				return false;
		} else if (!observation.equals(other.observation))
			return false;
		if (psEvidence == null) {
			if (other.psEvidence != null)
				return false;
		} else if (!psEvidence.equals(other.psEvidence))
			return false;
		if (psRefineEvidence == null) {
			if (other.psRefineEvidence != null)
				return false;
		} else if (!psRefineEvidence.equals(other.psRefineEvidence))
			return false;
		if (psReinEvidence == null) {
			if (other.psReinEvidence != null)
				return false;
		} else if (!psReinEvidence.equals(other.psReinEvidence))
			return false;
		if (spEvidence == null) {
			if (other.spEvidence != null)
				return false;
		} else if (!spEvidence.equals(other.spEvidence))
			return false;
		if (spRefineEvidence == null) {
			if (other.spRefineEvidence != null)
				return false;
		} else if (!spRefineEvidence.equals(other.spRefineEvidence))
			return false;
		if (spReinEvidence == null) {
			if (other.spReinEvidence != null)
				return false;
		} else if (!spReinEvidence.equals(other.spReinEvidence))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Evidence [evidenceId=" + evidenceId + ", elementCode=" + elementCode + ", psEvidence=" + psEvidence
				+ ", spEvidence=" + spEvidence + ", calEvidence=" + calEvidence + ", psRefineEvidence="
				+ psRefineEvidence + ", spRefineEvidence=" + spRefineEvidence + ", calRefineEvidence="
				+ calRefineEvidence + ", psReinEvidence=" + psReinEvidence + ", spReinEvidence=" + spReinEvidence
				+ ", calReinEvidence=" + calReinEvidence + ", expDate=" + expDate + ", effDate=" + effDate
				+ ", observation=" + observation + "]";
	}
	
	
	

}
