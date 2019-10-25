package edu.mass.doe.cap.candidate;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;

import edu.mass.doe.cap.dataservice.pojo.CandidateInfo;
import edu.mass.doe.cap.dataservice.pojo.SupervisorInfo;

public class CandidateEnrollment {
	
	private Long orgId; 
	private String selectProgramId;
	private boolean isNewForm;
	private String selectSponsoringOrgId;

	
	@Pattern(regexp="^5\\d{7}")
	private String inputmepid;
	
	
	private Long programCompleterId;
	
	private Long programId;
	private String programName;
	
	private String candidateName;
	
	private Long candidatePersonId;
	private Long candidatedaPersonId;
	private Long mepid;
	
	private String supervisorName;
	private Long supervisorPersonId;
	private Long supervisordaPersonId;
	
	private Integer schoolYear;
	
		
	private SupervisorInfo practitioner;
	
	

	
	@DateTimeFormat(pattern = "MM/dd/yyyy") 
	private Date cycleStartDate;
	
	
	private String practitionerInputMEPID;
	
	private String schoolYearName;
	
	
	
	public CandidateEnrollment() {
		practitioner=new SupervisorInfo();
	}



	public Long getOrgId() {
		return orgId;
	}



	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}



	public String getSelectProgramId() {
		return selectProgramId;
	}



	public void setSelectProgramId(String selectProgramId) {
		this.selectProgramId = selectProgramId;
	}



	public String getInputmepid() {
		return inputmepid;
	}



	public void setInputmepid(String inputmepid) {
		this.inputmepid = inputmepid;
	}



	public Long getProgramCompleterId() {
		return programCompleterId;
	}



	public void setProgramCompleterId(Long programCompleterId) {
		this.programCompleterId = programCompleterId;
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



	public String getCandidateName() {
		return candidateName;
	}



	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}



	public Long getCandidatePersonId() {
		return candidatePersonId;
	}



	public void setCandidatePersonId(Long candidatePersonId) {
		this.candidatePersonId = candidatePersonId;
	}



	public Long getCandidatedaPersonId() {
		return candidatedaPersonId;
	}



	public void setCandidatedaPersonId(Long candidatedaPersonId) {
		this.candidatedaPersonId = candidatedaPersonId;
	}



	public Long getMepid() {
		return mepid;
	}



	public void setMepid(Long mepid) {
		this.mepid = mepid;
	}



	public String getSupervisorName() {
		return supervisorName;
	}



	public void setSupervisorName(String supervisorName) {
		this.supervisorName = supervisorName;
	}



	public Long getSupervisorPersonId() {
		return supervisorPersonId;
	}



	public void setSupervisorPersonId(Long supervisorPersonId) {
		this.supervisorPersonId = supervisorPersonId;
	}



	public Long getSupervisordaPersonId() {
		return supervisordaPersonId;
	}



	public void setSupervisordaPersonId(Long supervisordaPersonId) {
		this.supervisordaPersonId = supervisordaPersonId;
	}



	public Integer getSchoolYear() {
		return schoolYear;
	}



	public void setSchoolYear(Integer schoolYear) {
		this.schoolYear = schoolYear;
	}



	public SupervisorInfo getPractitioner() {
		return practitioner;
	}



	public void setPractitioner(SupervisorInfo practitioner) {
		this.practitioner = practitioner;
	}




	public Date getCycleStartDate() {
		return cycleStartDate;
	}



	public void setCycleStartDate(Date cycleStartDate) {
		this.cycleStartDate = cycleStartDate;
	}



	public String getPractitionerInputMEPID() {
		return practitionerInputMEPID;
	}



	public void setPractitionerInputMEPID(String practitionerInputMEPID) {
		this.practitionerInputMEPID = practitionerInputMEPID;
	}



	public String getSchoolYearName() {
		return schoolYearName;
	}



	public void setSchoolYearName(String schoolYearName) {
		this.schoolYearName = schoolYearName;
	}



	/**
	 * @return the isNewForm
	 */
	public boolean getNewForm() {
		return isNewForm;
	}



	/**
	 * @param isNewForm the isNewForm to set
	 */
	public void setNewForm(boolean isNewForm) {
		this.isNewForm = isNewForm;
	}



	public String getSelectSponsoringOrgId() {
		return selectSponsoringOrgId;
	}



	public void setSelectSponsoringOrgId(String selectSponsoringOrgId) {
		this.selectSponsoringOrgId = selectSponsoringOrgId;
		//this.orgId=new Long(selectSponsoringOrgId);
	}



	public boolean isNewForm() {
		return isNewForm;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((candidateName == null) ? 0 : candidateName.hashCode());
		result = prime * result + ((candidatePersonId == null) ? 0 : candidatePersonId.hashCode());
		result = prime * result + ((candidatedaPersonId == null) ? 0 : candidatedaPersonId.hashCode());
		result = prime * result + ((cycleStartDate == null) ? 0 : cycleStartDate.hashCode());
		result = prime * result + ((inputmepid == null) ? 0 : inputmepid.hashCode());
		result = prime * result + (isNewForm ? 1231 : 1237);
		result = prime * result + ((mepid == null) ? 0 : mepid.hashCode());
		result = prime * result + ((orgId == null) ? 0 : orgId.hashCode());
		result = prime * result + ((practitioner == null) ? 0 : practitioner.hashCode());
		result = prime * result + ((practitionerInputMEPID == null) ? 0 : practitionerInputMEPID.hashCode());
		result = prime * result + ((programCompleterId == null) ? 0 : programCompleterId.hashCode());
		result = prime * result + ((programId == null) ? 0 : programId.hashCode());
		result = prime * result + ((programName == null) ? 0 : programName.hashCode());
		result = prime * result + ((schoolYear == null) ? 0 : schoolYear.hashCode());
		result = prime * result + ((schoolYearName == null) ? 0 : schoolYearName.hashCode());
		result = prime * result + ((selectProgramId == null) ? 0 : selectProgramId.hashCode());
		result = prime * result + ((selectSponsoringOrgId == null) ? 0 : selectSponsoringOrgId.hashCode());
		result = prime * result + ((supervisorName == null) ? 0 : supervisorName.hashCode());
		result = prime * result + ((supervisorPersonId == null) ? 0 : supervisorPersonId.hashCode());
		result = prime * result + ((supervisordaPersonId == null) ? 0 : supervisordaPersonId.hashCode());
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
		CandidateEnrollment other = (CandidateEnrollment) obj;
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
		if (candidatedaPersonId == null) {
			if (other.candidatedaPersonId != null)
				return false;
		} else if (!candidatedaPersonId.equals(other.candidatedaPersonId))
			return false;
		if (cycleStartDate == null) {
			if (other.cycleStartDate != null)
				return false;
		} else if (!cycleStartDate.equals(other.cycleStartDate))
			return false;
		if (inputmepid == null) {
			if (other.inputmepid != null)
				return false;
		} else if (!inputmepid.equals(other.inputmepid))
			return false;
		if (isNewForm != other.isNewForm)
			return false;
		if (mepid == null) {
			if (other.mepid != null)
				return false;
		} else if (!mepid.equals(other.mepid))
			return false;
		if (orgId == null) {
			if (other.orgId != null)
				return false;
		} else if (!orgId.equals(other.orgId))
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
		if (schoolYear == null) {
			if (other.schoolYear != null)
				return false;
		} else if (!schoolYear.equals(other.schoolYear))
			return false;
		if (schoolYearName == null) {
			if (other.schoolYearName != null)
				return false;
		} else if (!schoolYearName.equals(other.schoolYearName))
			return false;
		if (selectProgramId == null) {
			if (other.selectProgramId != null)
				return false;
		} else if (!selectProgramId.equals(other.selectProgramId))
			return false;
		if (selectSponsoringOrgId == null) {
			if (other.selectSponsoringOrgId != null)
				return false;
		} else if (!selectSponsoringOrgId.equals(other.selectSponsoringOrgId))
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
		if (supervisordaPersonId == null) {
			if (other.supervisordaPersonId != null)
				return false;
		} else if (!supervisordaPersonId.equals(other.supervisordaPersonId))
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "CandidateEnrollment [orgId=" + orgId + ", selectProgramId=" + selectProgramId + ", isNewForm="
				+ isNewForm + ", selectSponsoringOrgId=" + selectSponsoringOrgId + ", inputmepid=" + inputmepid
				+ ", programCompleterId=" + programCompleterId + ", programId=" + programId + ", programName="
				+ programName + ", candidateName=" + candidateName + ", candidatePersonId=" + candidatePersonId
				+ ", candidatedaPersonId=" + candidatedaPersonId + ", mepid=" + mepid + ", supervisorName="
				+ supervisorName + ", supervisorPersonId=" + supervisorPersonId + ", supervisordaPersonId="
				+ supervisordaPersonId + ", schoolYear=" + schoolYear + ", practitioner=" + practitioner
				+ ", cycleStartDate=" + cycleStartDate + ", practitionerInputMEPID=" + practitionerInputMEPID
				+ ", schoolYearName=" + schoolYearName + "]";
	}


	

}
