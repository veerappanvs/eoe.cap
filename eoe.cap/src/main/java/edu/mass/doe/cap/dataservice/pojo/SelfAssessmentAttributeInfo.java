package edu.mass.doe.cap.dataservice.pojo;

import java.util.Date;
import java.util.List;


public class SelfAssessmentAttributeInfo  {
	
	private Long id;
	
	private String typeCode;
	
	private String area;
	
	private String rationale;
	
	private Date expDate;
	
	private Date effDate;
	
	private List<SelfAssessmentElementInfo> elements;
	
	private Long attributeNumber;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getRationale() {
		return rationale;
	}

	public void setRationale(String rationale) {
		this.rationale = rationale;
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

	public List<SelfAssessmentElementInfo> getElements() {
		return elements;
	}

	public void setElements(List<SelfAssessmentElementInfo> elements) {
		this.elements = elements;
	}

	public Long getAttributeNumber() {
		return attributeNumber;
	}

	public void setAttributeNumber(Long attributeNumber) {
		this.attributeNumber = attributeNumber;
	}


}
