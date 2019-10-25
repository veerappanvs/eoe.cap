package edu.mass.doe.cap.dataservice.entity;

import java.util.Date;
import java.util.List;
import java.util.Set;

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
@Table(name = "CP050CPC_CAP_CYCLE")
@AttributeOverrides({
@AttributeOverride(name = "createDate", column = @Column(name = "CPC_CREATE_DATE")),
@AttributeOverride(name = "createUser", column = @Column(name = "CPC_CREATED_BY")),
@AttributeOverride(name = "modifyDate", column = @Column(name = "CPC_UPDATE_DATE")),
@AttributeOverride(name = "modifyUser", column = @Column(name = "CPC_UPDATED_BY"))
})
public class CapCycle extends BaseJPAEntity {
	
	
	@Id
	@Column(name="CPC_CYCLE_ID")
	@SequenceGenerator(name = "CYCLE_PK_GENERATOR", sequenceName = "CP050CPC_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CYCLE_PK_GENERATOR")
	private Long cycleId;
	
	@Column(name="CPC_CYCLE_DATAENTRY_TYPE")
	private String cycleDataEntryType;
	
	@Column(name="CPC_SCHOOL_YEAR" )
	private Integer schoolYear;
	
	@Column(name="CPC_PCOM_ID" )
	private Long pcomId;
	
	@Column(name="CPC_WAIVED" )
	private Character waived;
	
	@Column(name="CPC_CREDIT_HOURS" )
	private Long creditHours;
	
	@Column(name="CPC_TOTAL_HOURS" )
	private Long totalHours;
	
	@Column(name="CPC_START_DATE" )
	private Date startDate;
	
	@Column(name="CPC_END_DATE" )
	private Date endDate;
	
	@Column(name="CPC_EFF_DATE" )
	private Date effDate;
	
	@Column(name="CPC_READY_TO_TEACH" )
	private Character readyToTeach;
	
	@Column(name="CPC_CST_STATUS_CODE" )
	private String cstStatusCode;
	
	
	@Column(name="CPC_CPT_PRACTICUM_TYPE_CODE")
	private String cptPracticumTypeCode;
	
	@Column(name="CPC_PRACTICUM_COURSE_NUMBER")
	private String practicumCourseNumber;
	
	@Column(name="CPC_PRACTICUM_COURSE_TITLE")
	private String practicumCourseTitle;
	
	@Column(name="CPC_PRACTICUM_GRADE_LEVEL")
	private String practicumGradeLevel;
		
		
	
	@Column(name="CPC_PRACTICUM_HOURS")
	private Long practicumHours;
	
	
	@Column(name="CPC_FULLRESP_HOURS")
	private Long fullRespHours;
	
	@Column(name="CPC_REOPEN_DATE")
	private Date reopenDate;
	
	@Column(name="CPC_AUTOCLOSE_IND")
	private String autoCloseIndictor;
	
	@Column(name="CPC_AUTOCLOSE_FIRST_EMAILDATE")
	private Date autoCloseFirstEmailDate;
	
	@Column(name="CPC_AUTOCLOSE_EMAIL_DATE")
	private Date autoCloseEmailDate;
	
	
	
	@OneToMany(mappedBy = "capCycle", cascade = CascadeType.PERSIST,fetch=FetchType.LAZY)
	@Where(clause = "CPA_EXP_DATE is null")
	private List<CapAssignment> assignments;
	
	
	@OneToMany(mappedBy = "capCycle", cascade = CascadeType.PERSIST,fetch=FetchType.LAZY)
	@Where(clause = "SOA_EXP_DATE is null")
	private List<OutOfStateSpAssignment> OutOfStateAssignments;

	
	@OneToMany(mappedBy = "capCycle", cascade = CascadeType.PERSIST,fetch=FetchType.LAZY)
	private List<ThreewayMeeting> threewayMeetings;
	
	
	@OneToMany(mappedBy = "capCycle", cascade = CascadeType.PERSIST,fetch=FetchType.LAZY)
	@Where(clause = "AST_EXP_DATE is null")
	private List<Assessment> assessments;
	
	@OneToMany(mappedBy = "capCycle", cascade = CascadeType.PERSIST,fetch=FetchType.LAZY)
	@Where(clause = "GOP_EXP_DATE is null")
	private List<GoalPlan> goalPlans;
	
	@OneToMany(mappedBy = "capCycle", cascade = CascadeType.PERSIST,fetch=FetchType.LAZY)
	private List<EvidenceFile > files;

	
	public Long getCycleId() {
		return cycleId;
	}

	public void setCycleId(Long cycleId) {
		this.cycleId = cycleId;
	}

	public String getCycleDataEntryType() {
		return cycleDataEntryType;
	}

	public void setCycleDataEntryType(String cycleDataEntryType) {
		this.cycleDataEntryType = cycleDataEntryType;
	}

	public Integer getSchoolYear() {
		return schoolYear;
	}

	public void setSchoolYear(Integer schoolYear) {
		this.schoolYear = schoolYear;
	}

	public Long getPcomId() {
		return pcomId;
	}

	public void setPcomId(Long pcomId) {
		this.pcomId = pcomId;
	}

	public Character getWaived() {
		return waived;
	}

	public void setWaived(Character waived) {
		this.waived = waived;
	}

	public Long getCreditHours() {
		return creditHours;
	}

	public void setCreditHours(Long creditHours) {
		this.creditHours = creditHours;
	}

	public Long getTotalHours() {
		return totalHours;
	}

	public void setTotalHours(Long totalHours) {
		this.totalHours = totalHours;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getEffDate() {
		return effDate;
	}

	public void setEffDate(Date effDate) {
		this.effDate = effDate;
	}

	public List<CapAssignment> getAssignments() {
		return assignments;
	}
	
	
	public void setAssignments(List<CapAssignment> assignments) {
		this.assignments = assignments;
	}

	public Character getReadyToTeach() {
		return readyToTeach;
	}

	public void setReadyToTeach(Character readyToTeach) {
		this.readyToTeach = readyToTeach;
	}

	public List<OutOfStateSpAssignment> getOutOfStateAssignments() {
		return OutOfStateAssignments;
	}

	public void setOutOfStateAssignments(List<OutOfStateSpAssignment> outOfStateAssignments) {
		OutOfStateAssignments = outOfStateAssignments;
	}

	public String getCstStatusCode() {
		return cstStatusCode;
	}

	public void setCstStatusCode(String cstStatusCode) {
		this.cstStatusCode = cstStatusCode;
	}

	public String getCptPracticumTypeCode() {
		return cptPracticumTypeCode;
	}

	public void setCptPracticumTypeCode(String cptPracticumTypeCode) {
		this.cptPracticumTypeCode = cptPracticumTypeCode;
	}

	public String getPracticumCourseNumber() {
		return practicumCourseNumber;
	}

	public void setPracticumCourseNumber(String practicumCourseNumber) {
		this.practicumCourseNumber = practicumCourseNumber;
	}

	public String getPracticumCourseTitle() {
		return practicumCourseTitle;
	}

	public void setPracticumCourseTitle(String practicumCourseTitle) {
		this.practicumCourseTitle = practicumCourseTitle;
	}

	public String getPracticumGradeLevel() {
		return practicumGradeLevel;
	}

	public void setPracticumGradeLevel(String practicumGradeLevel) {
		this.practicumGradeLevel = practicumGradeLevel;
	}

	

	public Long getPracticumHours() {
		return practicumHours;
	}

	public void setPracticumHours(Long practicumHours) {
		this.practicumHours = practicumHours;
	}

	public Long getFullRespHours() {
		return fullRespHours;
	}

	public void setFullRespHours(Long fullRespHours) {
		this.fullRespHours = fullRespHours;
	}

	public Date getReopenDate() {
		return reopenDate;
	}

	public void setReopenDate(Date reopenDate) {
		this.reopenDate = reopenDate;
	}

	public List<ThreewayMeeting> getThreewayMeetings() {
		return threewayMeetings;
	}

	public void setThreewayMeetings(List<ThreewayMeeting> threewayMeetings) {
		this.threewayMeetings = threewayMeetings;
	}

	public List<Assessment> getAssessments() {
		return assessments;
	}

	public void setAssessments(List<Assessment> assessments) {
		this.assessments = assessments;
	}

	

	public List<EvidenceFile> getFiles() {
		return files;
	}

	public void setFiles(List<EvidenceFile> files) {
		this.files = files;
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

	public List<GoalPlan> getGoalPlans() {
		return goalPlans;
	}

	public void setGoalPlans(List<GoalPlan> goalPlans) {
		this.goalPlans = goalPlans;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((OutOfStateAssignments == null) ? 0 : OutOfStateAssignments.hashCode());
		result = prime * result + ((assessments == null) ? 0 : assessments.hashCode());
		result = prime * result + ((assignments == null) ? 0 : assignments.hashCode());
		result = prime * result + ((autoCloseEmailDate == null) ? 0 : autoCloseEmailDate.hashCode());
		result = prime * result + ((autoCloseFirstEmailDate == null) ? 0 : autoCloseFirstEmailDate.hashCode());
		result = prime * result + ((autoCloseIndictor == null) ? 0 : autoCloseIndictor.hashCode());
		result = prime * result + ((cptPracticumTypeCode == null) ? 0 : cptPracticumTypeCode.hashCode());
		result = prime * result + ((creditHours == null) ? 0 : creditHours.hashCode());
		result = prime * result + ((cstStatusCode == null) ? 0 : cstStatusCode.hashCode());
		result = prime * result + ((cycleDataEntryType == null) ? 0 : cycleDataEntryType.hashCode());
		result = prime * result + ((cycleId == null) ? 0 : cycleId.hashCode());
		result = prime * result + ((effDate == null) ? 0 : effDate.hashCode());
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result + ((files == null) ? 0 : files.hashCode());
		result = prime * result + ((fullRespHours == null) ? 0 : fullRespHours.hashCode());
		result = prime * result + ((goalPlans == null) ? 0 : goalPlans.hashCode());
		result = prime * result + ((pcomId == null) ? 0 : pcomId.hashCode());
		result = prime * result + ((practicumCourseNumber == null) ? 0 : practicumCourseNumber.hashCode());
		result = prime * result + ((practicumCourseTitle == null) ? 0 : practicumCourseTitle.hashCode());
		result = prime * result + ((practicumGradeLevel == null) ? 0 : practicumGradeLevel.hashCode());
		result = prime * result + ((practicumHours == null) ? 0 : practicumHours.hashCode());
		result = prime * result + ((readyToTeach == null) ? 0 : readyToTeach.hashCode());
		result = prime * result + ((reopenDate == null) ? 0 : reopenDate.hashCode());
		result = prime * result + ((schoolYear == null) ? 0 : schoolYear.hashCode());
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
		result = prime * result + ((threewayMeetings == null) ? 0 : threewayMeetings.hashCode());
		result = prime * result + ((totalHours == null) ? 0 : totalHours.hashCode());
		result = prime * result + ((waived == null) ? 0 : waived.hashCode());
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
		if (!(obj instanceof CapCycle))
			return false;
		CapCycle other = (CapCycle) obj;
		if (OutOfStateAssignments == null) {
			if (other.OutOfStateAssignments != null)
				return false;
		} else if (!OutOfStateAssignments.equals(other.OutOfStateAssignments))
			return false;
		if (assessments == null) {
			if (other.assessments != null)
				return false;
		} else if (!assessments.equals(other.assessments))
			return false;
		if (assignments == null) {
			if (other.assignments != null)
				return false;
		} else if (!assignments.equals(other.assignments))
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
		if (cptPracticumTypeCode == null) {
			if (other.cptPracticumTypeCode != null)
				return false;
		} else if (!cptPracticumTypeCode.equals(other.cptPracticumTypeCode))
			return false;
		if (creditHours == null) {
			if (other.creditHours != null)
				return false;
		} else if (!creditHours.equals(other.creditHours))
			return false;
		if (cstStatusCode == null) {
			if (other.cstStatusCode != null)
				return false;
		} else if (!cstStatusCode.equals(other.cstStatusCode))
			return false;
		if (cycleDataEntryType == null) {
			if (other.cycleDataEntryType != null)
				return false;
		} else if (!cycleDataEntryType.equals(other.cycleDataEntryType))
			return false;
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
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (files == null) {
			if (other.files != null)
				return false;
		} else if (!files.equals(other.files))
			return false;
		if (fullRespHours == null) {
			if (other.fullRespHours != null)
				return false;
		} else if (!fullRespHours.equals(other.fullRespHours))
			return false;
		if (goalPlans == null) {
			if (other.goalPlans != null)
				return false;
		} else if (!goalPlans.equals(other.goalPlans))
			return false;
		if (pcomId == null) {
			if (other.pcomId != null)
				return false;
		} else if (!pcomId.equals(other.pcomId))
			return false;
		if (practicumCourseNumber == null) {
			if (other.practicumCourseNumber != null)
				return false;
		} else if (!practicumCourseNumber.equals(other.practicumCourseNumber))
			return false;
		if (practicumCourseTitle == null) {
			if (other.practicumCourseTitle != null)
				return false;
		} else if (!practicumCourseTitle.equals(other.practicumCourseTitle))
			return false;
		if (practicumGradeLevel == null) {
			if (other.practicumGradeLevel != null)
				return false;
		} else if (!practicumGradeLevel.equals(other.practicumGradeLevel))
			return false;
		if (practicumHours == null) {
			if (other.practicumHours != null)
				return false;
		} else if (!practicumHours.equals(other.practicumHours))
			return false;
		if (readyToTeach == null) {
			if (other.readyToTeach != null)
				return false;
		} else if (!readyToTeach.equals(other.readyToTeach))
			return false;
		if (reopenDate == null) {
			if (other.reopenDate != null)
				return false;
		} else if (!reopenDate.equals(other.reopenDate))
			return false;
		if (schoolYear == null) {
			if (other.schoolYear != null)
				return false;
		} else if (!schoolYear.equals(other.schoolYear))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		if (threewayMeetings == null) {
			if (other.threewayMeetings != null)
				return false;
		} else if (!threewayMeetings.equals(other.threewayMeetings))
			return false;
		if (totalHours == null) {
			if (other.totalHours != null)
				return false;
		} else if (!totalHours.equals(other.totalHours))
			return false;
		if (waived == null) {
			if (other.waived != null)
				return false;
		} else if (!waived.equals(other.waived))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CapCycle [cycleId=" + cycleId + ", cycleDataEntryType=" + cycleDataEntryType + ", schoolYear="
				+ schoolYear + ", pcomId=" + pcomId + ", waived=" + waived + ", creditHours=" + creditHours
				+ ", totalHours=" + totalHours + ", startDate=" + startDate + ", endDate=" + endDate + ", effDate="
				+ effDate + ", readyToTeach=" + readyToTeach + ", cstStatusCode=" + cstStatusCode
				+ ", cptPracticumTypeCode=" + cptPracticumTypeCode + ", practicumCourseNumber=" + practicumCourseNumber
				+ ", practicumCourseTitle=" + practicumCourseTitle + ", practicumGradeLevel=" + practicumGradeLevel
				+ ", practicumHours=" + practicumHours + ", fullRespHours=" + fullRespHours + ", reopenDate="
				+ reopenDate + ", autoCloseIndictor=" + autoCloseIndictor + ", autoCloseFirstEmailDate="
				+ autoCloseFirstEmailDate + ", autoCloseEmailDate=" + autoCloseEmailDate + ", assignments="
				+ assignments + ", OutOfStateAssignments=" + OutOfStateAssignments + ", threewayMeetings="
				+ threewayMeetings + ", assessments=" + assessments + ", goalPlans=" + goalPlans + ", files=" + files
				+ "]";
	}

	

}
