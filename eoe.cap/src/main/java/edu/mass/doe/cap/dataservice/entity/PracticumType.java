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
@Table(name = "CP001CPT_CAP_PRACTICUM_TYPE")
@AttributeOverrides({
@AttributeOverride(name = "createDate", column = @Column(name = "CPT_CREATE_DATE")),
@AttributeOverride(name = "createUser", column = @Column(name = "CPT_CREATED_BY")),
@AttributeOverride(name = "modifyDate", column = @Column(name = "CPT_UPDATE_DATE")),
@AttributeOverride(name = "modifyUser", column = @Column(name = "CPT_UPDATED_BY"))
})

public class PracticumType extends BaseJPAEntity  {
	
	@Id
	@Column(name="CPT_PRACTICUM_TYPE_CODE")	
	private String typeCode;
	
	@Column(name="CPT_PRACTICUM_TYPE_DESC")	
	private String typeDesc;
	
	@Column(name="CPT_EXP_DATE")	
	private Date expDate;
	
	@Column(name="CPT_EFF_DATE")	
	private Date effDate;

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getTypeDesc() {
		return typeDesc;
	}

	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
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
	
	
	

}
