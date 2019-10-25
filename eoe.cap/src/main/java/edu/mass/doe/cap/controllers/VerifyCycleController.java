
package edu.mass.doe.cap.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import edu.mass.doe.EOEAuthorization.exception.NesterException;
import edu.mass.doe.EOEAuthorization.exception.NoOrganizationFoundException;
import edu.mass.doe.cap.candidate.CandidateEnrollment;
import edu.mass.doe.cap.dataservice.pojo.CandidateInfo;
import edu.mass.doe.cap.dataservice.pojo.CapYear;
import edu.mass.doe.cap.dataservice.pojo.ProgramInfo;
import edu.mass.doe.cap.dataservice.pojo.SponsoringOrg;
import edu.mass.doe.cap.dataservice.pojo.SupervisorInfo;
import edu.mass.doe.cap.model.candidate.CandidateManager;
import edu.mass.doe.cap.model.cycle.CycleManager;
import edu.mass.doe.cap.model.email.EmailManager;
import edu.mass.doe.cap.model.util.CAPUtil;
import edu.mass.doe.cap.security.EOEUser;
import edu.mass.doe.cap.util.CAPValidationUtil;
import edu.mass.doe.cap.util.MapValue;

/**
 * The Class VerifyCycleController.
 */
@Controller
@RequestMapping("verifycycle")
@SessionAttributes("verifyCycleCandidateEnrollment")
public class VerifyCycleController {

	private static Logger logger = LoggerFactory.getLogger(VerifyCycleController.class);

	private final Map<Integer, String> pageForms = new HashMap<Integer, String>(3);

	@Autowired(required = true)
	private CandidateManager candidateManager;

	@Autowired(required = true)
	private CycleManager cycleManager;

	@Autowired(required = true)
	public EmailManager emailManager;
	
	@Autowired(required = true)
	public CAPUtil CAPUtil;

	/**
	 * Initialize.
	 */
	@PostConstruct
	public void initialize() {
		pageForms.put(0, ".step1form");
		pageForms.put(1, ".step2form");
		pageForms.put(2, ".step3form");
		pageForms.put(3, ".step4form");
		pageForms.put(4, ".step5form");
	}

	/**
	 * Gets the teacher candidate by program id.
	 *
	 * @param candidateEnrollment the candidate enrollment
	 * @param model the model
	 * @param result the result
	 * @return the teacher candidate by program id
	 * @throws NesterException the nester exception
	 * @throws NumberFormatException the number format exception
	 * @throws NoOrganizationFoundException the no organization found exception
	 */
	@RequestMapping("programid")
	public String getEqtTeacherCandidateByProgramId(
			@ModelAttribute("verifyCycleCandidateEnrollment") CandidateEnrollment candidateEnrollment, Model model,
			BindingResult result) throws NesterException, NumberFormatException, NoOrganizationFoundException {

		logger.info( candidateEnrollment.toString());

		if (CAPValidationUtil.validateRequired(candidateEnrollment.getSelectProgramId(), "selectProgramId", result)) {
			List<CandidateInfo> candidates = candidateManager.fetchAllEqtCandidateByProgramId(
					new Long(candidateEnrollment.getSelectProgramId()), candidateEnrollment.getOrgId());
			model.addAttribute("candidates", candidates);
			logger.debug(candidates.toString());

		}
		
		
		return "verifyTc";

	}
	
	@ResponseBody
	@RequestMapping("sponsoringorgs")
	public List<MapValue<Long, String>>  getAllSponsoringOrgs(
			@ModelAttribute("verifyCycleCandidateEnrollment") CandidateEnrollment candidateEnrollment, Model model,
			BindingResult result) throws NesterException, NumberFormatException, NoOrganizationFoundException {

		List<MapValue<Long, String>>  sponsoringOrgs = candidateManager.findAllSponsoringOrgs();
			model.addAttribute("sponsoringorgs", sponsoringOrgs);
			
			logger.info("Total No of SO : "+sponsoringOrgs.size());
			
		return sponsoringOrgs;

	}
	

	/**
	 * Gets the teacher candidate bymepid.
	 *
	 * @param candidateEnrollment the candidate enrollment
	 * @param model the model
	 * @param result the result
	 * @return the teacher candidate bymepid
	 * @throws NesterException the nester exception
	 * @throws NumberFormatException the number format exception
	 * @throws NoOrganizationFoundException the no organization found exception
	 */
	@RequestMapping("mepid")
	public String getEqtTeacherCandidateBymepid(
			@ModelAttribute("verifyCycleCandidateEnrollment") CandidateEnrollment candidateEnrollment, Model model,
			BindingResult result) throws NesterException, NumberFormatException, NoOrganizationFoundException {

		logger.debug( candidateEnrollment.toString());

		if (CAPValidationUtil.validateRequired(candidateEnrollment.getInputmepid(), "inputmepid", result)
				&& CAPValidationUtil.validateMEPID(candidateEnrollment.getInputmepid(), "inputmepid", result)) {
			List<CandidateInfo> candidates = candidateManager.fetchAllEqtCandidateByMEPID(
					new Long(candidateEnrollment.getInputmepid()), candidateEnrollment.getOrgId());
			model.addAttribute("candidates", candidates);
			logger.info(candidates.toString());

		}
		

		return "verifyTc";

	}

	/**
	 * Gets the programs.
	 *
	 * @param candidateEnrollment the candidate enrollment
	 * @param model the model
	 * @return the programs
	 * @throws NesterException the nester exception
	 */
	@ResponseBody
	@RequestMapping("programs")
	public List<MapValue<Long, String>> getPrograms(@ModelAttribute("verifyCycleCandidateEnrollment") CandidateEnrollment candidateEnrollment,
			Model model) throws NesterException {

		logger.info( candidateEnrollment.toString());
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		EOEUser userContext = (EOEUser) authentication.getPrincipal();
		long orgId = userContext.getOrgId();
		//if(userContext.getAuthorities().contains("ROLE_ADMIN")  && candidateEnrollment.getOrgId() != null) {
		if( candidateEnrollment.getOrgId() != null) {
			
			orgId = candidateEnrollment.getOrgId();
		}
		List<ProgramInfo> programs = candidateManager.fetchProgramByOrgId(orgId);

		List<MapValue<Long, String>> programMap = new ArrayList<MapValue<Long, String>>();

		for (ProgramInfo program : programs) {
			MapValue<Long, String> value = new MapValue<Long, String>(program.getProgramId(), program.getProgramDesc());
			programMap.add(value);
		}
		
		return programMap;
	}
	
	

	/**
	 * Gets the programs.
	 *
	 * @param candidateEnrollment the candidate enrollment
	 * @param model the model
	 * @return the programs
	 * @throws NesterException the nester exception
	 */
	@ResponseBody
	@RequestMapping("programsfororgid")
	public List<MapValue<Long, String>> getProgramsForOrgid(@ModelAttribute("verifyCycleCandidateEnrollment") CandidateEnrollment candidateEnrollment,
			@RequestParam("selectSponsoringOrgId") Long selectSponsoringOrgId,
			Model model) throws NesterException {

		logger.info(" selectSponsoringOrgId "+selectSponsoringOrgId);

		List<MapValue<Long, String>> programMap = new ArrayList<MapValue<Long, String>>();

		if(selectSponsoringOrgId != null ) {
			List<ProgramInfo> programs = candidateManager.fetchProgramByOrgId(selectSponsoringOrgId);
			for (ProgramInfo program : programs) {
				MapValue<Long, String> value = new MapValue<Long, String>(program.getProgramId(), program.getProgramDesc());
				programMap.add(value);
			}
		}
		return programMap;
	}
	
	
	

	
	 
	/**
	 * Gets the supervisors.
	 *
	 * @param candidateEnrollment the candidate enrollment
	 * @param model the model
	 * @return the supervisors
	 * @throws NesterException the nester exception
	 */
	@RequestMapping("supervisor")
	public String getSupervisors(@ModelAttribute("verifyCycleCandidateEnrollment") CandidateEnrollment candidateEnrollment,
			Model model) throws NesterException {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		EOEUser userContext = (EOEUser) authentication.getPrincipal();

		List<SupervisorInfo> supervisors = candidateManager.getPersonnelByOrgRole(userContext.getOrgId(), "723");

		model.addAttribute("supervisors", supervisors);

		return "supervisorList";

	}

	/**
	 * Gets the practitioner.
	 *
	 * @param mepid the mepid
	 * @param districtOrgTypeId the district org type id
	 * @param districtOrgId the district org id
	 * @param schoolOrgId the school org id
	 * @param candidateEnrollment the candidate enrollment
	 * @param model the model
	 * @param result the result
	 * @return the practitioner
	 * @throws Exception the exception
	 */
	@RequestMapping("findpractitioner")
	public String getPractitioner(@RequestParam("practitionermepid") String mepid, 
			@RequestParam("districtOrgTypeId") Long districtOrgTypeId, @RequestParam("districtOrgId") Long districtOrgId,
			@RequestParam("schoolOrgId") Long schoolOrgId,
			@ModelAttribute("verifyCycleCandidateEnrollment") CandidateEnrollment candidateEnrollment, Model model,BindingResult result)
			throws Exception {
		
		SupervisorInfo practitioner= new SupervisorInfo();
		

		logger.info(candidateEnrollment.toString());
		logger.info(" districtOrgTypeId "+districtOrgTypeId);
		logger.info(" schoolOrgId "+schoolOrgId);
		logger.info(" mepid "+mepid);
		if (CAPValidationUtil.validateRequired(districtOrgTypeId,
				"practitioner.districtOrgTypeId", result)
				&& CAPValidationUtil.validateRequired(districtOrgId,
						"practitioner.districtOrgId", result)
				&& CAPValidationUtil.validateRequired(schoolOrgId,
						"practitioner.schoolOrgId", result)
				&& CAPValidationUtil.validateRequired(mepid,
						"practitionerInputMEPID", result)
				&& CAPValidationUtil.validateMEPID(mepid,
						"practitionerInputMEPID", result)) {
			

			
			List<SupervisorInfo> supervisors = candidateManager.fetchpractitionerByMEPID(new Long(mepid), schoolOrgId,
					districtOrgId);
			
				model.addAttribute("noPractitionerFound", true);
			if (supervisors.size() > 0) {
				practitioner = supervisors.get(0);

				Long personId = candidateManager.getPractitionerPersonId(new Long(mepid),
						candidateEnrollment.getOrgId());

				if (personId == null) {
					practitioner.setEmailExist(false);
				} else {
					practitioner.setEmailExist(true);
					practitioner.setDaPersonId(personId);
					practitioner.setEmail(candidateManager.getEmail(personId, candidateEnrollment.getOrgId()));

				}
				
				practitioner.setMepid(new Long(mepid));
				practitioner.setDisplaySupervisorInfo(true);
				model.addAttribute("noPractitionerFound", false);
				
			}

		}
		Map<Long, String> districts = getDistricts(candidateEnrollment, model);
		model.addAttribute("districts", districts);
		model.addAttribute("schools", getSchool(candidateEnrollment, model));
		practitioner.setDistrictOrgTypeId(districtOrgTypeId);
		practitioner.setDistrictOrgId(districtOrgId);
		practitioner.setSchoolOrgId(schoolOrgId);
		candidateEnrollment.setPractitionerInputMEPID(mepid);
		candidateEnrollment.setPractitioner(practitioner);
		return "verifyPractitioner";

	}

	




	/**
	 * Setup form.
	 *
	 * @param model the model
	 * @param authentication the authentication
	 * @return the string
	 */
	@RequestMapping
	public String setupForm(Model model, Authentication authentication) {
	
		EOEUser userContext = (EOEUser) authentication.getPrincipal();
		logger.info(userContext.toString());
		CandidateEnrollment candidateEnrollment = new CandidateEnrollment();

		List<ProgramInfo> programs = candidateManager.fetchProgramByOrgId(userContext.getOrgId());

		candidateEnrollment.setSupervisordaPersonId(userContext.getPersonId());
		candidateEnrollment.setSupervisorName(userContext.getName());
		candidateEnrollment.setOrgId(userContext.getOrgId());
	
		Map<Long, String> programMap = new HashMap<Long, String>();
	
		for (ProgramInfo program : programs) {
			programMap.put(program.getOrgId(), program.getProgramDesc());
		}
	
		logger.info(candidateEnrollment.toString());

			
		model.addAttribute("verifyCycleCandidateEnrollment", candidateEnrollment);
		//model.addAttribute("programMap", programMap);
	
		return ".verifyCycle";
	}
	
	
	/**
	 * Setup form.
	 *
	 * @param model the model
	 * @param authentication the authentication
	 * @return the string
	 */
	@RequestMapping("setsponsoringid")
	public String setSponsoringOrgId(Model model, 
			@RequestParam("selectSponsoringOrgId") Long selectSponsoringOrgId,
			@ModelAttribute("verifyCycleCandidateEnrollment") CandidateEnrollment candidateEnrollment) {
	
		
		logger.info(candidateEnrollment.toString());
		logger.info("selectSponsoringOrgId "+selectSponsoringOrgId);
		if(null != selectSponsoringOrgId) {
			long selectSponOrgid = selectSponsoringOrgId;
			logger.info(" selectSponOrgid "+selectSponOrgid);
			List<ProgramInfo> programs = candidateManager.fetchProgramByOrgId(selectSponOrgid);
			logger.info(programs.toString());
			candidateEnrollment.setOrgId(selectSponOrgid);
		
			Map<Long, String> programMap = new HashMap<Long, String>();
		
			for (ProgramInfo program : programs) {
				programMap.put(program.getOrgId(), program.getProgramDesc());
			}
		
			logger.info(candidateEnrollment.toString());
				
			model.addAttribute("verifyCycleCandidateEnrollment", candidateEnrollment);
			model.addAttribute("programMap", programMap);
			
		} 
		
		
		return ".verifyCycle";
	
	}

	
	

	/**
	 * Submit form.
	 *
	 * @param request the request
	 * @param candidateEnrollment the candidate enrollment
	 * @param model the model
	 * @param result the result
	 * @param current the current
	 * @param command the command
	 * @return the string
	 * @throws Exception the exception
	 */
	@PostMapping
	@PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_SUPERVISOR')")
	public String submitForm(HttpServletRequest request,
			@ModelAttribute("verifyCycleCandidateEnrollment") CandidateEnrollment candidateEnrollment, Model model,
			BindingResult result, @RequestParam("_page") int current, @RequestParam("_command") String command)
			throws Exception {

		String targetPage = "";

	
		if(candidateEnrollment.getPractitioner() != null
				&& candidateEnrollment.getPractitioner().getDistrictOrgTypeId()!= null)
			model.addAttribute("districts", getDistricts(candidateEnrollment, model));
		
		if(candidateEnrollment.getPractitioner() != null
				&& candidateEnrollment.getPractitioner().getDistrictOrgId()!= null)
			model.addAttribute("schools", getSchool(candidateEnrollment, model));
		
			
		

		switch (current) {
		case 0:

			CandidateInfo candidateInfo = candidateManager.getCandiate(candidateEnrollment.getProgramCompleterId());
			candidateEnrollment.setCandidateName(candidateInfo.getCandidateName());
			candidateEnrollment.setProgramName(candidateInfo.getProgramName());
			candidateEnrollment.setMepid(candidateInfo.getMepid());
			candidateEnrollment.setCandidatePersonId(candidateInfo.getElarPersonId());
			result.getModel().putAll(candidateManager.getOrgTypes());


			break;

		case 1:

			if (!validateStep2(candidateEnrollment, result))
				return pageForms.get(current);


			break;

		case 2:

			List<SupervisorInfo> supervisors = candidateManager.getPersonnelByOrgRole(candidateEnrollment.getOrgId(),
					"723");
			for (SupervisorInfo supervisor : supervisors) {
				if (supervisor.getDaPersonId().equals(candidateEnrollment.getSupervisordaPersonId())) {
					candidateEnrollment.setSupervisorName(supervisor.getName());
				}
			}

			break;
		case 3:

			if (!validateStep3(candidateEnrollment, result)) {
				return pageForms.get(current);
			}

			break;

		case 4:

			boolean sendEmail = false;
			if (!(candidateEnrollment.getPractitioner().getDistrictOrgTypeId().equals(29L)
					|| candidateEnrollment.getPractitioner().getDistrictOrgTypeId().equals(11L))
					&& candidateEnrollment.getPractitioner().getDaPersonId() == null) {
				createpractitioner(candidateEnrollment);
				sendEmail = true;
			}

			Long id = cycleManager.createCycle(candidateEnrollment).getCycleId();

			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMMMM dd, yyyy");
			String date = simpleDateFormat.format(candidateEnrollment.getCycleStartDate());
			if (sendEmail)
				/*emailManager.createSpEvent(id, candidateEnrollment.getProgramName(), date,
						candidateEnrollment.getPractitioner().getDaPersonId(), candidateEnrollment.getOrgId(),
						candidateEnrollment.getPractitioner().getUserId(),
						candidateEnrollment.getPractitioner().getPwd(), capCycleInfo);*/

			emailManager.createCycleEvent(id, cycleManager.getCycleInfo(id));

			List<Long> cycles= (List<Long>) request.getSession().getAttribute("cycles");
			
			if(cycles!=null){
				cycles.add(id);
				request.getSession().setAttribute("cycles",cycles);
			}
			return "redirect:cycle?cycleId="+id;

		default:
			break;
		}

		if (command.equals("Next") || command.equals("Assign"))
			targetPage = pageForms.get(++current);

		logger.debug( candidateEnrollment.toString());
		
		return targetPage;
	}

	/**
	 * Validate step 3.
	 *
	 * @param candidateEnrollment the candidate enrollment
	 * @param bindingResult the binding result
	 * @return true, if successful
	 */
	public boolean validateStep3(CandidateEnrollment candidateEnrollment, BindingResult bindingResult) {

		boolean result = true;

		if (!CAPValidationUtil.validateRequired(candidateEnrollment.getPractitioner().getDistrictOrgTypeId(),
				"practitioner.districtOrgTypeId", bindingResult))
			return false;

		if (candidateEnrollment.getPractitioner().getDistrictOrgTypeId().equals(29L)
				|| candidateEnrollment.getPractitioner().getDistrictOrgTypeId().equals(11L)) {
			result = CAPValidationUtil.validateRequired(candidateEnrollment.getPractitioner().getFirstName(),
					"practitioner.firstName", bindingResult) && result;
			result = CAPValidationUtil.validateText(candidateEnrollment.getPractitioner().getFirstName(),
					"practitioner.firstName", bindingResult, new Object[] { "First Name" }) && result;
			result = CAPValidationUtil.validateRequired(candidateEnrollment.getPractitioner().getLastName(),
					"practitioner.lastName", bindingResult) && result;
			result = CAPValidationUtil.validateText(candidateEnrollment.getPractitioner().getLastName(),
					"practitioner.lastName", bindingResult, new Object[] { "Last Name" }) && result;
			result = CAPValidationUtil.validateRequired(candidateEnrollment.getPractitioner().getState(),
					"practitioner.state", bindingResult) && result;
			result = CAPValidationUtil.validateAlphanumericSpace(candidateEnrollment.getPractitioner().getState(),
					"practitioner.state", bindingResult, new Object[] { "State Name" }) && result;
			result = CAPValidationUtil.validateRequired(candidateEnrollment.getPractitioner().getDistrictName(),
					"practitioner.districtName", bindingResult) && result;
			result = CAPValidationUtil.validateAlphanumericSpace(
					candidateEnrollment.getPractitioner().getDistrictName(), "practitioner.districtName", bindingResult,
					new Object[] { "District Name" }) && result;
			result = CAPValidationUtil.validateRequired(candidateEnrollment.getPractitioner().getSchoolName(),
					"practitioner.schoolName", bindingResult) && result;
			result = CAPValidationUtil.validateAlphanumericSpace(candidateEnrollment.getPractitioner().getSchoolName(),
					"practitioner.schoolName", bindingResult, new Object[] { "School" }) && result;

		} else {
			
			result = CAPValidationUtil.validateRequired(candidateEnrollment.getPractitioner().getDistrictOrgId(),
					"practitioner.districtOrgId", bindingResult);
			
			result = CAPValidationUtil.validateRequired(candidateEnrollment.getPractitioner().getSchoolOrgId(),
					"practitioner.schoolOrgId", bindingResult)&&result;
			
			result = result&&CAPValidationUtil.validateRequired(candidateEnrollment.getPractitioner().getMepid(),
					"practitioner.mepid", bindingResult)&&result;
			if (result) {

				if (candidateEnrollment.getPractitioner().getDaPersonId() == null) {
					result = result && CAPValidationUtil.validateRequired(
							candidateEnrollment.getPractitioner().getEmail(), "practitioner.email", bindingResult);
					result = result && CAPValidationUtil.validateRequired(
							candidateEnrollment.getPractitioner().getConfirmEmail(), "practitioner.confirmEmail",
							bindingResult);
					result = result && CAPValidationUtil.validateEmail(candidateEnrollment.getPractitioner().getEmail(),
							"practitioner.email", bindingResult, new Object[] { "Email" });

					result = result
							&& CAPValidationUtil.validateEmail(candidateEnrollment.getPractitioner().getConfirmEmail(),
									"practitioner.confirmEmail", bindingResult, new Object[] { "Email" });

					result = result && CAPValidationUtil.validateRelatedFields(
							candidateEnrollment.getPractitioner().getEmail(),
							candidateEnrollment.getPractitioner().getConfirmEmail(), "practitioner.confirmEmail",
							bindingResult, new Object[] { "Confirm Email", "Email" });

				} else {
					result = result && CAPValidationUtil.validateRequired(
							candidateEnrollment.getPractitioner().getEmail(), "practitioner.email", bindingResult);
					result = result && CAPValidationUtil.validateEmail(candidateEnrollment.getPractitioner().getEmail(),
							"practitioner.email", bindingResult, new Object[] { "Email" });
				}

			}

		}

		return result;
	}

	/**
	 * Validate step 2.
	 *
	 * @param candidateEnrollment the candidate enrollment
	 * @param bindingResult the binding result
	 * @return true, if successful
	 */
	public boolean validateStep2(CandidateEnrollment candidateEnrollment, BindingResult bindingResult) {

		boolean result = true;

		
		result = CAPValidationUtil.validateRequired(candidateEnrollment.getCycleStartDate(), "cycleStartDate",
				bindingResult) && result;

		if (result) {			
			CapYear capYear=CAPUtil.getCurrSchoolYear();
			
			SimpleDateFormat sdf= new SimpleDateFormat("MM/dd/yyyy");
			
			result = CAPValidationUtil.validateCycleStartDate(candidateEnrollment.getCycleStartDate(), capYear.getStartDate(),
					capYear.getEndDate(), "cycleStartDate", bindingResult,
					new Object[] { "Cycle Start Date",sdf.format(capYear.getStartDate()),sdf.format(capYear.getEndDate())});
		}

		return result;

	}

	

	/**
	 * Createpractitioner.
	 *
	 * @param candidateEnrollment the candidate enrollment
	 * @throws Exception the exception
	 */
	public void createpractitioner(CandidateEnrollment candidateEnrollment) throws Exception {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		EOEUser userContext = (EOEUser) authentication.getPrincipal();
		
		candidateManager.createpractitioner(candidateEnrollment.getPractitioner(),
				candidateEnrollment.getOrgId(), userContext.getUsername());
		
	}

	
	/**
	 * Modify district type.
	 *
	 * @param typeId the type id
	 * @param model the model
	 * @return the string
	 * @throws NesterException the nester exception
	 * @throws NoOrganizationFoundException the no organization found exception
	 */
	@RequestMapping("modifyDistrictType")
	public String modifyDistrictType(@RequestParam("districtOrgTypeId") Long typeId,
			Model model) throws NesterException, NoOrganizationFoundException {
		CandidateEnrollment candidateEnrollment = (CandidateEnrollment) model.asMap().get("verifyCycleCandidateEnrollment");
		SupervisorInfo practitioner = new SupervisorInfo();
		practitioner.setDistrictOrgTypeId(typeId);
		candidateEnrollment.setPractitioner(practitioner);
		Map<Long, String> districts = getDistricts(candidateEnrollment, model);
		model.addAttribute("districts", districts);
		model.addAttribute("schools", getSchool(candidateEnrollment, model));
		return "verifyCycleAssignPractitioner";
	}
	
	/**
	 * Modify district.
	 *
	 * @param typeId the type id
	 * @param districtOrgId the district org id
	 * @param model the model
	 * @return the string
	 * @throws NesterException the nester exception
	 * @throws NoOrganizationFoundException the no organization found exception
	 */
	@RequestMapping("modifyDistrict")
	public String modifyDistrict( @RequestParam("districtOrgTypeId") Long typeId,@RequestParam("districtOrgId") Long districtOrgId,
			Model model) throws NesterException, NoOrganizationFoundException {
		CandidateEnrollment candidateEnrollment = (CandidateEnrollment) model.asMap().get("verifyCycleCandidateEnrollment");
		SupervisorInfo practitioner = candidateEnrollment.getPractitioner();
		practitioner.setDistrictOrgTypeId(typeId);
		practitioner.setDistrictOrgId(districtOrgId);
		candidateEnrollment.setPractitioner(practitioner);
		Map<Long, String> districts = getDistricts(candidateEnrollment, model);
		model.addAttribute("districts", districts);
		model.addAttribute("schools", getSchool(candidateEnrollment, model));
		return "verifyCycleAssignPractitioner";
	}
	
	
	/**
	 * Gets the districts.
	 *
	 * @param candidateEnrollment the candidate enrollment
	 * @param model the model
	 * @return the districts
	 * @throws NesterException the nester exception
	 */
	public Map<Long, String> getDistricts(@ModelAttribute("verifyCycleCandidateEnrollment") CandidateEnrollment candidateEnrollment, Model model)
			throws NesterException {
		logger.debug("Org Type :" + candidateEnrollment.getPractitioner().getDistrictOrgTypeId());
		Map<Long, String> districts = new HashMap<Long, String>();

		if (candidateEnrollment.getPractitioner().getDistrictOrgTypeId() != null)
			districts = candidateManager.getAllOrgsForOrgType(candidateEnrollment.getPractitioner().getDistrictOrgTypeId());

		return districts;
	}
	
	/**
	 * Gets the school.
	 *
	 * @param candidateEnrollment the candidate enrollment
	 * @param model the model
	 * @return the school
	 * @throws NesterException the nester exception
	 * @throws NoOrganizationFoundException the no organization found exception
	 */
	public Map<Long, String> getSchool(@ModelAttribute("verifyCycleCandidateEnrollment") CandidateEnrollment candidateEnrollment, Model model)
			throws NesterException, NoOrganizationFoundException {
		logger.debug("Org Code :" + candidateEnrollment.getPractitioner().getDistrictOrgCode());
		Map<Long, String> districts = new HashMap<Long, String>();

		if (candidateEnrollment.getPractitioner().getDistrictOrgId() != null)
			districts = candidateManager.getSubOrgForOrg(candidateEnrollment.getPractitioner().getDistrictOrgId());

		return districts;
	}
}
