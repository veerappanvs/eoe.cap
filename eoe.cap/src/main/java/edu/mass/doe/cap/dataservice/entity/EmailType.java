package edu.mass.doe.cap.dataservice.entity;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CP013CET_CAP_EMAIL_TYP")
@AttributeOverrides({
@AttributeOverride(name = "createDate", column = @Column(name = "CET_CREATE_DATE")),
@AttributeOverride(name = "createUser", column = @Column(name = "CET_CREATED_BY")),
@AttributeOverride(name = "modifyDate", column = @Column(name = "CET_UPDATE_DATE")),
@AttributeOverride(name = "modifyUser", column = @Column(name = "CET_UPDATED_BY"))
})
public class EmailType extends BaseJPAEntity {
	
	
	@Id
	@Column(name="CET_EMAIL_TYPE_CODE")
	private String typecode;
	
	@Column(name="CET_EMAIL_TYPE_DESC")
	private String desc;
	
	@Column(name="CET_EMAIL_SUBJECT_LINE")
	private String subject;
	
	@Column(name="CET_TEMPLATE_NAME")
	private String template;
	
	@Column(name="CET_EXP_DATE")
	private Date expDate;
	
	@Column(name="CET_EFF_DATE")
	private Date effDate;

	public String getTypecode() {
		return typecode;
	}

	public void setTypecode(String typecode) {
		this.typecode = typecode;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
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
		result = prime * result + ((desc == null) ? 0 : desc.hashCode());
		result = prime * result + ((effDate == null) ? 0 : effDate.hashCode());
		result = prime * result + ((expDate == null) ? 0 : expDate.hashCode());
		result = prime * result + ((subject == null) ? 0 : subject.hashCode());
		result = prime * result + ((template == null) ? 0 : template.hashCode());
		result = prime * result + ((typecode == null) ? 0 : typecode.hashCode());
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
		if (!(obj instanceof EmailType))
			return false;
		EmailType other = (EmailType) obj;
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
		if (subject == null) {
			if (other.subject != null)
				return false;
		} else if (!subject.equals(other.subject))
			return false;
		if (template == null) {
			if (other.template != null)
				return false;
		} else if (!template.equals(other.template))
			return false;
		if (typecode == null) {
			if (other.typecode != null)
				return false;
		} else if (!typecode.equals(other.typecode))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "EmailType [typecode=" + typecode + ", desc=" + desc + ", subject=" + subject + ", template=" + template
				+ ", expDate=" + expDate + ", effDate=" + effDate + "]";
	}	

	
	
	
}
