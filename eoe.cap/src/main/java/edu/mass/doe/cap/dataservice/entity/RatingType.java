package edu.mass.doe.cap.dataservice.entity;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CP007RRT_RUBRIC_RATING_TYP")
@AttributeOverrides({
@AttributeOverride(name = "createDate", column = @Column(name = "RRT_CREATE_DATE")),
@AttributeOverride(name = "createUser", column = @Column(name = "RRT_CREATED_BY")),
@AttributeOverride(name = "modifyDate", column = @Column(name = "RRT_UPDATE_DATE")),
@AttributeOverride(name = "modifyUser", column = @Column(name = "RRT_UPDATED_BY"))
})
public class RatingType extends BaseJPAEntity  {
	
	
	@Id
	@Column(name="RRT_RUB_RATING_CODE")	
	private String ratingCode;
	
	@Column(name="RRT_RUB_RATING_DESC")	
	private String desc;
	
	@Column(name="RRT_RUB_RATING_SHORT_DESC")	
	private String shortDesc;
	
	@Column(name="RRT_RUB_RATING_LONG_DESC")	
	private String longDesc;
	
	
	@Column(name="RRT_EXP_DATE")	
	private Date expDate;	
	
	@Column(name="RRT_EFF_DATE")	
	private Date effDate;

	public String getRatingCode() {
		return ratingCode;
	}

	public void setRatingCode(String ratingCode) {
		this.ratingCode = ratingCode;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getShortDesc() {
		return shortDesc;
	}

	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
	}

	public String getLongDesc() {
		return longDesc;
	}

	public void setLongDesc(String longDesc) {
		this.longDesc = longDesc;
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
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RatingType [ratingCode=" + ratingCode + ", desc=" + desc + ", shortDesc=" + shortDesc + ", longDesc="
				+ longDesc + ", expDate=" + expDate + ", effDate=" + effDate + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((desc == null) ? 0 : desc.hashCode());
		result = prime * result + ((effDate == null) ? 0 : effDate.hashCode());
		result = prime * result + ((expDate == null) ? 0 : expDate.hashCode());
		result = prime * result + ((longDesc == null) ? 0 : longDesc.hashCode());
		result = prime * result + ((ratingCode == null) ? 0 : ratingCode.hashCode());
		result = prime * result + ((shortDesc == null) ? 0 : shortDesc.hashCode());
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
		if (!(obj instanceof RatingType))
			return false;
		RatingType other = (RatingType) obj;
		if (desc == null) {
			if (other.desc != null)
				return false;
		} else if (!desc.equals(other.desc))
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
		if (longDesc == null) {
			if (other.longDesc != null)
				return false;
		} else if (!longDesc.equals(other.longDesc))
			return false;
		if (ratingCode == null) {
			if (other.ratingCode != null)
				return false;
		} else if (!ratingCode.equals(other.ratingCode))
			return false;
		if (shortDesc == null) {
			if (other.shortDesc != null)
				return false;
		} else if (!shortDesc.equals(other.shortDesc))
			return false;
		return true;
	}
	
	

}
