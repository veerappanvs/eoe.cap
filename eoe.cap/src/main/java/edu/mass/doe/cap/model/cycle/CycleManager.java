package edu.mass.doe.cap.model.cycle;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.mail.MessagingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import edu.mass.doe.EOEAuthorization.DirAdminAccessUtil;
import edu.mass.doe.EOEAuthorization.exception.NesterException;
import edu.mass.doe.EOEAuthorization.exception.NoPersonFoundException;
import edu.mass.doe.cap.candidate.CandidateEnrollment;
import edu.mass.doe.cap.dataservice.candidate.AssignmentService;
import edu.mass.doe.cap.dataservice.candidate.CycleGradeLevelService;
import edu.mass.doe.cap.dataservice.candidate.CycleService;
import edu.mass.doe.cap.dataservice.candidate.OutOfStateSpAssignmentService;
import edu.mass.doe.cap.dataservice.entity.CapAssignment;
import edu.mass.doe.cap.dataservice.entity.CapCycle;
import edu.mass.doe.cap.dataservice.entity.CycleGradeLevel;
import edu.mass.doe.cap.dataservice.entity.OutOfStateSpAssignment;
import edu.mass.doe.cap.dataservice.entity.SimpleAuditIdentity;
import edu.mass.doe.cap.dataservice.pojo.CandidateInfo;
import edu.mass.doe.cap.dataservice.pojo.CapCycleInfo;
import edu.mass.doe.cap.dataservice.pojo.CapManagerInfo;
import edu.mass.doe.cap.dataservice.pojo.SupervisorInfo;
import edu.mass.doe.cap.dataservice.pojo.ThreewayMeetingInfo;
import edu.mass.doe.cap.model.Meeting.ThreewayMeetingManager;
import edu.mass.doe.cap.model.candidate.CandidateManager;
import edu.mass.doe.cap.model.email.EmailManager;
import edu.mass.doe.cap.model.util.CAPUtil;
import edu.mass.doe.cap.security.EOEUser;
import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

/**
 * The Class CycleManager.
 */
@Component
public class CycleManager {

	@Autowired(required = true)
	private CycleService cycleService;

	@Autowired(required = true)
	private AssignmentService assignmentService;

	@Autowired(required = true)
	private OutOfStateSpAssignmentService outOfStateSpAssignmentService;

	@Autowired(required = true)
	private CandidateManager candidateManager;

	@Autowired(required = true)
	private ThreewayMeetingManager threewayMeetingManager;
	
	@Autowired(required = true)
	private EmailManager emailManager;
	
	@Autowired(required = true)
	private CAPUtil capUtil;

	
	@PersistenceContext(unitName = "capPeristanceUnit")
	EntityManager entityManager;
	
	@Autowired(required = true)
	private CycleGradeLevelService cycleGradeLevelService;

	private static Logger logger = LoggerFactory.getLogger(CycleManager.class);

	@Autowired
	private Environment env;

	/**
	 * Find cycle.
	 *
	 * @param cycelId the cycel id
	 * @return the cap cycle
	 */
	public CapCycle findCycle(Long cycelId) {
		return cycleService.findByPrimaryKey(cycelId);
	}

	/**
	 * Creates the cycle.
	 *
	 * @param candidateEnrollment the candidate enrollment
	 * @return the cap cycle
	 * @throws Exception the exception
	 */
	public CapCycle createCycle(CandidateEnrollment candidateEnrollment)
			throws Exception {

		CapCycle capCycle = new CapCycle();
		capCycle.setCycleDataEntryType("SU");
		capCycle.setSchoolYear(candidateEnrollment.getSchoolYear());
		capCycle.setPcomId(candidateEnrollment.getProgramCompleterId());
		capCycle.setStartDate(candidateEnrollment.getCycleStartDate());
		capCycle.setEffDate(new Date());
		capCycle.setWaived('N');
		capCycle.setReadyToTeach('N');
		capCycle.setCptPracticumTypeCode("P");

		CapAssignment superVisor = new CapAssignment();

		List<CapAssignment> assignments = new ArrayList<CapAssignment>();
		List<OutOfStateSpAssignment> outOfStateAssignments = new ArrayList<OutOfStateSpAssignment>();

		superVisor.setDaPersonId(candidateEnrollment.getSupervisordaPersonId());
		superVisor.setDaRoleCode(new Long(723));
		superVisor.setStartDate(candidateEnrollment.getCycleStartDate());
		superVisor.setEffDate(candidateEnrollment.getCycleStartDate());
		superVisor.setCapCycle(capCycle);

		if (candidateEnrollment.getPractitioner().getDistrictOrgTypeId().equals(29L)
				|| candidateEnrollment.getPractitioner().getDistrictOrgTypeId().equals(11L)) {
			OutOfStateSpAssignment Practitioner = new OutOfStateSpAssignment();
			Practitioner.setFirstName(candidateEnrollment.getPractitioner().getFirstName());
			Practitioner.setLastName(candidateEnrollment.getPractitioner().getLastName());
			Practitioner.setState(candidateEnrollment.getPractitioner().getState());
			Practitioner.setDistrict(candidateEnrollment.getPractitioner().getDistrictName());
			Practitioner.setSchool(candidateEnrollment.getPractitioner().getSchoolName());
			Practitioner.setDistrictType(candidateEnrollment.getPractitioner().getDistrictOrgTypeId().toString());
			Practitioner.setEffDate(new Date());
			Practitioner.setCapCycle(capCycle);
			outOfStateAssignments.add(Practitioner);

		} else {
			CapAssignment Practitioner = new CapAssignment();
			Practitioner.setDaPersonId(candidateEnrollment.getPractitioner().getDaPersonId());
			Practitioner.setDaRoleCode(new Long(724));
			Practitioner.setStartDate(candidateEnrollment.getCycleStartDate());
			Practitioner.setEffDate(candidateEnrollment.getCycleStartDate());
			Practitioner.setPracticumOrgId(candidateEnrollment.getPractitioner().getSchoolOrgId());
			Practitioner.setCapCycle(capCycle);
			updateEmail(candidateEnrollment.getPractitioner().getDaPersonId(), candidateEnrollment.getPractitioner().getEmail(), candidateEnrollment.getOrgId());
			
			assignments.add(Practitioner);
		}

		assignments.add(superVisor);

		capCycle.setAssignments(assignments);
		capCycle.setOutOfStateAssignments(outOfStateAssignments);
		capCycle.setAutoCloseIndictor("N");

		capCycle = cycleService.create(capCycle);

		return capCycle;
	}
	
	

	/**
	 * Update cycle info.
	 *
	 * @param capCycleInfo the cap cycle info
	 */
	public void updateCycleInfo(CapCycleInfo capCycleInfo) {

		CapCycle dbcapCycle = cycleService.findByPrimaryKey(capCycleInfo.getCycleId());

		dbcapCycle.setSchoolYear(capCycleInfo.getSchoolYear());
		dbcapCycle.setWaived(capCycleInfo.getWaived());
		dbcapCycle.setCptPracticumTypeCode(capCycleInfo.getPracticumTypeCode());
		dbcapCycle.setCstStatusCode(capCycleInfo.getStatusReasonTypeCode());
		dbcapCycle.setPracticumCourseTitle(capCycleInfo.getCourseTitle());
		if (capCycleInfo.getCourseNumber() != null && capCycleInfo.getCourseNumber().length() > 0)
			dbcapCycle.setPracticumCourseNumber(capCycleInfo.getCourseNumber());

		if (capCycleInfo.getCreditHours() != null && capCycleInfo.getCreditHours().length() > 0)
			dbcapCycle.setCreditHours(new Long(capCycleInfo.getCreditHours()));

		if (capCycleInfo.getHoursFullResponsibility() != null && capCycleInfo.getHoursFullResponsibility().length() > 0)
			dbcapCycle.setFullRespHours(new Long(capCycleInfo.getHoursFullResponsibility()));
		else
			dbcapCycle.setFullRespHours(null);
		
		if (capCycleInfo.getPracticumHours() != null && capCycleInfo.getPracticumHours().length() > 0)
			dbcapCycle.setPracticumHours(new Long(capCycleInfo.getPracticumHours()));
		else
			dbcapCycle.setPracticumHours(null);

		List <String> levelCodes=capCycleInfo.getGradLevel();
		List <CycleGradeLevel> gradeLevels=cycleGradeLevelService.findGradesByCycle(capCycleInfo.getCycleId());
		
		for(CycleGradeLevel cycleGradeLevel:gradeLevels){
			if(!levelCodes.remove(cycleGradeLevel.getLevelCode())){
				cycleGradeLevel.setExpDate(new Date());
				cycleGradeLevelService.update(cycleGradeLevel);
			}
		}
		
		for(String levelCode:levelCodes){
			CycleGradeLevel cycleGradeLevel= new CycleGradeLevel();
			cycleGradeLevel.setCycleId(capCycleInfo.getCycleId());
			cycleGradeLevel.setEffDate(new Date());
			cycleGradeLevel.setLevelCode(levelCode);
			cycleGradeLevelService.create(cycleGradeLevel);
		}
		
		cycleService.update(dbcapCycle);
	}

	/**
	 * Update cycle.
	 *
	 * @param capCycleInfo the cap cycle info
	 * @throws MessagingException the messaging exception
	 * @throws IOException SgradeLvel ignals that an I/O exception has occurred.
	 * @throws TemplateException the template exception
	 * @throws NesterException the nester exception
	 * @throws NoPersonFoundException the no person found exception
	 */
	public void updateCycle(CapCycleInfo capCycleInfo) throws MessagingException, IOException, TemplateException, NesterException, NoPersonFoundException {

		CapCycle dbcapCycle = cycleService.findByPrimaryKey(capCycleInfo.getCycleId());

		dbcapCycle.setSchoolYear(capCycleInfo.getSchoolYear());
		dbcapCycle.setWaived(capCycleInfo.getWaived());
		dbcapCycle.setCptPracticumTypeCode(capCycleInfo.getPracticumTypeCode());
		dbcapCycle.setCstStatusCode(capCycleInfo.getStatusReasonTypeCode());
		dbcapCycle.setPracticumCourseTitle(capCycleInfo.getCourseTitle());

		if (capCycleInfo.getCourseNumber() != null && capCycleInfo.getCourseNumber().length() > 0)
			dbcapCycle.setPracticumCourseNumber(capCycleInfo.getCourseNumber());

		if (capCycleInfo.getCreditHours() != null && capCycleInfo.getCreditHours().length() > 0)
			dbcapCycle.setCreditHours(new Long(capCycleInfo.getCreditHours()));

		if (capCycleInfo.getHoursFullResponsibility() != null && capCycleInfo.getHoursFullResponsibility().length() > 0)
			dbcapCycle.setFullRespHours(new Long(capCycleInfo.getHoursFullResponsibility()));

		if (capCycleInfo.getPracticumHours() != null && capCycleInfo.getPracticumHours().length() > 0)
			dbcapCycle.setPracticumHours(new Long(capCycleInfo.getPracticumHours()));


		List<CapAssignment> assignments = dbcapCycle.getAssignments();
		List<OutOfStateSpAssignment> outOfStateAssignments = dbcapCycle.getOutOfStateAssignments();

		CapAssignment currSupervisor = assignments.stream().filter(temp -> temp.getDaRoleCode().equals(723L))
				.findFirst().orElse(null);
		
		
		
		currSupervisor.setDaPersonId(capCycleInfo.getSupervisor().getDaPersonId());
		assignmentService.update(currSupervisor);

		CapAssignment currPractitioner = null;
		OutOfStateSpAssignment currOutOfStatePractitioner = null;

		/*
		 * boolean newPractitoner; boolean updatePractitoner; boolean
		 * deletePratitioner;
		 */
		for (SupervisorInfo practitionerInfo : capCycleInfo.getPractitioners()) {

			if (practitionerInfo.getPreviousPractitioner() != null) {
				if (practitionerInfo.getPreviousPractitioner().getAssignmentId() != null
						&& practitionerInfo.getPreviousPractitioner().isOutOfStatePractitioner()) {
					currOutOfStatePractitioner = outOfStateSpAssignmentService
							.findByPrimaryKey(practitionerInfo.getPreviousPractitioner().getAssignmentId());
				}

				if (practitionerInfo.getPreviousPractitioner().getAssignmentId() != null
						&& !practitionerInfo.getPreviousPractitioner().isOutOfStatePractitioner()) {
					currPractitioner = assignmentService
							.findByPrimaryKey(practitionerInfo.getPreviousPractitioner().getAssignmentId());
				}

				if (practitionerInfo.getDistrictOrgTypeId().equals(29L)
						|| practitionerInfo.getDistrictOrgTypeId().equals(11L)) {

					if (!practitionerInfo.getPreviousPractitioner().isOutOfStatePractitioner()) {
						currPractitioner.setExpDate(new Date());
						assignmentService.update(currPractitioner);

						currOutOfStatePractitioner = new OutOfStateSpAssignment();
						currOutOfStatePractitioner.setFirstName(practitionerInfo.getFirstName());
						currOutOfStatePractitioner.setLastName(practitionerInfo.getLastName());
						currOutOfStatePractitioner.setState(practitionerInfo.getState());
						currOutOfStatePractitioner.setDistrict(practitionerInfo.getDistrictName());
						currOutOfStatePractitioner.setDistrictType(practitionerInfo.getDistrictOrgTypeId().toString());
						currOutOfStatePractitioner.setSchool(practitionerInfo.getSchoolName());
						currOutOfStatePractitioner.setEffDate(new Date());
						currOutOfStatePractitioner.setCapCycle(dbcapCycle);

						outOfStateSpAssignmentService.create(currOutOfStatePractitioner);
					} else {

						if (practitionerInfo.getFirstName().equalsIgnoreCase(currOutOfStatePractitioner.getFirstName())
								&& practitionerInfo.getLastName()
										.equalsIgnoreCase(currOutOfStatePractitioner.getLastName())) {

							currOutOfStatePractitioner.setState(practitionerInfo.getState());
							currOutOfStatePractitioner.setDistrict(practitionerInfo.getDistrictName());
							currOutOfStatePractitioner.setSchool(practitionerInfo.getSchoolName());
							outOfStateSpAssignmentService.update(currOutOfStatePractitioner);

						} else {
							currOutOfStatePractitioner = new OutOfStateSpAssignment();
							currOutOfStatePractitioner.setFirstName(practitionerInfo.getFirstName());
							currOutOfStatePractitioner.setLastName(practitionerInfo.getLastName());
							currOutOfStatePractitioner.setState(practitionerInfo.getState());
							currOutOfStatePractitioner.setDistrict(practitionerInfo.getDistrictName());
							currOutOfStatePractitioner.setSchool(practitionerInfo.getSchoolName());
							currOutOfStatePractitioner
									.setDistrictType(practitionerInfo.getDistrictOrgTypeId().toString());
							currOutOfStatePractitioner.setEffDate(new Date());
							currOutOfStatePractitioner.setCapCycle(dbcapCycle);
							outOfStateSpAssignmentService.create(currOutOfStatePractitioner);
						}

					}

				} else {

					if (practitionerInfo.getPreviousPractitioner().isOutOfStatePractitioner()) {
						currOutOfStatePractitioner.setExpDate(new Date());
						outOfStateSpAssignmentService.update(currOutOfStatePractitioner);

						currPractitioner = new CapAssignment();
						currPractitioner.setDaPersonId(practitionerInfo.getDaPersonId());
						currPractitioner.setDaRoleCode(new Long(724));
						currPractitioner.setStartDate(new Date());
						currPractitioner.setEffDate(new Date());
						currPractitioner.setPracticumOrgId(practitionerInfo.getSchoolOrgId());
						currPractitioner.setCapCycle(dbcapCycle);
						assignmentService.create(currPractitioner);

					} else {
						if (practitionerInfo.getAssignmentId().equals(currPractitioner.getAssignmentId())) {
								currPractitioner.setDaPersonId(practitionerInfo.getDaPersonId());
							currPractitioner.setPracticumOrgId(practitionerInfo.getSchoolOrgId());
							assignmentService.update(currPractitioner);
						} else {
							currPractitioner = new CapAssignment();
							currPractitioner.setDaPersonId(practitionerInfo.getDaPersonId());
							currPractitioner.setDaRoleCode(new Long(724));
							currPractitioner.setStartDate(new Date());
							currPractitioner.setEffDate(new Date());
							currPractitioner.setPracticumOrgId(practitionerInfo.getSchoolOrgId());
							currPractitioner.setCapCycle(dbcapCycle);
							assignmentService.create(currPractitioner);

						}
					}

				}

			} else if (practitionerInfo.getAssignmentId() == null) {

				if (practitionerInfo.isOutOfStatePractitioner()) {
					currOutOfStatePractitioner = new OutOfStateSpAssignment();
					currOutOfStatePractitioner.setFirstName(practitionerInfo.getFirstName());
					currOutOfStatePractitioner.setLastName(practitionerInfo.getLastName());
					currOutOfStatePractitioner.setState(practitionerInfo.getState());
					currOutOfStatePractitioner.setDistrict(practitionerInfo.getDistrictName());
					currOutOfStatePractitioner.setSchool(practitionerInfo.getSchoolName());
					currOutOfStatePractitioner.setDistrictType(practitionerInfo.getDistrictOrgTypeId().toString());
					currOutOfStatePractitioner.setEffDate(new Date());
					currOutOfStatePractitioner.setCapCycle(dbcapCycle);
					outOfStateSpAssignmentService.create(currOutOfStatePractitioner);
				} else {
					currPractitioner = new CapAssignment();
					currPractitioner.setDaPersonId(practitionerInfo.getDaPersonId());
					currPractitioner.setDaRoleCode(new Long(724));
					currPractitioner.setStartDate(new Date());
					currPractitioner.setEffDate(new Date());
					currPractitioner.setPracticumOrgId(practitionerInfo.getSchoolOrgId());
					currPractitioner.setCapCycle(dbcapCycle);
					assignmentService.create(currPractitioner);
				}

			}

		}

		cycleService.update(dbcapCycle);

	}

	/**
	 * Update email.
	 *
	 * @param personId the person id
	 * @param userEmailId the user email id
	 * @param orgId the org id
	 * @throws Exception the exception
	 */
	private void updateEmail(Long personId,String userEmailId,Long orgId) throws Exception{
		
		
		String emailId = DirAdminAccessUtil.getUserEmail(
				DirAdminAccessUtil
						.getUserIDByDAPersonID(personId.intValue()),
						orgId);
		if (!userEmailId.equals(emailId)) {
			EOEUser eoeUser = (EOEUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			
			logger.info(" emailId  "+emailId+"New  userEmailId "+userEmailId);
			DirAdminAccessUtil.upateUserEmailbyContactid(userEmailId,
					personId,
					orgId,
					DirAdminAccessUtil.getUserIDByDAPersonID(eoeUser.getPersonId().intValue()));
			
			logger.info(" Updated email id sussfully  "+eoeUser);

		}
	}

	/**
	 * Update practitioner.
	 *
	 * @param capCycleInfo the cap cycle info
	 * @throws Exception the exception
	 */
	@Transactional(value = TxType.REQUIRES_NEW)
	public void updatePractitioner(CapCycleInfo capCycleInfo) throws Exception {

		CapCycle dbcapCycle = cycleService.findByPrimaryKey(capCycleInfo.getCycleId());

		List<CapAssignment> assignments = dbcapCycle.getAssignments();
		List<OutOfStateSpAssignment> outOfStateAssignments = dbcapCycle.getOutOfStateAssignments();

		CapAssignment currPractitioner = null;
		OutOfStateSpAssignment currOutOfStatePractitioner = null;

		/*
		 * boolean newPractitoner; boolean updatePractitoner; boolean
		 * deletePratitioner;
		 */
		for (SupervisorInfo practitionerInfo : capCycleInfo.getPractitioners()) {

			if (practitionerInfo.getPreviousPractitioner() != null) {
				if (practitionerInfo.getPreviousPractitioner().getAssignmentId() != null
						&& practitionerInfo.getPreviousPractitioner().isOutOfStatePractitioner()) {
					currOutOfStatePractitioner = outOfStateSpAssignmentService
							.findByPrimaryKey(practitionerInfo.getPreviousPractitioner().getAssignmentId());
				}

				if (practitionerInfo.getPreviousPractitioner().getAssignmentId() != null
						&& !practitionerInfo.getPreviousPractitioner().isOutOfStatePractitioner()) {
					currPractitioner = assignmentService
							.findByPrimaryKey(practitionerInfo.getPreviousPractitioner().getAssignmentId());
				}

				if (practitionerInfo.getDistrictOrgTypeId().equals(29L)
						|| practitionerInfo.getDistrictOrgTypeId().equals(11L)) {

					if (!practitionerInfo.getPreviousPractitioner().isOutOfStatePractitioner()) {
						currPractitioner.setExpDate(new Date());
						assignmentService.update(currPractitioner);
						currOutOfStatePractitioner = new OutOfStateSpAssignment();
						currOutOfStatePractitioner.setFirstName(practitionerInfo.getFirstName());
						currOutOfStatePractitioner.setLastName(practitionerInfo.getLastName());
						currOutOfStatePractitioner.setState(practitionerInfo.getState());
						currOutOfStatePractitioner.setDistrict(practitionerInfo.getDistrictName());
						currOutOfStatePractitioner.setSchool(practitionerInfo.getSchoolName());
						currOutOfStatePractitioner.setDistrictType(practitionerInfo.getDistrictOrgTypeId().toString());
						currOutOfStatePractitioner.setEffDate(new Date());
						currOutOfStatePractitioner.setCapCycle(dbcapCycle);
						currOutOfStatePractitioner=outOfStateSpAssignmentService.create(currOutOfStatePractitioner);
						practitionerInfo.setAssignmentId(currOutOfStatePractitioner.getPersonId());
						practitionerInfo.setPreviousPractitioner(null);
						practitionerInfo.setOutOfStatePractitioner(true);
					} else {
							currOutOfStatePractitioner.setFirstName(practitionerInfo.getFirstName());
							currOutOfStatePractitioner.setLastName(practitionerInfo.getLastName());
							currOutOfStatePractitioner.setState(practitionerInfo.getState());
							currOutOfStatePractitioner.setDistrict(practitionerInfo.getDistrictName());
							currOutOfStatePractitioner.setSchool(practitionerInfo.getSchoolName());
							currOutOfStatePractitioner
									.setDistrictType(practitionerInfo.getDistrictOrgTypeId().toString());
							outOfStateSpAssignmentService.update(currOutOfStatePractitioner);
							practitionerInfo.setPreviousPractitioner(null);
							practitionerInfo.setOutOfStatePractitioner(true);
					}

				} else {

					if (practitionerInfo.getPreviousPractitioner().isOutOfStatePractitioner()) {
						currOutOfStatePractitioner.setExpDate(new Date());
						outOfStateSpAssignmentService.update(currOutOfStatePractitioner);
						currPractitioner = new CapAssignment();
						currPractitioner.setDaPersonId(practitionerInfo.getDaPersonId());
						currPractitioner.setDaRoleCode(new Long(724));
						currPractitioner.setStartDate(new Date());
						currPractitioner.setEffDate(new Date());
						currPractitioner.setPracticumOrgId(practitionerInfo.getSchoolOrgId());
						currPractitioner.setCapCycle(dbcapCycle);
						updateEmail(practitionerInfo.getDaPersonId(),practitionerInfo.getEmail(),capCycleInfo.getOrgId());
						currPractitioner=assignmentService.create(currPractitioner);
						emailManager.addPractitionerEvent(capCycleInfo.getCycleId(), capCycleInfo, practitionerInfo);
						practitionerInfo.setAssignmentId(currPractitioner.getAssignmentId());
						practitionerInfo.setPreviousPractitioner(null);
						practitionerInfo.setOutOfStatePractitioner(false);

					} else {
							if(!currPractitioner.getDaPersonId().equals(practitionerInfo.getDaPersonId())){
								emailManager.addPractitionerEvent(capCycleInfo.getCycleId(), capCycleInfo, practitionerInfo);
							}
							currPractitioner.setDaPersonId(practitionerInfo.getDaPersonId());
							currPractitioner.setPracticumOrgId(practitionerInfo.getSchoolOrgId());							
							assignmentService.update(currPractitioner);
							updateEmail(practitionerInfo.getDaPersonId(),practitionerInfo.getEmail(),capCycleInfo.getOrgId());
							practitionerInfo.setPreviousPractitioner(null);
							practitionerInfo.setOutOfStatePractitioner(false);							
					}

				}

			} else if (practitionerInfo.getAssignmentId() == null) {
				if (practitionerInfo.isOutOfStatePractitioner()) {
					currOutOfStatePractitioner = new OutOfStateSpAssignment();
					currOutOfStatePractitioner.setFirstName(practitionerInfo.getFirstName());
					currOutOfStatePractitioner.setLastName(practitionerInfo.getLastName());
					currOutOfStatePractitioner.setState(practitionerInfo.getState());
					currOutOfStatePractitioner.setDistrict(practitionerInfo.getDistrictName());
					currOutOfStatePractitioner.setSchool(practitionerInfo.getSchoolName());
					currOutOfStatePractitioner.setDistrictType(practitionerInfo.getDistrictOrgTypeId().toString());
					currOutOfStatePractitioner.setEffDate(new Date());
					currOutOfStatePractitioner.setCapCycle(dbcapCycle);
					currOutOfStatePractitioner=outOfStateSpAssignmentService.create(currOutOfStatePractitioner);
					practitionerInfo.setAssignmentId(currOutOfStatePractitioner.getPersonId());
					
				} else {
					currPractitioner = new CapAssignment();
					currPractitioner.setDaPersonId(practitionerInfo.getDaPersonId());
					currPractitioner.setDaRoleCode(new Long(724));
					currPractitioner.setStartDate(new Date());
					currPractitioner.setEffDate(new Date());
					currPractitioner.setPracticumOrgId(practitionerInfo.getSchoolOrgId());
					currPractitioner.setCapCycle(dbcapCycle);
					updateEmail(practitionerInfo.getDaPersonId(),practitionerInfo.getEmail(),capCycleInfo.getOrgId());
					currPractitioner=assignmentService.create(currPractitioner);
					emailManager.addPractitionerEvent(capCycleInfo.getCycleId(), capCycleInfo, practitionerInfo);
					practitionerInfo.setAssignmentId(currPractitioner.getAssignmentId());
				}

			}

		}
		entityManager.flush();
		entityManager.clear();
	}

	/**
	 * Delete practitioner.
	 *
	 * @param practitionerInfo the practitioner info
	 */
	@Transactional(value = TxType.REQUIRES_NEW)
	public void deletePractitioner(SupervisorInfo practitionerInfo) {

		if (practitionerInfo.isOutOfStatePractitioner()) {
			OutOfStateSpAssignment currOutOfStatePractitioner = outOfStateSpAssignmentService
					.findByPrimaryKey(practitionerInfo.getAssignmentId());
			currOutOfStatePractitioner.setExpDate(new Date());
			outOfStateSpAssignmentService.update(currOutOfStatePractitioner);
		} else {
			CapAssignment currPractitioner = assignmentService.findByPrimaryKey(practitionerInfo.getAssignmentId());
			currPractitioner.setExpDate(new Date());
			assignmentService.update(currPractitioner);
		}
		
		entityManager.flush();
		entityManager.clear();

	}

	/**
	 * Update supervisor.
	 *
	 * @param capCycleInfo the cap cycle info
	 * @throws TemplateNotFoundException the template not found exception
	 * @throws MalformedTemplateNameException the malformed template name exception
	 * @throws ParseException the parse exception
	 * @throws TemplateException the template exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws MessagingException the messaging exception
	 * @throws NesterException the nester exception
	 * @throws NoPersonFoundException the no person found exception
	 */
	@Transactional(value = TxType.REQUIRES_NEW)
	public void updateSupervisor(CapCycleInfo capCycleInfo) throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, TemplateException, IOException, MessagingException, NesterException, NoPersonFoundException {
		SupervisorInfo supervisorInfo=capCycleInfo.getSupervisor();
		CapAssignment supervisor = assignmentService.findByPrimaryKey(supervisorInfo.getAssignmentId());
		if(!supervisor.getDaPersonId().equals(supervisorInfo.getDaPersonId())){
			emailManager.addSuperviorEvent(capCycleInfo.getCycleId(), capCycleInfo);
		}
		supervisor.setDaPersonId(supervisorInfo.getDaPersonId());
		assignmentService.update(supervisor);
		entityManager.flush();
		entityManager.clear();
	}

	/**
	 * End cycle.
	 *
	 * @param cycleId the cycle id
	 * @param statusCode the status code
	 * @throws MessagingException the messaging exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws TemplateException the template exception
	 * @throws Exception the exception
	 */
	public void endCycle(Long cycleId, String statusCode) throws MessagingException, IOException, TemplateException, Exception {

		CapCycle cycle = cycleService.findByPrimaryKey(cycleId);
		cycle.setCstStatusCode(statusCode);
		boolean capCompletionStatus=candidateManager.getCAPCompletionStatus(cycleId);
		ThreewayMeetingInfo  threewayMeetingInfo =threewayMeetingManager.getMeetingsByCycleId(cycleId);

		if (statusCode.equals("01") && capCompletionStatus)
			cycle.setEndDate(threewayMeetingInfo.getMeetingDate3());
		else
			cycle.setEndDate(new Date());
		
	
		cycle.setSchoolYear(capUtil.getSchoolyear(cycle.getEndDate()).getSchoolYear());
		
		cycleService.update(cycle);
		CapCycleInfo capcycleinfo  = getCycleInfo(cycleId);
		if(capcycleinfo.getReOpenDate() != null)	{		
			for (CapManagerInfo manager : capcycleinfo.getManagers()) 
			emailManager.cycleClose(cycleId, capcycleinfo, manager);
		}
		else 
			emailManager.cycleClose(cycleId, capcycleinfo);

	}

	/**
	 * Re open cycle.
	 *
	 * @param cycleId the cycle id
	 * @param reOpenCycleFlag the re open cycle flag
	 */
	public String reOpenCycle(CapCycleInfo capCycleInfo) {
		
			long cycleId = capCycleInfo.getCycleId();
			List<String> vaidCyles = cycleService.getAllValidCyclesForReopening(cycleId);
			String result = null;
			if (vaidCyles.size() == 0  ) { //No open/valid cycles exists.
				CapCycle cycle = cycleService.findByPrimaryKey(cycleId);
				cycle.setCstStatusCode(null);
				cycle.setEndDate(null);
				cycle.setReopenDate(new Date());	
				cycleService.update(cycle);
				logger.info("Re-Opened the cycle "+cycleId);
				capCycleInfo.setReOpenDate(cycle.getReopenDate());
				capCycleInfo.setEndDate(cycle.getEndDate());
				//capCycleInfo.setStatusReasonTypeCode(statusReasonTypeCode);

			}
			else {
				String failReason[] = vaidCyles.get(0).split(",");
				if( "0".equals(failReason[0]) && "APP".equals(failReason[1]) ) {
					result= "This candidate has an existing open cycle. Please contact the CAP Manger for more information.";
				}
				else 	if( "01".equals(failReason[0]) && "APP".equals(failReason[1]) ) {
					result= "This candidate has an existing cycle with a status of “Completed CAP”. Please contact the CAP Manger for more information.";
				}
				else 	if( "01".equals(failReason[0]) && "UPLOAD".equals(failReason[1]) ) {
					result= "This candidate has a cycle uploaded through the Spreadsheet Upload feature. Please contact the CAP Manger for more information.";

				}
				logger.info("Duplicate Cycles found for cycleId : "+cycleId+ " Here are the cycle details : "+vaidCyles +result);

			}
			return result;
	}

	/**
	 * Gets the cycle info.
	 *
	 * @param cycleId the cycle id
	 * @return the cycle info
	 * @throws Exception the exception
	 */
	public CapCycleInfo getCycleInfo(Long cycleId) throws Exception {

		CapCycleInfo capCycleInfo = new CapCycleInfo();

		CapCycle capCycle = findCycle(cycleId);

		CandidateInfo candidateIfo = candidateManager.getCandiate(capCycle.getPcomId());

		capCycleInfo.setCycleId(cycleId);
		capCycleInfo.setCandidateFirstName(candidateIfo.getFirstName());
		capCycleInfo.setCandidateLastName(candidateIfo.getLastName());
		capCycleInfo.setCandidateMEPID(candidateIfo.getMepid());
		capCycleInfo.setCandidatePersonId(candidateIfo.getElarPersonId());
		capCycleInfo.setProgramName(candidateIfo.getProgramName());
		capCycleInfo.setSchoolYear(capCycle.getSchoolYear());
		capCycleInfo.setPracticumTypeCode(capCycle.getCptPracticumTypeCode());
		capCycleInfo.setPracticumTypeDesc(candidateManager.getPracticumTypeDesc(capCycle.getCptPracticumTypeCode()));
		capCycleInfo.setStatusReasonTypeCode(capCycle.getCstStatusCode());
		capCycleInfo.setCycleStatus(capCycle.getCstStatusCode());
		capCycleInfo.setCycleStatusCode(capCycle.getCstStatusCode());
		capCycleInfo.setCycleCloseDate(threewayMeetingManager.getMeetingsByCycleId(cycleId).getMeetingDate3());
		capCycleInfo.setStartDate(capCycle.getStartDate());
		capCycleInfo.setReOpenDate(capCycle.getReopenDate());
		capCycleInfo.setEndDate(capCycle.getEndDate());
		capCycleInfo.setReadyToTeach(capCycle.getReadyToTeach());
		capCycleInfo.setAutoCloseIndictor(capCycle.getAutoCloseIndictor());
		capCycleInfo.setAutoCloseFirstEmailDate(capCycle.getAutoCloseFirstEmailDate());
		capCycleInfo.setAutoCloseEmailDate(capCycle.getAutoCloseEmailDate());
		
		List <String> gradeLevels=getAllgradLevelForCycle(capCycleInfo.getCycleId());
		capCycleInfo.setGradLevel(gradeLevels);
		
		
		
		if(gradeLevels.size()<1)
			capCycleInfo.getGradLevel().add(capCycle.getPracticumGradeLevel());
		
		if (capCycle.getWaived() != null)
			capCycleInfo.setWaived(capCycle.getWaived());
		capCycleInfo.setCourseTitle(capCycle.getPracticumCourseTitle());

		if (capCycle.getPracticumCourseNumber() != null)
			capCycleInfo.setCourseNumber(capCycle.getPracticumCourseNumber());

		if (capCycle.getCreditHours() != null)
			capCycleInfo.setCreditHours(capCycle.getCreditHours().toString());

		if (capCycle.getFullRespHours() != null)
			capCycleInfo.setHoursFullResponsibility(capCycle.getFullRespHours().toString());

		if (capCycle.getPracticumHours() != null)
			capCycleInfo.setPracticumHours(capCycle.getPracticumHours().toString());


		/*EOEUser userContext = (EOEUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		capCycleInfo.setOrgId(userContext.getOrgId());*/
		
		capCycleInfo.setOrgId(candidateIfo.getOrgId());
		capCycleInfo.setOrgCode(candidateIfo.getOrgCode());
		capCycleInfo.setOrgName(candidateIfo.getOrgName());

		List<CapAssignment> assignments = capCycle.getAssignments();
		List<OutOfStateSpAssignment> outOfStateAssignments = capCycle.getOutOfStateAssignments();

		for (CapAssignment capAssignment : assignments) {

			if (capAssignment.getDaRoleCode().equals(724L)) {
				SupervisorInfo practitioner = candidateManager.getpractitioner(capAssignment.getDaPersonId(),
						capAssignment.getPracticumOrgId());
				practitioner.setAssignmentId(capAssignment.getAssignmentId());
				capCycleInfo.getPractitioners().add(practitioner);
				capCycleInfo.setPractitioner(practitioner);
				capCycleInfo.setOrigPractitioner(practitioner);
				capCycleInfo.setpractitionerInputMEPID(practitioner.getMepid().toString());
			}

			if (capAssignment.getDaRoleCode().equals(723L)) {
				SupervisorInfo supervisor = candidateManager.getSupervisor(capAssignment.getDaPersonId());
				supervisor.setAssignmentId(capAssignment.getAssignmentId());
				capCycleInfo.setSupervisorFirstName(supervisor.getFirstName());
				capCycleInfo.setSupervisorLastName(supervisor.getLastName());
				capCycleInfo.setSupervisorMEPID(supervisor.getMepid());
				capCycleInfo.setSupervisor(supervisor);
			}

		}
		
		capCycleInfo.setManagers(candidateManager.getCapManagers(capCycleInfo.getOrgId()));

		for (OutOfStateSpAssignment outOfStateSpAssignment : outOfStateAssignments) {

			SupervisorInfo practitioner = new SupervisorInfo();
			practitioner.setAssignmentId(outOfStateSpAssignment.getPersonId());
			practitioner.setDistrictOrgTypeId(outOfStateSpAssignment.getDistrictType() != null
					? new Long(outOfStateSpAssignment.getDistrictType()) : 29L);
			practitioner.setFirstName(outOfStateSpAssignment.getFirstName());
			practitioner.setLastName(outOfStateSpAssignment.getLastName());
			practitioner.setSchoolName(outOfStateSpAssignment.getSchool());
			practitioner.setDistrictName(outOfStateSpAssignment.getDistrict());
			practitioner.setState(outOfStateSpAssignment.getState());
			practitioner.setOutOfStatePractitioner(true);
			capCycleInfo.setPractitioner(practitioner);
			capCycleInfo.setOrigPractitioner(practitioner);
			capCycleInfo.getPractitioners().add(practitioner);

		}
		return capCycleInfo;

	}

	
	public List<String> getAllgradLevel(){
		return cycleService.getAllgradLevel();
	}
	
	public List<String> getAllgradLevelForCycle(Long cycleId){
		return cycleService.getAllgradLevelForCycle(cycleId);
	}
}
