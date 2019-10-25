package edu.mass.doe.cap.dataservice.candidate;

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
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import edu.mass.doe.EOEAuthorization.DirAdminAccessUtil;
import edu.mass.doe.EOEAuthorization.EOEAdminUserInfo;
import edu.mass.doe.cap.dataservice.assessment.AssessmentRepository;
import edu.mass.doe.cap.dataservice.pojo.CandidateInfo;
import edu.mass.doe.cap.dataservice.pojo.CapCycleInfo;
import edu.mass.doe.cap.dataservice.pojo.CapManagerInfo;
import edu.mass.doe.cap.dataservice.pojo.CapYear;
import edu.mass.doe.cap.dataservice.pojo.ProgramInfo;
import edu.mass.doe.cap.dataservice.pojo.SponsoringOrg;
import edu.mass.doe.cap.dataservice.pojo.SupervisorInfo;
import edu.mass.doe.cap.model.util.CAPUtil;
import edu.mass.doe.cap.util.MapValue;
import edu.mass.doe.cap.dataservice.pojo.CapAdminViewInfo;

/**
 * The Class CandidateService.
 */
@Repository
public class CandidateService {

	private static Logger logger = LoggerFactory.getLogger(CandidateService.class);

	@PersistenceContext(unitName="capPeristanceUnit")
	EntityManager entityManager;
	
	@Autowired
	private CAPUtil capUtil;
	
	
	
	@Autowired
	private Environment env;
	
	/**
	 * Find candidate.
	 *
	 * @param pcomId the pcom id
	 * @return the candidate info
	 */
	public CandidateInfo findCandidate(Long pcomId){
		
		Query q = entityManager.createNativeQuery(env.getProperty("findCandidate"));
		q.setParameter("pcomId", pcomId);
		
		Object[] obj=   (Object[]) q.getSingleResult();

			CandidateInfo candidateInfo = new CandidateInfo();
			candidateInfo.setProgramCompleterId(((BigDecimal) obj[0]).longValue());
			candidateInfo.setCandidateName((String) obj[2]+", "+(String) obj[1]);
			candidateInfo.setLastName((String) obj[2]);
			candidateInfo.setFirstName((String) obj[1]);
			candidateInfo.setElarPersonId(((BigDecimal) obj[4]).longValue());
			candidateInfo.setProgramId(((BigDecimal) obj[3]).longValue());
			candidateInfo.setProgramName((String) obj[7]);						
			candidateInfo.setMepid(((BigDecimal) obj[5]).longValue());
			candidateInfo.setOrgId(((BigDecimal) obj[8]).longValue());
			candidateInfo.setOrgCode((String) obj[9]);
			candidateInfo.setOrgName((String) obj[10]);

			return candidateInfo;
		
	}
	
	/**
	 * Gets the da person id.
	 *
	 * @param elarPersonId the elar person id
	 * @return the da person id
	 */
	public Long getDaPersonId(Long elarPersonId){
		Query q = entityManager.createNativeQuery(env.getProperty("daPersonId.from.elarPeronId"));
		q.setParameter("elarPeronId", elarPersonId);
		Long dapersonId=  ( (BigDecimal) q.getSingleResult()).longValue();
		
		return dapersonId;
		
	}
	
	
	/**
	 * Findpractitioner.
	 *
	 * @param personId the person id
	 * @param orgId the org id
	 * @return the supervisor info
	 */
	public SupervisorInfo findpractitioner(Long personId, Long orgId) {

		Query q = entityManager.createNativeQuery(env.getProperty("findPractitioner"));
		q.setParameter("personId", personId);
		q.setParameter("orgId", orgId);

		Object[] obj = (Object[]) q.getSingleResult();

		SupervisorInfo supervisorInfo = new SupervisorInfo();

		supervisorInfo.setDaPersonId(personId);
		supervisorInfo.setFirstName((String) obj[0]);
		supervisorInfo.setLastName((String) obj[1]);
		supervisorInfo.setMepid(((BigDecimal) obj[2]).longValue());
		supervisorInfo.setDistrictName((String) obj[3]);
		supervisorInfo.setDistrictOrgId(((BigDecimal) obj[4]).longValue());
		supervisorInfo.setDistrictOrgTypeId(new Long((String) obj[5]));
		supervisorInfo.setSchoolOrgId(((BigDecimal) obj[6]).longValue());
		supervisorInfo.setSchoolName((String) obj[7]);

		return supervisorInfo;

	}
	
	
	/**
	 * Find supervisor.
	 *
	 * @param personId the person id
	 * @return the supervisor info
	 * @throws Exception the exception
	 */
	public SupervisorInfo findSupervisor(Long personId) throws Exception {

		SupervisorInfo supervisorInfo = new SupervisorInfo();

		EOEAdminUserInfo eoeAdminUserInfo = DirAdminAccessUtil.getPerson(personId.intValue());

		supervisorInfo.setDaPersonId(personId);
		supervisorInfo.setFirstName(eoeAdminUserInfo.getFirstName());
		supervisorInfo.setLastName(eoeAdminUserInfo.getLastName());
		supervisorInfo.setUserID(eoeAdminUserInfo.getUserID());
		return supervisorInfo;

	}
	
	
	/**
	 * Find Cap manger.
	 *
	 * @param personId the person id
	 * @return the supervisor info
	 * @throws Exception the exception
	 */
	public List<CapManagerInfo> findCapManagers(Long orgId) throws Exception {

		
		Query q = entityManager.createNativeQuery(env.getProperty("findCapManagerByOrgid"));
		q.setParameter("orgId", orgId);
		List<Object[]>	results= q.getResultList();
		List<CapManagerInfo>	managers = new ArrayList<CapManagerInfo>();
		
		for(Object[] result:results){
			CapManagerInfo capManagerInfo= new CapManagerInfo();
			capManagerInfo.setPersonId(((BigDecimal)result[1]).longValue());
			capManagerInfo.setEmailId((String)result[8]);
			managers.add(capManagerInfo);
		}
		return managers;

	}
	
	

	/**
	 * Find program by org id.
	 *
	 * @param orgId the org id
	 * @return the list
	 */
	public List<ProgramInfo> findProgramByOrgId(Long orgId) {

	
		Query q = entityManager.createNativeQuery(env.getProperty("programByOrgId"));
		q.setParameter("orgId", orgId);
		List<Object[]> programs = q.getResultList();
		List<ProgramInfo> programList = new ArrayList<ProgramInfo>();
		for (Object[] program : programs) {
			ProgramInfo programInfo = new ProgramInfo();

			programInfo.setOrgId(((BigDecimal) program[0]).longValue());
			programInfo.setOrgCode((String) program[1]);
			programInfo.setOrgName(((String) program[2]));
			programInfo.setProgramId(((BigDecimal) program[3]).longValue());
			programInfo.setProgramDesc(((String) program[4]));
			programList.add(programInfo);
		}

		return programList;
	}

	/**
	 * Find all candidates by program id.
	 *
	 * @param programId the program id
	 * @param orgCode the org code
	 * @return the list
	 */
	public List<CandidateInfo> findAllCandidatesByProgramId(Long programId,String orgCode) {
		
		Query q = entityManager.createNativeQuery(env.getProperty("candidatesByProgramId"));
		q.setParameter("programId", programId);
		q.setParameter("orgCode", orgCode);
		
		List<Object[]> candidates = q.getResultList();
		List<CandidateInfo> candidateList = new ArrayList<CandidateInfo>();

		for (Object[] candidate : candidates) {

			CandidateInfo candidateInfo = new CandidateInfo();
			candidateInfo.setProgramCompleterId(((BigDecimal) candidate[4]).longValue());
			candidateInfo.setElarPersonId(((BigDecimal) candidate[5]).longValue());
			candidateInfo.setDaPersonId(((BigDecimal) candidate[6]).longValue());
			candidateInfo.setProgramId(((BigDecimal) candidate[12]).longValue());
			candidateInfo.setProgramName((String) candidate[3]);
			candidateInfo.setProgramStatusCode((String) candidate[13]);
			candidateInfo.setProgramStatus((String) candidate[14]);
			candidateInfo.setCandidateName((String) candidate[7]+", "+(String) candidate[9]);
			candidateInfo.setMepid(((BigDecimal) candidate[11]).longValue());
			candidateInfo.setCycleId( ((BigDecimal) candidate[17]).longValue() );
			candidateInfo.setFileUploadId( ((BigDecimal) candidate[18]).longValue() );

			candidateList.add(candidateInfo);

		}

		return candidateList;
	}

	
	
	
	/**
	 * Find all candidates by MEPID.
	 *
	 * @param mepid the mepid
	 * @param orgCode the org code
	 * @return the list
	 */
	public List<CandidateInfo> findAllCandidatesByMEPID(Long mepid,String orgCode) {
			
		Query q = entityManager.createNativeQuery(env.getProperty("candidatesByMEPID"));
		q.setParameter("mepid", mepid);
		q.setParameter("orgCode", orgCode);

		List<Object[]> candidates = q.getResultList();
		List<CandidateInfo> candidateList = new ArrayList<CandidateInfo>();

		for (Object[] candidate : candidates) {

			CandidateInfo candidateInfo = new CandidateInfo();
			candidateInfo.setProgramCompleterId(((BigDecimal) candidate[0]).longValue());
			candidateInfo.setElarPersonId(((BigDecimal) candidate[1]).longValue());
			candidateInfo.setDaPersonId(((BigDecimal) candidate[2]).longValue());
			candidateInfo.setMepid(((BigDecimal) candidate[3]).longValue());
			candidateInfo.setProgramId(((BigDecimal) candidate[4]).longValue());
			candidateInfo.setProgramName((String) candidate[5]);
			candidateInfo.setProgramStatusCode((String) candidate[6]);
			candidateInfo.setProgramStatus((String) candidate[7]);
			candidateInfo.setCandidateName((String) candidate[8]);
			candidateInfo.setCycleId( ((BigDecimal) candidate[9]).longValue()    );
			candidateInfo.setFileUploadId( ((BigDecimal) candidate[10]).longValue()    );

			candidateList.add(candidateInfo);

		}

		return candidateList;
		
	}
	
	/**
	 * Find distinct school year cap cycles.
	 *
	 * @param orgCode the org code
	 * @return the list
	 */
	public List<Integer> findDistinctSchoolYearCapCycles(String orgCode) {
		List<Integer> years = new ArrayList<Integer>();
		Query q = entityManager.createNativeQuery(env.getProperty("capCycles.schoolYears"));
		q.setParameter("orgCode", orgCode);
		List<BigDecimal> infoList = q.getResultList();
		for (BigDecimal info : infoList) {
			years.add(info.intValue());
		}
		return years;
		
	}
	
	/**
	 * Find distinct school year cap cycles.
	 *
	 * @return the list
	 */
	public List<Integer> findDistinctSchoolYearCapCycles() {
		List<Integer> years = new ArrayList<Integer>();
		Query q = entityManager.createNativeQuery(env.getProperty("capCycles.schoolYears.landing"));
		List<BigDecimal> infoList = q.getResultList();
		for (BigDecimal info : infoList) {
			years.add(info.intValue());
		}
		return years;
		
	}
	
	
	/**
	 * Find all cap cycles.
	 *
	 * @param orgCode the org code
	 * @param schoolYear the school year
	 * @param statusCode the status code
	 * @return the list
	 */
	public List<CapCycleInfo> findAllCapCycles(String orgCode,int schoolYear,String statusCode) {
		
		List<CapCycleInfo> capCycles = new ArrayList<CapCycleInfo>();

		Query q = entityManager.createNativeQuery(env.getProperty("capCycles"));
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
		
		
		q.setParameter("statusCode", statusCode);
		q.setParameter("allStatus", statusCode!=null?statusCode:'0');
		
		
		List<Object[]> cycles = q.getResultList();
		
		for (Object[] cycle : cycles) {
			CapCycleInfo capCycleInfo= new CapCycleInfo();
			capCycleInfo.setCycleId(((BigDecimal) cycle[0]).longValue());
			capCycleInfo.setCandidateName((String) cycle[1]);
			capCycleInfo.setProgramName((String) cycle[2]);
			capCycleInfo.setSupervisorName((String) cycle[3]);
			capCycleInfo.setStartDate((Date) cycle[5]);
			
			SupervisorInfo practitioner = new SupervisorInfo();
			
			practitioner.setName((String) cycle[4]);
			practitioner.setSchoolName((String) cycle[6]);
		
			
			capCycleInfo.setPractitioner(practitioner);
			capCycleInfo.setCycleStatus((String) cycle[7]);
			
			
			
			capCycles.add(capCycleInfo);
		}
		
		return capCycles;
	}
	
	/**
	 * Find distinct school year ps cap cycles.
	 *
	 * @param orgCode the org code
	 * @param personId the person id
	 * @return the list
	 */
	public List<Integer> findDistinctSchoolYearPsCapCycles(String orgCode,Long personId) {
		List<Integer> years = new ArrayList<Integer>();
		Query q = entityManager.createNativeQuery(env.getProperty("psCapCycles.schoolYears"));
		q.setParameter("orgCode", orgCode);
		q.setParameter("personId", personId);
		List<BigDecimal> infoList = q.getResultList();
		for (BigDecimal info : infoList) {
			years.add(info.intValue());
		}
		return years;
		
	}
	
	/**
	 * Find all ps cap cycles.
	 *
	 * @param orgCode the org code
	 * @param personId the person id
	 * @param schoolYear the school year
	 * @param statusCode the status code
	 * @return the list
	 */
	public List<CapCycleInfo> findAllPsCapCycles(String orgCode, Long personId,int schoolYear,String statusCode) {
		
		List<CapCycleInfo> capCycles = new ArrayList<CapCycleInfo>();

		Query q = entityManager.createNativeQuery(env.getProperty("psCapCycles"));
		q.setParameter("orgCode", orgCode);
		q.setParameter("personId", personId);
		List<CapYear> capYears=capUtil.getSchoolYears();
		
		if(schoolYear!=0)
			q.setParameter("fromSchoolYear", schoolYear);
		else
			q.setParameter("fromSchoolYear", capYears.get(capYears.size()-1).getSchoolYear());
			
		if(schoolYear!=0)
			q.setParameter("toSchoolYear", schoolYear);
		else
			q.setParameter("toSchoolYear", capYears.get(0).getSchoolYear());
		
		q.setParameter("statusCode", statusCode);
		q.setParameter("allStatus", statusCode!=null?statusCode:'0');
		
		List<Object[]> cycles = q.getResultList();
		
		for (Object[] cycle : cycles) {
			CapCycleInfo capCycleInfo= new CapCycleInfo();
			capCycleInfo.setCycleId(((BigDecimal) cycle[0]).longValue());
			capCycleInfo.setCandidatePersonId(((BigDecimal) cycle[1]).longValue());		
			
			capCycleInfo.setCandidateFirstName((String) cycle[2]);
			capCycleInfo.setCandidateLastName((String) cycle[4]);
			capCycleInfo.setProgramName((String) cycle[5]);
			
			if(cycle[6]!=null)
				capCycleInfo.setSupervisorPersonId(((BigDecimal) cycle[6]).longValue());
				
			if(cycle[7]!=null)
				capCycleInfo.setSupervisorFirstName((String) cycle[7]);
			
			if(cycle[9]!=null)
				capCycleInfo.setSupervisorLastName((String) cycle[9]);
			
			SupervisorInfo practitioner = new SupervisorInfo();
			
			if(cycle[10]!=null)
				practitioner.setSpPersonIdString((String) cycle[10]);
			
			if(cycle[11]!=null)
				practitioner.setSpName((String) cycle[11]);
			
			capCycleInfo.setStartDate((Date) cycle[12]);
			
			capCycleInfo.setSelfAssessment((String) cycle[13]);
			
			capCycleInfo.setAnnouncedObs1((String) cycle[14]);
			
			capCycleInfo.setGoalPlan((String) cycle[15]);
			
			capCycleInfo.setUnAnnouncedObs1((String) cycle[16]);
			
			capCycleInfo.setAnnouncedObs2((String) cycle[17]);
			
			capCycleInfo.setFormativeAssessment((String) cycle[18]);
			
			capCycleInfo.setUnAnnouncedObs2((String) cycle[19]);
			
			capCycleInfo.setSummativeAssessment((String) cycle[20]);
			
			//capCycleInfo.setLatestActDate((Date) cycle[21]);
			
			capCycleInfo.setCycleStatus((String) cycle[25]);
			
			capCycleInfo.setPracticumSchoolName((String) cycle[26]);
			
			capCycleInfo.setPractitioner(practitioner);
						
			capCycles.add(capCycleInfo);
		}
		
		return capCycles;
	}
	
	/**
	 * Find distinct school year sp cap cycles.
	 *
	 * @param orgCode the org code
	 * @param personId the person id
	 * @return the list
	 */
	public List<Integer> findDistinctSchoolYearSpCapCycles(String orgCode,Long personId) {
		List<Integer> years = new ArrayList<Integer>();
		Query q = entityManager.createNativeQuery(env.getProperty("spCapCycles.schoolYears"));
		q.setParameter("orgCode", orgCode);
		q.setParameter("personId", personId);
		List<BigDecimal> infoList = q.getResultList();
		for (BigDecimal info : infoList) {
			years.add(info.intValue());
		}
		return years;
		
	}
	
	/**
	 * Find all sp cap cycles.
	 *
	 * @param orgCode the org code
	 * @param personId the person id
	 * @param schoolYear the school year
	 * @param statusCode the status code
	 * @return the list
	 */
	public List<CapCycleInfo> findAllSpCapCycles(String orgCode, Long personId,int schoolYear,String statusCode) {
		
		List<CapCycleInfo> capCycles = new ArrayList<CapCycleInfo>();

		Query q = entityManager.createNativeQuery(env.getProperty("spCapCycles"));
		q.setParameter("orgCode", orgCode);
		q.setParameter("personId", personId);
		List<CapYear> capYears=capUtil.getSchoolYears();
		
		if(schoolYear!=0)
			q.setParameter("fromSchoolYear", schoolYear);
		else
			q.setParameter("fromSchoolYear", capYears.get(capYears.size()-1).getSchoolYear());
			
		if(schoolYear!=0)
			q.setParameter("toSchoolYear", schoolYear);
		else
			q.setParameter("toSchoolYear", capYears.get(0).getSchoolYear());
		
		q.setParameter("statusCode", statusCode);
		q.setParameter("allStatus", statusCode!=null?statusCode:'0');
		
		List<Object[]> cycles = q.getResultList();
		
		for (Object[] cycle : cycles) {
			CapCycleInfo capCycleInfo= new CapCycleInfo();
			capCycleInfo.setCycleId(((BigDecimal) cycle[0]).longValue());
			capCycleInfo.setCandidatePersonId(((BigDecimal) cycle[1]).longValue());		
			
			capCycleInfo.setCandidateFirstName((String) cycle[2]);
			capCycleInfo.setCandidateLastName((String) cycle[4]);
			capCycleInfo.setProgramName((String) cycle[5]);
			
			if(cycle[6]!=null)
				capCycleInfo.setSupervisorPersonId(((BigDecimal) cycle[6]).longValue());
				
			if(cycle[7]!=null)
				capCycleInfo.setSupervisorFirstName((String) cycle[7]);
			
			if(cycle[9]!=null)
				capCycleInfo.setSupervisorLastName((String) cycle[9]);
			
			SupervisorInfo practitioner = new SupervisorInfo();
			
			//Note that for SP this field is a number but for PS
			if(cycle[10]!=null)	
				practitioner.setSpPersonIdString(String.valueOf(((BigDecimal) cycle[10]).longValue()));	
			
			if(cycle[11]!=null)
				practitioner.setSpName((String) cycle[11]);
			
			capCycleInfo.setStartDate((Date) cycle[12]);
			
			capCycleInfo.setSelfAssessment((String) cycle[13]);
			
			capCycleInfo.setAnnouncedObs1((String) cycle[14]);
			
			capCycleInfo.setGoalPlan((String) cycle[15]);
			
			capCycleInfo.setUnAnnouncedObs1((String) cycle[16]);
			
			capCycleInfo.setAnnouncedObs2((String) cycle[17]);
			
			capCycleInfo.setFormativeAssessment((String) cycle[18]);
			
			capCycleInfo.setUnAnnouncedObs2((String) cycle[19]);
			
			capCycleInfo.setSummativeAssessment((String) cycle[20]);
			
			//capCycleInfo.setLatestActDate((Date) cycle[21]);
			
			capCycleInfo.setCycleStatus((String) cycle[24]);
			
			capCycleInfo.setPractitioner(practitioner);
						
			capCycles.add(capCycleInfo);
		}
		
		return capCycles;
	}
	
	/**
	 * Find distinct school year tc cap cycles.
	 *
	 * @param personId the person id
	 * @return the list
	 */
	public List<Integer> findDistinctSchoolYearTcCapCycles(Long personId) {
		List<Integer> years = new ArrayList<Integer>();
		Query q = entityManager.createNativeQuery(env.getProperty("tcCapCycles.schoolYears"));
		q.setParameter("personId", personId);
		List<BigDecimal> infoList = q.getResultList();
		for (BigDecimal info : infoList) {
			years.add(info.intValue());
		}
		return years;
		
	}
	
	/**
	 * Find all tc cap cycles.
	 *
	 * @param orgCode the org code
	 * @param personId the person id
	 * @param schoolYear the school year
	 * @param statusCode the status code
	 * @return the list
	 */
	public List<CapCycleInfo> findAllTcCapCycles(String orgCode, Long personId,int schoolYear,String statusCode) {
		
		List<CapCycleInfo> capCycles = new ArrayList<CapCycleInfo>();

		Query q = entityManager.createNativeQuery(env.getProperty("tcCapCycles"));
		q.setParameter("personId", personId);
		List<CapYear> capYears=capUtil.getSchoolYears();
		
		if(schoolYear!=0)
			q.setParameter("fromSchoolYear", schoolYear);
		else
			q.setParameter("fromSchoolYear", capYears.get(capYears.size()-1).getSchoolYear());
			
		if(schoolYear!=0)
			q.setParameter("toSchoolYear", schoolYear);
		else
			q.setParameter("toSchoolYear", capYears.get(0).getSchoolYear());
		q.setParameter("statusCode", statusCode);
		q.setParameter("allStatus", statusCode!=null?statusCode:'0');
		
		List<Object[]> cycles = q.getResultList();
		
		for (Object[] cycle : cycles) {
			CapCycleInfo capCycleInfo= new CapCycleInfo();
			capCycleInfo.setCycleId(((BigDecimal) cycle[0]).longValue());
			capCycleInfo.setCandidatePersonId(((BigDecimal) cycle[1]).longValue());		
			
			capCycleInfo.setCandidateFirstName((String) cycle[2]);
			capCycleInfo.setCandidateLastName((String) cycle[4]);
			capCycleInfo.setProgramName((String) cycle[5]);
			
			if(cycle[6]!=null)
				capCycleInfo.setSupervisorPersonId(((BigDecimal) cycle[6]).longValue());
				
			if(cycle[7]!=null)
				capCycleInfo.setSupervisorFirstName((String) cycle[7]);
			
			if(cycle[9]!=null)
				capCycleInfo.setSupervisorLastName((String) cycle[9]);
			
			SupervisorInfo practitioner = new SupervisorInfo();
			
			/*if(cycle[10]!=null)
				practitioner.setDaPersonId(((BigDecimal) cycle[10]).longValue());*/
			
			if(cycle[11]!=null)
				practitioner.setSpName((String) cycle[11]);
			
			capCycleInfo.setStartDate((Date) cycle[12]);
			
			capCycleInfo.setSelfAssessment((String) cycle[13]);
			
			capCycleInfo.setAnnouncedObs1((String) cycle[14]);
			
			capCycleInfo.setGoalPlan((String) cycle[15]);
			
			capCycleInfo.setUnAnnouncedObs1((String) cycle[16]);
			
			capCycleInfo.setAnnouncedObs2((String) cycle[17]);
			
			capCycleInfo.setFormativeAssessment((String) cycle[18]);
			
			capCycleInfo.setUnAnnouncedObs2((String) cycle[19]);
			
			capCycleInfo.setSummativeAssessment((String) cycle[20]);
			
			//capCycleInfo.setLatestActDate((Date) cycle[21]);
			
			capCycleInfo.setCycleStatus((String) cycle[24]);
			
			capCycleInfo.setPractitioner(practitioner);
						
			capCycles.add(capCycleInfo);
		}
		
		return capCycles;
	}
	
	/**
	 * Find incomplete form.
	 *
	 * @param cycleId the cycle id
	 * @return the string
	 */
	public String findIncompleteForm(Long cycleId) {
		String incompleteWorksString = null;
		
		Query q = entityManager.createNativeQuery(env.getProperty("incompleteForm"));		
		q.setParameter("cycleId", cycleId);
		
		Object[] incompleteWorksRecord = (Object[]) q.getSingleResult();
		incompleteWorksString = (String) incompleteWorksRecord[1];
		
		return incompleteWorksString;
	}
	
	/**
	 * Find all cap admin info.
	 *
	 * @param schoolYear the school year
	 * @return the list
	 */
	public List<CapAdminViewInfo> findAllCapAdminInfo(Integer schoolYear) {
		
		List<CapAdminViewInfo> capInfoList = new ArrayList<CapAdminViewInfo>();

		Query q = entityManager.createNativeQuery(env.getProperty("capAdminView"));
		
		q.setParameter("capYear", schoolYear);
		
		List<Object[]> infoList = q.getResultList();
		
		for (Object[] info : infoList) {
			CapAdminViewInfo capAdminViewInfo = new CapAdminViewInfo();
			capAdminViewInfo.setOrgCode((String) info[0]);
			capAdminViewInfo.setOrgName((String) info[1]);
			capAdminViewInfo.setSchoolYear(info[2]!=null?((BigDecimal) info[2]).longValue():null);
			capAdminViewInfo.setTotalCycles(((BigDecimal) info[3]).longValue());
			capAdminViewInfo.setTotalOpenCycles(((BigDecimal) info[4]).longValue());
			capAdminViewInfo.setTotalClosedCycles(((BigDecimal) info[5]).longValue());
			capAdminViewInfo.setTotalCandidates(((BigDecimal) info[6]).longValue());
			capAdminViewInfo.setTotalProgramSupervisors(((BigDecimal) info[7]).longValue());
			capAdminViewInfo.setLatestActDate((Date) info[8]);
				
			capInfoList.add(capAdminViewInfo);
		}
		
		return capInfoList;
	}
	
	/**
	 * Find all cap admin cap cycles.
	 *
	 * @param orgCode the org code
	 * @param schoolYear the school year
	 * @param statusCode the status code
	 * @return the list
	 */
	public List<CapCycleInfo> findAllCapAdminCapCycles(String orgCode,int schoolYear,String statusCode) {
		
		List<CapCycleInfo> capCycles = new ArrayList<CapCycleInfo>();
		List<CapYear> capYears=capUtil.getSchoolYears();
		Query q = entityManager.createNativeQuery(env.getProperty("capCyclesByOrgCode"));
		q.setParameter("orgCode", orgCode);
		if(schoolYear!=0)
			q.setParameter("fromSchoolYear", schoolYear);
		else
			q.setParameter("fromSchoolYear", capYears.get(capYears.size()-1).getSchoolYear());
			
		if(schoolYear!=0)
			q.setParameter("toSchoolYear", schoolYear);
		else
			q.setParameter("toSchoolYear", capYears.get(0).getSchoolYear());
		q.setParameter("statusCode", statusCode);
		q.setParameter("allStatus", statusCode!=null?statusCode:'0');
		
		List<Object[]> cycles = q.getResultList();
		
		for (Object[] cycle : cycles) {
			CapCycleInfo capCycleInfo= new CapCycleInfo();
			capCycleInfo.setCycleId(((BigDecimal) cycle[0]).longValue());
			capCycleInfo.setCandidatePersonId(((BigDecimal) cycle[1]).longValue());		
			
			capCycleInfo.setCandidateFirstName((String) cycle[2]);
			capCycleInfo.setCandidateLastName((String) cycle[4]);
			capCycleInfo.setProgramName((String) cycle[5]);
			
			if(cycle[6]!=null)
				capCycleInfo.setSupervisorPersonId(((BigDecimal) cycle[6]).longValue());
				
			if(cycle[7]!=null)
				capCycleInfo.setSupervisorFirstName((String) cycle[7]);
			
			if(cycle[9]!=null)
				capCycleInfo.setSupervisorLastName((String) cycle[9]);
			
			SupervisorInfo practitioner = new SupervisorInfo();
			
			if(cycle[10]!=null)
				practitioner.setSpPersonIdString((String) cycle[10]);
			
			if(cycle[11]!=null)
				practitioner.setSpName((String) cycle[11]);
			
			capCycleInfo.setStartDate((Date) cycle[12]);
			
			capCycleInfo.setSelfAssessment((String) cycle[13]);
			
			capCycleInfo.setAnnouncedObs1((String) cycle[14]);
			
			capCycleInfo.setGoalPlan((String) cycle[15]);
			
			capCycleInfo.setUnAnnouncedObs1((String) cycle[16]);
			
			capCycleInfo.setAnnouncedObs2((String) cycle[17]);
			
			capCycleInfo.setFormativeAssessment((String) cycle[18]);
			
			capCycleInfo.setUnAnnouncedObs2((String) cycle[19]);
			
			capCycleInfo.setSummativeAssessment((String) cycle[20]);
			
			capCycleInfo.setLatestActDate((Date) cycle[21]);
			
			capCycleInfo.setCycleStatus((String) cycle[25]);
			
			capCycleInfo.setPracticumSchoolName((String) cycle[26]);
			
			capCycleInfo.setPractitioner(practitioner);
						
			capCycles.add(capCycleInfo);
		}
		
		return capCycles;
	}



/**
 * Find CAP completion status.
 *
 * @param cycleId the cycle id
 * @return true, if successful
 */
public boolean findCAPCompletionStatus(long cycleId) {
	
	boolean result=false;
	
	List<Integer> capInfoList = new ArrayList<Integer>();

	Query q = entityManager.createNativeQuery(env.getProperty("cap.completion"));
	q.setParameter("cycleId", cycleId);
	
	List<BigDecimal> infoList = q.getResultList();
	
	int sum=0;
	
	for (BigDecimal info: infoList) {
		sum=sum+info.intValue();
	}
	
	if(sum >3)result=true;
	
	return result;
}

	/**
	 * Find All the Sponsoring Organization
	 * 
	 * @return
	 */
	@Cacheable("sponsoringOrgs")
	public List<MapValue<Long, String>>  findAllSponsoringOrgs() {
		
		boolean result=false;
		
		List<MapValue<Long, String>> soMapList = new ArrayList<MapValue<Long, String>>(); 
		Query q = entityManager.createNativeQuery(env.getProperty("verifycycle.orgList"));
		
		List<Object[]> programs = q.getResultList();
		List<SponsoringOrg> soList = new ArrayList<SponsoringOrg>();
		for (Object[] program : programs) {
			
			MapValue<Long, String>  mapvalue = new MapValue<Long, String>(((BigDecimal) program[0]).longValue(), ((String) program[2]));
			soMapList.add(mapvalue);
		}
		
		return soMapList;
	}

	/**
	 * Find all candidates by program id for Enquiry Tool.
	 *
	 * @param programId the program id
	 * @param orgCode the org code
	 * @return the list
	 */
	public List<CandidateInfo> findAllEqtCandidatesByProgramId(Long programId,String orgCode) {
		
		Query q = entityManager.createNativeQuery(env.getProperty("eqtCandidatesByProgramId"));
		q.setParameter("programId", programId);
		q.setParameter("orgCode", orgCode);
		
		List<Object[]> candidates = q.getResultList();
		List<CandidateInfo> candidateList = new ArrayList<CandidateInfo>();

		for (Object[] candidate : candidates) {

			CandidateInfo candidateInfo = new CandidateInfo();
			
			
			candidateInfo.setOrgName((String) candidate[2]);
			candidateInfo.setYear((String) candidate[3]);
			candidateInfo.setProgramName((String) candidate[4]);
			candidateInfo.setProgramCompleterId(((BigDecimal) candidate[5]).longValue());
			candidateInfo.setElarPersonId(((BigDecimal) candidate[6]).longValue());
			candidateInfo.setDaPersonId(((BigDecimal) candidate[7]).longValue());
			candidateInfo.setFirstName((String)candidate[8]);
			candidateInfo.setLastName((String)candidate[10]);
			candidateInfo.setEmail((String)candidate[11]);
			candidateInfo.setMepid(((BigDecimal) candidate[12]).longValue());
			
			candidateInfo.setProgramId(((BigDecimal) candidate[13]).longValue());
			candidateInfo.setProgramStatusCode((String) candidate[14]);
			candidateInfo.setProgramStatus((String) candidate[15]);
			candidateList.add(candidateInfo);

		}

		return candidateList;
	}
	
	
	/**
	 * Find all candidates by MEPID.
	 *
	 * @param mepid the mepid
	 * @param orgCode the org code
	 * @return the list
	 */
	public List<CandidateInfo> findAllEqtCandidatesByMEPID(Long mepid,String orgCode) {
			
		Query q = entityManager.createNativeQuery(env.getProperty("eqtCandidatesByMEPID"));
		q.setParameter("mepid", mepid);
		q.setParameter("orgCode", orgCode);

		List<Object[]> candidates = q.getResultList();
		List<CandidateInfo> candidateList = new ArrayList<CandidateInfo>();

		for (Object[] candidate : candidates) {

			CandidateInfo candidateInfo = new CandidateInfo();
			
			candidateInfo.setOrgName((String) candidate[2]);
			candidateInfo.setYear((String) candidate[3]);
			candidateInfo.setProgramName((String) candidate[4]);
			candidateInfo.setProgramCompleterId(((BigDecimal) candidate[5]).longValue());
			candidateInfo.setElarPersonId(((BigDecimal) candidate[6]).longValue());
			candidateInfo.setDaPersonId(((BigDecimal) candidate[7]).longValue());
			candidateInfo.setFirstName((String)candidate[8]);
			candidateInfo.setLastName((String)candidate[10]);
			candidateInfo.setEmail((String)candidate[11]);
			candidateInfo.setMepid(((BigDecimal) candidate[12]).longValue());
			
			candidateInfo.setProgramId(((BigDecimal) candidate[13]).longValue());
			candidateInfo.setProgramStatusCode((String) candidate[14]);
			candidateInfo.setProgramStatus((String) candidate[15]);
			candidateList.add(candidateInfo);

		}
		return candidateList;	
	}
}
