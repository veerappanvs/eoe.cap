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
@Table(name = "CP080CGL_CYCLE_GRADE_LINK")
@AttributeOverrides({
@AttributeOverride(name = "createDate", column = @Column(name = "CPC_CREATE_DATE")),
@AttributeOverride(name = "createUser", column = @Column(name = "CPC_CREATED_BY")),
@AttributeOverride(name = "modifyDate", column = @Column(name = "CPC_UPDATE_DATE")),
@AttributeOverride(name = "modifyUser", column = @Column(name = "CPC_UPDATED_BY"))
})
public class CycleGradeLevel {
	
	@Id
	@Column(name="CGL_CYCLE_GRADE_LINK_ID")
	@SequenceGenerator(name = "CGL_PK_GENERATOR", sequenceName = "CP080CGL_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CGL_PK_GENERATOR")
	private Long gradeId;
	
	@Column(name="CGL_CYCLE_ID")
	private Long cycleId;
	
	@Column(name="CGL_GRADE_LEVEL_CODE")
	private String levelCode;
	
	@Column(name="CGL_EXP_DATE")
	private Date expDate;
	
	@Column(name="CGL_EFF_DATE")
	private Date effDate;

	public Long getGradeId() {
		return gradeId;
	}

	public void setGradeId(Long gradeId) {
		this.gradeId = gradeId;
	}

	public Long getCycleId() {
		return cycleId;
	}

	public void setCycleId(Long cycleId) {
		this.cycleId = cycleId;
	}

	public String getLevelCode() {
		return levelCode;
	}

	public void setLevelCode(String levelCode) {
		this.levelCode = levelCode;
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
		int result = 1;
		result = prime * result + ((cycleId == null) ? 0 : cycleId.hashCode());
		result = prime * result + ((effDate == null) ? 0 : effDate.hashCode());
		result = prime * result + ((expDate == null) ? 0 : expDate.hashCode());
		result = prime * result + ((gradeId == null) ? 0 : gradeId.hashCode());
		result = prime * result + ((levelCode == null) ? 0 : levelCode.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof CycleGradeLevel))
			return false;
		CycleGradeLevel other = (CycleGradeLevel) obj;
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
		if (expDate == null) {
			if (other.expDate != null)
				return false;
		} else if (!expDate.equals(other.expDate))
			return false;
		if (gradeId == null) {
			if (other.gradeId != null)
				return false;
		} else if (!gradeId.equals(other.gradeId))
			return false;
		if (levelCode == null) {
			if (other.levelCode != null)
				return false;
		} else if (!levelCode.equals(other.levelCode))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CycleGradeLevel [gradeId=" + gradeId + ", cycleId=" + cycleId + ", levelCode=" + levelCode
				+ ", expDate=" + expDate + ", effDate=" + effDate + "]";
	}	
	
	
	
}
