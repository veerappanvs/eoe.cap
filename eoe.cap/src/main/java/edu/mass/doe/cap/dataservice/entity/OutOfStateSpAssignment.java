package edu.mass.doe.cap.dataservice.entity;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "CP052SOA_SP_OUTOFSTATE_ASGNMT")
@AttributeOverrides({
@AttributeOverride(name = "createDate", column = @Column(name = "SOA_CREATE_DATE")),
@AttributeOverride(name = "createUser", column = @Column(name = "SOA_CREATED_BY")),
@AttributeOverride(name = "modifyDate", column = @Column(name = "SOA_UPDATE_DATE")),
@AttributeOverride(name = "modifyUser", column = @Column(name = "SOA_UPDATED_BY"))
})
public class OutOfStateSpAssignment extends BaseJPAEntity {

	
	@Id
	@Column(name="SOA_PER_ID")
	@SequenceGenerator(name = "CYCLE_PK_GENERATOR", sequenceName = "CP051CPA_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CYCLE_PK_GENERATOR")
	private Long personId;
	
	@Column(name="SOA_PER_FIRST_NAME" )
	private String firstName;
	
	@Column(name="SOA_PER_LAST_NAME" )
	private String lastName;
	
	@Column(name="SOA_PRACTICUM_STATE" )
	private String state;
	
	@Column(name="SOA_PRACTICUM_DISTRICT" )
	private String district;
	
	@Column(name="SOA_PRACTICUM_SCHOOL_NAME" )
	private String school;
	
	@Column(name="SOA_PRACTICUM_SCHOOL" )
	private String districtType;
	
	@Column(name="SOA_EFF_DATE" )
	private Date effDate;
	
	@Column(name="SOA_EXP_DATE" )
	private Date expDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SOA_CYCLE_ID", insertable = true, updatable = false,nullable=false)
	private CapCycle capCycle;


	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public Date getEffDate() {
		return effDate;
	}

	public void setEffDate(Date effDate) {
		this.effDate = effDate;
	}

	public Date getExpDate() {
		return expDate;
	}

	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}

	public CapCycle getCapCycle() {
		return capCycle;
	}

	public void setCapCycle(CapCycle capCycle) {
		this.capCycle = capCycle;
	}

	public String getDistrictType() {
		return districtType;
	}

	public void setDistrictType(String districtType) {
		this.districtType = districtType;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((capCycle == null) ? 0 : capCycle.hashCode());
		result = prime * result + ((district == null) ? 0 : district.hashCode());
		result = prime * result + ((districtType == null) ? 0 : districtType.hashCode());
		result = prime * result + ((effDate == null) ? 0 : effDate.hashCode());
		result = prime * result + ((expDate == null) ? 0 : expDate.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((personId == null) ? 0 : personId.hashCode());
		result = prime * result + ((school == null) ? 0 : school.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
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
		if (!(obj instanceof OutOfStateSpAssignment))
			return false;
		OutOfStateSpAssignment other = (OutOfStateSpAssignment) obj;
		if (capCycle == null) {
			if (other.capCycle != null)
				return false;
		} else if (!capCycle.equals(other.capCycle))
			return false;
		if (district == null) {
			if (other.district != null)
				return false;
		} else if (!district.equals(other.district))
			return false;
		if (districtType == null) {
			if (other.districtType != null)
				return false;
		} else if (!districtType.equals(other.districtType))
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
		if (personId == null) {
			if (other.personId != null)
				return false;
		} else if (!personId.equals(other.personId))
			return false;
		if (school == null) {
			if (other.school != null)
				return false;
		} else if (!school.equals(other.school))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "OutOfStateSpAssignment [personId=" + personId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", state=" + state + ", district=" + district + ", school=" + school + ", districtType="
				+ districtType + ", effDate=" + effDate + ", expDate=" + expDate + ", capCycle=" + capCycle + "]";
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
