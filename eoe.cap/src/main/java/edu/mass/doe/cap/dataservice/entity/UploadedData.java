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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

@Entity
@Table(name = "CP071FUP_FILE_UPLOAD")
@AttributeOverrides({
@AttributeOverride(name = "createDate", column = @Column(name = "FUP_CREATE_DATE")),
@AttributeOverride(name = "createUser", column = @Column(name = "FUP_CREATED_BY")),
@AttributeOverride(name = "modifyDate", column = @Column(name = "FUP_UPDATE_DATE")),
@AttributeOverride(name = "modifyUser", column = @Column(name = "FUP_UPDATED_BY"))
})
public class UploadedData extends BaseJPAEntity {
	@Id
	@Column(name="FUP_UPLOAD_ID")
	@SequenceGenerator(name = "FUP_PK_GENERATOR", sequenceName = "CP071FUP_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FUP_PK_GENERATOR")
	private Long uploadId;
	
	@Column(name="FUP_SPONSORING_ORG_ID")
	private Long orgId;
	
	@Column(name="FUP_PRACT_LEA_ID")
	private Long districtId;
	
	@Column(name="FUP_PRACT_SCH_ID")
	private Long schoolId;
	
	@Column(name="FUP_SCH_YEAR_NUM")
	private String schoolYear;
	
	@Column(name="FUP_PRG_ID")
	private Long programId;
	
	@Column(name="FUP_CAND_MEPID")
	private Long candidateMepid;
	
	@Column(name="FUP_CAND_EMAIL")
	private String candidateEmail;

	
	@Column(name="FUP_PS_NAME")
	private String supervisorName;	
	
	@Column(name="FUP_PS_EMAIL")
	private String supervisorEmail;
	
	@Column(name="FUP_SP_EMAIL")
	private String practitionerEmail;
	
	@Column(name="FUP_SP_MEPID")
	private Long practitionerMepid;
	

	@Column(name="FUP_READY_TO_TEACH")
	private String readyToTeach;
	
	@Column(name="FUP_EXP_DATE")
	private Date expDate;
	
	@Column(name="FUP_EFF_DATE")
	private Date effDate;
	
	@Column(name="FUP_STATUS_CODE")
	private String FUP_STATUS_CODE;

	@OneToMany(mappedBy = "uploadedData", cascade = CascadeType.PERSIST,fetch=FetchType.LAZY)
	@Where(clause = "FUR_EXP_DATE is null")
	private List<UploadedRating> ratings;
	
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

	public List<UploadedRating> getRatings() {
		return ratings;
	}

	public void setRatings(List<UploadedRating> ratings) {
		this.ratings = ratings;
	}

	public String getFUP_STATUS_CODE() {
		return FUP_STATUS_CODE;
	}

	public void setFUP_STATUS_CODE(String fUP_STATUS_CODE) {
		FUP_STATUS_CODE = fUP_STATUS_CODE;
	}
	
	

}
