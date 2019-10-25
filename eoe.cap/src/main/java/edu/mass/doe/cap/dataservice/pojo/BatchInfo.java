package edu.mass.doe.cap.dataservice.pojo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Where;
import org.springframework.web.multipart.MultipartFile;

import edu.mass.doe.cap.dataservice.entity.RubricMap;

public class BatchInfo {
	
	private Long batchId;

	private String fileName;
	
	private String internalFileName;
	
	private String code;
	
	private Long personId;
	
	private MultipartFile file;
	
	private int errcount=-1;
	
	
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
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	public Long getBatchId() {
		return batchId;
	}
	public void setBatchId(Long batchId) {
		this.batchId = batchId;
	}
	public int getErrcount() {
		return errcount;
	}
	public void setErrcount(int errcount) {
		this.errcount = errcount;
	}

}
