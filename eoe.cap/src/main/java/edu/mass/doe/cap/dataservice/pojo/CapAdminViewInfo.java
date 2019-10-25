package edu.mass.doe.cap.dataservice.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CapAdminViewInfo {

	private String orgCode;
	private String orgName;
	private Long schoolYear;
	private Long totalCycles;
	private Long totalOpenCycles;
	private Long totalClosedCycles;
	private Long totalCandidates;
	private Long totalProgramSupervisors;
	private Date latestActDate;
	
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

	public Long getSchoolYear() {
		return schoolYear;
	}

	public void setSchoolYear(Long sy) {
		this.schoolYear = sy;
	}
	
	public Long getTotalCycles() {
		return totalCycles;
	}

	public void setTotalCycles(Long tc) {
		this.totalCycles = tc;
	}

	public Long getTotalOpenCycles() {
		return totalOpenCycles;
	}

	public void setTotalOpenCycles(Long toc) {
		this.totalOpenCycles = toc;
	}
	
	public Long getTotalClosedCycles() {
		return totalClosedCycles;
	}

	public void setTotalClosedCycles(Long tcc) {
		this.totalClosedCycles = tcc;
	}
	
	public Long getTotalCandidates() {
		return totalCandidates;
	}

	public void setTotalCandidates(Long tc) {
		this.totalCandidates = tc;
	}
	
	public Long getTotalProgramSupervisors() {
		return totalProgramSupervisors;
	}

	public void setTotalProgramSupervisors(Long tps) {
		this.totalProgramSupervisors = tps;
	}
	
	public Date getLatestActDate() {
		return  latestActDate;
	}

	public void setLatestActDate(Date laDate) {
		this.latestActDate = laDate;
	}
	
	
	@Override
	public String toString() {
		return "CapAdminViewInfo [orgCode=" + orgCode + ", orgName" + orgName + ", schoolYear=" + schoolYear
				+ ", totalCycles=" + totalCycles + ", totalOpenCycles=" + totalOpenCycles + ", totalClosedCycles=" + totalClosedCycles + ", totalCandidates=" + totalCandidates
				+ ", totalProgramSupervisors=" + totalProgramSupervisors + ", latestActDate=" + latestActDate +	"]";
	}

}
