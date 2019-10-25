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
@Table(name = "CP055RUM_RUBRIC_MAP")
@AttributeOverrides({
@AttributeOverride(name = "createDate", column = @Column(name = "RUM_CREATE_DATE")),
@AttributeOverride(name = "createUser", column = @Column(name = "RUM_CREATED_BY")),
@AttributeOverride(name = "modifyDate", column = @Column(name = "RUM_UPDATE_DATE")),
@AttributeOverride(name = "modifyUser", column = @Column(name = "RUM_UPDATED_BY"))
})
public class RubricMap  extends BaseJPAEntity  {
	
	@Id
	@Column(name="RUM_RUBRIC_MAP_ID")
	@SequenceGenerator(name = "RUM_PK_GENERATOR", sequenceName = "CP055RUM_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RUM_PK_GENERATOR")
	private Long mapId;
	

	@Column(name="RUM_RUBRIC_DIMENSION_CODE")
	private String dimensionCode;

	@Column(name="RUM_RATING_ELEMENT_LINK_ID")
	private Long ratingElementLinkId;
	
	@Column(name="RUM_EXP_DATE")
	private Date expDate;
	
	@Column(name="RUM_EFF_DATE")
	private Date effdate;
		
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RUM_ASSESSMENT_ID", insertable = true, updatable = false,nullable=false)
	private Assessment assessment;	
	

	public Date getExpDate() {
		return expDate;
	}


	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}


	public Date getEffdate() {
		return effdate;
	}


	public void setEffdate(Date effdate) {
		this.effdate = effdate;
	}




	public Assessment getAssessment() {
		return assessment;
	}


	public void setAssessment(Assessment assessment) {
		this.assessment = assessment;
	}


	public Long getMapId() {
		return mapId;
	}


	public void setMapId(Long mapId) {
		this.mapId = mapId;
	}


	public String getDimensionCode() {
		return dimensionCode;
	}


	public void setDimensionCode(String dimensionCode) {
		this.dimensionCode = dimensionCode;
	}


	public Long getRatingElementLinkId() {
		return ratingElementLinkId;
	}


	public void setRatingElementLinkId(Long ratingElementLinkId) {
		this.ratingElementLinkId = ratingElementLinkId;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((assessment == null) ? 0 : assessment.hashCode());
		result = prime * result + ((dimensionCode == null) ? 0 : dimensionCode.hashCode());
		result = prime * result + ((effdate == null) ? 0 : effdate.hashCode());
		result = prime * result + ((expDate == null) ? 0 : expDate.hashCode());
		result = prime * result + ((mapId == null) ? 0 : mapId.hashCode());
		result = prime * result + ((ratingElementLinkId == null) ? 0 : ratingElementLinkId.hashCode());
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
		if (!(obj instanceof RubricMap))
			return false;
		RubricMap other = (RubricMap) obj;
		if (assessment == null) {
			if (other.assessment != null)
				return false;
		} else if (!assessment.equals(other.assessment))
			return false;
		if (dimensionCode == null) {
			if (other.dimensionCode != null)
				return false;
		} else if (!dimensionCode.equals(other.dimensionCode))
			return false;
		if (effdate == null) {
			if (other.effdate != null)
				return false;
		} else if (!effdate.equals(other.effdate))
			return false;
		if (expDate == null) {
			if (other.expDate != null)
				return false;
		} else if (!expDate.equals(other.expDate))
			return false;
		if (mapId == null) {
			if (other.mapId != null)
				return false;
		} else if (!mapId.equals(other.mapId))
			return false;
		if (ratingElementLinkId == null) {
			if (other.ratingElementLinkId != null)
				return false;
		} else if (!ratingElementLinkId.equals(other.ratingElementLinkId))
			return false;
		return true;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RubricMap [mapId=" + mapId + ", dimensionCode=" + dimensionCode + ", ratingElementLinkId="
				+ ratingElementLinkId + ", expDate=" + expDate + ", effdate=" + effdate + ", assessment=" + assessment
				+ "]";
	}


	

}




