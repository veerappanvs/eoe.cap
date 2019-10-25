package edu.mass.doe.cap.dataservice.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "CP052OBS_OBSERVATION")
@AttributeOverrides({
@AttributeOverride(name = "createDate", column = @Column(name = "OBS_CREATE_DATE")),
@AttributeOverride(name = "createUser", column = @Column(name = "OBS_CREATED_BY")),
@AttributeOverride(name = "modifyDate", column = @Column(name = "OBS_UPDATE_DATE")),
@AttributeOverride(name = "modifyUser", column = @Column(name = "OBS_UPDATED_BY"))
})
public class Observation extends BaseJPAEntity {
	
	@Id
	@Column(name="OBS_OBSERVATION_ID")
	@SequenceGenerator(name = "OBS_PK_GENERATOR", sequenceName = "CP052OBS_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OBS_PK_GENERATOR")
	private Long observationId;
	
	@Column(name="OBS_CYCLE_ID")
	private Long cycleId;
	
	
	
	@Column(name="OBS_OBSERVATION_NUMBER")
	private Long observationNumber;
	
	@Column(name="OBS_OBSERVATION_DATE")
	private Date observationDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="OBS_START_TIME")
	private Date startTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="OBS_END_TIME")
	private Date endTime;
	
	@Column(name="OBS_OBS_TOPIC_OBJECTIVE")
	private String topicObjective;
	
	
	//Removed as a part of Multi Selection Group Code Changes.
	/*@Column(name="OBS_GROUP_CODE")
	private String groupCode;*/
	
	@Column(name="OBS_SP_REFINE_FEEDBACK")
	private String spRefineFeedback;
	
	@Column(name="OBS_PS_REFINE_FEEDBACK")
	private String psRefineFeedback;
	
	@Column(name="OBS_REFINE_CALIBRATED_FEEDBACK")
	private String calRefineFeedback;
	
	@Column(name="OBS_SP_REIN_FEEDBACK")
	private String spReinFeedback;
	
	@Column(name="OBS_PS_REIN_FEEDBACK")
	private String psReinFeedback;
	
	@Column(name="OBS_REIN_CALIBRATED_FEEDBACK")
	private String calReinFeedback;
	
	@Column(name="OBS_COMPLETED")
	private Character completed;
	
	@Column(name="OBS_BOTH_PSSP_COMPLETED")
	private Character bothPSSPCompleted;
	
	@Column(name="OBS_COMPLETE_DATE")
	private Date obsCompleteDate;
	
	@Column(name="OBS_COMPLETED_BY")
	private Long completedBy;
	
	@Column(name="OBS_EXP_DATE")
	private Date expDate;
	
	@Column(name="OBS_EFF_DATE")
	private Date effDate;

	
	@OneToMany(mappedBy = "observation", cascade = CascadeType.ALL,fetch=FetchType.LAZY)
	private List<Evidence> evidences;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "OBS_OBSERVATION_TYPE_CODE", insertable = true, updatable = false,nullable=false)
	private ObservationType observationType;
	
	public Long getObservationId() {
		return observationId;
	}

	public void setObservationId(Long observationId) {
		this.observationId = observationId;
	}

	public Long getCycleId() {
		return cycleId;
	}

	public void setCycleId(Long cycleId) {
		this.cycleId = cycleId;
	}

	
	public Long getObservationNumber() {
		return observationNumber;
	}

	public void setObservationNumber(Long observationNumber) {
		this.observationNumber = observationNumber;
	}

	public Date getObservationDate() {
		return observationDate;
	}

	public void setObservationDate(Date observationDate) {
		this.observationDate = observationDate;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getTopicObjective() {
		return topicObjective;
	}

	public void setTopicObjective(String topicObjective) {
		this.topicObjective = topicObjective;
	}

/*	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}*/

	public String getSpRefineFeedback() {
		return spRefineFeedback;
	}

	public void setSpRefineFeedback(String spRefineFeedback) {
		this.spRefineFeedback = spRefineFeedback;
	}

	public String getPsRefineFeedback() {
		return psRefineFeedback;
	}

	public void setPsRefineFeedback(String psRefineFeedback) {
		this.psRefineFeedback = psRefineFeedback;
	}

	public String getCalRefineFeedback() {
		return calRefineFeedback;
	}

	public void setCalRefineFeedback(String calRefineFeedback) {
		this.calRefineFeedback = calRefineFeedback;
	}

	public String getSpReinFeedback() {
		return spReinFeedback;
	}

	public void setSpReinFeedback(String spReinFeedback) {
		this.spReinFeedback = spReinFeedback;
	}

	public String getPsReinFeedback() {
		return psReinFeedback;
	}

	public void setPsReinFeedback(String psReinFeedback) {
		this.psReinFeedback = psReinFeedback;
	}

	public String getCalReinFeedback() {
		return calReinFeedback;
	}

	public void setCalReinFeedback(String calReinFeedback) {
		this.calReinFeedback = calReinFeedback;
	}

	public Character getCompleted() {
		return completed;
	}

	public void setCompleted(Character completed) {
		this.completed = completed;
	}

	public Character getBothPSSPCompleted() {
		return bothPSSPCompleted;
	}

	public void setBothPSSPCompleted(Character bothPSSPCompleted) {
		this.bothPSSPCompleted = bothPSSPCompleted;
	}

	public Date getObsCompleteDate() {
		return obsCompleteDate;
	}

	public void setObsCompleteDate(Date obsCompleteDate) {
		this.obsCompleteDate = obsCompleteDate;
	}

	public Long getCompletedBy() {
		return completedBy;
	}

	public void setCompletedBy(Long completedBy) {
		this.completedBy = completedBy;
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

	public List<Evidence> getEvidences() {
		return evidences;
	}

	public void setEvidences(List<Evidence> evidences) {
		this.evidences = evidences;
	}

	public ObservationType getObservationType() {
		return observationType;
	}

	public void setObservationType(ObservationType observationType) {
		this.observationType = observationType;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((bothPSSPCompleted == null) ? 0 : bothPSSPCompleted.hashCode());
		result = prime * result + ((calRefineFeedback == null) ? 0 : calRefineFeedback.hashCode());
		result = prime * result + ((calReinFeedback == null) ? 0 : calReinFeedback.hashCode());
		result = prime * result + ((completed == null) ? 0 : completed.hashCode());
		result = prime * result + ((completedBy == null) ? 0 : completedBy.hashCode());
		result = prime * result + ((cycleId == null) ? 0 : cycleId.hashCode());
		result = prime * result + ((effDate == null) ? 0 : effDate.hashCode());
		result = prime * result + ((endTime == null) ? 0 : endTime.hashCode());
		result = prime * result + ((evidences == null) ? 0 : evidences.hashCode());
		result = prime * result + ((expDate == null) ? 0 : expDate.hashCode());
		result = prime * result + ((obsCompleteDate == null) ? 0 : obsCompleteDate.hashCode());
		result = prime * result + ((observationDate == null) ? 0 : observationDate.hashCode());
		result = prime * result + ((observationId == null) ? 0 : observationId.hashCode());
		result = prime * result + ((observationNumber == null) ? 0 : observationNumber.hashCode());
		result = prime * result + ((observationType == null) ? 0 : observationType.hashCode());
		result = prime * result + ((psRefineFeedback == null) ? 0 : psRefineFeedback.hashCode());
		result = prime * result + ((psReinFeedback == null) ? 0 : psReinFeedback.hashCode());
		result = prime * result + ((spRefineFeedback == null) ? 0 : spRefineFeedback.hashCode());
		result = prime * result + ((spReinFeedback == null) ? 0 : spReinFeedback.hashCode());
		result = prime * result + ((startTime == null) ? 0 : startTime.hashCode());
		result = prime * result + ((topicObjective == null) ? 0 : topicObjective.hashCode());
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
		if (!(obj instanceof Observation))
			return false;
		Observation other = (Observation) obj;
		if (bothPSSPCompleted == null) {
			if (other.bothPSSPCompleted != null)
				return false;
		} else if (!bothPSSPCompleted.equals(other.bothPSSPCompleted))
			return false;
		if (calRefineFeedback == null) {
			if (other.calRefineFeedback != null)
				return false;
		} else if (!calRefineFeedback.equals(other.calRefineFeedback))
			return false;
		if (calReinFeedback == null) {
			if (other.calReinFeedback != null)
				return false;
		} else if (!calReinFeedback.equals(other.calReinFeedback))
			return false;
		if (completed == null) {
			if (other.completed != null)
				return false;
		} else if (!completed.equals(other.completed))
			return false;
		if (completedBy == null) {
			if (other.completedBy != null)
				return false;
		} else if (!completedBy.equals(other.completedBy))
			return false;
		if (cycleId == null) {
			if (other.cycleId != null)
				return false;
		} else if (!cycleId.equals(other.cycleId))
			return false;
		if (effDate == null) {
			if (other.effDate != null)
				return false;
		} else if (!effDate.equals(other.effDate))
			return false;
		if (endTime == null) {
			if (other.endTime != null)
				return false;
		} else if (!endTime.equals(other.endTime))
			return false;
		if (evidences == null) {
			if (other.evidences != null)
				return false;
		} else if (!evidences.equals(other.evidences))
			return false;
		if (expDate == null) {
			if (other.expDate != null)
				return false;
		} else if (!expDate.equals(other.expDate))
			return false;
		if (obsCompleteDate == null) {
			if (other.obsCompleteDate != null)
				return false;
		} else if (!obsCompleteDate.equals(other.obsCompleteDate))
			return false;
		if (observationDate == null) {
			if (other.observationDate != null)
				return false;
		} else if (!observationDate.equals(other.observationDate))
			return false;
		if (observationId == null) {
			if (other.observationId != null)
				return false;
		} else if (!observationId.equals(other.observationId))
			return false;
		if (observationNumber == null) {
			if (other.observationNumber != null)
				return false;
		} else if (!observationNumber.equals(other.observationNumber))
			return false;
		if (observationType == null) {
			if (other.observationType != null)
				return false;
		} else if (!observationType.equals(other.observationType))
			return false;
		if (psRefineFeedback == null) {
			if (other.psRefineFeedback != null)
				return false;
		} else if (!psRefineFeedback.equals(other.psRefineFeedback))
			return false;
		if (psReinFeedback == null) {
			if (other.psReinFeedback != null)
				return false;
		} else if (!psReinFeedback.equals(other.psReinFeedback))
			return false;
		if (spRefineFeedback == null) {
			if (other.spRefineFeedback != null)
				return false;
		} else if (!spRefineFeedback.equals(other.spRefineFeedback))
			return false;
		if (spReinFeedback == null) {
			if (other.spReinFeedback != null)
				return false;
		} else if (!spReinFeedback.equals(other.spReinFeedback))
			return false;
		if (startTime == null) {
			if (other.startTime != null)
				return false;
		} else if (!startTime.equals(other.startTime))
			return false;
		if (topicObjective == null) {
			if (other.topicObjective != null)
				return false;
		} else if (!topicObjective.equals(other.topicObjective))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Observation [observationId=" + observationId + ", cycleId=" + cycleId + ", observationNumber="
				+ observationNumber + ", observationDate=" + observationDate + ", startTime=" + startTime + ", endTime="
				+ endTime + ", topicObjective=" + topicObjective + ", spRefineFeedback=" + spRefineFeedback
				+ ", psRefineFeedback=" + psRefineFeedback + ", calRefineFeedback=" + calRefineFeedback
				+ ", spReinFeedback=" + spReinFeedback + ", psReinFeedback=" + psReinFeedback + ", calReinFeedback="
				+ calReinFeedback + ", completed=" + completed + ", bothPSSPCompleted=" + bothPSSPCompleted
				+ ", obsCompleteDate=" + obsCompleteDate + ", completedBy=" + completedBy + ", expDate=" + expDate
				+ ", effDate=" + effDate + ", evidences=" + evidences + ", observationType=" + observationType + "]";
	}
		
	

}
