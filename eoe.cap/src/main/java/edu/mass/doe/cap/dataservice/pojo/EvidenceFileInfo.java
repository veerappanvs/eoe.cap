package edu.mass.doe.cap.dataservice.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class EvidenceFileInfo  {
	
	private Long cycleId;
	private Long fileId;
	private String internalFileName;
	private String externalFileName;
	private Long personId;
	private String personName;
	private double fileSizeInDisk;
	
	private MultipartFile file;
	
	private boolean  canDelete;
	
	
	private List<Attribute> attributes = new ArrayList<Attribute>();
	private List<Attribute> evidenceTypes = new ArrayList<Attribute>();
	
	private Date createdDate;
	
	
	public Long getFileId() {
		return fileId;
	}
	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}
	public String getInternalFileName() {
		return internalFileName;
	}
	public void setInternalFileName(String internalFileName) {
		this.internalFileName = internalFileName;
	}
	public String getExternalFileName() {
		return externalFileName;
	}
	public void setExternalFileName(String externalFileName) {
		this.externalFileName = externalFileName;
	}
	public List<Attribute> getAttributes() {
		return attributes;
	}
	public void setAttributes(List<Attribute> attributes) {
		this.attributes = attributes;
	}
	public Long getPersonId() {
		return personId;
	}
	public void setPersonId(Long personId) {
		this.personId = personId;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public Long getCycleId() {
		return cycleId;
	}
	public void setCycleId(Long cycleId) {
		this.cycleId = cycleId;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	public boolean isCanDelete() {
		return canDelete;
	}
	public void setCanDelete(boolean canDelete) {
		this.canDelete = canDelete;
	}
	public List<Attribute> getEvidenceTypes() {
		return evidenceTypes;
	}
	public void setEvidenceTypes(List<Attribute> evidenceTypes) {
		this.evidenceTypes = evidenceTypes;
	}
	public double getFileSizeInDisk() {
		return fileSizeInDisk;
	}
	public void setFileSizeInDisk(double fileSizeInDisk) {
		this.fileSizeInDisk = fileSizeInDisk;
	}
	
	

}
