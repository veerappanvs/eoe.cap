package edu.mass.doe.cap.dataservice.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;


public class ProgramInfo {

	

	@Id
	private Long programId;
	private Long orgId;
	private String orgCode;
	private String orgName;
	private String programDesc;

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public Long getProgramId() {
		return programId;
	}

	public void setProgramId(Long programId) {
		this.programId = programId;
	}

	public String getProgramDesc() {
		return programDesc;
	}

	public void setProgramDesc(String programDesc) {
		this.programDesc = programDesc;
	}

	@Override
	public String toString() {
		return "ProgramInfo [programId=" + programId + ", programdDesc" + programDesc + ", orgId=" + orgId
				+ ", orgCode=" + orgCode + ", orgName=" + orgName + "]";
	}

}
