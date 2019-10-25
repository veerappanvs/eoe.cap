package edu.mass.doe.cap.model.reports;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.mass.doe.EOEAuthorization.DirAdminAccessUtil;
import edu.mass.doe.EOEAuthorization.EOEAdminUserInfo;
import edu.mass.doe.EOEAuthorization.exception.NesterException;
import edu.mass.doe.EOEAuthorization.exception.NoOrganizationFoundException;
import edu.mass.doe.EOEAuthorization.exception.NoPersonFoundException;
import edu.mass.doe.cap.candidate.CandidateEnrollment;
import edu.mass.doe.cap.dataservice.reports.CycleReportsService;

import edu.mass.doe.cap.dataservice.pojo.CycleReportInfo;
import edu.mass.doe.cap.dataservice.pojo.OrganizationInfo;

/**
 * The Class ReportsManager.
 */
@Component
public class ReportsManager {

	@Autowired(required = true)
	private CycleReportsService reportsService;

	private static Logger logger = LoggerFactory.getLogger(ReportsManager.class);

	/**
	 * Fetch all cap manager report info.
	 *
	 * @param orgCode the org code
	 * @param schoolYear the school year
	 * @return the list
	 * @throws NesterException the nester exception
	 */
	public List<CycleReportInfo> fetchAllCapManagerReportInfo(String orgCode, Integer schoolYear)throws NesterException {
		return reportsService.findAllCapManagerReportInfo(orgCode, schoolYear);
	}
	
	/**
	 * Fetch all cap admin report info.
	 *
	 * @param orgCode the org code
	 * @param schoolYear the school year
	 * @return the list
	 * @throws NesterException the nester exception
	 */
	public List<CycleReportInfo> fetchAllCapAdminReportInfo(String orgCode, Integer schoolYear)throws NesterException {
		return reportsService.findAllCapAdminReportInfo(orgCode, schoolYear);
	}
	
	/**
	 * Fetch organization list.
	 *
	 * @return the list
	 */
	public List<OrganizationInfo> fetchOrganizationList() {
		return reportsService.findAllOrganizations();
	}	
	
}
