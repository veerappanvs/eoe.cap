package edu.mass.doe.cap.dataservice.entity;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CP016COT_COMMUNICATION_TYP")
@AttributeOverrides({
@AttributeOverride(name = "createDate", column = @Column(name = "COT_CREATE_DATE")),
@AttributeOverride(name = "createUser", column = @Column(name = "COT_CREATED_BY")),
@AttributeOverride(name = "modifyDate", column = @Column(name = "COT_UPDATE_DATE")),
@AttributeOverride(name = "modifyUser", column = @Column(name = "COT_UPDATED_BY"))
})
public class MessageType extends BaseJPAEntity {
	
	@Id
	@Column(name="COT_COMM_TYPE_CODE")
	private String typeCode;
	
	@Column(name="COT_COMM_TYPE_DESC")
	private String desc;
	
	
	@Column(name="COT_COMM_TYPE_NAME")
	private String name;
	
	@Column(name="COT_EXP_DATE")
	private Date expDate ;
	
	@Column(name="COT_EFF_DATE")
	private Date effDate ;

	public Date getEffDate() {
		return effDate;
	}

	public void setEffDate(Date effDate) {
		this.effDate = effDate;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getExpDate() {
		return expDate;
	}

	public void setExpDate(Date expDate) {
		this.expDate = expDate;
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
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((typeCode == null) ? 0 : typeCode.hashCode());
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
		if (!(obj instanceof MessageType))
			return false;
		MessageType other = (MessageType) obj;
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
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (typeCode == null) {
			if (other.typeCode != null)
				return false;
		} else if (!typeCode.equals(other.typeCode))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MessageType [typeCode=" + typeCode + ", desc=" + desc + ", name=" + name + ", expDate=" + expDate
				+ ", effDate=" + effDate + "]";
	}

}
