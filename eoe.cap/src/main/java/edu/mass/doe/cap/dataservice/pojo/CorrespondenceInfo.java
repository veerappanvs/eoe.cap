/**
 * 
 */
package edu.mass.doe.cap.dataservice.pojo;

import java.util.Date;

/**
 * @author vsubramaniyan
 *
 */
public class CorrespondenceInfo {

  
	Long capEmailId;
	Long cycleId;	
	String toEmailAddr;
	String fullName;
	String programName;
	String emailType;
	String emailSubject;
	String emailBody;
	Date   lastUdateDate;
	

	

	public Long getCapEmailId() {
		return capEmailId;
	}
	public void setCapEmailId(Long capEmailId) {
		this.capEmailId = capEmailId;
	}
	public Long getCycleId() {
		return cycleId;
	}
	public void setCycleId(Long cycleId) {
		this.cycleId = cycleId;
	}
	public String getToEmailAddr() {
		return toEmailAddr;
	}
	public void setToEmailAddr(String toEmailAddr) {
		this.toEmailAddr = toEmailAddr;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getProgramName() {
		return programName;
	}
	public void setProgramName(String programName) {
		this.programName = programName;
	}
	public String getEmailType() {
		return emailType;
	}
	public void setEmailType(String emailType) {
		this.emailType = emailType;
	}
	public String getEmailBody() {
		return emailBody;
	}
	public void setEmailBody(String emailBody) {
		this.emailBody = emailBody;
	}
	public Date getLastUdateDate() {
		return lastUdateDate;
	}
	public void setLastUdateDate(Date lastUdateDate) {
		this.lastUdateDate = lastUdateDate;
	}
	public String getEmailSubject() {
		return emailSubject;
	}
	public void setEmailSubject(String emailSubject) {
		this.emailSubject = emailSubject;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((capEmailId == null) ? 0 : capEmailId.hashCode());
		result = prime * result + ((cycleId == null) ? 0 : cycleId.hashCode());
		result = prime * result + ((emailBody == null) ? 0 : emailBody.hashCode());
		result = prime * result + ((emailSubject == null) ? 0 : emailSubject.hashCode());
		result = prime * result + ((emailType == null) ? 0 : emailType.hashCode());
		result = prime * result + ((fullName == null) ? 0 : fullName.hashCode());
		result = prime * result + ((lastUdateDate == null) ? 0 : lastUdateDate.hashCode());
		result = prime * result + ((programName == null) ? 0 : programName.hashCode());
		result = prime * result + ((toEmailAddr == null) ? 0 : toEmailAddr.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CorrespondenceInfo other = (CorrespondenceInfo) obj;
		if (capEmailId == null) {
			if (other.capEmailId != null)
				return false;
		} else if (!capEmailId.equals(other.capEmailId))
			return false;
		if (cycleId == null) {
			if (other.cycleId != null)
				return false;
		} else if (!cycleId.equals(other.cycleId))
			return false;
		if (emailBody == null) {
			if (other.emailBody != null)
				return false;
		} else if (!emailBody.equals(other.emailBody))
			return false;
		if (emailSubject == null) {
			if (other.emailSubject != null)
				return false;
		} else if (!emailSubject.equals(other.emailSubject))
			return false;
		if (emailType == null) {
			if (other.emailType != null)
				return false;
		} else if (!emailType.equals(other.emailType))
			return false;
		if (fullName == null) {
			if (other.fullName != null)
				return false;
		} else if (!fullName.equals(other.fullName))
			return false;
		if (lastUdateDate == null) {
			if (other.lastUdateDate != null)
				return false;
		} else if (!lastUdateDate.equals(other.lastUdateDate))
			return false;
		if (programName == null) {
			if (other.programName != null)
				return false;
		} else if (!programName.equals(other.programName))
			return false;
		if (toEmailAddr == null) {
			if (other.toEmailAddr != null)
				return false;
		} else if (!toEmailAddr.equals(other.toEmailAddr))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "CorrespondenceInfo [capEmailId=" + capEmailId + ", cycleId=" + cycleId + ", toEmailAddr=" + toEmailAddr
				+ ", fullName=" + fullName + ", programName=" + programName + ", emailType=" + emailType
				+ ", emailSubject=" + emailSubject + ", emailBody=" + emailBody + ", lastUdateDate=" + lastUdateDate+ "]";
	}
	
}
