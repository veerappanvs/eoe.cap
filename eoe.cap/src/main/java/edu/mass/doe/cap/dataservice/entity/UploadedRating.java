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
@Table(name = "CP072FUR_FILE_UPLOAD_RATING")
@AttributeOverrides({
@AttributeOverride(name = "createDate", column = @Column(name = "FUR_CREATE_DATE")),
@AttributeOverride(name = "createUser", column = @Column(name = "FUR_CREATED_BY")),
@AttributeOverride(name = "modifyDate", column = @Column(name = "FUR_UPDATE_DATE")),
@AttributeOverride(name = "modifyUser", column = @Column(name = "FUR_UPDATED_BY"))
})
public class UploadedRating extends BaseJPAEntity {
	
	@Id
	@Column(name="FUR_UPLD_RATING_ID")
	@SequenceGenerator(name = "FUR_PK_GENERATOR", sequenceName = "CP072FUR_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FUR_PK_GENERATOR")
	private Long uploadRatingId;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FUR_FUP_ID", insertable = true, updatable = false,nullable=false)
	private UploadedData uploadedData;
	
	@Column(name="FUR_REL_ID")
	private Long linkId;
	
	
	@Column(name="FUR_RDT_CODE")
	private String dimensionCode;
	
	@Column(name="FUR_EXP_DATE")
	private Date expDate;
	
	@Column(name="FUR_EFF_DATE")
	private Date effDate;

	@Column(name="FUR_ASMT_TYPE_CODE")
	private String  asmtTypeCode;

	public Long getUploadRatingId() {
		return uploadRatingId;
	}

	public void setUploadRatingId(Long uploadRatingId) {
		this.uploadRatingId = uploadRatingId;
	}

	public UploadedData getUploadedData() {
		return uploadedData;
	}

	public void setUploadedData(UploadedData uploadedData) {
		this.uploadedData = uploadedData;
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

	public String getAsmtTypeCode() {
		return asmtTypeCode;
	}

	public void setAsmtTypeCode(String asmtTypeCode) {
		this.asmtTypeCode = asmtTypeCode;
	}

	public Long getLinkId() {
		return linkId;
	}

	public void setLinkId(Long linkId) {
		this.linkId = linkId;
	}

	public String getDimensionCode() {
		return dimensionCode;
	}

	public void setDimensionCode(String dimensionCode) {
		this.dimensionCode = dimensionCode;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((asmtTypeCode == null) ? 0 : asmtTypeCode.hashCode());
		result = prime * result + ((dimensionCode == null) ? 0 : dimensionCode.hashCode());
		result = prime * result + ((effDate == null) ? 0 : effDate.hashCode());
		result = prime * result + ((expDate == null) ? 0 : expDate.hashCode());
		result = prime * result + ((linkId == null) ? 0 : linkId.hashCode());
		result = prime * result + ((uploadRatingId == null) ? 0 : uploadRatingId.hashCode());
		result = prime * result + ((uploadedData == null) ? 0 : uploadedData.hashCode());
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
		if (!(obj instanceof UploadedRating))
			return false;
		UploadedRating other = (UploadedRating) obj;
		if (asmtTypeCode == null) {
			if (other.asmtTypeCode != null)
				return false;
		} else if (!asmtTypeCode.equals(other.asmtTypeCode))
			return false;
		if (dimensionCode == null) {
			if (other.dimensionCode != null)
				return false;
		} else if (!dimensionCode.equals(other.dimensionCode))
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
		if (linkId == null) {
			if (other.linkId != null)
				return false;
		} else if (!linkId.equals(other.linkId))
			return false;
		if (uploadRatingId == null) {
			if (other.uploadRatingId != null)
				return false;
		} else if (!uploadRatingId.equals(other.uploadRatingId))
			return false;
		if (uploadedData == null) {
			if (other.uploadedData != null)
				return false;
		} else if (!uploadedData.equals(other.uploadedData))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UploadedRating [uploadRatingId=" + uploadRatingId + ", uploadedData=" + uploadedData + ", linkId="
				+ linkId + ", dimensionCode=" + dimensionCode + ", expDate=" + expDate + ", effDate=" + effDate
				+ ", asmtTypeCode=" + asmtTypeCode + "]";
	}
	

}
