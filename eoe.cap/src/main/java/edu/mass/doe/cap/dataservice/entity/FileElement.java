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

/**
 * @author vsubramaniyan
 *
 */
@Entity
@Table(name = "CP066EFE_EVIDENCE_FILE_ELEMENT")
@AttributeOverrides({
@AttributeOverride(name = "createDate", column = @Column(name = "EFE_CREATE_DATE")),
@AttributeOverride(name = "createUser", column = @Column(name = "EFE_CREATED_BY")),
@AttributeOverride(name = "modifyDate", column = @Column(name = "EFE_UPDATE_DATE")),
@AttributeOverride(name = "modifyUser", column = @Column(name = "EFE_UPDATED_BY"))
})
public class FileElement  extends BaseJPAEntity{
	
	
	@Id
	@Column(name="EFE_FILE_ELEMENT_ID")
	@SequenceGenerator(name = "EFE_PK_GENERATOR", sequenceName = "CP066EFE_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EFE_PK_GENERATOR")
	private Long elementId;
	
	@Column(name="EFE_ELEMENT_CODE")
	private String elementCode;

	@Column(name="EFE_EVT_CODE")
	private String evidenceCode;
	
	@Column(name="EFE_EFF_DATE")
	private Date effdate;
	
	@Column(name="EFE_EXP_DATE")
	private Date expdate;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EFE_EVF_FILE_ID", insertable = true, updatable = false,nullable=false)
	private EvidenceFile evidenceFile;


	public Long getElementId() {
		return elementId;
	}


	public void setElementId(Long elementId) {
		this.elementId = elementId;
	}


	public String getElementCode() {
		return elementCode;
	}


	public void setElementCode(String elementCode) {
		this.elementCode = elementCode;
	}


	public Date getEffdate() {
		return effdate;
	}


	public void setEffdate(Date effdate) {
		this.effdate = effdate;
	}


	public Date getExpdate() {
		return expdate;
	}


	public void setExpdate(Date expdate) {
		this.expdate = expdate;
	}


	public EvidenceFile getEvidenceFile() {
		return evidenceFile;
	}


	public void setEvidenceFile(EvidenceFile evidenceFile) {
		this.evidenceFile = evidenceFile;
	}


	public String getEvidenceCode() {
		return evidenceCode;
	}


	public void setEvidenceCode(String evidenceCode) {
		this.evidenceCode = evidenceCode;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((effdate == null) ? 0 : effdate.hashCode());
		result = prime * result + ((elementCode == null) ? 0 : elementCode.hashCode());
		result = prime * result + ((elementId == null) ? 0 : elementId.hashCode());
		result = prime * result + ((evidenceCode == null) ? 0 : evidenceCode.hashCode());
		result = prime * result + ((evidenceFile == null) ? 0 : evidenceFile.hashCode());
		result = prime * result + ((expdate == null) ? 0 : expdate.hashCode());
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
		if (!(obj instanceof FileElement))
			return false;
		FileElement other = (FileElement) obj;
		if (effdate == null) {
			if (other.effdate != null)
				return false;
		} else if (!effdate.equals(other.effdate))
			return false;
		if (elementCode == null) {
			if (other.elementCode != null)
				return false;
		} else if (!elementCode.equals(other.elementCode))
			return false;
		if (elementId == null) {
			if (other.elementId != null)
				return false;
		} else if (!elementId.equals(other.elementId))
			return false;
		if (evidenceCode == null) {
			if (other.evidenceCode != null)
				return false;
		} else if (!evidenceCode.equals(other.evidenceCode))
			return false;
		if (evidenceFile == null) {
			if (other.evidenceFile != null)
				return false;
		} else if (!evidenceFile.equals(other.evidenceFile))
			return false;
		if (expdate == null) {
			if (other.expdate != null)
				return false;
		} else if (!expdate.equals(other.expdate))
			return false;
		return true;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FileElement [elementId=" + elementId + ", elementCode=" + elementCode + ", evidenceCode=" + evidenceCode
				+ ", effdate=" + effdate + ", expdate=" + expdate + ", evidenceFile=" + evidenceFile + "]";
	}
	
	
	
	
}
