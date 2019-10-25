package edu.mass.doe.cap.dataservice.reports;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import edu.mass.doe.cap.dataservice.pojo.CapYear;
import edu.mass.doe.cap.dataservice.pojo.CycleReportInfo;
import edu.mass.doe.cap.dataservice.pojo.OrganizationInfo;
import edu.mass.doe.cap.model.util.CAPUtil;

/**
 * The Class CycleReportsService.
 */
@Repository
public class CycleReportsService {

	private static Logger logger = LoggerFactory.getLogger(CycleReportsService.class);

	@PersistenceContext(unitName="capPeristanceUnit")
	EntityManager entityManager;
	
	@Autowired
	private Environment env;
	
	@Autowired
	private CAPUtil capUtil;
	

	/**
	 * Find all organizations.
	 *
	 * @return the list
	 */
	public List<OrganizationInfo> findAllOrganizations() {
		
		List<OrganizationInfo> orgList = new ArrayList<OrganizationInfo>();

		Query q = entityManager.createNativeQuery(env.getProperty("orgList"));
	
		List<Object[]> infoList = q.getResultList();
		
		for (Object[] info : infoList) {
			OrganizationInfo organizationInfo = new OrganizationInfo();
			organizationInfo.setOrganizationCode((String) info[0]);
			organizationInfo.setOrganizationName((String) info[1]);
			orgList.add(organizationInfo);
		}		
		return orgList;
	}
	
	
	/**
	 * Find all cap manager report info.
	 *
	 * @param orgCode the org code
	 * @param schoolYear the school year
	 * @return the list
	 */
	public List<CycleReportInfo> findAllCapManagerReportInfo(String orgCode, Integer schoolYear) {
		
		List<CycleReportInfo> capReportList = new ArrayList<CycleReportInfo>();

		Query q = entityManager.createNativeQuery(env.getProperty("capManagerReport"));
		q.setParameter("orgCode", orgCode);
		
		List<CapYear> capYears=capUtil.getSchoolYears();
		
		if(schoolYear!=0)
			q.setParameter("fromSchoolYear", schoolYear);
		else
			q.setParameter("fromSchoolYear", capYears.get(capYears.size()-1).getSchoolYear());
			
		if(schoolYear!=0)
			q.setParameter("toSchoolYear", schoolYear);
		else
			q.setParameter("toSchoolYear", capYears.get(0).getSchoolYear());		
		
		List<Object[]> infoList = q.getResultList();
		
		for (Object[] info : infoList) {
			CycleReportInfo cycleReportInfo = new CycleReportInfo();
			cycleReportInfo.setOrgName((String) info[0]);
			cycleReportInfo.setCycleId(((BigDecimal) info[1]).longValue());
			cycleReportInfo.setPracticumDistrict((String) info[2]);
			cycleReportInfo.setPracticumSchoolName((String) info[3]);
			cycleReportInfo.setSchoolYear(info[4]!=null?((BigDecimal) info[4]).longValue():null);
			cycleReportInfo.setProgramDescription((String) info[5]);
			cycleReportInfo.setCandidateMEPID (((BigDecimal) info[6]).longValue());
			cycleReportInfo.setCandidateFirstName((String) info[7]);
			cycleReportInfo.setCandidateLastName((String) info[8]);
			cycleReportInfo.setCandidateEmailAddress((String) info[9]);
			cycleReportInfo.setSpName((String) info[12]);
			cycleReportInfo.setSpEmailAddress((String) info[13]);
			cycleReportInfo.setPsName((String) info[10]);
			cycleReportInfo.setPsEmailAddress((String) info[11]);
			cycleReportInfo.setElementF1A4Q((String) info[14]);
			cycleReportInfo.setElementF1A4S((String) info[15]);
			cycleReportInfo.setElementF1A4C((String) info[16]);
			cycleReportInfo.setElementF1B2Q((String) info[17]);
			cycleReportInfo.setElementF1B2S((String) info[18]);
			cycleReportInfo.setElementF1B2C((String) info[19]);
			cycleReportInfo.setElementF2A3Q((String) info[20]);
			cycleReportInfo.setElementF2A3S((String) info[21]);
			cycleReportInfo.setElementF2A3C((String) info[22]);
			cycleReportInfo.setElementF2B1Q((String) info[23]);
			cycleReportInfo.setElementF2B1S((String) info[24]);
			cycleReportInfo.setElementF2B1C((String) info[25]);
			cycleReportInfo.setElementF2D2Q((String) info[26]);
			cycleReportInfo.setElementF2D2S((String) info[27]);
			cycleReportInfo.setElementF2D2C((String) info[28]);
			cycleReportInfo.setElementF4A1Q((String) info[29]);
			cycleReportInfo.setElementF4A1S((String) info[30]);
			cycleReportInfo.setElementF4A1C((String) info[31]);
			cycleReportInfo.setElementS1A4Q((String) info[32]);
			cycleReportInfo.setElementS1A4S((String) info[33]);
			cycleReportInfo.setElementS1A4C((String) info[34]);
			cycleReportInfo.setElementS1B2Q((String) info[35]);
			cycleReportInfo.setElementS1B2S((String) info[36]);
			cycleReportInfo.setElementS1B2C((String) info[37]);
			cycleReportInfo.setElementS2A3Q((String) info[38]);
			cycleReportInfo.setElementS2A3S((String) info[39]);
			cycleReportInfo.setElementS2A3C((String) info[40]);
			cycleReportInfo.setElementS2B1Q((String) info[41]);
			cycleReportInfo.setElementS2B1S((String) info[42]);
			cycleReportInfo.setElementS2B1C((String) info[43]);
			cycleReportInfo.setElementS2D2Q((String) info[44]);
			cycleReportInfo.setElementS2D2S((String) info[45]);
			cycleReportInfo.setElementS2D2C((String) info[46]);
			cycleReportInfo.setElementS4A1Q((String) info[47]);
			cycleReportInfo.setElementS4A1S((String) info[48]);
			cycleReportInfo.setElementS4A1C((String) info[49]);
			cycleReportInfo.setReadyToTeach((String) info[50]);
			cycleReportInfo.setDataSource((String) info[51]);
			cycleReportInfo.setSpMEPID(info[52]!=null?((BigDecimal) info[52]).longValue():null);			
			capReportList.add(cycleReportInfo);
		}
		
		return capReportList;
	}
	
	/**
	 * Find all cap admin report info.
	 *
	 * @param orgCode the org code
	 * @param schoolYear the school year
	 * @return the list
	 */
	public List<CycleReportInfo> findAllCapAdminReportInfo(String orgCode, Integer schoolYear) {
		
		List<CycleReportInfo> capReportList = new ArrayList<CycleReportInfo>();

		Query q = entityManager.createNativeQuery(env.getProperty("capAdminReport"));
		q.setParameter("orgCode", orgCode);
		
		List<CapYear> capYears=capUtil.getSchoolYears();
		
		if(schoolYear!=0)
			q.setParameter("fromSchoolYear", schoolYear);
		else
			q.setParameter("fromSchoolYear", capYears.get(capYears.size()-1).getSchoolYear());
			
		if(schoolYear!=0)
			q.setParameter("toSchoolYear", schoolYear);
		else
			q.setParameter("toSchoolYear", capYears.get(0).getSchoolYear());
		
		
		List<Object[]> infoList = q.getResultList();
		
		for (Object[] info : infoList) {
			CycleReportInfo cycleReportInfo = new CycleReportInfo();
			cycleReportInfo.setOrgName((String) info[0]);
			cycleReportInfo.setCycleId(((BigDecimal) info[1]).longValue());
			cycleReportInfo.setPracticumDistrict((String) info[2]);
			cycleReportInfo.setPracticumSchoolName((String) info[3]);
			cycleReportInfo.setSchoolYear(info[4]!=null?((BigDecimal) info[4]).longValue():null);
			cycleReportInfo.setProgramDescription((String) info[5]);
			cycleReportInfo.setCandidateMEPID (((BigDecimal) info[6]).longValue());
			cycleReportInfo.setCandidateFirstName((String) info[7]);
			cycleReportInfo.setCandidateLastName((String) info[8]);
			cycleReportInfo.setCandidateEmailAddress((String) info[9]);
			cycleReportInfo.setSpName((String) info[12]);
			cycleReportInfo.setSpEmailAddress((String) info[13]);
			cycleReportInfo.setPsName((String) info[10]);
			cycleReportInfo.setPsEmailAddress((String) info[11]);
			cycleReportInfo.setElementF1A4Q((String) info[14]);
			cycleReportInfo.setElementF1A4S((String) info[15]);
			cycleReportInfo.setElementF1A4C((String) info[16]);
			cycleReportInfo.setElementF1B2Q((String) info[17]);
			cycleReportInfo.setElementF1B2S((String) info[18]);
			cycleReportInfo.setElementF1B2C((String) info[19]);
			cycleReportInfo.setElementF2A3Q((String) info[20]);
			cycleReportInfo.setElementF2A3S((String) info[21]);
			cycleReportInfo.setElementF2A3C((String) info[22]);
			cycleReportInfo.setElementF2B1Q((String) info[23]);
			cycleReportInfo.setElementF2B1S((String) info[24]);
			cycleReportInfo.setElementF2B1C((String) info[25]);
			cycleReportInfo.setElementF2D2Q((String) info[26]);
			cycleReportInfo.setElementF2D2S((String) info[27]);
			cycleReportInfo.setElementF2D2C((String) info[28]);
			cycleReportInfo.setElementF4A1Q((String) info[29]);
			cycleReportInfo.setElementF4A1S((String) info[30]);
			cycleReportInfo.setElementF4A1C((String) info[31]);
			cycleReportInfo.setElementS1A4Q((String) info[32]);
			cycleReportInfo.setElementS1A4S((String) info[33]);
			cycleReportInfo.setElementS1A4C((String) info[34]);
			cycleReportInfo.setElementS1B2Q((String) info[35]);
			cycleReportInfo.setElementS1B2S((String) info[36]);
			cycleReportInfo.setElementS1B2C((String) info[37]);
			cycleReportInfo.setElementS2A3Q((String) info[38]);
			cycleReportInfo.setElementS2A3S((String) info[39]);
			cycleReportInfo.setElementS2A3C((String) info[40]);
			cycleReportInfo.setElementS2B1Q((String) info[41]);
			cycleReportInfo.setElementS2B1S((String) info[42]);
			cycleReportInfo.setElementS2B1C((String) info[43]);
			cycleReportInfo.setElementS2D2Q((String) info[44]);
			cycleReportInfo.setElementS2D2S((String) info[45]);
			cycleReportInfo.setElementS2D2C((String) info[46]);
			cycleReportInfo.setElementS4A1Q((String) info[47]);
			cycleReportInfo.setElementS4A1S((String) info[48]);
			cycleReportInfo.setElementS4A1C((String) info[49]);
			cycleReportInfo.setReadyToTeach((String) info[50]);
			cycleReportInfo.setDataSource((String) info[51]);
			cycleReportInfo.setSpMEPID(info[52]!=null?((BigDecimal) info[52]).longValue():null);			
			capReportList.add(cycleReportInfo);
		}
		
		return capReportList;
	}
}
