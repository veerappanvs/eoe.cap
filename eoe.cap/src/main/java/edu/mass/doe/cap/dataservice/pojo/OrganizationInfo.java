package edu.mass.doe.cap.dataservice.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrganizationInfo {

	private String organizationCode;
	private String organizationName;
	private Long schoolYear;
	
	public String getOrganizationCode() {
		return organizationCode;
	}

	public void setOrganizationCode(String orgCode) {
		this.organizationCode = orgCode;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String orgName) {
		this.organizationName = orgName;
	}

	public Long getSchoolYear() {
		return schoolYear;
	}

	public void setSchoolYear(Long sy) {
		this.schoolYear = sy;
	}

}
