package edu.mass.doe.cap.dataservice.pojo;

import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;



public class CandidateInfo {
	
		

	private Long programCompleterId;
	
	private Long elarPersonId;	
	
	private Long mepid;
	
	private Long programId;
	
	private String programName;
	
	private String programStatusCode;
	
	private String programStatus;
	
	private Long daPersonId;	
	
	private String candidateName;
	
	private String firstName;
	
	private String lastName;
	
	private Long orgId;
	
	private String orgCode;
	
	private String email;
	
	private String orgName;

	private String year;
	
	private Long cycleId;
	
	private Long fileUploadId;

	
	public Long getProgramCompleterId() {
		return programCompleterId;
	}

	public void setProgramCompleterId(Long programCompleterId) {
		this.programCompleterId = programCompleterId;
	}

	public Long getElarPersonId() {
		return elarPersonId;
	}

	public void setElarPersonId(Long elarPersonId) {
		this.elarPersonId = elarPersonId;
	}

	public Long getMepid() {
		return mepid;
	}

	public void setMepid(Long mepid) {
		this.mepid = mepid;
	}

	public Long getProgramId() {
		return programId;
	}

	public void setProgramId(Long programId) {
		this.programId = programId;
	}

	public String getProgramName() {
		return programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}

	public String getProgramStatusCode() {
		return programStatusCode;
	}

	public void setProgramStatusCode(String programStatusCode) {
		this.programStatusCode = programStatusCode;
	}

	public String getProgramStatus() {
		return programStatus;
	}

	public void setProgramStatus(String programStatus) {
		this.programStatus = programStatus;
	}

	public Long getDaPersonId() {
		return daPersonId;
	}

	public void setDaPersonId(Long daPersonId) {
		this.daPersonId = daPersonId;
	}

	public String getCandidateName() {
		return candidateName;
	}

	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Long getCycleId() {
		return cycleId;
	}

	public void setCycleId(Long cycleId) {
		this.cycleId = cycleId;
	}
	
	public Long getFileUploadId() {
		return fileUploadId;
	}

	public void setFileUploadId(Long fileUploadId) {
		this.fileUploadId = fileUploadId;
	}

	@Override
	public String toString() {
		return "CandidateInfo [programCompleterId=" + programCompleterId + ", elarPersonId=" + elarPersonId + ", mepid="
				+ mepid + ", programId=" + programId + ", programName=" + programName + ", programStatusCode="
				+ programStatusCode + ", programStatus=" + programStatus + ", daPersonId=" + daPersonId
				+ ", candidateName=" + candidateName + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", orgId=" + orgId + ", orgCode=" + orgCode + ", email=" + email + ", orgName=" + orgName + ", year="
				+ year + ", cycleId=" + cycleId + ", fileUploadId=" + fileUploadId + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((candidateName == null) ? 0 : candidateName.hashCode());
		result = prime * result + ((cycleId == null) ? 0 : cycleId.hashCode());
		result = prime * result + ((daPersonId == null) ? 0 : daPersonId.hashCode());
		result = prime * result + ((elarPersonId == null) ? 0 : elarPersonId.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((fileUploadId == null) ? 0 : fileUploadId.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((mepid == null) ? 0 : mepid.hashCode());
		result = prime * result + ((orgCode == null) ? 0 : orgCode.hashCode());
		result = prime * result + ((orgId == null) ? 0 : orgId.hashCode());
		result = prime * result + ((orgName == null) ? 0 : orgName.hashCode());
		result = prime * result + ((programCompleterId == null) ? 0 : programCompleterId.hashCode());
		result = prime * result + ((programId == null) ? 0 : programId.hashCode());
		result = prime * result + ((programName == null) ? 0 : programName.hashCode());
		result = prime * result + ((programStatus == null) ? 0 : programStatus.hashCode());
		result = prime * result + ((programStatusCode == null) ? 0 : programStatusCode.hashCode());
		result = prime * result + ((year == null) ? 0 : year.hashCode());
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
		CandidateInfo other = (CandidateInfo) obj;
		if (candidateName == null) {
			if (other.candidateName != null)
				return false;
		} else if (!candidateName.equals(other.candidateName))
			return false;
		if (cycleId == null) {
			if (other.cycleId != null)
				return false;
		} else if (!cycleId.equals(other.cycleId))
			return false;
		if (daPersonId == null) {
			if (other.daPersonId != null)
				return false;
		} else if (!daPersonId.equals(other.daPersonId))
			return false;
		if (elarPersonId == null) {
			if (other.elarPersonId != null)
				return false;
		} else if (!elarPersonId.equals(other.elarPersonId))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (fileUploadId == null) {
			if (other.fileUploadId != null)
				return false;
		} else if (!fileUploadId.equals(other.fileUploadId))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (mepid == null) {
			if (other.mepid != null)
				return false;
		} else if (!mepid.equals(other.mepid))
			return false;
		if (orgCode == null) {
			if (other.orgCode != null)
				return false;
		} else if (!orgCode.equals(other.orgCode))
			return false;
		if (orgId == null) {
			if (other.orgId != null)
				return false;
		} else if (!orgId.equals(other.orgId))
			return false;
		if (orgName == null) {
			if (other.orgName != null)
				return false;
		} else if (!orgName.equals(other.orgName))
			return false;
		if (programCompleterId == null) {
			if (other.programCompleterId != null)
				return false;
		} else if (!programCompleterId.equals(other.programCompleterId))
			return false;
		if (programId == null) {
			if (other.programId != null)
				return false;
		} else if (!programId.equals(other.programId))
			return false;
		if (programName == null) {
			if (other.programName != null)
				return false;
		} else if (!programName.equals(other.programName))
			return false;
		if (programStatus == null) {
			if (other.programStatus != null)
				return false;
		} else if (!programStatus.equals(other.programStatus))
			return false;
		if (programStatusCode == null) {
			if (other.programStatusCode != null)
				return false;
		} else if (!programStatusCode.equals(other.programStatusCode))
			return false;
		if (year == null) {
			if (other.year != null)
				return false;
		} else if (!year.equals(other.year))
			return false;
		return true;
	}




}
