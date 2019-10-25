package edu.mass.doe.cap.dataservice.pojo;


public class DimensionInfo {
	
	
	private Long id;
	private String dimensionCode;
	private String dimensionDesc;
	private String dimensionShortDesc;
	private String dimensionLongDesc;	
	private String ratingCode;
	private String ratingDesc;	
	private Long linkId;
	private String formativeRatingCode;
	private String formativeRatingDesc;	

	
	
	
	
	public void setDimensionCode(String dimensionCode) {
		this.dimensionCode = dimensionCode;
	}
	public String getDimensionDesc() {
		return dimensionDesc;
	}
	public void setDimensionDesc(String dimensionDesc) {
		this.dimensionDesc = dimensionDesc;
	}
	public String getDimensionShortDesc() {
		return dimensionShortDesc;
	}
	public void setDimensionShortDesc(String dimensionShortDesc) {
		this.dimensionShortDesc = dimensionShortDesc;
	}
	public String getDimensionLongDesc() {
		return dimensionLongDesc;
	}
	public void setDimensionLongDesc(String dimensionLongDesc) {
		this.dimensionLongDesc = dimensionLongDesc;
	}
	
	public String getDimensionCode() {
		return dimensionCode;
	}
	public String getRatingCode() {
		return ratingCode;
	}
	public void setRatingCode(String ratingCode) {
		this.ratingCode = ratingCode;
	}
	public String getFormativeRatingCode() {
		return formativeRatingCode;
	}
	public void setFormativeRatingCode(String formativeRatingCode) {
		this.formativeRatingCode = formativeRatingCode;
	}
	public Long getLinkId() {
		return linkId;
	}
	public void setLinkId(Long linkId) {
		this.linkId = linkId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRatingDesc() {
		return ratingDesc;
	}
	public void setRatingDesc(String ratingDesc) {
		this.ratingDesc = ratingDesc;
	}
	public String getFormativeRatingDesc() {
		return formativeRatingDesc;
	}
	public void setFormativeRatingDesc(String formativeRatingDesc) {
		this.formativeRatingDesc = formativeRatingDesc;
	}
	
	
	

}
