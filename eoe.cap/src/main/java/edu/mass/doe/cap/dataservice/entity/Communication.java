package edu.mass.doe.cap.dataservice.entity;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "CP067CCC_CYCLE_COMMUNICATION")
@AttributeOverrides({
@AttributeOverride(name = "createDate", column = @Column(name = "CCC_CREATE_DATE")),
@AttributeOverride(name = "createUser", column = @Column(name = "CCC_CREATED_BY")),
@AttributeOverride(name = "modifyDate", column = @Column(name = "CCC_UPDATE_DATE")),
@AttributeOverride(name = "modifyUser", column = @Column(name = "CCC_UPDATED_BY"))
})
public class Communication extends BaseJPAEntity {
	
	
	@Id
	@Column(name="CCC_CAP_EMAIL_ID")
	@SequenceGenerator(name = "CCC_PK_GENERATOR", sequenceName = "CP067CCC_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CCC_PK_GENERATOR")
	private Long emailId;
	
	@Column(name="CCC_CPC_ID")
	private Long cycleId;
	
	@Column(name="CCC_CET_EMAIL_TYPE_CODE")
	private String typeCode;
	
	@Column(name="CCC_FROM_EMAIL_ADDR")
	private String from;
	
	@Column(name="CCC_TO_EMAIL_ADDR")
	private String to;
	
	@Column(name="CCC_SUBJECT_LINE")
	private String subject;
		
	@Column(name="CCC_DA_PER_ID")
	private Long personId;
	
	@Column(name="CCC_EMAIL_FILE_NAME")
	private String file;
	
	@Column(name="CCC_SENT_DATE")
	private Date sentDate;
	
	@Column(name="CCC_EXP_DATE")
	private Date expDate;
	
	@Column(name="CCC_EFF_DATE")
	private Date effDate;

	public Long getEmailId() {
		return emailId;
	}

	public void setEmailId(Long emailId) {
		this.emailId = emailId;
	}

	public Long getCycleId() {
		return cycleId;
	}

	public void setCycleId(Long cycleId) {
		this.cycleId = cycleId;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public Date getSentDate() {
		return sentDate;
	}

	public void setSentDate(Date sentDate) {
		this.sentDate = sentDate;
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
		result = prime * result + ((cycleId == null) ? 0 : cycleId.hashCode());
		result = prime * result + ((effDate == null) ? 0 : effDate.hashCode());
		result = prime * result + ((emailId == null) ? 0 : emailId.hashCode());
		result = prime * result + ((expDate == null) ? 0 : expDate.hashCode());
		result = prime * result + ((file == null) ? 0 : file.hashCode());
		result = prime * result + ((from == null) ? 0 : from.hashCode());
		result = prime * result + ((personId == null) ? 0 : personId.hashCode());
		result = prime * result + ((sentDate == null) ? 0 : sentDate.hashCode());
		result = prime * result + ((subject == null) ? 0 : subject.hashCode());
		result = prime * result + ((to == null) ? 0 : to.hashCode());
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
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof Communication))
			return false;
		Communication other = (Communication) obj;
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
		if (emailId == null) {
			if (other.emailId != null)
				return false;
		} else if (!emailId.equals(other.emailId))
			return false;
		if (expDate == null) {
			if (other.expDate != null)
				return false;
		} else if (!expDate.equals(other.expDate))
			return false;
		if (file == null) {
			if (other.file != null)
				return false;
		} else if (!file.equals(other.file))
			return false;
		if (from == null) {
			if (other.from != null)
				return false;
		} else if (!from.equals(other.from))
			return false;
		if (personId == null) {
			if (other.personId != null)
				return false;
		} else if (!personId.equals(other.personId))
			return false;
		if (sentDate == null) {
			if (other.sentDate != null)
				return false;
		} else if (!sentDate.equals(other.sentDate))
			return false;
		if (subject == null) {
			if (other.subject != null)
				return false;
		} else if (!subject.equals(other.subject))
			return false;
		if (to == null) {
			if (other.to != null)
				return false;
		} else if (!to.equals(other.to))
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
		return "Communication [emailId=" + emailId + ", cycleId=" + cycleId + ", typeCode=" + typeCode + ", from="
				+ from + ", to=" + to + ", subject=" + subject + ", personId=" + personId + ", file=" + file
				+ ", sentDate=" + sentDate + ", expDate=" + expDate + ", effDate=" + effDate + "]";
	}
	

	
	
}

