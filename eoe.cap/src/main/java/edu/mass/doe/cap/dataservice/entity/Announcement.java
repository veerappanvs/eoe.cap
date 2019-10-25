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
@Table(name = "CP074ANN_ANNOUNCEMENT")
@AttributeOverrides({
@AttributeOverride(name = "createDate", column = @Column(name = "ANN_CREATE_DATE")),
@AttributeOverride(name = "createUser", column = @Column(name = "ANN_CREATED_BY")),
@AttributeOverride(name = "modifyDate", column = @Column(name = "ANN_UPDATE_DATE")),
@AttributeOverride(name = "modifyUser", column = @Column(name = "ANN_UPDATED_BY"))
})
public class Announcement extends BaseJPAEntity {
	@Id
	@Column(name="ANN_ANNOUNCEMENT_ID")
	@SequenceGenerator(name = "ANN_PK_GENERATOR", sequenceName = "CP074ANN_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ANN_PK_GENERATOR")
	private Long announcementId;
	
	@Column(name="ANN_ANNOUCEMENT")
	private String text;
	
	@Column(name="ANN_EXP_DATE")
	private Date expDate;
	
	@Column(name="ANN_EFF_DATE")
	private Date effDate;

	public Long getAnnouncementId() {
		return announcementId;
	}

	public void setAnnouncementId(Long announcementId) {
		this.announcementId = announcementId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
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
		int result = 1;
		result = prime * result + ((announcementId == null) ? 0 : announcementId.hashCode());
		result = prime * result + ((effDate == null) ? 0 : effDate.hashCode());
		result = prime * result + ((expDate == null) ? 0 : expDate.hashCode());
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Announcement))
			return false;
		Announcement other = (Announcement) obj;
		if (announcementId == null) {
			if (other.announcementId != null)
				return false;
		} else if (!announcementId.equals(other.announcementId))
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
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Announcement [announcementId=" + announcementId + ", text=" + text + ", expDate=" + expDate
				+ ", effDate=" + effDate + "]";
	}
	
	
	
	
}
