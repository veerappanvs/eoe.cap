package edu.mass.doe.cap.dataservice.pojo;

public class EvidenceInfo {
	
	
	private Long evidenceId;
	private String elementDesc;
	private Integer focusElement;
	private String elementCode;
	private String psEvidence;
	private String spEvidence;
	private String calEvidence;
	
	
	public String getElementDesc() {
		return elementDesc;
	}
	public void setElementDesc(String elementDesc) {
		this.elementDesc = elementDesc;
	}
	public Integer getFocusElement() {
		return focusElement;
	}
	public void setFocusElement(Integer focusElement) {
		this.focusElement = focusElement;
	}
	public String getElementCode() {
		return elementCode;
	}
	public void setElementCode(String elementCode) {
		this.elementCode = elementCode;
	}
	public String getPsEvidence() {
		return psEvidence;
	}
	public void setPsEvidence(String psEvidence) {
		this.psEvidence = psEvidence;
	}
	public String getSpEvidence() {
		return spEvidence;
	}
	public void setSpEvidence(String spEvidence) {
		this.spEvidence = spEvidence;
	}
	public String getCalEvidence() {
		return calEvidence;
	}
	public void setCalEvidence(String calEvidence) {
		this.calEvidence = calEvidence;
	}
	public Long getEvidenceId() {
		return evidenceId;
	}
	public void setEvidenceId(Long evidenceId) {
		this.evidenceId = evidenceId;
	}
	

}
