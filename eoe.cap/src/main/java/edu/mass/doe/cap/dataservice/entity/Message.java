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
@Table(name = "CP073COM_COMMUNICATION")
@AttributeOverrides({
@AttributeOverride(name = "createDate", column = @Column(name = "COM_CREATE_DATE")),
@AttributeOverride(name = "createUser", column = @Column(name = "COM_CREATED_BY")),
@AttributeOverride(name = "modifyDate", column = @Column(name = "COM_UPDATE_DATE")),
@AttributeOverride(name = "modifyUser", column = @Column(name = "COM_UPDATED_BY"))
})
public class Message extends BaseJPAEntity{
	
	
	@Id
	@Column(name="COM_COUMMUNICATION_ID")
	@SequenceGenerator(name = "COM_PK_GENERATOR", sequenceName = "CP073COM_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COM_PK_GENERATOR")
	private Long messageId;
	
	
	@Column(name="COM_CPC_ID")
	private Long cycleId;
	
	@Column(name="COM_COMM_TYPE_CODE")
	private String  typeCode;
	
	@Column(name="COM_DA_PERSON_ID")
	private Long daPersonId;
	
	@Column(name="COM_MESSAGE")
	private String message;
	
	@Column(name="COM_MESSAGE_DATE")
	private Date messageDate;
	
	@Column(name="COM_EXP_DATE")
	private Date expDate;
	
	@Column(name="COM_EFF_DATE")
	private Date effDate;
	

	public Long getMessageId() {
		return messageId;
	}

	public void setMessageId(Long messageId) {
		this.messageId = messageId;
	}

	public Long getCycleId() {
		return cycleId;
	}

	public void setCycleId(Long cycleId) {
		this.cycleId = cycleId;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public Long getDaPersonId() {
		return daPersonId;
	}

	public void setDaPersonId(Long daPersonId) {
		this.daPersonId = daPersonId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getMessageDate() {
		return messageDate;
	}

	public void setMessageDate(Date messageDate) {
		this.messageDate = messageDate;
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
		result = prime * result + ((cycleId == null) ? 0 : cycleId.hashCode());
		result = prime * result + ((daPersonId == null) ? 0 : daPersonId.hashCode());
		result = prime * result + ((effDate == null) ? 0 : effDate.hashCode());
		result = prime * result + ((expDate == null) ? 0 : expDate.hashCode());
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + ((messageDate == null) ? 0 : messageDate.hashCode());
		result = prime * result + ((messageId == null) ? 0 : messageId.hashCode());
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
		if (!(obj instanceof Message))
			return false;
		Message other = (Message) obj;
		if (cycleId == null) {
			if (other.cycleId != null)
				return false;
		} else if (!cycleId.equals(other.cycleId))
			return false;
		if (daPersonId == null) {
			if (other.daPersonId != null)
				return false;
		} else if (!daPersonId.equals(other.daPersonId))
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
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (messageDate == null) {
			if (other.messageDate != null)
				return false;
		} else if (!messageDate.equals(other.messageDate))
			return false;
		if (messageId == null) {
			if (other.messageId != null)
				return false;
		} else if (!messageId.equals(other.messageId))
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
		return "Message [messageId=" + messageId + ", cycleId=" + cycleId + ", typeCode=" + typeCode + ", daPersonId="
				+ daPersonId + ", message=" + message + ", messageDate=" + messageDate + ", expDate=" + expDate
				+ ", effDate=" + effDate + "]";
	}
	
	

}
