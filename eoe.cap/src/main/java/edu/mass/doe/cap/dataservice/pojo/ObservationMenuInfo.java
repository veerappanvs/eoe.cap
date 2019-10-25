package edu.mass.doe.cap.dataservice.pojo;

public class ObservationMenuInfo {
	
	
	private Long observationNumber;
	private String observationTypeCode;
	private Long cycleId;
	private String typeDesc;
	
	public Long getObservationNumber() {
		return observationNumber;
	}
	public void setObservationNumber(Long observationNumber) {
		this.observationNumber = observationNumber;
	}
	public String getObservationTypeCode() {
		return observationTypeCode;
	}
	public void setObservationTypeCode(String observationTypeCode) {
		this.observationTypeCode = observationTypeCode;
	}
	public Long getCycleId() {
		return cycleId;
	}
	public void setCycleId(Long cycleId) {
		this.cycleId = cycleId;
	}
	public String getTypeDesc() {
		return typeDesc;
	}
	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
	}

}
