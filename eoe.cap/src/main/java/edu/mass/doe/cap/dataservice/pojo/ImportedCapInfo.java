package edu.mass.doe.cap.dataservice.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;

public class ImportedCapInfo {

	private Long uploadId;
	
	private Long orgId;
	
	private Long districtId;
	
	private String districtName;
	
	private Long schoolId;
	
	private String schoolName;
	
	private String schoolYear;
	
	private Long programId;
	
	private String programName;
	
	private String candidateName;
	
	private Long candidateMepid;
	
	private String candidateEmail;
	
	private String supervisorName;	
	
	private String supervisorEmail;
	
	private String practitionerName;	
	
	private String practitionerEmail;
	
	private Long practitionerMepid;

	private String readyToTeach;
	
	private String status;
	
	private String statusDesc;
	
	
	private Date importDate;
	
	private List<ElementInfo> summativeElements = new ArrayList<ElementInfo>();
	
	private List<ElementInfo> formativeElements = new ArrayList<ElementInfo>();

	public Long getUploadId() {
		return uploadId;
	}

	public void setUploadId(Long uploadId) {
		this.uploadId = uploadId;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public Long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}

	public Long getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Long schoolId) {
		this.schoolId = schoolId;
	}

	public String getSchoolYear() {
		return schoolYear;
	}

	public void setSchoolYear(String schoolYear) {
		this.schoolYear = schoolYear;
	}

	public Long getProgramId() {
		return programId;
	}

	public void setProgramId(Long programId) {
		this.programId = programId;
	}

	public Long getCandidateMepid() {
		return candidateMepid;
	}

	public void setCandidateMepid(Long candidateMepid) {
		this.candidateMepid = candidateMepid;
	}

	public String getCandidateEmail() {
		return candidateEmail;
	}

	public void setCandidateEmail(String candidateEmail) {
		this.candidateEmail = candidateEmail;
	}

	public String getSupervisorName() {
		return supervisorName;
	}

	public void setSupervisorName(String supervisorName) {
		this.supervisorName = supervisorName;
	}

	public String getSupervisorEmail() {
		return supervisorEmail;
	}

	public void setSupervisorEmail(String supervisorEmail) {
		this.supervisorEmail = supervisorEmail;
	}

	public String getPractitionerEmail() {
		return practitionerEmail;
	}

	public void setPractitionerEmail(String practitionerEmail) {
		this.practitionerEmail = practitionerEmail;
	}

	public Long getPractitionerMepid() {
		return practitionerMepid;
	}

	public void setPractitionerMepid(Long practitionerMepid) {
		this.practitionerMepid = practitionerMepid;
	}

	public String getReadyToTeach() {
		return readyToTeach;
	}

	public void setReadyToTeach(String readyToTeach) {
		this.readyToTeach = readyToTeach;
	}

	public List<ElementInfo> getSummativeElements() {
		return summativeElements;
	}

	public void setSummativeElements(List<ElementInfo> summativeElements) {
		this.summativeElements = summativeElements;
	}

	public List<ElementInfo> getFormativeElements() {
		return formativeElements;
	}

	public void setFormativeElements(List<ElementInfo> formativeElements) {
		this.formativeElements = formativeElements;
	}

	public String getCandidateName() {
		return candidateName;
	}

	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}

	public String getPractitionerName() {
		return practitionerName;
	}

	public void setPractitionerName(String practitionerName) {
		this.practitionerName = practitionerName;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public Date getImportDate() {
		return importDate;
	}

	public void setImportDate(Date importDate) {
		this.importDate = importDate;
	}

	public String getProgramName() {
		return programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	
}
