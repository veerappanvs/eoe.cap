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

import org.hibernate.annotations.Where;




@Entity
@Table(name = "CP069FUS_FILE_UPLOAD_STAGING")
@AttributeOverrides({
@AttributeOverride(name = "createDate", column = @Column(name = "FUS_CREATE_DATE")),
@AttributeOverride(name = "createUser", column = @Column(name = "FUS_CREATED_BY")),
@AttributeOverride(name = "modifyDate", column = @Column(name = "FUS_UPDATE_DATE")),
@AttributeOverride(name = "modifyUser", column = @Column(name = "FUS_UPDATED_BY"))
})
public class BatchStaging  extends BaseJPAEntity{
	
	@Id
	@Column(name="FUS_FILE_STAGING_ID")
	@SequenceGenerator(name = "FUS_PK_GENERATOR", sequenceName = "CP069FUS_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FUS_PK_GENERATOR")
	private Long stagingId;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FUS_FILE_UPLOAD_ID", insertable = true, updatable = false,nullable=false)
	private Batch batch;	
	
	@Column(name="FUS_ORGANIZATION_NM")
	private String organizationName;
	
	@Column(name="FUS_PRACT_DISTRICT_NAME")
	private String districtName;
	
	@Column(name="FUS_PRACT_SCHOOL_NAME")
	private String schoolName;
	
	@Column(name="FUS_SCH_YEAR")
	private String schoolYear;
	
	@Column(name="FUS_PROGRAM_NAME")
	private String programName;
	
	@Column(name="FUS_CAND_MEPID")
	private String candidateMEPId;
	
	@Column(name="FUS_CAND_FIRST_NAME")
	private String candidateFirstname;
	
	@Column(name="FUS_CAND_LAST_NAME")
	private String candidateLastname;
	
	@Column(name="FUS_CAND_EMAIL")
	private String candidateEmail;
	
		
	@Column(name="FUS_PS_NAME")
	private String psName;
	
	@Column(name="FUS_PS_EMAIL")
	private String psEmail;
	
	@Column(name="FUS_SP_MEPID")
	private String spMEPID;
	
	@Column(name="FUS_SP_NAME")
	private String spName;
	
	@Column(name="FUS_SP_FIRST_NAME")
	private String spFirstName;
	
	@Column(name="FUS_SP_LAST_NAME")
	private String spLastName;
	
	@Column(name="FUS_SP_EMAIL")
	private String spEmail;
	
	@Column(name="FUS_F1A4Q")
	private String f1a4q;
	
	@Column(name="FUS_F1A4S")
	private String f1a4s;
	
	@Column(name="FUS_F1A4C")
	private String f1a4c;
	
	@Column(name="FUS_F1B2Q")
	private String f1b2q;
	
	@Column(name="FUS_F1B2S")
	private String f1b2s;
	
	@Column(name="FUS_F1B2C")
	private String f1b2c;
	
	@Column(name="FUS_F2A3Q")
	private String f2a3q;
	
	@Column(name="FUS_F2A3S")
	private String f2a3s;
	
	@Column(name="FUS_F2A3C")
	private String f2a3c;
	
	@Column(name="FUS_F2B1Q")
	private String f2b1q;
	
	@Column(name="FUS_F2B1S")
	private String f2b1s;
	
	@Column(name="FUS_F2B1C")
	private String f2b1c;
	
	@Column(name="FUS_F2D2Q")
	private String f2d2q;
	
	@Column(name="FUS_F2D2S")
	private String f2d2s;
	
	@Column(name="FUS_F2D2C")
	private String f2d2c;
	
	@Column(name="FUS_F4A1Q")
	private String f4a1q;
	
	@Column(name="FUS_F4A1S")
	private String f4a1s;
	
	@Column(name="FUS_F4A1C")
	private String f4a1c;
	
	@Column(name="FUS_S1A4Q")
	private String s1a4q;
	
	@Column(name="FUS_S1A4S")
	private String s1a4s;
	
	@Column(name="FUS_S1A4C")
	private String s1a4c;
	
	@Column(name="FUS_S1B2Q")
	private String s1b2q;
	
	@Column(name="FUS_S1B2S")
	private String s1b2s;
	
	@Column(name="FUS_S1B2C")
	private String s1b2c;
	
	@Column(name="FUS_S2A3Q")
	private String s2a3q;
	
	@Column(name="FUS_S2A3S")
	private String s2a3s;
	
	@Column(name="FUS_S2A3C")
	private String s2a3c;
	
	@Column(name="FUS_S2B1Q")
	private String s2b1q;
	
	@Column(name="FUS_S2B1S")
	private String s2b1s;
	
	@Column(name="FUS_S2B1C")
	private String s2b1c;
	
	@Column(name="FUS_S2D2Q")
	private String s2d2q;
	
	@Column(name="FUS_S2D2S")
	private String s2d2s;
	
	@Column(name="FUS_S2D2C")
	private String s2d2c;
	
	@Column(name="FUS_S4A1Q")
	private String s4a1q;
	
	@Column(name="FUS_S4A1S")
	private String s4a1s;
	
	@Column(name="FUS_S4A1C")
	private String s4a1c;
	
	@Column(name="FUS_READY_TO_TEACH")
	private String readyToTeach;
	
	@Column(name="FUS_STATUS_CODE")
	private String statusCode;
	
	@Column(name="FUS_EXP_DATE")
	private Date expDate;
	
	@Column(name="FUS_EFF_DATE")
	private Date effDate;
	
	
	@OneToMany(mappedBy = "batchStaging", cascade = CascadeType.PERSIST,fetch=FetchType.LAZY)
	@Where(clause = "FUE_EXP_DATE is null")
	private List<BatchError> errors;


	public Long getStagingId() {
		return stagingId;
	}


	public void setStagingId(Long stagingId) {
		this.stagingId = stagingId;
	}


	public Batch getBatch() {
		return batch;
	}


	public void setBatch(Batch batch) {
		this.batch = batch;
	}


	public String getOrganizationName() {
		return organizationName;
	}


	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}


	public String getDistrictName() {
		return districtName;
	}


	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}


	public String getSchoolName() {
		return schoolName;
	}


	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}


	public String getSchoolYear() {
		return schoolYear;
	}


	public void setSchoolYear(String schoolYear) {
		this.schoolYear = schoolYear;
	}


	public String getProgramName() {
		return programName;
	}


	public void setProgramName(String programName) {
		this.programName = programName;
	}


	public String getCandidateMEPId() {
		return candidateMEPId;
	}


	public void setCandidateMEPId(String candidateMEPId) {
		this.candidateMEPId = candidateMEPId;
	}


	public String getCandidateFirstname() {
		return candidateFirstname;
	}


	public void setCandidateFirstname(String candidateFirstname) {
		this.candidateFirstname = candidateFirstname;
	}


	public String getCandidateLastname() {
		return candidateLastname;
	}


	public void setCandidateLastname(String candidateLastname) {
		this.candidateLastname = candidateLastname;
	}


	public String getCandidateEmail() {
		return candidateEmail;
	}


	public void setCandidateEmail(String candidateEmail) {
		this.candidateEmail = candidateEmail;
	}


	public String getPsName() {
		return psName;
	}


	public void setPsName(String psName) {
		this.psName = psName;
	}


	public String getPsEmail() {
		return psEmail;
	}


	public void setPsEmail(String psEmail) {
		this.psEmail = psEmail;
	}


	public String getSpMEPID() {
		return spMEPID;
	}


	public void setSpMEPID(String spMEPID) {
		this.spMEPID = spMEPID;
	}


	public String getSpName() {
		return spName;
	}


	public void setSpName(String spName) {
		this.spName = spName;
	}


	public String getSpFirstName() {
		return spFirstName;
	}


	public void setSpFirstName(String spFirstName) {
		this.spFirstName = spFirstName;
	}


	public String getSpLastName() {
		return spLastName;
	}


	public void setSpLastName(String spLastName) {
		this.spLastName = spLastName;
	}


	


	public String getF1a4q() {
		return f1a4q;
	}


	public void setF1a4q(String f1a4q) {
		this.f1a4q = f1a4q;
	}


	public String getF1a4s() {
		return f1a4s;
	}


	public void setF1a4s(String f1a4s) {
		this.f1a4s = f1a4s;
	}


	public String getF1a4c() {
		return f1a4c;
	}


	public void setF1a4c(String f1a4c) {
		this.f1a4c = f1a4c;
	}


	public String getF1b2q() {
		return f1b2q;
	}


	public void setF1b2q(String f1b2q) {
		this.f1b2q = f1b2q;
	}


	public String getF1b2s() {
		return f1b2s;
	}


	public void setF1b2s(String f1b2s) {
		this.f1b2s = f1b2s;
	}


	public String getF1b2c() {
		return f1b2c;
	}


	public void setF1b2c(String f1b2c) {
		this.f1b2c = f1b2c;
	}


	public String getF2a3q() {
		return f2a3q;
	}


	public void setF2a3q(String f2a3q) {
		this.f2a3q = f2a3q;
	}


	public String getF2a3s() {
		return f2a3s;
	}


	public void setF2a3s(String f2a3s) {
		this.f2a3s = f2a3s;
	}


	public String getF2a3c() {
		return f2a3c;
	}


	public void setF2a3c(String f2a3c) {
		this.f2a3c = f2a3c;
	}


	public String getF2b1q() {
		return f2b1q;
	}


	public void setF2b1q(String f2b1q) {
		this.f2b1q = f2b1q;
	}


	public String getF2b1s() {
		return f2b1s;
	}


	public void setF2b1s(String f2b1s) {
		this.f2b1s = f2b1s;
	}


	public String getF2b1c() {
		return f2b1c;
	}


	public void setF2b1c(String f2b1c) {
		this.f2b1c = f2b1c;
	}


	public String getF2d2q() {
		return f2d2q;
	}


	public void setF2d2q(String f2d2q) {
		this.f2d2q = f2d2q;
	}


	public String getF2d2s() {
		return f2d2s;
	}


	public void setF2d2s(String f2d2s) {
		this.f2d2s = f2d2s;
	}


	public String getF2d2c() {
		return f2d2c;
	}


	public void setF2d2c(String f2d2c) {
		this.f2d2c = f2d2c;
	}


	public String getF4a1q() {
		return f4a1q;
	}


	public void setF4a1q(String f4a1q) {
		this.f4a1q = f4a1q;
	}


	public String getF4a1s() {
		return f4a1s;
	}


	public void setF4a1s(String f4a1s) {
		this.f4a1s = f4a1s;
	}


	public String getF4a1c() {
		return f4a1c;
	}


	public void setF4a1c(String f4a1c) {
		this.f4a1c = f4a1c;
	}


	public String getS1a4q() {
		return s1a4q;
	}


	public void setS1a4q(String s1a4q) {
		this.s1a4q = s1a4q;
	}


	public String getS1a4s() {
		return s1a4s;
	}


	public void setS1a4s(String s1a4s) {
		this.s1a4s = s1a4s;
	}


	public String getS1a4c() {
		return s1a4c;
	}


	public void setS1a4c(String s1a4c) {
		this.s1a4c = s1a4c;
	}


	public String getS1b2q() {
		return s1b2q;
	}


	public void setS1b2q(String s1b2q) {
		this.s1b2q = s1b2q;
	}


	public String getS1b2s() {
		return s1b2s;
	}


	public void setS1b2s(String s1b2s) {
		this.s1b2s = s1b2s;
	}


	public String getS1b2c() {
		return s1b2c;
	}


	public void setS1b2c(String s1b2c) {
		this.s1b2c = s1b2c;
	}


	public String getS2a3q() {
		return s2a3q;
	}


	public void setS2a3q(String s2a3q) {
		this.s2a3q = s2a3q;
	}


	public String getS2a3s() {
		return s2a3s;
	}


	public void setS2a3s(String s2a3s) {
		this.s2a3s = s2a3s;
	}


	public String getS2a3c() {
		return s2a3c;
	}


	public void setS2a3c(String s2a3c) {
		this.s2a3c = s2a3c;
	}


	public String getS2b1q() {
		return s2b1q;
	}


	public void setS2b1q(String s2b1q) {
		this.s2b1q = s2b1q;
	}


	public String getS2b1s() {
		return s2b1s;
	}


	public void setS2b1s(String s2b1s) {
		this.s2b1s = s2b1s;
	}


	public String getS2b1c() {
		return s2b1c;
	}


	public void setS2b1c(String s2b1c) {
		this.s2b1c = s2b1c;
	}


	public String getS2d2q() {
		return s2d2q;
	}


	public void setS2d2q(String s2d2q) {
		this.s2d2q = s2d2q;
	}


	public String getS2d2s() {
		return s2d2s;
	}


	public void setS2d2s(String s2d2s) {
		this.s2d2s = s2d2s;
	}


	public String getS2d2c() {
		return s2d2c;
	}


	public void setS2d2c(String s2d2c) {
		this.s2d2c = s2d2c;
	}


	public String getS4a1q() {
		return s4a1q;
	}


	public void setS4a1q(String s4a1q) {
		this.s4a1q = s4a1q;
	}


	public String getS4a1s() {
		return s4a1s;
	}


	public void setS4a1s(String s4a1s) {
		this.s4a1s = s4a1s;
	}


	public String getS4a1c() {
		return s4a1c;
	}


	public void setS4a1c(String s4a1c) {
		this.s4a1c = s4a1c;
	}


	public String getReadyToTeach() {
		return readyToTeach;
	}


	public void setReadyToTeach(String readyToTeach) {
		this.readyToTeach = readyToTeach;
	}


	public String getStatusCode() {
		return statusCode;
	}


	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
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


	public List<BatchError> getErrors() {
		return errors;
	}


	public void setErrors(List<BatchError> errors) {
		this.errors = errors;
	}


	public String getSpEmail() {
		return spEmail;
	}


	public void setSpEmail(String spEmail) {
		this.spEmail = spEmail;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((batch == null) ? 0 : batch.hashCode());
		result = prime * result + ((candidateEmail == null) ? 0 : candidateEmail.hashCode());
		result = prime * result + ((candidateFirstname == null) ? 0 : candidateFirstname.hashCode());
		result = prime * result + ((candidateLastname == null) ? 0 : candidateLastname.hashCode());
		result = prime * result + ((candidateMEPId == null) ? 0 : candidateMEPId.hashCode());
		result = prime * result + ((districtName == null) ? 0 : districtName.hashCode());
		result = prime * result + ((effDate == null) ? 0 : effDate.hashCode());
		result = prime * result + ((errors == null) ? 0 : errors.hashCode());
		result = prime * result + ((expDate == null) ? 0 : expDate.hashCode());
		result = prime * result + ((f1a4c == null) ? 0 : f1a4c.hashCode());
		result = prime * result + ((f1a4q == null) ? 0 : f1a4q.hashCode());
		result = prime * result + ((f1a4s == null) ? 0 : f1a4s.hashCode());
		result = prime * result + ((f1b2c == null) ? 0 : f1b2c.hashCode());
		result = prime * result + ((f1b2q == null) ? 0 : f1b2q.hashCode());
		result = prime * result + ((f1b2s == null) ? 0 : f1b2s.hashCode());
		result = prime * result + ((f2a3c == null) ? 0 : f2a3c.hashCode());
		result = prime * result + ((f2a3q == null) ? 0 : f2a3q.hashCode());
		result = prime * result + ((f2a3s == null) ? 0 : f2a3s.hashCode());
		result = prime * result + ((f2b1c == null) ? 0 : f2b1c.hashCode());
		result = prime * result + ((f2b1q == null) ? 0 : f2b1q.hashCode());
		result = prime * result + ((f2b1s == null) ? 0 : f2b1s.hashCode());
		result = prime * result + ((f2d2c == null) ? 0 : f2d2c.hashCode());
		result = prime * result + ((f2d2q == null) ? 0 : f2d2q.hashCode());
		result = prime * result + ((f2d2s == null) ? 0 : f2d2s.hashCode());
		result = prime * result + ((f4a1c == null) ? 0 : f4a1c.hashCode());
		result = prime * result + ((f4a1q == null) ? 0 : f4a1q.hashCode());
		result = prime * result + ((f4a1s == null) ? 0 : f4a1s.hashCode());
		result = prime * result + ((organizationName == null) ? 0 : organizationName.hashCode());
		result = prime * result + ((programName == null) ? 0 : programName.hashCode());
		result = prime * result + ((psEmail == null) ? 0 : psEmail.hashCode());
		result = prime * result + ((psName == null) ? 0 : psName.hashCode());
		result = prime * result + ((readyToTeach == null) ? 0 : readyToTeach.hashCode());
		result = prime * result + ((s1a4c == null) ? 0 : s1a4c.hashCode());
		result = prime * result + ((s1a4q == null) ? 0 : s1a4q.hashCode());
		result = prime * result + ((s1a4s == null) ? 0 : s1a4s.hashCode());
		result = prime * result + ((s1b2c == null) ? 0 : s1b2c.hashCode());
		result = prime * result + ((s1b2q == null) ? 0 : s1b2q.hashCode());
		result = prime * result + ((s1b2s == null) ? 0 : s1b2s.hashCode());
		result = prime * result + ((s2a3c == null) ? 0 : s2a3c.hashCode());
		result = prime * result + ((s2a3q == null) ? 0 : s2a3q.hashCode());
		result = prime * result + ((s2a3s == null) ? 0 : s2a3s.hashCode());
		result = prime * result + ((s2b1c == null) ? 0 : s2b1c.hashCode());
		result = prime * result + ((s2b1q == null) ? 0 : s2b1q.hashCode());
		result = prime * result + ((s2b1s == null) ? 0 : s2b1s.hashCode());
		result = prime * result + ((s2d2c == null) ? 0 : s2d2c.hashCode());
		result = prime * result + ((s2d2q == null) ? 0 : s2d2q.hashCode());
		result = prime * result + ((s2d2s == null) ? 0 : s2d2s.hashCode());
		result = prime * result + ((s4a1c == null) ? 0 : s4a1c.hashCode());
		result = prime * result + ((s4a1q == null) ? 0 : s4a1q.hashCode());
		result = prime * result + ((s4a1s == null) ? 0 : s4a1s.hashCode());
		result = prime * result + ((schoolName == null) ? 0 : schoolName.hashCode());
		result = prime * result + ((schoolYear == null) ? 0 : schoolYear.hashCode());
		result = prime * result + ((spEmail == null) ? 0 : spEmail.hashCode());
		result = prime * result + ((spFirstName == null) ? 0 : spFirstName.hashCode());
		result = prime * result + ((spLastName == null) ? 0 : spLastName.hashCode());
		result = prime * result + ((spMEPID == null) ? 0 : spMEPID.hashCode());
		result = prime * result + ((spName == null) ? 0 : spName.hashCode());
		result = prime * result + ((stagingId == null) ? 0 : stagingId.hashCode());
		result = prime * result + ((statusCode == null) ? 0 : statusCode.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		BatchStaging other = (BatchStaging) obj;
		if (batch == null) {
			if (other.batch != null)
				return false;
		} else if (!batch.equals(other.batch))
			return false;
		if (candidateEmail == null) {
			if (other.candidateEmail != null)
				return false;
		} else if (!candidateEmail.equals(other.candidateEmail))
			return false;
		if (candidateFirstname == null) {
			if (other.candidateFirstname != null)
				return false;
		} else if (!candidateFirstname.equals(other.candidateFirstname))
			return false;
		if (candidateLastname == null) {
			if (other.candidateLastname != null)
				return false;
		} else if (!candidateLastname.equals(other.candidateLastname))
			return false;
		if (candidateMEPId == null) {
			if (other.candidateMEPId != null)
				return false;
		} else if (!candidateMEPId.equals(other.candidateMEPId))
			return false;
		if (districtName == null) {
			if (other.districtName != null)
				return false;
		} else if (!districtName.equals(other.districtName))
			return false;
		if (effDate == null) {
			if (other.effDate != null)
				return false;
		} else if (!effDate.equals(other.effDate))
			return false;
		if (errors == null) {
			if (other.errors != null)
				return false;
		} else if (!errors.equals(other.errors))
			return false;
		if (expDate == null) {
			if (other.expDate != null)
				return false;
		} else if (!expDate.equals(other.expDate))
			return false;
		if (f1a4c == null) {
			if (other.f1a4c != null)
				return false;
		} else if (!f1a4c.equals(other.f1a4c))
			return false;
		if (f1a4q == null) {
			if (other.f1a4q != null)
				return false;
		} else if (!f1a4q.equals(other.f1a4q))
			return false;
		if (f1a4s == null) {
			if (other.f1a4s != null)
				return false;
		} else if (!f1a4s.equals(other.f1a4s))
			return false;
		if (f1b2c == null) {
			if (other.f1b2c != null)
				return false;
		} else if (!f1b2c.equals(other.f1b2c))
			return false;
		if (f1b2q == null) {
			if (other.f1b2q != null)
				return false;
		} else if (!f1b2q.equals(other.f1b2q))
			return false;
		if (f1b2s == null) {
			if (other.f1b2s != null)
				return false;
		} else if (!f1b2s.equals(other.f1b2s))
			return false;
		if (f2a3c == null) {
			if (other.f2a3c != null)
				return false;
		} else if (!f2a3c.equals(other.f2a3c))
			return false;
		if (f2a3q == null) {
			if (other.f2a3q != null)
				return false;
		} else if (!f2a3q.equals(other.f2a3q))
			return false;
		if (f2a3s == null) {
			if (other.f2a3s != null)
				return false;
		} else if (!f2a3s.equals(other.f2a3s))
			return false;
		if (f2b1c == null) {
			if (other.f2b1c != null)
				return false;
		} else if (!f2b1c.equals(other.f2b1c))
			return false;
		if (f2b1q == null) {
			if (other.f2b1q != null)
				return false;
		} else if (!f2b1q.equals(other.f2b1q))
			return false;
		if (f2b1s == null) {
			if (other.f2b1s != null)
				return false;
		} else if (!f2b1s.equals(other.f2b1s))
			return false;
		if (f2d2c == null) {
			if (other.f2d2c != null)
				return false;
		} else if (!f2d2c.equals(other.f2d2c))
			return false;
		if (f2d2q == null) {
			if (other.f2d2q != null)
				return false;
		} else if (!f2d2q.equals(other.f2d2q))
			return false;
		if (f2d2s == null) {
			if (other.f2d2s != null)
				return false;
		} else if (!f2d2s.equals(other.f2d2s))
			return false;
		if (f4a1c == null) {
			if (other.f4a1c != null)
				return false;
		} else if (!f4a1c.equals(other.f4a1c))
			return false;
		if (f4a1q == null) {
			if (other.f4a1q != null)
				return false;
		} else if (!f4a1q.equals(other.f4a1q))
			return false;
		if (f4a1s == null) {
			if (other.f4a1s != null)
				return false;
		} else if (!f4a1s.equals(other.f4a1s))
			return false;
		if (organizationName == null) {
			if (other.organizationName != null)
				return false;
		} else if (!organizationName.equals(other.organizationName))
			return false;
		if (programName == null) {
			if (other.programName != null)
				return false;
		} else if (!programName.equals(other.programName))
			return false;
		if (psEmail == null) {
			if (other.psEmail != null)
				return false;
		} else if (!psEmail.equals(other.psEmail))
			return false;
		if (psName == null) {
			if (other.psName != null)
				return false;
		} else if (!psName.equals(other.psName))
			return false;
		if (readyToTeach == null) {
			if (other.readyToTeach != null)
				return false;
		} else if (!readyToTeach.equals(other.readyToTeach))
			return false;
		if (s1a4c == null) {
			if (other.s1a4c != null)
				return false;
		} else if (!s1a4c.equals(other.s1a4c))
			return false;
		if (s1a4q == null) {
			if (other.s1a4q != null)
				return false;
		} else if (!s1a4q.equals(other.s1a4q))
			return false;
		if (s1a4s == null) {
			if (other.s1a4s != null)
				return false;
		} else if (!s1a4s.equals(other.s1a4s))
			return false;
		if (s1b2c == null) {
			if (other.s1b2c != null)
				return false;
		} else if (!s1b2c.equals(other.s1b2c))
			return false;
		if (s1b2q == null) {
			if (other.s1b2q != null)
				return false;
		} else if (!s1b2q.equals(other.s1b2q))
			return false;
		if (s1b2s == null) {
			if (other.s1b2s != null)
				return false;
		} else if (!s1b2s.equals(other.s1b2s))
			return false;
		if (s2a3c == null) {
			if (other.s2a3c != null)
				return false;
		} else if (!s2a3c.equals(other.s2a3c))
			return false;
		if (s2a3q == null) {
			if (other.s2a3q != null)
				return false;
		} else if (!s2a3q.equals(other.s2a3q))
			return false;
		if (s2a3s == null) {
			if (other.s2a3s != null)
				return false;
		} else if (!s2a3s.equals(other.s2a3s))
			return false;
		if (s2b1c == null) {
			if (other.s2b1c != null)
				return false;
		} else if (!s2b1c.equals(other.s2b1c))
			return false;
		if (s2b1q == null) {
			if (other.s2b1q != null)
				return false;
		} else if (!s2b1q.equals(other.s2b1q))
			return false;
		if (s2b1s == null) {
			if (other.s2b1s != null)
				return false;
		} else if (!s2b1s.equals(other.s2b1s))
			return false;
		if (s2d2c == null) {
			if (other.s2d2c != null)
				return false;
		} else if (!s2d2c.equals(other.s2d2c))
			return false;
		if (s2d2q == null) {
			if (other.s2d2q != null)
				return false;
		} else if (!s2d2q.equals(other.s2d2q))
			return false;
		if (s2d2s == null) {
			if (other.s2d2s != null)
				return false;
		} else if (!s2d2s.equals(other.s2d2s))
			return false;
		if (s4a1c == null) {
			if (other.s4a1c != null)
				return false;
		} else if (!s4a1c.equals(other.s4a1c))
			return false;
		if (s4a1q == null) {
			if (other.s4a1q != null)
				return false;
		} else if (!s4a1q.equals(other.s4a1q))
			return false;
		if (s4a1s == null) {
			if (other.s4a1s != null)
				return false;
		} else if (!s4a1s.equals(other.s4a1s))
			return false;
		if (schoolName == null) {
			if (other.schoolName != null)
				return false;
		} else if (!schoolName.equals(other.schoolName))
			return false;
		if (schoolYear == null) {
			if (other.schoolYear != null)
				return false;
		} else if (!schoolYear.equals(other.schoolYear))
			return false;
		if (spEmail == null) {
			if (other.spEmail != null)
				return false;
		} else if (!spEmail.equals(other.spEmail))
			return false;
		if (spFirstName == null) {
			if (other.spFirstName != null)
				return false;
		} else if (!spFirstName.equals(other.spFirstName))
			return false;
		if (spLastName == null) {
			if (other.spLastName != null)
				return false;
		} else if (!spLastName.equals(other.spLastName))
			return false;
		if (spMEPID == null) {
			if (other.spMEPID != null)
				return false;
		} else if (!spMEPID.equals(other.spMEPID))
			return false;
		if (spName == null) {
			if (other.spName != null)
				return false;
		} else if (!spName.equals(other.spName))
			return false;
		if (stagingId == null) {
			if (other.stagingId != null)
				return false;
		} else if (!stagingId.equals(other.stagingId))
			return false;
		if (statusCode == null) {
			if (other.statusCode != null)
				return false;
		} else if (!statusCode.equals(other.statusCode))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "BatchStaging [stagingId=" + stagingId + ", batch=" + batch + ", organizationName=" + organizationName
				+ ", districtName=" + districtName + ", schoolName=" + schoolName + ", schoolYear=" + schoolYear
				+ ", programName=" + programName + ", candidateMEPId=" + candidateMEPId + ", candidateFirstname="
				+ candidateFirstname + ", candidateLastname=" + candidateLastname + ", candidateEmail=" + candidateEmail
				+ ", psName=" + psName + ", psEmail=" + psEmail + ", spMEPID=" + spMEPID + ", spName=" + spName
				+ ", spFirstName=" + spFirstName + ", spLastName=" + spLastName + ", spEmail=" + spEmail + ", f1a4q="
				+ f1a4q + ", f1a4s=" + f1a4s + ", f1a4c=" + f1a4c + ", f1b2q=" + f1b2q + ", f1b2s=" + f1b2s + ", f1b2c="
				+ f1b2c + ", f2a3q=" + f2a3q + ", f2a3s=" + f2a3s + ", f2a3c=" + f2a3c + ", f2b1q=" + f2b1q + ", f2b1s="
				+ f2b1s + ", f2b1c=" + f2b1c + ", f2d2q=" + f2d2q + ", f2d2s=" + f2d2s + ", f2d2c=" + f2d2c + ", f4a1q="
				+ f4a1q + ", f4a1s=" + f4a1s + ", f4a1c=" + f4a1c + ", s1a4q=" + s1a4q + ", s1a4s=" + s1a4s + ", s1a4c="
				+ s1a4c + ", s1b2q=" + s1b2q + ", s1b2s=" + s1b2s + ", s1b2c=" + s1b2c + ", s2a3q=" + s2a3q + ", s2a3s="
				+ s2a3s + ", s2a3c=" + s2a3c + ", s2b1q=" + s2b1q + ", s2b1s=" + s2b1s + ", s2b1c=" + s2b1c + ", s2d2q="
				+ s2d2q + ", s2d2s=" + s2d2s + ", s2d2c=" + s2d2c + ", s4a1q=" + s4a1q + ", s4a1s=" + s4a1s + ", s4a1c="
				+ s4a1c + ", readyToTeach=" + readyToTeach + ", statusCode=" + statusCode + ", expDate=" + expDate
				+ ", effDate=" + effDate + ", errors=" + errors + "]";
	}



}