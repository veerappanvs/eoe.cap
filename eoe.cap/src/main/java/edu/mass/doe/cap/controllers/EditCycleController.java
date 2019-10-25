package edu.mass.doe.cap.controllers;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import edu.mass.doe.EOEAuthorization.exception.NesterException;
import edu.mass.doe.EOEAuthorization.exception.NoOrganizationFoundException;
import edu.mass.doe.EOEAuthorization.exception.NoPersonFoundException;
import edu.mass.doe.cap.dataservice.entity.PracticumType;
import edu.mass.doe.cap.dataservice.entity.StatusReasonType;
import edu.mass.doe.cap.dataservice.pojo.CapCycleInfo;
import edu.mass.doe.cap.dataservice.pojo.CapManagerInfo;
import edu.mass.doe.cap.dataservice.pojo.SupervisorInfo;
import edu.mass.doe.cap.dataservice.util.CapService;
import edu.mass.doe.cap.security.EOEUser;
import edu.mass.doe.cap.util.CAPValidationUtil;
import edu.mass.doe.cap.util.CapUtil;
import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

/**
 * The Class EditCycleController.
 */
@Controller
@RequestMapping("cycle")
@SessionAttributes("capCycleInfo")
public class EditCycleController extends BaseCycleInformation {

	private static Logger logger = LoggerFactory.getLogger(EditCycleController.class);

	@Autowired(required = true)
	private CapService capService;
	
	/**
	 * Gets the teacher candidate by program id.
	 *
	 * @param cycleId the cycle id
	 * @param model the model
	 * @param authentication the authentication
	 * @return the teacher candidate by program id
	 * @throws Exception the exception
	 */
	@RequestMapping
	public String getCycleDetailsById(@RequestParam("cycleId") Long cycleId, Model model,
			Authentication authentication) throws Exception {

		model.addAttribute("scrollX", 0);
		model.addAttribute("scrollY", 0);
		String reOpenErrorMessage =null;
		CapCycleInfo capCycleInfo=null;
		Object obejct = model.asMap().get("capCycleInfo");
		if(obejct != null ) {
			capCycleInfo = (CapCycleInfo)obejct;
			if(capCycleInfo.getCycleId().equals(cycleId ))
				reOpenErrorMessage = ((CapCycleInfo)obejct).getReOpenErrorMessage(); 
		}
		
		capCycleInfo = cycleManager.getCycleInfo(cycleId);
		capCycleInfo.setIncompleteWorksString(getIncompleteWorks(cycleId));
		boolean allowCycleReOpen = false;
		logger.info(capCycleInfo.toString());		
		
		model.addAttribute("capCycleInfo", capCycleInfo);
		model.addAttribute("schooYearMap", getCyleYear());
		model.addAttribute("practicumTypes", getPracticumTypes());
		model.addAttribute("statusReasonTypes", getStatusReasonTypes(cycleId));
		
		


		String page = ".editCycleView";

		if (capCycleInfo.getCycleStatusCode() != null && !CapUtil.isAdmin())
			return page;
		if (CapUtil.isCandidate())
			page = ".editCycleTC";

		if (CapUtil.isAdmin()) {
			if(		capCycleInfo.getEndDate() !=null  && 
					capCycleInfo.getCycleStatus() != null && 
					!("04".equalsIgnoreCase(capCycleInfo.getCycleStatus())) 
					)
			{
					allowCycleReOpen = true;
					if ( ("01".equalsIgnoreCase(capCycleInfo.getCycleStatus()) && capCycleInfo.getSchoolYear().intValue() != capService.getCurrentSchoolYear() ) )
							allowCycleReOpen = false;
			}
			logger.info("allowCycleReOpen for "+cycleId+" :"+allowCycleReOpen);
			model.addAttribute("allowCycleReOpen", allowCycleReOpen);
			capCycleInfo.setReOpenErrorMessage(reOpenErrorMessage);
			
			page = ".editCycleCAM";
		
		}
		if (CapUtil.isManager() || CapUtil.isSupervisor())
			page = ".editCycle";

		return page;

	}

	/**
	 * Gets the incomplete works.
	 *
	 * @param cycleId the cycle id
	 * @return the incomplete works
	 */
	public String getIncompleteWorks(Long cycleId) {
		return candidateManager.getIncompleteFormString(cycleId);
	}

	/**
	 * Gets the practicum types.
	 *
	 * @return the practicum types
	 */
	public Map<String, String> getPracticumTypes() {

		Map<String, String> types = new HashMap<String, String>();

		for (PracticumType practicumType : candidateManager.getPractiumTypes()) {
			types.put(practicumType.getTypeCode(), practicumType.getTypeDesc());
		}

		return types;
	}

	/**
	 * Gets the status reason types.
	 *
	 * @param cycleId the cycle id
	 * @return the status reason types
	 */
	public Map<String, String> getStatusReasonTypes(long cycleId) {

		Map<String, String> types = new HashMap<String, String>();
		boolean capCompletionStatus=candidateManager.getCAPCompletionStatus(cycleId);
	
		for (StatusReasonType statusReasonType : candidateManager.getStatusReasonTypes()) {	
	
			if((statusReasonType.getTypeCode().equals("01")&&capCompletionStatus)||(!statusReasonType.getTypeCode().equals("01")&&!statusReasonType.getTypeCode().equals("04")))
			types.put(statusReasonType.getTypeCode(), statusReasonType.getTypeDesc());
		}

		return types;
	}

	/**
	 * Gets the cyle year.
	 *
	 * @return the cyle year
	 * @throws NesterException the nester exception
	 */
	public Map<Integer, String> getCyleYear() throws NesterException {

		int year = Calendar.getInstance().get(Calendar.YEAR);

		Map<Integer, String> years = new HashMap<Integer, String>();

		years.put(year, year - 1 + "-" + year);

		return years;
	}

	/**
	 * On org type change.
	 *
	 * @param capCycleInfo the cap cycle info
	 * @param model the model
	 */
	@ResponseBody
	@RequestMapping("onOrgTypeChange")
	public void onOrgTypeChange(@ModelAttribute("capCycleInfo") CapCycleInfo capCycleInfo, Model model) {

		capCycleInfo.getPractitioner().setMepid(null);
		capCycleInfo.getPractitioner().setFirstName(null);
		capCycleInfo.getPractitioner().setLastName(null);
		capCycleInfo.getPractitioner().setEmail(null);
		;
		capCycleInfo.getPractitioner().setDistrictOrgId(null);

	}

	
	/**
	 * Gets the districts.
	 *
	 * @param capCycleInfo the cap cycle info
	 * @param model the model
	 * @return the districts
	 * @throws NesterException the nester exception
	 */
	public Map<Long, String> getDistricts(@ModelAttribute("capCycleInfo") CapCycleInfo capCycleInfo, Model model)
			throws NesterException {
		logger.debug("Org Type :" + capCycleInfo.getPractitioner().getDistrictOrgTypeId());
		Map<Long, String> districts = new HashMap<Long, String>();

		if (capCycleInfo.getPractitioner().getDistrictOrgTypeId() != null)
			districts = candidateManager.getAllOrgsForOrgType(capCycleInfo.getPractitioner().getDistrictOrgTypeId());

		return districts;
	}
	
	/**
	 * Gets the school.
	 *
	 * @param capCycleInfo the cap cycle info
	 * @param model the model
	 * @return the school
	 * @throws NesterException the nester exception
	 * @throws NoOrganizationFoundException the no organization found exception
	 */
	public Map<Long, String> getSchool(@ModelAttribute("capCycleInfo") CapCycleInfo capCycleInfo, Model model)
			throws NesterException, NoOrganizationFoundException {
		logger.debug("Org Code :" + capCycleInfo.getPractitioner().getDistrictOrgCode());
		Map<Long, String> districts = new HashMap<Long, String>();

		if (capCycleInfo.getPractitioner().getDistrictOrgId() != null)
			districts = candidateManager.getSubOrgForOrg(capCycleInfo.getPractitioner().getDistrictOrgId());

		return districts;
	}
	

	/**
	 * Createpractitioner.
	 *
	 * @param capCycleInfo the cap cycle info
	 * @throws Exception the exception
	 */
	public void createpractitioner(CapCycleInfo capCycleInfo) throws Exception {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		for (SupervisorInfo temp : capCycleInfo.getPractitioners()) {

			if (!(temp.getDistrictOrgTypeId().equals(29L) || temp.getDistrictOrgTypeId().equals(11L))
					&& temp.getDaPersonId() == null) {
				EOEUser userContext = (EOEUser) authentication.getPrincipal();
				SupervisorInfo supervisorInfo = candidateManager.createpractitioner(temp, capCycleInfo.getOrgId(),
						userContext.getUsername());
				temp.setDaPersonId(supervisorInfo.getDaPersonId());
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMMMM dd, yyyy");
				String date = simpleDateFormat.format(capCycleInfo.getStartDate());
				emailManager.createSpEvent(capCycleInfo.getCycleId(), capCycleInfo.getProgramName(), date,
						supervisorInfo.getDaPersonId(),capCycleInfo.getOrgId(), supervisorInfo.getUserId(), supervisorInfo.getPwd(), capCycleInfo);
			}

		}

	}

	/**
	 * Save cycle.
	 *
	 * @param request the request
	 * @param capCycleInfo the cap cycle info
	 * @param scrollX the scroll X
	 * @param scrollY the scroll Y
	 * @param model the model
	 * @param result the result
	 * @return the string
	 * @throws Exception the exception
	 */
	@PostMapping
	public String saveCycle(HttpServletRequest request, @ModelAttribute("capCycleInfo") CapCycleInfo capCycleInfo,
			@RequestParam("scrollX") double scrollX, @RequestParam("scrollY") double scrollY, Model model,
			BindingResult result) throws Exception {

		model.addAttribute("capCycleInfo", capCycleInfo);
		model.addAttribute("scrollX", scrollX);
		model.addAttribute("scrollY", scrollY);
		model.addAttribute("schooYearMap", getCyleYear());
		model.addAttribute("practicumTypes", getPracticumTypes());
		model.addAttribute("statusReasonTypes", getStatusReasonTypes(capCycleInfo.getCycleId()));

		if (request.getParameter("Cancel") != null)
			return "redirect:/home";

		if (request.getParameter("saveCycleInfo") != null) {

			if (validateCycleInfo(capCycleInfo, result)) {
				cycleManager.updateCycleInfo(capCycleInfo);
				return "redirect:/cycle?cycleId=" + capCycleInfo.getCycleId();
			}

			return ".editCycle";
		}
		if (request.getParameter("endCycle") != null) {
			boolean vResult = CAPValidationUtil.validateRequired(capCycleInfo.getStatusReasonTypeCode(),
					"statusReasonTypeCode", result);
			logger.debug("VALIDATION RESULT FOR ENDING CYCLE = " + vResult);

			if (vResult) {
				cycleManager.endCycle(capCycleInfo.getCycleId(), capCycleInfo.getStatusReasonTypeCode());
				return "redirect:/cycle?cycleId=" + capCycleInfo.getCycleId();
			}

			if (CapUtil.isAdmin())
				return ".editCycleCAM";

			if (CapUtil.isManager() || CapUtil.isSupervisor())
				return ".editCycle";
		}

		if (request.getParameter("reOpenCycle") != null || "Y".equalsIgnoreCase(capCycleInfo.getReOpenCycleFlag()) ) {
			String reOpenErrorMessage = cycleManager.reOpenCycle(capCycleInfo);
			capCycleInfo.setReOpenErrorMessage(reOpenErrorMessage);
			model.addAttribute("capCycleInfo", capCycleInfo);
			
			if (reOpenErrorMessage == null || reOpenErrorMessage.trim().equals("")) {
				for(CapManagerInfo manager : capCycleInfo.getManagers()) {
				emailManager.createReopenEvent(capCycleInfo, manager);
				}
			}

			
			return "redirect:/cycle?cycleId=" + capCycleInfo.getCycleId();
		}

		return "redirect:/cycle?cycleId=" + capCycleInfo.getCycleId();

	}

	/**
	 * Cancel edit practioner.
	 *
	 * @param request the request
	 * @param capCycleInfo the cap cycle info
	 * @param model the model
	 * @param result the result
	 * @return the string
	 * @throws Exception the exception
	 */
	@GetMapping("cancelEditPractioner")
	public String cancelEditPractioner(HttpServletRequest request,
			@ModelAttribute("capCycleInfo") CapCycleInfo capCycleInfo, Model model, BindingResult result)
			throws Exception {
		capCycleInfo.setPractitioner(capCycleInfo.getOrigPractitioner());
		model.addAttribute("capCycleInfo", capCycleInfo);
		return "editPractitioner";

	}

	/**
	 * Validate cycle info.
	 *
	 * @param capCycleInfo the cap cycle info
	 * @param bindingResult the binding result
	 * @return true, if successful
	 */
	public boolean validateCycleInfo(CapCycleInfo capCycleInfo, BindingResult bindingResult) {
		boolean result = true;

		result = CAPValidationUtil.validateRequired(capCycleInfo.getWaived(), "waived", bindingResult) && result;
		result = CAPValidationUtil.validateRequired(capCycleInfo.getPracticumTypeCode(), "practicumTypeCode",
				bindingResult) && result;
		/*result = CAPValidationUtil.validateRequired(capCycleInfo.getSchoolYear(), "schoolYear", bindingResult)
				&& result;*/
		result = CAPValidationUtil.validateRequired(capCycleInfo.getCourseNumber(), "courseNumber", bindingResult)
				&& result;
		result = CAPValidationUtil.validateRequired(capCycleInfo.getCourseTitle(), "courseTitle", bindingResult)
				&& result;
		result = CAPValidationUtil.validateRequired(capCycleInfo.getCreditHours(), "creditHours", bindingResult)
				&& result;
		result = CAPValidationUtil.validateRequired(capCycleInfo.getGradLevel().size()>0?"gradLevel":null, "gradLevel", bindingResult) && result;

		if (result) {
			result = CAPValidationUtil.validateNumeric(capCycleInfo.getPracticumHours(), "practicumHours",
					bindingResult, new Object[] { "Total Number of Practicum Hours" }) && result;

			result = CAPValidationUtil.validateNumeric(capCycleInfo.getHoursFullResponsibility(),
					"hoursFullResponsibility", bindingResult,
					new Object[] { "Number of hours assumed full responsibility in the role" }) && result;

		
			result = CAPValidationUtil.validateNumeric(capCycleInfo.getCreditHours(), "creditHours", bindingResult,
					new Object[] { "Credit Hours" }) && result;

			result = CAPValidationUtil.validateText(capCycleInfo.getCourseTitle(), "courseTitle",
					bindingResult, new Object[] { "Practicum/Equivalent Seminar Course Title" }) && result;

			result = CAPValidationUtil.validateText(capCycleInfo.getCourseNumber(), "courseNumber", bindingResult,
					new Object[] { "Practicum/Equivalent Course Number" }) && result;
		}

		return result;
	}

	/**
	 * Validate practitioner.
	 *
	 * @param capCycleInfo the cap cycle info
	 * @param bindingResult the binding result
	 * @param index the index
	 * @return true, if successful
	 */
	public boolean validatePractitioner(CapCycleInfo capCycleInfo, BindingResult bindingResult,int index) {

		boolean result = true;

		if (!CAPValidationUtil.validateRequired(capCycleInfo.getPractitioner().getDistrictOrgTypeId(),
				"practitioner.districtOrgTypeId", bindingResult))
			return false;

		List<SupervisorInfo> practitioners =  capCycleInfo.getPractitioners();
		
		if (capCycleInfo.getPractitioner().getDistrictOrgTypeId().equals(29L)
				|| capCycleInfo.getPractitioner().getDistrictOrgTypeId().equals(11L)) {
			
		
			
			result = CAPValidationUtil.validateRequired(capCycleInfo.getPractitioner().getFirstName(),
					"practitioner.firstName", bindingResult) && result;
			result = CAPValidationUtil.validateText(capCycleInfo.getPractitioner().getFirstName(),
					"practitioner.firstName", bindingResult, new Object[] { "First Name" }) && result;

			result = CAPValidationUtil.validateRequired(capCycleInfo.getPractitioner().getLastName(),
					"practitioner.lastName", bindingResult) && result;
			result = CAPValidationUtil.validateText(capCycleInfo.getPractitioner().getLastName(),
					"practitioner.lastName", bindingResult, new Object[] { "Last Name" }) && result;

			result = CAPValidationUtil.validateRequired(capCycleInfo.getPractitioner().getState(), "practitioner.state",
					bindingResult) && result;
			result = CAPValidationUtil.validateAlphanumericSpace(capCycleInfo.getPractitioner().getState(),
					"practitioner.state", bindingResult, new Object[] { "State Name" }) && result;

			result = CAPValidationUtil.validateRequired(capCycleInfo.getPractitioner().getDistrictName(),
					"practitioner.districtName", bindingResult) && result;
			result = CAPValidationUtil.validateAlphanumericSpace(capCycleInfo.getPractitioner().getDistrictName(),
					"practitioner.districtName", bindingResult, new Object[] { "District Name" }) && result;

			result = CAPValidationUtil.validateRequired(capCycleInfo.getPractitioner().getSchoolName(),
					"practitioner.schoolName", bindingResult) && result;
			result = CAPValidationUtil.validateAlphanumericSpace(capCycleInfo.getPractitioner().getSchoolName(),
					"practitioner.schoolName", bindingResult, new Object[] { "School" }) && result;
			
			
			
				if (result) {
					for (SupervisorInfo practitioner : practitioners) {
						if ((practitioner.getDistrictOrgTypeId().equals(29L)
								|| practitioner.getDistrictOrgTypeId().equals(11L))&&practitioners.indexOf(practitioner)!=index) {
							if (capCycleInfo.getPractitioner().getFirstName().equals(practitioner.getFirstName())
									|| capCycleInfo.getPractitioner().getLastName().equals(practitioner.getLastName())
									|| capCycleInfo.getPractitioner().getState().equals(practitioner.getState())
									|| capCycleInfo.getPractitioner().getDistrictName()
											.equals(practitioner.getDistrictName())
									|| capCycleInfo.getPractitioner().getSchoolName()
											.equals(practitioner.getSchoolName())) {
								bindingResult.rejectValue(null, "cap.duplicate.practitioner", new Object[]{}, null);
								result=false;
								break;
							}
						}
					}
				}
				
			
			

		} else {

			result = CAPValidationUtil.validateRequired(capCycleInfo.getPractitioner().getMepid(), "practitioner.mepid",
					bindingResult);
			if (result) {

				if (capCycleInfo.getPractitioner().getDaPersonId() == null) {
					result = result && CAPValidationUtil.validateRequired(capCycleInfo.getPractitioner().getEmail(),
							"practitioner.email", bindingResult);
					result = result
							&& CAPValidationUtil.validateRequired(capCycleInfo.getPractitioner().getConfirmEmail(),
									"practitioner.confirmEmail", bindingResult);
					result = result && CAPValidationUtil.validateEmail(capCycleInfo.getPractitioner().getEmail(),
							"practitioner.email", bindingResult, new Object[] { "Email" });

					result = result && CAPValidationUtil.validateEmail(capCycleInfo.getPractitioner().getConfirmEmail(),
							"practitioner.confirmEmail", bindingResult, new Object[] { "Email" });

					result = result && CAPValidationUtil.validateRelatedFields(
							capCycleInfo.getPractitioner().getEmail(), capCycleInfo.getPractitioner().getConfirmEmail(),
							"practitioner.confirmEmail", bindingResult, new Object[] { "Confirm Email", "Email" });

				} else {
					result = result && CAPValidationUtil.validateRequired(capCycleInfo.getPractitioner().getEmail(),
							"practitioner.email", bindingResult);
					result = result && CAPValidationUtil.validateEmail(capCycleInfo.getPractitioner().getEmail(),
							"practitioner.email", bindingResult, new Object[] { "Email" });
				}

			}
			

			if (result) {
				for (SupervisorInfo practitioner : practitioners) {
					if (!(practitioner.getDistrictOrgTypeId().equals(29L)
							|| practitioner.getDistrictOrgTypeId().equals(11L))&& practitioners.indexOf(practitioner)!=index) {
						if (practitioner.getDaPersonId().equals(capCycleInfo.getPractitioner().getDaPersonId())) {
							bindingResult.rejectValue(null, "cap.duplicate.practitioner", new Object[]{}, null);
							result=false;
							break;
						}
					}
				}
			}
			

		}

		return result;
	}

	/**
	 * Load practitioner.
	 *
	 * @param index the index
	 * @param model the model
	 * @return the string
	 * @throws NesterException the nester exception
	 * @throws NoOrganizationFoundException the no organization found exception
	 */
	@RequestMapping("loadPractitioner")
	public String loadPractitioner(@RequestParam("index") int index, Model model) throws NesterException, NoOrganizationFoundException {

		CapCycleInfo capCycleInfo = (CapCycleInfo) model.asMap().get("capCycleInfo");
		capCycleInfo.setPractitioner(capCycleInfo.getPractitioners().get(index));
		Map<Long, String> districts = getDistricts(capCycleInfo, model);
		model.addAttribute("districts", districts);
		model.addAttribute("schools", getSchool(capCycleInfo, model));
		model.addAttribute("index", index);
		model.addAttribute("showResult", false);
		model.addAttribute("listPractitioners", false);
		return "editPractitioner";
	}

	/**
	 * Adds the practitioner.
	 *
	 * @param model the model
	 * @return the string
	 * @throws NesterException the nester exception
	 * @throws NoOrganizationFoundException the no organization found exception
	 */
	@RequestMapping("addPractitioner")
	public String addPractitioner(Model model) throws NesterException, NoOrganizationFoundException {
		SupervisorInfo practitioner = new SupervisorInfo();
		CapCycleInfo capCycleInfo = (CapCycleInfo) model.asMap().get("capCycleInfo");
		capCycleInfo.setPractitioner(practitioner);
		Map<Long, String> districts = new HashMap<Long, String>();
		model.addAttribute("districts", districts);
		model.addAttribute("schools", getSchool(capCycleInfo, model));
		model.addAttribute("index", capCycleInfo.getPractitioners().size());
		model.addAttribute("showResult", false);
		model.addAttribute("listPractitioners", false);
		return "editPractitioner";
	}

	/**
	 * Modify district type.
	 *
	 * @param index the index
	 * @param typeId the type id
	 * @param model the model
	 * @return the string
	 * @throws NesterException the nester exception
	 * @throws NoOrganizationFoundException the no organization found exception
	 */
	@RequestMapping("modifyDistrictType")
	public String modifyDistrictType(@RequestParam("index") int index, @RequestParam("districtOrgTypeId") Long typeId,
			Model model) throws NesterException, NoOrganizationFoundException {
		CapCycleInfo capCycleInfo = (CapCycleInfo) model.asMap().get("capCycleInfo");
		SupervisorInfo practitioner = new SupervisorInfo();
		practitioner.setDistrictOrgTypeId(typeId);
		capCycleInfo.setPractitioner(practitioner);
		Map<Long, String> districts = getDistricts(capCycleInfo, model);
		model.addAttribute("districts", districts);
		model.addAttribute("schools", getSchool(capCycleInfo, model));
		model.addAttribute("index", index);
		model.addAttribute("showResult", false);
		model.addAttribute("loadPractitionerInfo", false);
		model.addAttribute("listPractitioners", false);
		return "editPractitioner";
	}
	
	/**
	 * Modify district.
	 *
	 * @param index the index
	 * @param typeId the type id
	 * @param districtOrgId the district org id
	 * @param model the model
	 * @return the string
	 * @throws NesterException the nester exception
	 * @throws NoOrganizationFoundException the no organization found exception
	 */
	@RequestMapping("modifyDistrict")
	public String modifyDistrict(@RequestParam("index") int index, @RequestParam("districtOrgTypeId") Long typeId,@RequestParam("districtOrgId") Long districtOrgId,
			Model model) throws NesterException, NoOrganizationFoundException {
		CapCycleInfo capCycleInfo = (CapCycleInfo) model.asMap().get("capCycleInfo");
		SupervisorInfo practitioner = new SupervisorInfo();
		practitioner.setDistrictOrgTypeId(typeId);
		practitioner.setDistrictOrgId(districtOrgId);
		capCycleInfo.setPractitioner(practitioner);
		Map<Long, String> districts = getDistricts(capCycleInfo, model);
		model.addAttribute("districts", districts);
		model.addAttribute("schools", getSchool(capCycleInfo, model));
		model.addAttribute("index", index);
		model.addAttribute("showResult", false);
		model.addAttribute("loadPractitionerInfo", false);
		model.addAttribute("listPractitioners", false);
		return "editPractitioner";
	}


	/**
	 * Load practitioner info.
	 *
	 * @param index the index
	 * @param mepid the mepid
	 * @param typeId the type id
	 * @param districtOrgId the district org id
	 * @param orgId the org id
	 * @param model the model
	 * @param request the request
	 * @param capCycleInfo the cap cycle info
	 * @param result the result
	 * @return the string
	 * @throws Exception the exception
	 */
	@RequestMapping("loadPractitionerInfo")
	public String loadPractitionerInfo(@RequestParam("index") int index, @RequestParam("mepid") String mepid,
			@RequestParam("districtOrgTypeId") Long typeId, @RequestParam("districtOrgId") Long districtOrgId,
			@RequestParam("schoolOrgId") Long orgId, Model model,HttpServletRequest request,  @ModelAttribute("capCycleInfo") CapCycleInfo capCycleInfo,
			BindingResult result) throws Exception {
		model.addAttribute("loadPractitionerInfo", false);
		SupervisorInfo practitioner= new SupervisorInfo();
		if (CAPValidationUtil.validateRequired(typeId, "practitioner.districtOrgTypeId", result)
				&& CAPValidationUtil.validateRequired(districtOrgId, "practitioner.districtOrgId", result)
				&& CAPValidationUtil.validateRequired(orgId, "practitioner.schoolOrgId", result)
				&& CAPValidationUtil.validateRequired(mepid, "practitioner.mepid", result)
				&& CAPValidationUtil.validateMEPID(mepid, "practitioner.mepid", result)) {
			List<SupervisorInfo> supervisors = candidateManager.fetchpractitionerByMEPID(new Long(mepid), orgId,
					districtOrgId);
			if (supervisors.size() > 0) {
				practitioner = supervisors.get(0);
				model.addAttribute("loadPractitionerInfo", true);
				Long personId = candidateManager.getPractitionerPersonId(new Long(mepid), capCycleInfo.getOrgId());
				practitioner.setDaPersonId(personId);
				practitioner.setEmail(candidateManager.getEmail(personId, capCycleInfo.getOrgId()));
			}
			else{
				model.addAttribute("noPractitionerFound", true);
			}
			practitioner.setMepid(new Long(mepid));
		}
		practitioner.setDistrictOrgTypeId(typeId);
		practitioner.setDistrictOrgId(districtOrgId);
		practitioner.setSchoolOrgId(orgId);
		capCycleInfo.setPractitioner(practitioner);
		Map<Long, String> districts = getDistricts(capCycleInfo, model);
		model.addAttribute("districts", districts);
		model.addAttribute("schools", getSchool(capCycleInfo, model));
		model.addAttribute("index", index);
		model.addAttribute("listPractitioners", false);
		return "editPractitioner";

	}

	/**
	 * Delete practitioner.
	 *
	 * @param index the index
	 * @param capCycleInfo the cap cycle info
	 * @param model the model
	 * @param result the result
	 * @return the string
	 * @throws Exception the exception
	 */
	@RequestMapping("deletePractitioner")
	public String deletePractitioner(@RequestParam("index") int index,
			@ModelAttribute("capCycleInfo") CapCycleInfo capCycleInfo, Model model, BindingResult result)
			throws Exception {

		if (capCycleInfo.getPractitioners().size() > 1) {
			SupervisorInfo practitioner = capCycleInfo.getPractitioners().get(index);
			cycleManager.deletePractitioner(practitioner);
		}

		capCycleInfo = cycleManager.getCycleInfo(capCycleInfo.getCycleId());
		capCycleInfo.setIncompleteWorksString(getIncompleteWorks(capCycleInfo.getCycleId()));

		model.addAttribute("capCycleInfo", capCycleInfo);
		return "listPractitioner";
	}

	/**
	 * Edits the practioner.
	 *
	 * @param index the index
	 * @param capCycleInfo the cap cycle info
	 * @param model the model
	 * @param result the result
	 * @return the string
	 * @throws Exception the exception
	 */
	@RequestMapping("savePractitioner")
	public String editPractioner(@RequestParam("index") int index,
			@ModelAttribute("capCycleInfo") CapCycleInfo capCycleInfo, Model model, BindingResult result)
			throws Exception {
		

		if (validatePractitioner(capCycleInfo, result,index)) {
			SupervisorInfo newPractitioner = capCycleInfo.getPractitioner();

			if (capCycleInfo.getPractitioners().size() > index) {
				SupervisorInfo currPractitioner = capCycleInfo.getPractitioners().get(index);
				newPractitioner.setAssignmentId(currPractitioner.getAssignmentId());

				if (currPractitioner.getAssignmentId() != null)
					newPractitioner.setPreviousPractitioner(currPractitioner);

			}

			if (newPractitioner.getDistrictOrgTypeId().equals(29L)
					|| newPractitioner.getDistrictOrgTypeId().equals(11L))
				newPractitioner.setOutOfStatePractitioner(true);

			if (capCycleInfo.getPractitioners().size() > index)
				capCycleInfo.getPractitioners().set(index, capCycleInfo.getPractitioner());
			else
				capCycleInfo.getPractitioners().add(capCycleInfo.getPractitioner());

			createpractitioner(capCycleInfo);
			
			cycleManager.updatePractitioner(capCycleInfo);
			capCycleInfo = cycleManager.getCycleInfo(capCycleInfo.getCycleId());
			capCycleInfo.setIncompleteWorksString(getIncompleteWorks(capCycleInfo.getCycleId()));
			model.addAttribute("capCycleInfo", capCycleInfo);

		} else {
			Map<Long, String> districts = getDistricts(capCycleInfo, model);
			model.addAttribute("districts", districts);
			model.addAttribute("schools", getSchool(capCycleInfo, model));
			model.addAttribute("index", index);
			model.addAttribute("showResult", false);

			if (!(capCycleInfo.getPractitioner().getDistrictOrgTypeId().equals(29l)
					|| capCycleInfo.getPractitioner().getDistrictOrgTypeId().equals(11L)))
				model.addAttribute("loadPractitionerInfo", true);
			else
				model.addAttribute("loadPractitionerInfo", false);

			model.addAttribute("listPractitioners", false);
			return "editPractitioner";
		}

		return "listPractitioner";

	}

	/**
	 * Gets the supervisors.
	 *
	 * @param capCycleInfo the cap cycle info
	 * @param model the model
	 * @return the supervisors
	 * @throws NesterException the nester exception
	 */
	@RequestMapping("loadProgramSupervisor")
	public String getSupervisors(@ModelAttribute("capCycleInfo") CapCycleInfo capCycleInfo, Model model)
			throws NesterException {
		logger.debug("loadProgramSupervisor method is invoked");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		EOEUser userContext = (EOEUser) authentication.getPrincipal();

		List<SupervisorInfo> supervisors = candidateManager.getPersonnelByOrgRole(userContext.getOrgId(), "723");

		model.addAttribute("supervisors", supervisors);

		return "supervisorList2";
	}

	/**
	 * Handle file delete.
	 *
	 * @param supervisorId the supervisor id
	 * @param capCycleInfo the cap cycle info
	 * @param model the model
	 * @return the response entity
	 * @throws NesterException the nester exception
	 * @throws TemplateNotFoundException the template not found exception
	 * @throws MalformedTemplateNameException the malformed template name exception
	 * @throws ParseException the parse exception
	 * @throws TemplateException the template exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws MessagingException the messaging exception
	 * @throws NoPersonFoundException the no person found exception
	 */
	@RequestMapping("saveSupervisor")
	@ResponseBody
	public ResponseEntity handleFileDelete(@RequestParam("supervisorId") Long supervisorId,
			@ModelAttribute("capCycleInfo") CapCycleInfo capCycleInfo, Model model) throws NesterException, TemplateNotFoundException, MalformedTemplateNameException, ParseException, TemplateException, IOException, MessagingException, NoPersonFoundException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		EOEUser userContext = (EOEUser) authentication.getPrincipal();
		List<SupervisorInfo> supervisors = candidateManager.getPersonnelByOrgRole(userContext.getOrgId(), "723");
		SupervisorInfo newSupervisor =supervisors.stream().filter(temp->temp.getDaPersonId().equals(supervisorId)).findFirst().orElse(null);
		newSupervisor.setAssignmentId(capCycleInfo.getSupervisor().getAssignmentId());
		capCycleInfo.setSupervisor(newSupervisor);
		cycleManager.updateSupervisor(capCycleInfo);
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}
	
	@ModelAttribute
	public void loadGradeLevel(Model model)  {
		
		Map<String, String> level =new LinkedHashMap<String, String>();
		for(String val:cycleManager.getAllgradLevel()){
			level.put(val,val);
		}
		
		model.addAttribute("gradeLevel", level);

	}

}
