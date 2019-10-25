package edu.mass.doe.cap.dataservice.entity;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "CP003OGT_OBS_GROUP_TYP")
@AttributeOverrides({
@AttributeOverride(name = "createDate", column = @Column(name = "OGT_CREATE_DATE")),
@AttributeOverride(name = "createUser", column = @Column(name = "OGT_CREATED_BY")),
@AttributeOverride(name = "modifyDate", column = @Column(name = "OGT_UPDATE_DATE")),
@AttributeOverride(name = "modifyUser", column = @Column(name = "OGT_UPDATED_BY"))
})
public class ObservationGroupType extends BaseJPAEntity {
	@Id
	@Column(name="OGT_OBS_GROUP_CODE")	
	private String groupCode;
	
	@Column(name="OGT_OBS_GROUP_DESC")	
	private String groupDesc;
	
	@Column(name="OGT_EXP_DATE")	
	private Date expDate;
	
	@Column(name="OGT_EFF_DATE")	
	private Date effDate;

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public String getGroupDesc() {
		return groupDesc;
	}

	public void setGroupDesc(String groupDesc) {
		this.groupDesc = groupDesc;
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
		int result = super.hashCode();
		result = prime * result + ((effDate == null) ? 0 : effDate.hashCode());
		result = prime * result + ((expDate == null) ? 0 : expDate.hashCode());
		result = prime * result + ((groupCode == null) ? 0 : groupCode.hashCode());
		result = prime * result + ((groupDesc == null) ? 0 : groupDesc.hashCode());
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
		if (!(obj instanceof ObservationGroupType))
			return false;
		ObservationGroupType other = (ObservationGroupType) obj;
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
		if (groupCode == null) {
			if (other.groupCode != null)
				return false;
		} else if (!groupCode.equals(other.groupCode))
			return false;
		if (groupDesc == null) {
			if (other.groupDesc != null)
				return false;
		} else if (!groupDesc.equals(other.groupDesc))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ObservationGroupType [groupCode=" + groupCode + ", groupDesc=" + groupDesc + ", expDate=" + expDate
				+ ", effDate=" + effDate + "]";
	}
	

}
