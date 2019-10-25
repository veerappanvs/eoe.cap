package edu.mass.doe.cap.dataservice.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;



public class CapCycleInfo  {
	
	private Long cycleId;
	
	private String candidateFirstName;
	private String candidateLastName;
	private String candidateMiddleName;
	private String candidateName;
	private Long candidateMEPID;
	private Long candidatePersonId;
	
	
	private String supervisorFirstName;
	private String supervisorLastName;
	private String supervisorMiddleName;
	private String supervisorName;
	private Long supervisorMEPID;
	private Long supervisorPersonId;
	
	
	private SupervisorInfo supervisor;
	
	
	private SupervisorInfo practitioner;
	private SupervisorInfo origPractitioner;

	private List<SupervisorInfo> practitioners=new ArrayList<SupervisorInfo>();
	
	
	private String programName;
	private Long programId;
	
	private Date startDate;
	private Date cycleCloseDate;
	private Integer schoolYear;	
	
	private String practitionerInputMEPID;
	
	private Long orgId;	
	
	private String orgCode;	
	
	private String orgName;	

	private Character waived;
		
	private String practicumTypeCode;
	
	private String practicumTypeDesc;
	
	private String statusReasonTypeCode;
	
	private String programAreaGradLevel;

	private String courseTitle;

	private String courseNumber;
	
	private List<String> gradLevel;
	
	private Integer hours;
	
	private String hoursFullResponsibility;
	
	private String practicumHours;
	
	private String creditHours;
	
	private Character readyToTeach;

	private String selfAssessment;
	private String announcedObs1;
	private String goalPlan;
	private String unAnnouncedObs1;
	private String announcedObs2;
	private String formativeAssessment;
	private String unAnnouncedObs2;
	private String summativeAssessment;
	private Date latestActDate;
	private String cycleStatus;
	private String cycleStatusCode;
	private String practicumSchoolName;
	
	private String incompleteWorksString;
	
	private String reOpenCycleFlag;

	private Date reOpenDate;
	private Date endDate;
	
	private String autoCloseIndictor;
	
	private Date autoCloseFirstEmailDate;
	
	private Date autoCloseEmailDate;
	
	
	private List<CapManagerInfo> managers=new ArrayList<CapManagerInfo>();
	
	private List<CorrespondenceInfo> correspondenceInfo = new  ArrayList<CorrespondenceInfo>();
	
	private String reOpenErrorMessage;
	
	public Long getCycleId() {
		return cycleId;
	}

	public void setCycleId(Long cycleId) {
		this.cycleId = cycleId;
	}

	public String getCandidateFirstName() {
		return candidateFirstName;
	}

	public void setCandidateFirstName(String candidateFirstName) {
		this.candidateFirstName = candidateFirstName;
	}



	public String getCandidateLastName() {
		return candidateLastName;
	}



	public void setCandidateLastName(String candidateLastName) {
		this.candidateLastName = candidateLastName;
	}



	public String getCandidateMiddleName() {
		return candidateMiddleName;
	}



	public void setCandidateMiddleName(String candidateMiddleName) {
		this.candidateMiddleName = candidateMiddleName;
	}



	public Long getCandidateMEPID() {
		return candidateMEPID;
	}



	public void setCandidateMEPID(Long candidateMEPID) {
		this.candidateMEPID = candidateMEPID;
	}



	public Long getCandidatePersonId() {
		return candidatePersonId;
	}



	public void setCandidatePersonId(Long candidatePersonId) {
		this.candidatePersonId = candidatePersonId;
	}



	public String getSupervisorFirstName() {
		return supervisorFirstName;
	}



	public void setSupervisorFirstName(String supervisorFirstName) {
		this.supervisorFirstName = supervisorFirstName;
	}



	public String getSupervisorLastName() {
		return supervisorLastName;
	}



	public void setSupervisorLastName(String supervisorLastName) {
		this.supervisorLastName = supervisorLastName;
	}



	public String getSupervisorMiddleName() {
		return supervisorMiddleName;
	}



	public void setSupervisorMiddleName(String supervisorMiddleName) {
		this.supervisorMiddleName = supervisorMiddleName;
	}



	public Long getSupervisorMEPID() {
		return supervisorMEPID;
	}



	public void setSupervisorMEPID(Long supervisorMEPID) {
		this.supervisorMEPID = supervisorMEPID;
	}



	public Long getSupervisorPersonId() {
		return supervisorPersonId;
	}



	public void setSupervisorPersonId(Long supervisorPersonId) {
		this.supervisorPersonId = supervisorPersonId;
	}



	public SupervisorInfo getPractitioner() {
		return practitioner;
	}



	public void setPractitioner(SupervisorInfo practitioner) {
		this.practitioner = practitioner;
	}



	public String getProgramName() {
		return programName;
	}



	public void setProgramName(String programName) {
		this.programName = programName;
	}



	public Long getProgramId() {
		return programId;
	}



	public void setProgramId(Long programId) {
		this.programId = programId;
	}



	public Date getStartDate() {
		return startDate;
	}



	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}



	public Integer getSchoolYear() {
		return schoolYear;
	}



	public void setSchoolYear(Integer schoolYear) {
		this.schoolYear = schoolYear;
	}



	public Character getWaived() {
		return waived;
	}



	public void setWaived(Character waived) {
		this.waived = waived;
	}



	public String getPracticumTypeCode() {
		return practicumTypeCode;
	}



	public void setPracticumTypeCode(String practicumTypeCode) {
		this.practicumTypeCode = practicumTypeCode;
	}


	public String getStatusReasonTypeCode() {
		return statusReasonTypeCode;
	}



	public void setStatusReasonTypeCode(String statusReasonTypeCode) {
		this.statusReasonTypeCode = statusReasonTypeCode;
	}


	public String getpractitionerInputMEPID() {
		return practitionerInputMEPID;
	}



	public void setpractitionerInputMEPID(String practitionerInputMEPID) {
		this.practitionerInputMEPID = practitionerInputMEPID;
	}



	public String getPractitionerInputMEPID() {
		return practitionerInputMEPID;
	}



	public void setPractitionerInputMEPID(String practitionerInputMEPID) {
		this.practitionerInputMEPID = practitionerInputMEPID;
	}



	public Long getOrgId() {
		return orgId;
	}



	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}



	public SupervisorInfo getOrigPractitioner() {
		return origPractitioner;
	}



	public void setOrigPractitioner(SupervisorInfo origPractitioner) {
		this.origPractitioner = origPractitioner;
	}



	public String getProgramAreaGradLevel() {
		return programAreaGradLevel;
	}



	public void setProgramAreaGradLevel(String programAreaGradLevel) {
		this.programAreaGradLevel = programAreaGradLevel;
	}



	public String getCourseTitle() {
		return courseTitle;
	}



	public void setCourseTitle(String courseTitle) {
		this.courseTitle = courseTitle;
	}



	public String getCourseNumber() {
		return courseNumber;
	}



	public void setCourseNumber(String courseNumber) {
		this.courseNumber = courseNumber;
	}



	public List<String> getGradLevel() {
		return gradLevel;
	}
	
	public String getGradLevelAsString(){
		return StringUtils.join(gradLevel,", ");
	}



	public void setGradLevel(List<String> gradLevel) {
		this.gradLevel = gradLevel;
	}



	public Integer getHours() {
		return hours;
	}



	public void setHours(Integer hours) {
		this.hours = hours;
	}



	public String getHoursFullResponsibility() {
		return hoursFullResponsibility;
	}



	public void setHoursFullResponsibility(String hoursFullResponsibility) {
		this.hoursFullResponsibility = hoursFullResponsibility;
	}



	public String getCreditHours() {
		return creditHours;
	}



	public void setCreditHours(String creditHours) {
		this.creditHours = creditHours;
	}



	public String getPracticumHours() {
		return practicumHours;
	}



	public void setPracticumHours(String practicumHours) {
		this.practicumHours = practicumHours;
	}



	public List<SupervisorInfo> getPractitioners() {
		return practitioners;
	}



	public void setPractitioners(List<SupervisorInfo> practitioners) {
		this.practitioners = practitioners;
	}

	public SupervisorInfo getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(SupervisorInfo supervisor) {
		this.supervisor = supervisor;
	}



	public Character getReadyToTeach() {
		return readyToTeach;
	}



	public void setReadyToTeach(Character readyToTeach) {
		this.readyToTeach = readyToTeach;
	} 
	
	////////////////////
	public String getSelfAssessment() {
		return selfAssessment;
	}

	public void setSelfAssessment(String sa) {
		this.selfAssessment = sa;
	}
	
	public String getAnnouncedObs1() {
		return announcedObs1;
	}

	public void setAnnouncedObs1(String aobs1) {
		this.announcedObs1 = aobs1;
	}
	
	public String getGoalPlan() {
		return goalPlan;
	}

	public void setGoalPlan(String gp) {
		this.goalPlan = gp;
	}
	
	public String getUnAnnouncedObs1() {
		return unAnnouncedObs1;
	}

	public void setUnAnnouncedObs1(String uaobs1) {
		this.unAnnouncedObs1 = uaobs1;
	}
	
	public String getAnnouncedObs2() {
		return announcedObs2;
	}

	public void setAnnouncedObs2(String aobs2) {
		this.announcedObs2 = aobs2;
	}
	
	public String getFormativeAssessment() {
		return formativeAssessment;
	}

	public void setFormativeAssessment(String fa) {
		this.formativeAssessment = fa;
	}
		
	public String getUnAnnouncedObs2() {
		return unAnnouncedObs2;
	}

	public void setUnAnnouncedObs2(String uaobs2) {
		this.unAnnouncedObs2 = uaobs2;
	}
	
	public String getSummativeAssessment() {
		return summativeAssessment;
	}

	public void setSummativeAssessment(String sa) {
		this.summativeAssessment = sa;
	}
	
	public Date getLatestActDate() {
		return  latestActDate;
	}

	public void setLatestActDate(Date laDate) {
		this.latestActDate = laDate;
	}
	
	public String getCycleStatus() {
		return cycleStatus;
	}

	public void setCycleStatus(String cs) {
		this.cycleStatus = cs;
	}
	
	public String getPracticumSchoolName() {
		return practicumSchoolName;
	}

	public void setPracticumSchoolName(String practicumSchool) {
		this.practicumSchoolName = practicumSchool;
	}

	public String getCandidateName() {
		return candidateName;
	}

	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}

	public String getSupervisorName() {
		return supervisorName;
	}

	public void setSupervisorName(String supervisorName) {
		this.supervisorName = supervisorName;
	}

	public String getCycleStatusCode() {
		return cycleStatusCode;
	}

	public void setCycleStatusCode(String cycleStatusCode) {
		this.cycleStatusCode = cycleStatusCode;
	}
	public Date getCycleCloseDate() {
		return cycleCloseDate;
	}

	public void setCycleCloseDate(Date cycleCloseDate) {
		this.cycleCloseDate = cycleCloseDate;
	}
	
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date eDate) {
		this.endDate = eDate;
	}
	
	public String getIncompleteWorksString() {
		return incompleteWorksString;
	}

	public void setIncompleteWorksString(String incompleteWorksString) {
		this.incompleteWorksString = incompleteWorksString;
	}

	public String getPracticumTypeDesc() {
		return practicumTypeDesc;
	}

	public void setPracticumTypeDesc(String practicumTypeDesc) {
		this.practicumTypeDesc = practicumTypeDesc;
	}
	
	public String getReOpenCycleFlag() {
		return reOpenCycleFlag;
	}
	
	public void setReOpenCycleFlag(String rocf) {
		this.reOpenCycleFlag = rocf;
	}
	
	public Date getReOpenDate() {
		return reOpenDate;
	}

	public void setReOpenDate(Date reopenDate) {
		this.reOpenDate = reopenDate;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getAutoCloseIndictor() {
		return autoCloseIndictor;
	}

	public void setAutoCloseIndictor(String autoCloseIndictor) {
		this.autoCloseIndictor = autoCloseIndictor;
	}

	public Date getAutoCloseFirstEmailDate() {
		return autoCloseFirstEmailDate;
	}

	public void setAutoCloseFirstEmailDate(Date autoCloseFirstEmailDate) {
		this.autoCloseFirstEmailDate = autoCloseFirstEmailDate;
	}

	public Date getAutoCloseEmailDate() {
		return autoCloseEmailDate;
	}

	public void setAutoCloseEmailDate(Date autoCloseEmailDate) {
		this.autoCloseEmailDate = autoCloseEmailDate;
	}

	public List<CapManagerInfo> getManagers() {
		return managers;
	}

	public void setManagers(List<CapManagerInfo> managers) {
		this.managers = managers;
	}

	public List<CorrespondenceInfo> getCorrespondenceInfo() {
		return correspondenceInfo;
	}

	public void setCorrespondenceInfo(List<CorrespondenceInfo> correspondenceInfo) {
		this.correspondenceInfo = correspondenceInfo;
	}

	public String getReOpenErrorMessage() {
		return reOpenErrorMessage;
	}

	public void setReOpenErrorMessage(String reOpenErrorMessage) {
		this.reOpenErrorMessage = reOpenErrorMessage;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((announcedObs1 == null) ? 0 : announcedObs1.hashCode());
		result = prime * result + ((announcedObs2 == null) ? 0 : announcedObs2.hashCode());
		result = prime * result + ((autoCloseEmailDate == null) ? 0 : autoCloseEmailDate.hashCode());
		result = prime * result + ((autoCloseFirstEmailDate == null) ? 0 : autoCloseFirstEmailDate.hashCode());
		result = prime * result + ((autoCloseIndictor == null) ? 0 : autoCloseIndictor.hashCode());
		result = prime * result + ((candidateFirstName == null) ? 0 : candidateFirstName.hashCode());
		result = prime * result + ((candidateLastName == null) ? 0 : candidateLastName.hashCode());
		result = prime * result + ((candidateMEPID == null) ? 0 : candidateMEPID.hashCode());
		result = prime * result + ((candidateMiddleName == null) ? 0 : candidateMiddleName.hashCode());
		result = prime * result + ((candidateName == null) ? 0 : candidateName.hashCode());
		result = prime * result + ((candidatePersonId == null) ? 0 : candidatePersonId.hashCode());
		result = prime * result + ((correspondenceInfo == null) ? 0 : correspondenceInfo.hashCode());
		result = prime * result + ((courseNumber == null) ? 0 : courseNumber.hashCode());
		result = prime * result + ((courseTitle == null) ? 0 : courseTitle.hashCode());
		result = prime * result + ((creditHours == null) ? 0 : creditHours.hashCode());
		result = prime * result + ((cycleCloseDate == null) ? 0 : cycleCloseDate.hashCode());
		result = prime * result + ((cycleId == null) ? 0 : cycleId.hashCode());
		result = prime * result + ((cycleStatus == null) ? 0 : cycleStatus.hashCode());
		result = prime * result + ((cycleStatusCode == null) ? 0 : cycleStatusCode.hashCode());
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result + ((formativeAssessment == null) ? 0 : formativeAssessment.hashCode());
		result = prime * result + ((goalPlan == null) ? 0 : goalPlan.hashCode());
		result = prime * result + ((gradLevel == null) ? 0 : gradLevel.hashCode());
		result = prime * result + ((hours == null) ? 0 : hours.hashCode());
		result = prime * result + ((hoursFullResponsibility == null) ? 0 : hoursFullResponsibility.hashCode());
		result = prime * result + ((incompleteWorksString == null) ? 0 : incompleteWorksString.hashCode());
		result = prime * result + ((latestActDate == null) ? 0 : latestActDate.hashCode());
		result = prime * result + ((managers == null) ? 0 : managers.hashCode());
		result = prime * result + ((orgCode == null) ? 0 : orgCode.hashCode());
		result = prime * result + ((orgId == null) ? 0 : orgId.hashCode());
		result = prime * result + ((orgName == null) ? 0 : orgName.hashCode());
		result = prime * result + ((origPractitioner == null) ? 0 : origPractitioner.hashCode());
		result = prime * result + ((practicumHours == null) ? 0 : practicumHours.hashCode());
		result = prime * result + ((practicumSchoolName == null) ? 0 : practicumSchoolName.hashCode());
		result = prime * result + ((practicumTypeCode == null) ? 0 : practicumTypeCode.hashCode());
		result = prime * result + ((practicumTypeDesc == null) ? 0 : practicumTypeDesc.hashCode());
		result = prime * result + ((practitioner == null) ? 0 : practitioner.hashCode());
		result = prime * result + ((practitionerInputMEPID == null) ? 0 : practitionerInputMEPID.hashCode());
		result = prime * result + ((practitioners == null) ? 0 : practitioners.hashCode());
		result = prime * result + ((programAreaGradLevel == null) ? 0 : programAreaGradLevel.hashCode());
		result = prime * result + ((programId == null) ? 0 : programId.hashCode());
		result = prime * result + ((programName == null) ? 0 : programName.hashCode());
		result = prime * result + ((reOpenCycleFlag == null) ? 0 : reOpenCycleFlag.hashCode());
		result = prime * result + ((reOpenDate == null) ? 0 : reOpenDate.hashCode());
		result = prime * result + ((reOpenErrorMessage == null) ? 0 : reOpenErrorMessage.hashCode());
		result = prime * result + ((readyToTeach == null) ? 0 : readyToTeach.hashCode());
		result = prime * result + ((schoolYear == null) ? 0 : schoolYear.hashCode());
		result = prime * result + ((selfAssessment == null) ? 0 : selfAssessment.hashCode());
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
		result = prime * result + ((statusReasonTypeCode == null) ? 0 : statusReasonTypeCode.hashCode());
		result = prime * result + ((summativeAssessment == null) ? 0 : summativeAssessment.hashCode());
		result = prime * result + ((supervisor == null) ? 0 : supervisor.hashCode());
		result = prime * result + ((supervisorFirstName == null) ? 0 : supervisorFirstName.hashCode());
		result = prime * result + ((supervisorLastName == null) ? 0 : supervisorLastName.hashCode());
		result = prime * result + ((supervisorMEPID == null) ? 0 : supervisorMEPID.hashCode());
		result = prime * result + ((supervisorMiddleName == null) ? 0 : supervisorMiddleName.hashCode());
		result = prime * result + ((supervisorName == null) ? 0 : supervisorName.hashCode());
		result = prime * result + ((supervisorPersonId == null) ? 0 : supervisorPersonId.hashCode());
		result = prime * result + ((unAnnouncedObs1 == null) ? 0 : unAnnouncedObs1.hashCode());
		result = prime * result + ((unAnnouncedObs2 == null) ? 0 : unAnnouncedObs2.hashCode());
		result = prime * result + ((waived == null) ? 0 : waived.hashCode());
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
		CapCycleInfo other = (CapCycleInfo) obj;
		if (announcedObs1 == null) {
			if (other.announcedObs1 != null)
				return false;
		} else if (!announcedObs1.equals(other.announcedObs1))
			return false;
		if (announcedObs2 == null) {
			if (other.announcedObs2 != null)
				return false;
		} else if (!announcedObs2.equals(other.announcedObs2))
			return false;
		if (autoCloseEmailDate == null) {
			if (other.autoCloseEmailDate != null)
				return false;
		} else if (!autoCloseEmailDate.equals(other.autoCloseEmailDate))
			return false;
		if (autoCloseFirstEmailDate == null) {
			if (other.autoCloseFirstEmailDate != null)
				return false;
		} else if (!autoCloseFirstEmailDate.equals(other.autoCloseFirstEmailDate))
			return false;
		if (autoCloseIndictor == null) {
			if (other.autoCloseIndictor != null)
				return false;
		} else if (!autoCloseIndictor.equals(other.autoCloseIndictor))
			return false;
		if (candidateFirstName == null) {
			if (other.candidateFirstName != null)
				return false;
		} else if (!candidateFirstName.equals(other.candidateFirstName))
			return false;
		if (candidateLastName == null) {
			if (other.candidateLastName != null)
				return false;
		} else if (!candidateLastName.equals(other.candidateLastName))
			return false;
		if (candidateMEPID == null) {
			if (other.candidateMEPID != null)
				return false;
		} else if (!candidateMEPID.equals(other.candidateMEPID))
			return false;
		if (candidateMiddleName == null) {
			if (other.candidateMiddleName != null)
				return false;
		} else if (!candidateMiddleName.equals(other.candidateMiddleName))
			return false;
		if (candidateName == null) {
			if (other.candidateName != null)
				return false;
		} else if (!candidateName.equals(other.candidateName))
			return false;
		if (candidatePersonId == null) {
			if (other.candidatePersonId != null)
				return false;
		} else if (!candidatePersonId.equals(other.candidatePersonId))
			return false;
		if (correspondenceInfo == null) {
			if (other.correspondenceInfo != null)
				return false;
		} else if (!correspondenceInfo.equals(other.correspondenceInfo))
			return false;
		if (courseNumber == null) {
			if (other.courseNumber != null)
				return false;
		} else if (!courseNumber.equals(other.courseNumber))
			return false;
		if (courseTitle == null) {
			if (other.courseTitle != null)
				return false;
		} else if (!courseTitle.equals(other.courseTitle))
			return false;
		if (creditHours == null) {
			if (other.creditHours != null)
				return false;
		} else if (!creditHours.equals(other.creditHours))
			return false;
		if (cycleCloseDate == null) {
			if (other.cycleCloseDate != null)
				return false;
		} else if (!cycleCloseDate.equals(other.cycleCloseDate))
			return false;
		if (cycleId == null) {
			if (other.cycleId != null)
				return false;
		} else if (!cycleId.equals(other.cycleId))
			return false;
		if (cycleStatus == null) {
			if (other.cycleStatus != null)
				return false;
		} else if (!cycleStatus.equals(other.cycleStatus))
			return false;
		if (cycleStatusCode == null) {
			if (other.cycleStatusCode != null)
				return false;
		} else if (!cycleStatusCode.equals(other.cycleStatusCode))
			return false;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (formativeAssessment == null) {
			if (other.formativeAssessment != null)
				return false;
		} else if (!formativeAssessment.equals(other.formativeAssessment))
			return false;
		if (goalPlan == null) {
			if (other.goalPlan != null)
				return false;
		} else if (!goalPlan.equals(other.goalPlan))
			return false;
		if (gradLevel == null) {
			if (other.gradLevel != null)
				return false;
		} else if (!gradLevel.equals(other.gradLevel))
			return false;
		if (hours == null) {
			if (other.hours != null)
				return false;
		} else if (!hours.equals(other.hours))
			return false;
		if (hoursFullResponsibility == null) {
			if (other.hoursFullResponsibility != null)
				return false;
		} else if (!hoursFullResponsibility.equals(other.hoursFullResponsibility))
			return false;
		if (incompleteWorksString == null) {
			if (other.incompleteWorksString != null)
				return false;
		} else if (!incompleteWorksString.equals(other.incompleteWorksString))
			return false;
		if (latestActDate == null) {
			if (other.latestActDate != null)
				return false;
		} else if (!latestActDate.equals(other.latestActDate))
			return false;
		if (managers == null) {
			if (other.managers != null)
				return false;
		} else if (!managers.equals(other.managers))
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
		if (origPractitioner == null) {
			if (other.origPractitioner != null)
				return false;
		} else if (!origPractitioner.equals(other.origPractitioner))
			return false;
		if (practicumHours == null) {
			if (other.practicumHours != null)
				return false;
		} else if (!practicumHours.equals(other.practicumHours))
			return false;
		if (practicumSchoolName == null) {
			if (other.practicumSchoolName != null)
				return false;
		} else if (!practicumSchoolName.equals(other.practicumSchoolName))
			return false;
		if (practicumTypeCode == null) {
			if (other.practicumTypeCode != null)
				return false;
		} else if (!practicumTypeCode.equals(other.practicumTypeCode))
			return false;
		if (practicumTypeDesc == null) {
			if (other.practicumTypeDesc != null)
				return false;
		} else if (!practicumTypeDesc.equals(other.practicumTypeDesc))
			return false;
		if (practitioner == null) {
			if (other.practitioner != null)
				return false;
		} else if (!practitioner.equals(other.practitioner))
			return false;
		if (practitionerInputMEPID == null) {
			if (other.practitionerInputMEPID != null)
				return false;
		} else if (!practitionerInputMEPID.equals(other.practitionerInputMEPID))
			return false;
		if (practitioners == null) {
			if (other.practitioners != null)
				return false;
		} else if (!practitioners.equals(other.practitioners))
			return false;
		if (programAreaGradLevel == null) {
			if (other.programAreaGradLevel != null)
				return false;
		} else if (!programAreaGradLevel.equals(other.programAreaGradLevel))
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
		if (reOpenCycleFlag == null) {
			if (other.reOpenCycleFlag != null)
				return false;
		} else if (!reOpenCycleFlag.equals(other.reOpenCycleFlag))
			return false;
		if (reOpenDate == null) {
			if (other.reOpenDate != null)
				return false;
		} else if (!reOpenDate.equals(other.reOpenDate))
			return false;
		if (reOpenErrorMessage == null) {
			if (other.reOpenErrorMessage != null)
				return false;
		} else if (!reOpenErrorMessage.equals(other.reOpenErrorMessage))
			return false;
		if (readyToTeach == null) {
			if (other.readyToTeach != null)
				return false;
		} else if (!readyToTeach.equals(other.readyToTeach))
			return false;
		if (schoolYear == null) {
			if (other.schoolYear != null)
				return false;
		} else if (!schoolYear.equals(other.schoolYear))
			return false;
		if (selfAssessment == null) {
			if (other.selfAssessment != null)
				return false;
		} else if (!selfAssessment.equals(other.selfAssessment))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		if (statusReasonTypeCode == null) {
			if (other.statusReasonTypeCode != null)
				return false;
		} else if (!statusReasonTypeCode.equals(other.statusReasonTypeCode))
			return false;
		if (summativeAssessment == null) {
			if (other.summativeAssessment != null)
				return false;
		} else if (!summativeAssessment.equals(other.summativeAssessment))
			return false;
		if (supervisor == null) {
			if (other.supervisor != null)
				return false;
		} else if (!supervisor.equals(other.supervisor))
			return false;
		if (supervisorFirstName == null) {
			if (other.supervisorFirstName != null)
				return false;
		} else if (!supervisorFirstName.equals(other.supervisorFirstName))
			return false;
		if (supervisorLastName == null) {
			if (other.supervisorLastName != null)
				return false;
		} else if (!supervisorLastName.equals(other.supervisorLastName))
			return false;
		if (supervisorMEPID == null) {
			if (other.supervisorMEPID != null)
				return false;
		} else if (!supervisorMEPID.equals(other.supervisorMEPID))
			return false;
		if (supervisorMiddleName == null) {
			if (other.supervisorMiddleName != null)
				return false;
		} else if (!supervisorMiddleName.equals(other.supervisorMiddleName))
			return false;
		if (supervisorName == null) {
			if (other.supervisorName != null)
				return false;
		} else if (!supervisorName.equals(other.supervisorName))
			return false;
		if (supervisorPersonId == null) {
			if (other.supervisorPersonId != null)
				return false;
		} else if (!supervisorPersonId.equals(other.supervisorPersonId))
			return false;
		if (unAnnouncedObs1 == null) {
			if (other.unAnnouncedObs1 != null)
				return false;
		} else if (!unAnnouncedObs1.equals(other.unAnnouncedObs1))
			return false;
		if (unAnnouncedObs2 == null) {
			if (other.unAnnouncedObs2 != null)
				return false;
		} else if (!unAnnouncedObs2.equals(other.unAnnouncedObs2))
			return false;
		if (waived == null) {
			if (other.waived != null)
				return false;
		} else if (!waived.equals(other.waived))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CapCycleInfo [cycleId=" + cycleId + ", candidateFirstName=" + candidateFirstName
				+ ", candidateLastName=" + candidateLastName + ", candidateMiddleName=" + candidateMiddleName
				+ ", candidateName=" + candidateName + ", candidateMEPID=" + candidateMEPID + ", candidatePersonId="
				+ candidatePersonId + ", supervisorFirstName=" + supervisorFirstName + ", supervisorLastName="
				+ supervisorLastName + ", supervisorMiddleName=" + supervisorMiddleName + ", supervisorName="
				+ supervisorName + ", supervisorMEPID=" + supervisorMEPID + ", supervisorPersonId=" + supervisorPersonId
				+ ", supervisor=" + supervisor + ", practitioner=" + practitioner + ", origPractitioner="
				+ origPractitioner + ", practitioners=" + practitioners + ", programName=" + programName
				+ ", programId=" + programId + ", startDate=" + startDate + ", cycleCloseDate=" + cycleCloseDate
				+ ", schoolYear=" + schoolYear + ", practitionerInputMEPID=" + practitionerInputMEPID + ", orgId="
				+ orgId + ", orgCode=" + orgCode + ", orgName=" + orgName + ", waived=" + waived
				+ ", practicumTypeCode=" + practicumTypeCode + ", practicumTypeDesc=" + practicumTypeDesc
				+ ", statusReasonTypeCode=" + statusReasonTypeCode + ", programAreaGradLevel=" + programAreaGradLevel
				+ ", courseTitle=" + courseTitle + ", courseNumber=" + courseNumber + ", gradLevel=" + gradLevel
				+ ", hours=" + hours + ", hoursFullResponsibility=" + hoursFullResponsibility + ", practicumHours="
				+ practicumHours + ", creditHours=" + creditHours + ", readyToTeach=" + readyToTeach
				+ ", selfAssessment=" + selfAssessment + ", announcedObs1=" + announcedObs1 + ", goalPlan=" + goalPlan
				+ ", unAnnouncedObs1=" + unAnnouncedObs1 + ", announcedObs2=" + announcedObs2 + ", formativeAssessment="
				+ formativeAssessment + ", unAnnouncedObs2=" + unAnnouncedObs2 + ", summativeAssessment="
				+ summativeAssessment + ", latestActDate=" + latestActDate + ", cycleStatus=" + cycleStatus
				+ ", cycleStatusCode=" + cycleStatusCode + ", practicumSchoolName=" + practicumSchoolName
				+ ", incompleteWorksString=" + incompleteWorksString + ", reOpenCycleFlag=" + reOpenCycleFlag
				+ ", reOpenDate=" + reOpenDate + ", endDate=" + endDate + ", autoCloseIndictor=" + autoCloseIndictor
				+ ", autoCloseFirstEmailDate=" + autoCloseFirstEmailDate + ", autoCloseEmailDate=" + autoCloseEmailDate
				+ ", managers=" + managers + ", correspondenceInfo=" + correspondenceInfo + ", reOpenErrorMessage="
				+ reOpenErrorMessage + "]";
	}
	
	
	
}
