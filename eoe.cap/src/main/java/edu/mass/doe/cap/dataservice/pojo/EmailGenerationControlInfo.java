package edu.mass.doe.cap.dataservice.pojo;



public class EmailGenerationControlInfo {
	

	
	private Long egnId;
	
	private String slectionQuery;
	
	private String emailQuery;
	
	private String params;
	
	private String updateQuery;
	
	private String updateParams;

	
	private String typeCode;
	public Long getEgnId() {
		return egnId;
	}

	public void setEgnId(Long egnId) {
		this.egnId = egnId;
	}

	public String getSlectionQuery() {
		return slectionQuery;
	}

	public void setSlectionQuery(String slectionQuery) {
		this.slectionQuery = slectionQuery;
	}

	public String getEmailQuery() {
		return emailQuery;
	}

	public void setEmailQuery(String emailQuery) {
		this.emailQuery = emailQuery;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public String getUpdateQuery() {
		return updateQuery;
	}

	public void setUpdateQuery(String updateQuery) {
		this.updateQuery = updateQuery;
	}

	public String getUpdateParams() {
		return updateParams;
	}

	public void setUpdateParams(String updateParams) {
		this.updateParams = updateParams;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

}
