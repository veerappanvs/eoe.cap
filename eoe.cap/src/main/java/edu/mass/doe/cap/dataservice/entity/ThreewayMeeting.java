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
@Table(name = "CP055TWM_THREE_WAY_MEETING")
@AttributeOverrides({
@AttributeOverride(name = "createDate", column = @Column(name = "TWM_CREATE_DATE")),
@AttributeOverride(name = "createUser", column = @Column(name = "TWM_CREATED_BY")),
@AttributeOverride(name = "modifyDate", column = @Column(name = "TWM_UPDATE_DATE")),
@AttributeOverride(name = "modifyUser", column = @Column(name = "TWM_UPDATED_BY"))
})
public class ThreewayMeeting extends BaseJPAEntity {

	@Id
	@Column(name="TWM_MEETING_ID")
	@SequenceGenerator(name = "MEETING_PK_GENERATOR", sequenceName = "CP055TWM_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEETING_PK_GENERATOR")
	private Long meetingId;
	
	@Column(name="TWM_MEETING_NUMBER")
	private Long meetingNumber;
	
	@Column(name="TWM_MEETING_DATE")
	private Date meetingDate;
	
	@Column(name="TWM_EFF_DATE")
	private Date effDate;
	
	@Column(name="TWM_EXP_DATE")
	private Date expDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TWM_CYCLE_ID", insertable = true, updatable = false,nullable=false)
	private CapCycle capCycle;
	
	@Column(name="TWM_NOTES")
	private String notes;

	public Long getMeetingId() {
		return meetingId;
	}

	public void setMeetingId(Long meetingId) {
		this.meetingId = meetingId;
	}

	

	public Long getMeetingNumber() {
		return meetingNumber;
	}

	public void setMeetingNumber(Long meetingNumber) {
		this.meetingNumber = meetingNumber;
	}

	public Date getMeetingDate() {
		return meetingDate;
	}

	public void setMeetingDate(Date meetingDate) {
		this.meetingDate = meetingDate;
	}

	public Date getEffDate() {
		return effDate;
	}

	public void setEffDate(Date effDate) {
		this.effDate = effDate;
	}

	public Date getExpDate() {
		return expDate;
	}

	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}

	public CapCycle getCapCycle() {
		return capCycle;
	}

	public void setCapCycle(CapCycle capCycle) {
		this.capCycle = capCycle;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((capCycle == null) ? 0 : capCycle.hashCode());
		result = prime * result + ((effDate == null) ? 0 : effDate.hashCode());
		result = prime * result + ((expDate == null) ? 0 : expDate.hashCode());
		result = prime * result + ((meetingDate == null) ? 0 : meetingDate.hashCode());
		result = prime * result + ((meetingId == null) ? 0 : meetingId.hashCode());
		result = prime * result + ((meetingNumber == null) ? 0 : meetingNumber.hashCode());
		result = prime * result + ((notes == null) ? 0 : notes.hashCode());
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
		if (!(obj instanceof ThreewayMeeting))
			return false;
		ThreewayMeeting other = (ThreewayMeeting) obj;
		if (capCycle == null) {
			if (other.capCycle != null)
				return false;
		} else if (!capCycle.equals(other.capCycle))
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
		if (meetingDate == null) {
			if (other.meetingDate != null)
				return false;
		} else if (!meetingDate.equals(other.meetingDate))
			return false;
		if (meetingId == null) {
			if (other.meetingId != null)
				return false;
		} else if (!meetingId.equals(other.meetingId))
			return false;
		if (meetingNumber == null) {
			if (other.meetingNumber != null)
				return false;
		} else if (!meetingNumber.equals(other.meetingNumber))
			return false;
		if (notes == null) {
			if (other.notes != null)
				return false;
		} else if (!notes.equals(other.notes))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ThreewayMeeting [meetingId=" + meetingId + ", meetingNumber=" + meetingNumber + ", meetingDate="
				+ meetingDate + ", effDate=" + effDate + ", expDate=" + expDate + ", capCycle=" + capCycle + ", notes="
				+ notes + "]";
	}
	
	

}
