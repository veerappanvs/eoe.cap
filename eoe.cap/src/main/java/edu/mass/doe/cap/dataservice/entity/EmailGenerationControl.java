package edu.mass.doe.cap.dataservice.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CP076EGN_EMAIL_GENERATION")
@AttributeOverrides({
@AttributeOverride(name = "createDate", column = @Column(name = "EGN_CREATE_DATE")),
@AttributeOverride(name = "createUser", column = @Column(name = "EGN_CREATED_BY")),
@AttributeOverride(name = "modifyDate", column = @Column(name = "EGN_UPDATE_DATE")),
@AttributeOverride(name = "modifyUser", column = @Column(name = "EGN_UPDATED_BY"))
})
public class EmailGenerationControl extends BaseJPAEntity {
	
	
	@Id
	@Column(name="EGN_ID")
	private Long egnId;
	
	@Column(name="EGN_EMAIL_TRIGGER_QUERY")
	private String slectionQuery;
	
	@Column(name="EGN_RECIPIENT_QUERY" )
	private String emailQuery;
	
	@Column(name="EGN_RECIPIENT_COLPARAMS" )
	private String params;
	
	@Column(name="EGN_UPDATE_QUERY" )
	private String updateQuery;
	
	@Column(name="EGN_UPDATE_COLPARAMS" )
	private String updateParams;
	
	@Column(name="EGN_CET_TYPE_CODE" )
	private String typeCode;

	public Long getEgnId() {
		return egnId;
	}

	public void setEgnId(Long egnId) {
		this.egnId = egnId;
	}

	public String getSlectionQuery() {
		return slectionQuery;
	}

	public void setSlectionQuery(String slectionQuery) {
		this.slectionQuery = slectionQuery;
	}

	public String getEmailQuery() {
		return emailQuery;
	}

	public void setEmailQuery(String emailQuery) {
		this.emailQuery = emailQuery;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public String getUpdateQuery() {
		return updateQuery;
	}

	public void setUpdateQuery(String updateQuery) {
		this.updateQuery = updateQuery;
	}

	public String getUpdateParams() {
		return updateParams;
	}

	public void setUpdateParams(String updateParams) {
		this.updateParams = updateParams;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((egnId == null) ? 0 : egnId.hashCode());
		result = prime * result + ((emailQuery == null) ? 0 : emailQuery.hashCode());
		result = prime * result + ((params == null) ? 0 : params.hashCode());
		result = prime * result + ((slectionQuery == null) ? 0 : slectionQuery.hashCode());
		result = prime * result + ((typeCode == null) ? 0 : typeCode.hashCode());
		result = prime * result + ((updateParams == null) ? 0 : updateParams.hashCode());
		result = prime * result + ((updateQuery == null) ? 0 : updateQuery.hashCode());
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
		if (!(obj instanceof EmailGenerationControl))
			return false;
		EmailGenerationControl other = (EmailGenerationControl) obj;
		if (egnId == null) {
			if (other.egnId != null)
				return false;
		} else if (!egnId.equals(other.egnId))
			return false;
		if (emailQuery == null) {
			if (other.emailQuery != null)
				return false;
		} else if (!emailQuery.equals(other.emailQuery))
			return false;
		if (params == null) {
			if (other.params != null)
				return false;
		} else if (!params.equals(other.params))
			return false;
		if (slectionQuery == null) {
			if (other.slectionQuery != null)
				return false;
		} else if (!slectionQuery.equals(other.slectionQuery))
			return false;
		if (typeCode == null) {
			if (other.typeCode != null)
				return false;
		} else if (!typeCode.equals(other.typeCode))
			return false;
		if (updateParams == null) {
			if (other.updateParams != null)
				return false;
		} else if (!updateParams.equals(other.updateParams))
			return false;
		if (updateQuery == null) {
			if (other.updateQuery != null)
				return false;
		} else if (!updateQuery.equals(other.updateQuery))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "EmailGenerationControl [egnId=" + egnId + ", slectionQuery=" + slectionQuery + ", emailQuery="
				+ emailQuery + ", params=" + params + ", updateQuery=" + updateQuery + ", updateParams=" + updateParams
				+ ", typeCode=" + typeCode + "]";
	}
	
	
	
	

}

