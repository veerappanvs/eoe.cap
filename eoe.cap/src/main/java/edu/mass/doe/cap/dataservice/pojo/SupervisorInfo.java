package edu.mass.doe.cap.dataservice.pojo;

public class SupervisorInfo {
	private Long assignmentId;
	 public Long getAssignmentId() {
		return assignmentId;
	}
	public void setAssignmentId(Long assignmentId) {
		this.assignmentId = assignmentId;
	}
	 private Long daPersonId;
	 private String userId = "";
	 private String name;
	 private String firstName;
	 private String lastName;
	 private String middleName;
	 private Long mepid;
	 private String districtOrgCode;
	 private String districtName;
	 private Long districtOrgId;
	 private Long districtOrgTypeId;	 
	 private String schoolOrgCode;
	 private String schoolName;
	 private Long schoolOrgId;
	 private String state;
	 private String email;
	 private String confirmEmail;
	 private boolean outOfStatePractitioner=false;
	 private SupervisorInfo previousPractitioner;
	 private String spName;
	 private String spPersonIdString;
	 private Long personId;
	 private String pwd;
	 private  boolean emailExist;
	 private boolean displaySupervisorInfo;
	 
	public SupervisorInfo getPreviousPractitioner() {
		return previousPractitioner;
	}
	public void setPreviousPractitioner(SupervisorInfo previousPractitioner) {
		this.previousPractitioner = previousPractitioner;
	}
	public boolean isOutOfStatePractitioner() {
		return outOfStatePractitioner;
	}
	public void setOutOfStatePractitioner(boolean outOfStatePractitioner) {
		this.outOfStatePractitioner = outOfStatePractitioner;
	}
	public Long getDaPersonId() {
		return daPersonId;
	}
	public void setDaPersonId(Long daPersonId) {
		this.daPersonId = daPersonId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserID(String userID) {
		this.userId = userID;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getMepid() {
		return mepid;
	}
	public void setMepid(Long mepid) {
		this.mepid = mepid;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
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
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getDistrictOrgCode() {
		return districtOrgCode;
	}
	public void setDistrictOrgCode(String districtOrgCode) {
		this.districtOrgCode = districtOrgCode;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	
	public String getSchoolOrgCode() {
		return schoolOrgCode;
	}
	public void setSchoolOrgCode(String schoolOrgCode) {
		this.schoolOrgCode = schoolOrgCode;
	}
	public Long getSchoolOrgId() {
		return schoolOrgId;
	}
	public void setSchoolOrgId(Long schoolOrgId) {
		this.schoolOrgId = schoolOrgId;
	}
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getConfirmEmail() {
		return confirmEmail;
	}
	public void setConfirmEmail(String confirmEmail) {
		this.confirmEmail = confirmEmail;
	}
	public Long getDistrictOrgId() {
		return districtOrgId;
	}
	public void setDistrictOrgId(Long districtOrgId) {
		this.districtOrgId = districtOrgId;
	}
	public Long getDistrictOrgTypeId() {
		return districtOrgTypeId;
	}
	public void setDistrictOrgTypeId(Long districtOrgTypeId) {
		this.districtOrgTypeId = districtOrgTypeId;
	}
	
	public String getSpName() {
		return spName;
	}
	public void setSpName(String sName) {
		this.spName = sName;
	}
	
	public String getSpPersonIdString() {
		return spPersonIdString;
	}
	public void setSpPersonIdString(String spPIdString) {
		this.spPersonIdString = spPIdString;
	}
	
	public Long getPersonId() {
		return personId;
	}
	public void setPersonId(Long daPersonId) {
		this.personId = daPersonId;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public boolean isEmailExist() {
		return emailExist;
	}
	public void setEmailExist(boolean emailExist) {
		this.emailExist = emailExist;
	}
	public boolean isDisplaySupervisorInfo() {
		return displaySupervisorInfo;
	}
	public void setDisplaySupervisorInfo(boolean displaySupervisorInfo) {
		this.displaySupervisorInfo = displaySupervisorInfo;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SupervisorInfo [assignmentId=" + assignmentId + ", daPersonId=" + daPersonId + ", userId=" + userId
				+ ", name=" + name + ", firstName=" + firstName + ", lastName=" + lastName + ", middleName="
				+ middleName + ", mepid=" + mepid + ", districtOrgCode=" + districtOrgCode + ", districtName="
				+ districtName + ", districtOrgId=" + districtOrgId + ", districtOrgTypeId=" + districtOrgTypeId
				+ ", schoolOrgCode=" + schoolOrgCode + ", schoolName=" + schoolName + ", schoolOrgId=" + schoolOrgId
				+ ", state=" + state + ", email=" + email + ", confirmEmail=" + confirmEmail
				+ ", outOfStatePractitioner=" + outOfStatePractitioner + ", previousPractitioner="
				+ previousPractitioner + ", spName=" + spName + ", spPersonIdString=" + spPersonIdString + ", personId="
				+ personId + ", pwd=" + pwd + ", emailExist=" + emailExist + ", displaySupervisorInfo="
				+ displaySupervisorInfo + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((assignmentId == null) ? 0 : assignmentId.hashCode());
		result = prime * result + ((confirmEmail == null) ? 0 : confirmEmail.hashCode());
		result = prime * result + ((daPersonId == null) ? 0 : daPersonId.hashCode());
		result = prime * result + (displaySupervisorInfo ? 1231 : 1237);
		result = prime * result + ((districtName == null) ? 0 : districtName.hashCode());
		result = prime * result + ((districtOrgCode == null) ? 0 : districtOrgCode.hashCode());
		result = prime * result + ((districtOrgId == null) ? 0 : districtOrgId.hashCode());
		result = prime * result + ((districtOrgTypeId == null) ? 0 : districtOrgTypeId.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + (emailExist ? 1231 : 1237);
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((mepid == null) ? 0 : mepid.hashCode());
		result = prime * result + ((middleName == null) ? 0 : middleName.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + (outOfStatePractitioner ? 1231 : 1237);
		result = prime * result + ((personId == null) ? 0 : personId.hashCode());
		result = prime * result + ((previousPractitioner == null) ? 0 : previousPractitioner.hashCode());
		result = prime * result + ((pwd == null) ? 0 : pwd.hashCode());
		result = prime * result + ((schoolName == null) ? 0 : schoolName.hashCode());
		result = prime * result + ((schoolOrgCode == null) ? 0 : schoolOrgCode.hashCode());
		result = prime * result + ((schoolOrgId == null) ? 0 : schoolOrgId.hashCode());
		result = prime * result + ((spName == null) ? 0 : spName.hashCode());
		result = prime * result + ((spPersonIdString == null) ? 0 : spPersonIdString.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
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
		SupervisorInfo other = (SupervisorInfo) obj;
		if (assignmentId == null) {
			if (other.assignmentId != null)
				return false;
		} else if (!assignmentId.equals(other.assignmentId))
			return false;
		if (confirmEmail == null) {
			if (other.confirmEmail != null)
				return false;
		} else if (!confirmEmail.equals(other.confirmEmail))
			return false;
		if (daPersonId == null) {
			if (other.daPersonId != null)
				return false;
		} else if (!daPersonId.equals(other.daPersonId))
			return false;
		if (displaySupervisorInfo != other.displaySupervisorInfo)
			return false;
		if (districtName == null) {
			if (other.districtName != null)
				return false;
		} else if (!districtName.equals(other.districtName))
			return false;
		if (districtOrgCode == null) {
			if (other.districtOrgCode != null)
				return false;
		} else if (!districtOrgCode.equals(other.districtOrgCode))
			return false;
		if (districtOrgId == null) {
			if (other.districtOrgId != null)
				return false;
		} else if (!districtOrgId.equals(other.districtOrgId))
			return false;
		if (districtOrgTypeId == null) {
			if (other.districtOrgTypeId != null)
				return false;
		} else if (!districtOrgTypeId.equals(other.districtOrgTypeId))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (emailExist != other.emailExist)
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
		if (middleName == null) {
			if (other.middleName != null)
				return false;
		} else if (!middleName.equals(other.middleName))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (outOfStatePractitioner != other.outOfStatePractitioner)
			return false;
		if (personId == null) {
			if (other.personId != null)
				return false;
		} else if (!personId.equals(other.personId))
			return false;
		if (previousPractitioner == null) {
			if (other.previousPractitioner != null)
				return false;
		} else if (!previousPractitioner.equals(other.previousPractitioner))
			return false;
		if (pwd == null) {
			if (other.pwd != null)
				return false;
		} else if (!pwd.equals(other.pwd))
			return false;
		if (schoolName == null) {
			if (other.schoolName != null)
				return false;
		} else if (!schoolName.equals(other.schoolName))
			return false;
		if (schoolOrgCode == null) {
			if (other.schoolOrgCode != null)
				return false;
		} else if (!schoolOrgCode.equals(other.schoolOrgCode))
			return false;
		if (schoolOrgId == null) {
			if (other.schoolOrgId != null)
				return false;
		} else if (!schoolOrgId.equals(other.schoolOrgId))
			return false;
		if (spName == null) {
			if (other.spName != null)
				return false;
		} else if (!spName.equals(other.spName))
			return false;
		if (spPersonIdString == null) {
			if (other.spPersonIdString != null)
				return false;
		} else if (!spPersonIdString.equals(other.spPersonIdString))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}
	
	
	
}
