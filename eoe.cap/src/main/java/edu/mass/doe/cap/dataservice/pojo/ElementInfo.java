package edu.mass.doe.cap.dataservice.pojo;

import java.util.ArrayList;
import java.util.List;

public class ElementInfo {
	
	private Long id;
	private String elementCode;
	
	private String elementLabel;
	
	private String longDesc;
	
	private String altDesc;	
	
	private String rationale;
	
	private Character metThreshHold;
	
	private Character completed='N';
	
	private List<DimensionInfo> dimensions= new ArrayList<DimensionInfo>();
	private List<RatingInfo> ratingInfos = new ArrayList<RatingInfo>(); 
	private List<EvidenceFileInfo> files=new ArrayList<EvidenceFileInfo>();
	private EvidenceSource evidenceSources ;

	
	private String formativeRationale;


	public void setElementCode(String elementCode) {
		this.elementCode = elementCode;
	}

	public String getElementLabel() {
		return elementLabel;
	}

	public void setElementLabel(String elementLabel) {
		this.elementLabel = elementLabel;
	}

	public String getLongDesc() {
		return longDesc;
	}

	public void setLongDesc(String longDesc) {
		this.longDesc = longDesc;
	}

	public String getAltDesc() {
		return altDesc;
	}

	public void setAltDesc(String altDesc) {
		this.altDesc = altDesc;
	}


	public String getElementCode() {
		return elementCode;
	}

	public List<DimensionInfo> getDimensions() {
		return dimensions;
	}

	public void setDimensions(List<DimensionInfo> dimensions) {
		this.dimensions = dimensions;
	}

	public List<RatingInfo> getRatingInfos() {
		return ratingInfos;
	}

	public void setRatingInfos(List<RatingInfo> ratingInfos) {
		this.ratingInfos = ratingInfos;
	}

	public String getRationale() {
		return rationale;
	}

	public void setRationale(String rationale) {
		this.rationale = rationale;
	}

	public Character getMetThreshHold() {
		return metThreshHold;
	}

	public void setMetThreshHold(Character metThreshHold) {
		this.metThreshHold = metThreshHold;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Character getCompleted() {
		return completed;
	}

	public void setCompleted(Character completed) {
		this.completed = completed;
	}

	public String getFormativeRationale() {
		return formativeRationale;
	}

	public void setFormativeRationale(String formativeRationale) {
		this.formativeRationale = formativeRationale;
	}

	public List<EvidenceFileInfo> getFiles() {
		return files;
	}

	public void setFiles(List<EvidenceFileInfo> files) {
		this.files = files;
	}

	public EvidenceSource getEvidenceSources() {
		return evidenceSources;
	}

	public void setEvidenceSources(EvidenceSource evidenceSources) {
		this.evidenceSources = evidenceSources;
	}

	
	
}
