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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import edu.mass.doe.cap.dataservice.pojo.CapData;

@Entity
@Table(name = "CP068CFU_FILE_UPLOAD_HDR")
@AttributeOverrides({
@AttributeOverride(name = "createDate", column = @Column(name = "CFU_CREATE_DATE")),
@AttributeOverride(name = "createUser", column = @Column(name = "CFU_CREATED_BY")),
@AttributeOverride(name = "modifyDate", column = @Column(name = "CFU_UPDATE_DATE")),
@AttributeOverride(name = "modifyUser", column = @Column(name = "CFU_UPDATED_BY"))
})
public class Batch  extends BaseJPAEntity{
	
	@Id
	@Column(name="CFU_FILE_UPLOAD_ID")
	@SequenceGenerator(name = "CFU_PK_GENERATOR", sequenceName = "CP068CFU_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CFU_PK_GENERATOR")
	private Long uploadId;
	
	@Column(name="CFU_FILE_NAME")
	private String fileName;
	
	@Column(name="CFU_INT_FILE_NAME")
	private String internalFileName;
	
	@Column(name="CFU_FUT_CODE")
	private String code;
	
	@Column(name="CFU_DA_PERSON_ID")
	private Long personId;
	
	@Column(name="CFU_EXP_DATE")
	private Date expDate;
	
	@Column(name="CFU_EFF_DATE")
	private Date effDate;
	
	@OneToMany(mappedBy = "batch", cascade = CascadeType.PERSIST,fetch=FetchType.LAZY)
	@Where(clause = "FUS_EXP_DATE is null")
	private List<BatchStaging> records;
	

	public Long getUploadId() {
		return uploadId;
	}

	public void setUploadId(Long uploadId) {
		this.uploadId = uploadId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getInternalFileName() {
		return internalFileName;
	}

	public void setInternalFileName(String internalFileName) {
		this.internalFileName = internalFileName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
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

	public List<BatchStaging> getRecords() {
		return records;
	}

	public void setRecords(List<BatchStaging> records) {
		this.records = records;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((effDate == null) ? 0 : effDate.hashCode());
		result = prime * result + ((expDate == null) ? 0 : expDate.hashCode());
		result = prime * result + ((fileName == null) ? 0 : fileName.hashCode());
		result = prime * result + ((internalFileName == null) ? 0 : internalFileName.hashCode());
		result = prime * result + ((personId == null) ? 0 : personId.hashCode());
		result = prime * result + ((records == null) ? 0 : records.hashCode());
		result = prime * result + ((uploadId == null) ? 0 : uploadId.hashCode());
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
		if (!(obj instanceof Batch))
			return false;
		Batch other = (Batch) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
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
		if (fileName == null) {
			if (other.fileName != null)
				return false;
		} else if (!fileName.equals(other.fileName))
			return false;
		if (internalFileName == null) {
			if (other.internalFileName != null)
				return false;
		} else if (!internalFileName.equals(other.internalFileName))
			return false;
		if (personId == null) {
			if (other.personId != null)
				return false;
		} else if (!personId.equals(other.personId))
			return false;
		if (records == null) {
			if (other.records != null)
				return false;
		} else if (!records.equals(other.records))
			return false;
		if (uploadId == null) {
			if (other.uploadId != null)
				return false;
		} else if (!uploadId.equals(other.uploadId))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Batch [uploadId=" + uploadId + ", fileName=" + fileName + ", internalFileName=" + internalFileName
				+ ", code=" + code + ", personId=" + personId + ", expDate=" + expDate + ", effDate=" + effDate
				+ ", records=" + records + "]";
	}
	

}
