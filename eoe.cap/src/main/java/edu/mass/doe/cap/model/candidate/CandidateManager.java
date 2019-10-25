package edu.mass.doe.cap.model.candidate;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import edu.mass.doe.EOEAuthorization.DirAdminAccessUtil;
import edu.mass.doe.EOEAuthorization.EOEAdminOrgInfo;
import edu.mass.doe.EOEAuthorization.EOEAdminUserInfo;
import edu.mass.doe.EOEAuthorization.exception.NesterException;
import edu.mass.doe.EOEAuthorization.exception.NoOrganizationFoundException;
import edu.mass.doe.EOEAuthorization.exception.NoPersonFoundException;
import edu.mass.doe.cap.dataservice.candidate.AssignmentService;
import edu.mass.doe.cap.dataservice.candidate.CandidateService;
import edu.mass.doe.cap.dataservice.candidate.PracticumTypeService;
import edu.mass.doe.cap.dataservice.entity.PracticumType;

import edu.mass.doe.cap.dataservice.candidate.StatusReasonTypeService;
import edu.mass.doe.cap.dataservice.entity.StatusReasonType;

import edu.mass.doe.cap.dataservice.pojo.CandidateInfo;
import edu.mass.doe.cap.dataservice.pojo.CapCycleInfo;
import edu.mass.doe.cap.dataservice.pojo.CapManagerInfo;
import edu.mass.doe.cap.dataservice.pojo.ProgramInfo;
import edu.mass.doe.cap.dataservice.pojo.SponsoringOrg;
import edu.mass.doe.cap.dataservice.pojo.SupervisorInfo;
import edu.mass.doe.cap.dataservice.util.CapService;
import edu.mass.doe.cap.util.CapUtil;
import edu.mass.doe.cap.util.MapValue;
import edu.mass.doe.cap.dataservice.pojo.CapAdminViewInfo;

/**
 * The Class CandidateManager.
 */
@Component
public class CandidateManager {

	@Autowired(required = true)
	private CandidateService candidateService;
	
	
	@Autowired(required = true)
	private AssignmentService assignmentService;
	
	@Autowired(required = true)
	private CapService capService;

		
	@Autowired(required = true)
	private PracticumTypeService practicumTypeService;
	
	@Autowired(required = true)
	private StatusReasonTypeService statusReasonTypeService;
	
	@Autowired
	private Environment env;

	private static Logger logger = LoggerFactory.getLogger(CandidateManager.class);

	/**
	 * Gets the candiate.
	 *
	 * @param pcomId the pcom id
	 * @return the candiate
	 */
	public CandidateInfo getCandiate(Long pcomId) {
		return candidateService.findCandidate(pcomId);
	}

	/**
	 * Fetch program by org id.
	 *
	 * @param orgId the org id
	 * @return the list
	 */
	public List<ProgramInfo> fetchProgramByOrgId(Long orgId) {
		return candidateService.findProgramByOrgId(orgId);
	}

	/**
	 * Fetch all candidate by program id .
	 *
	 * @param programId the program id
	 * @param orgId the org id
	 * @return the list
	 * @throws NesterException the nester exception
	 * @throws NoOrganizationFoundException the no organization found exception
	 */
	public List<CandidateInfo> fetchAllCandidateByProgramId(Long programId, Long orgId)
			throws NesterException, NoOrganizationFoundException {

		List<CandidateInfo> candidates = candidateService.findAllCandidatesByProgramId(programId,
				DirAdminAccessUtil.getOrgInfoByOrgId(orgId.intValue()).getOrgCode());

		return candidates;
	}
	
	
	/**
	 * Fetch all  candidate by program id for Eqt Screen.
	 *
	 * @param programId the program id
	 * @param orgId the org id
	 * @return the list
	 * @throws NesterException the nester exception
	 * @throws NoOrganizationFoundException the no organization found exception
	 */
	public List<CandidateInfo> fetchAllEqtCandidateByProgramId(Long programId, Long orgId)
			throws NesterException, NoOrganizationFoundException {

		List<CandidateInfo> candidates = candidateService.findAllEqtCandidatesByProgramId(programId,
				DirAdminAccessUtil.getOrgInfoByOrgId(orgId.intValue()).getOrgCode());

		return candidates;
	}
	
	
	/**
	 * Fetch All Sponsoring orgs
	 * @return
	 * @throws NesterException
	 * @throws NoOrganizationFoundException
	 */
	public List<MapValue<Long, String>>  findAllSponsoringOrgs()
			throws NesterException, NoOrganizationFoundException {

		List<MapValue<Long, String>>  sponsoringOrgs = candidateService.findAllSponsoringOrgs();

		return sponsoringOrgs;
	}
	
	

	/**
	 * Fetch all candidate by MEPID.
	 *
	 * @param mepid the mepid
	 * @param orgId the org id
	 * @return the list
	 * @throws NesterException the nester exception
	 * @throws NoOrganizationFoundException the no organization found exception
	 */
	public List<CandidateInfo> fetchAllCandidateByMEPID(Long mepid,Long orgId) throws NesterException, NoOrganizationFoundException {

		List<CandidateInfo> candidates = candidateService.findAllCandidatesByMEPID(mepid,DirAdminAccessUtil.getOrgInfoByOrgId(orgId.intValue()).getOrgCode());

		return candidates;
	}
	
	
	/**
	 * Fetch all candidate by MEPID.
	 *
	 * @param mepid the mepid
	 * @param orgId the org id
	 * @return the list
	 * @throws NesterException the nester exception
	 * @throws NoOrganizationFoundException the no organization found exception
	 */
	public List<CandidateInfo> fetchAllEqtCandidateByMEPID(Long mepid,Long orgId) throws NesterException, NoOrganizationFoundException {

		List<CandidateInfo> candidates = candidateService.findAllEqtCandidatesByMEPID(mepid,DirAdminAccessUtil.getOrgInfoByOrgId(orgId.intValue()).getOrgCode());

		return candidates;
	}

	

	/**
	 * Gets the org types.
	 *
	 * @return the org types
	 * @throws NesterException the nester exception
	 */
	public Map<String, String> getOrgTypes() throws NesterException {
		return CapUtil.sortByValue(DirAdminAccessUtil.getOrgTypes());
	}

	/**
	 * Gets the all orgs for org type.
	 *
	 * @param orgTypeId the org type id
	 * @return the all orgs for org type
	 * @throws NesterException the nester exception
	 */
	public Map<Long, String> getAllOrgsForOrgType(Long orgTypeId) throws NesterException {
		return CapUtil.sortByValue(DirAdminAccessUtil.getAllOrgsForOrgType(orgTypeId.toString()));
	}

	/**
	 * Gets the sub org for org.
	 *
	 * @param orgId the org id
	 * @return the sub org for org
	 * @throws NesterException the nester exception
	 * @throws NoOrganizationFoundException the no organization found exception
	 */
	public Map<Long, String> getSubOrgForOrg(Long orgId) throws NesterException, NoOrganizationFoundException {
		EOEAdminOrgInfo  eOEAdminOrgInfo =DirAdminAccessUtil.getOrgInfoByOrgId(orgId.intValue());
		Map<Long, String> schools=DirAdminAccessUtil.getSubOrgForOrg(new Long(eOEAdminOrgInfo.getOrgResourceId()));
		schools.put(eOEAdminOrgInfo.getOrgID(), eOEAdminOrgInfo.getOrgName());
		return CapUtil.sortByValue(schools);
	}

	/**
	 * Gets the personnel by org role.
	 *
	 * @param orgId the org id
	 * @param roleCode the role code
	 * @return the personnel by org role
	 * @throws NesterException the nester exception
	 */
	public List<SupervisorInfo> getPersonnelByOrgRole(Long orgId, String roleCode) throws NesterException {

		List<SupervisorInfo> supervisors = new ArrayList<SupervisorInfo>();

		for (EOEAdminUserInfo user : DirAdminAccessUtil.getPersonnelByOrgRole(orgId, roleCode)) {

			SupervisorInfo supervisorInfo = new SupervisorInfo();
			supervisorInfo.setDaPersonId(new Long(user.getDaPersonID()));
			supervisorInfo.setName(user.getName());
			supervisorInfo.setFirstName(user.getFirstName());
			supervisorInfo.setLastName(user.getLastName());
			supervisorInfo.setMiddleName(user.getMiddleName());
			supervisorInfo.setUserID(user.getUserID());
			supervisors.add(supervisorInfo);

		}

		return supervisors;
	}

	/**
	 * Fetchpractitioner by MEPID.
	 *
	 * @param mepid the mepid
	 * @param schoolOrgId the school org id
	 * @param districtOrgId the district org id
	 * @return the list
	 */
	public List<SupervisorInfo> fetchpractitionerByMEPID(Long mepid,Long schoolOrgId,Long districtOrgId) {
		return assignmentService.findpractitionerByMEPID(mepid,schoolOrgId,districtOrgId);
	}

		
	/**
	 * Fetch distinct school year cap cycles.
	 *
	 * @param orgCode the org code
	 * @return the list
	 * @throws NesterException the nester exception
	 */
	public List<Integer> fetchDistinctSchoolYearCapCycles(String orgCode)throws NesterException{
		return candidateService.findDistinctSchoolYearCapCycles(orgCode);
	}
	
	/**
	 * Fetch distinct school year cap cycles.
	 *
	 * @return the list
	 * @throws NesterException the nester exception
	 */
	public List<Integer> fetchDistinctSchoolYearCapCycles()throws NesterException{
		return candidateService.findDistinctSchoolYearCapCycles();
	}

	/**
	 * Fetch all cap cycles.
	 *
	 * @param orgId the org id
	 * @param schoolYear the school year
	 * @param statusCode the status code
	 * @return the list
	 * @throws NesterException the nester exception
	 * @throws NoOrganizationFoundException the no organization found exception
	 */
	public List<CapCycleInfo> fetchAllCapCycles(Long orgId,int schoolYear,String statusCode)throws NesterException, NoOrganizationFoundException {
		return candidateService.findAllCapCycles(DirAdminAccessUtil.getOrgInfoByOrgId(orgId.intValue()).getOrgCode(),schoolYear,statusCode);
	}
	
	/**
	 * Fetch distinct school year ps cap cycles.
	 *
	 * @param orgCode the org code
	 * @param personId the person id
	 * @return the list
	 * @throws NesterException the nester exception
	 */
	public List<Integer> fetchDistinctSchoolYearPsCapCycles(String orgCode, Long personId)throws NesterException{
		return candidateService.findDistinctSchoolYearPsCapCycles(orgCode,personId);
	}
	
	/**
	 * Fetch all ps cap cycles.
	 *
	 * @param orgCode the org code
	 * @param personId the person id
	 * @param schoolYear the school year
	 * @param statusCode the status code
	 * @return the list
	 * @throws NesterException the nester exception
	 * @throws NoOrganizationFoundException the no organization found exception
	 */
	public List<CapCycleInfo> fetchAllPsCapCycles(String orgCode, Long personId,int schoolYear,String statusCode) throws NesterException, NoOrganizationFoundException{
		return candidateService.findAllPsCapCycles(orgCode, personId,schoolYear,statusCode);
	}
	
	/**
	 * Fetch distinct school year sp cap cycles.
	 *
	 * @param orgCode the org code
	 * @param personId the person id
	 * @return the list
	 * @throws NesterException the nester exception
	 */
	public List<Integer> fetchDistinctSchoolYearSpCapCycles(String orgCode, Long personId)throws NesterException{
		return candidateService.findDistinctSchoolYearSpCapCycles(orgCode,personId);
	}
	
	/**
	 * Fetch all sp cap cycles.
	 *
	 * @param orgCode the org code
	 * @param personId the person id
	 * @param schoolYear the school year
	 * @param statusCode the status code
	 * @return the list
	 * @throws NesterException the nester exception
	 * @throws NoOrganizationFoundException the no organization found exception
	 */
	public List<CapCycleInfo> fetchAllSpCapCycles(String orgCode, Long personId,int schoolYear,String statusCode) throws NesterException, NoOrganizationFoundException {
		return candidateService.findAllSpCapCycles(orgCode, personId,schoolYear,statusCode);
	}
	
	/**
	 * Fetch distinct school year tc cap cycles.
	 *
	 * @param personId the person id
	 * @return the list
	 * @throws NesterException the nester exception
	 */
	public List<Integer> fetchDistinctSchoolYearTcCapCycles( Long personId)throws NesterException{
		return candidateService.findDistinctSchoolYearTcCapCycles(personId);
	}
	
	/**
	 * Fetch all tc cap cycles.
	 *
	 * @param orgCode the org code
	 * @param personId the person id
	 * @param schoolYear the school year
	 * @param statusCode the status code
	 * @return the list
	 * @throws NesterException the nester exception
	 * @throws NoOrganizationFoundException the no organization found exception
	 */
	public List<CapCycleInfo> fetchAllTcCapCycles(String orgCode, Long personId,int schoolYear,String statusCode) throws NesterException, NoOrganizationFoundException{
		return candidateService.findAllTcCapCycles(orgCode, personId,schoolYear,statusCode);
	}
	
	/**
	 * Fetch cap admin view.
	 *
	 * @param schoolYear the school year
	 * @return the list
	 * @throws NesterException the nester exception
	 */
	public List<CapAdminViewInfo> fetchCapAdminView(Integer schoolYear)throws NesterException{
		return candidateService.findAllCapAdminInfo(schoolYear);
	}
	
	/**
	 * Fetch all cap admin cap cycles.
	 *
	 * @param orgCode the org code
	 * @param schoolYear the school year
	 * @param statusCode the status code
	 * @return the list
	 * @throws NesterException the nester exception
	 * @throws NoOrganizationFoundException the no organization found exception
	 */
	public List<CapCycleInfo> fetchAllCapAdminCapCycles(String orgCode,int schoolYear,String statusCode)throws NesterException, NoOrganizationFoundException {
		return candidateService.findAllCapAdminCapCycles(orgCode,schoolYear,statusCode);
	}
	
	
	/**
	 * Gets the practitioner person id.
	 *
	 * @param mepid the mepid
	 * @param orgId the org id
	 * @return the practitioner person id
	 * @throws Exception the exception
	 */
	public Long getPractitionerPersonId(Long mepid, Long orgId) throws Exception {
		Long personId = null;

		List<String> userIds = capService.findUserIdBYMEPID(mepid);
		
		List<EOEAdminOrgInfo> orgs=new ArrayList<EOEAdminOrgInfo>();
		EOEAdminUserInfo userInfo=null;
		for (String userId : userIds) {
			//wrapping in try/catch block to avoid application crash due to incorrect/non existing id setups
			try{
			userInfo = DirAdminAccessUtil.getPerson(userId);
			orgs = userInfo.getOrgsForRole("724");
			}catch(Exception e ){
				continue;
			}
			
			if(orgs==null)
				continue;
			
			for (EOEAdminOrgInfo org : orgs) {
				if (org.getOrgID().equals(orgId)) {
					personId = new Long(userInfo.getDaPersonID());
					break;
				}
			}

		}
		return personId;

	}
	


	/**
	 * Gets the practitioner.
	 *
	 * @param personId the person id
	 * @param orgId the org id
	 * @return the practitioner
	 * @throws NesterException the nester exception
	 * @throws NoPersonFoundException the no person found exception
	 * @throws RemoteException the remote exception
	 */
	public SupervisorInfo getpractitioner(Long personId, Long orgId) throws NesterException, NoPersonFoundException, RemoteException {
		SupervisorInfo supervisorInfo = candidateService.findpractitioner(personId, orgId);
		supervisorInfo.setEmail(DirAdminAccessUtil.getUserEmail(DirAdminAccessUtil.getUserIDByDAPersonID(personId.intValue()), orgId));
		return supervisorInfo;

	}
	
	/**
	 * Gets the email.
	 *
	 * @param personId the person id
	 * @param orgId the org id
	 * @return the email
	 * @throws Exception the exception
	 */
	public String getEmail(Long personId, Long orgId)throws Exception{
		
		String email=null;
		
		if(personId!=null){
		String userId=DirAdminAccessUtil.getUserIDByDAPersonID(personId.intValue());
				if(userId!=null);
				email=	DirAdminAccessUtil.getUserEmail(userId, orgId);
		}
				return email;
	}

	/**
	 * Gets the supervisor.
	 *
	 * @param personId the person id
	 * @return the supervisor
	 * @throws Exception the exception
	 */
	public SupervisorInfo getSupervisor(Long personId) throws Exception {

		SupervisorInfo supervisorInfo = candidateService.findSupervisor(personId);

		return supervisorInfo;

	}

	

	/**
	 * Gets the manager.
	 *
	 * @param personId the person id
	 * @return the supervisor
	 * @throws Exception the exception
	 */
	public List<CapManagerInfo> getCapManagers(Long orgId) throws Exception {

		List<CapManagerInfo> managers = candidateService.findCapManagers(orgId);

		return managers;

	}
	
	/**
	 * Createpractitioner.
	 *
	 * @param supervisor the supervisor
	 * @param orgId the org id
	 * @param userId the user id
	 * @return the supervisor info
	 * @throws Exception the exception
	 */
	public SupervisorInfo createpractitioner(SupervisorInfo supervisor,Long orgId ,String userId) throws Exception {

		EOEAdminUserInfo eOEAdminUserInfo = new EOEAdminUserInfo();
		eOEAdminUserInfo.setFirstName(supervisor.getFirstName());
		eOEAdminUserInfo.setLastName(supervisor.getLastName());
		eOEAdminUserInfo.setMiddleName(supervisor.getMiddleName());
		eOEAdminUserInfo.setOrgID(orgId);
		eOEAdminUserInfo.setUserRoles(new String[] { "724" });
		eOEAdminUserInfo.setEmailAddress(supervisor.getEmail());
		eOEAdminUserInfo.setMepid(supervisor.getMepid());

		DirAdminAccessUtil.createPractitioner(eOEAdminUserInfo, userId, userId+" "+env.getProperty("DA.APPNAME"));
	//	DirAdminAccessUtil.createPractitioner(eOEAdminUserInfo, userId);
		

		supervisor.setDaPersonId(new Long(eOEAdminUserInfo.getDaPersonID()));
		supervisor.setName(eOEAdminUserInfo.getName());
		supervisor.setFirstName(eOEAdminUserInfo.getFirstName());
		supervisor.setLastName(eOEAdminUserInfo.getLastName());
		supervisor.setMiddleName(eOEAdminUserInfo.getMiddleName());
		supervisor.setUserID(eOEAdminUserInfo.getUserID());
		supervisor.setEmail(getEmail(supervisor.getDaPersonId(),  orgId));
		supervisor.setPwd("hxz98432sdkjds");
		return supervisor;

	}
	
	/**
	 * Gets the practium types.
	 *
	 * @return the practium types
	 */
	public List<PracticumType> getPractiumTypes(){
		return  practicumTypeService.findAll();
	}
	
	/**
	 * Gets the status reason types.
	 *
	 * @return the status reason types
	 */
	public List<StatusReasonType> getStatusReasonTypes(){
		List<StatusReasonType> srtList = statusReasonTypeService.findAll();
		return  srtList;
	}

	/**
	 * Gets the org code.
	 *
	 * @param orgId the org id
	 * @return the org code
	 * @throws NesterException the nester exception
	 * @throws NoOrganizationFoundException the no organization found exception
	 */
	public String getOrgCode(Long orgId) throws NesterException, NoOrganizationFoundException {
		return DirAdminAccessUtil.getOrgInfoByOrgId(orgId.intValue()).getOrgCode();
	}
	
	/**
	 * Gets the incomplete form string.
	 *
	 * @param cycleId the cycle id
	 * @return the incomplete form string
	 */
	//Incomplete form
	public String getIncompleteFormString (Long cycleId){	
		return candidateService.findIncompleteForm(cycleId);		
	}
	
	
	/**
	 * Gets the practicum type desc.
	 *
	 * @param code the code
	 * @return the practicum type desc
	 */
	public String getPracticumTypeDesc(String code){
		return practicumTypeService.findByPrimaryKey(code).getTypeDesc();
	}

	/**
	 * Gets the CAP completion status.
	 *
	 * @param cycleId the cycle id
	 * @return the CAP completion status
	 */
	public boolean getCAPCompletionStatus(long cycleId){
		return candidateService.findCAPCompletionStatus(cycleId);
	}
}
