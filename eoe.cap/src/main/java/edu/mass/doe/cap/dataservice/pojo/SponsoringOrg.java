/**
 * 
 */
package edu.mass.doe.cap.dataservice.pojo;

import javax.persistence.Id;

/**
 * @author vsubramaniyan
 *
 */
public class SponsoringOrg {


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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((orgCode == null) ? 0 : orgCode.hashCode());
		result = prime * result + ((orgId == null) ? 0 : orgId.hashCode());
		result = prime * result + ((orgName == null) ? 0 : orgName.hashCode());
		result = prime * result + ((programDesc == null) ? 0 : programDesc.hashCode());
		result = prime * result + ((programId == null) ? 0 : programId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SponsoringOrg other = (SponsoringOrg) obj;
		if (orgCode == null) {
			if (other.orgCode != null)
				return false;
		} else if (!orgCode.equals(other.orgCode))
			return false;
		if (orgId == null) {
			if (other.orgId != null)
				return false;
		} else if (!orgId.equals(other.orgId))
			return false;
		if (orgName == null) {
			if (other.orgName != null)
				return false;
		} else if (!orgName.equals(other.orgName))
			return false;
		if (programDesc == null) {
			if (other.programDesc != null)
				return false;
		} else if (!programDesc.equals(other.programDesc))
			return false;
		if (programId == null) {
			if (other.programId != null)
				return false;
		} else if (!programId.equals(other.programId))
			return false;
		return true;
	}

	
	
}
