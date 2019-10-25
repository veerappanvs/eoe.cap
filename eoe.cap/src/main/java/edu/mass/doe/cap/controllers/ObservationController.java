package edu.mass.doe.cap.controllers;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import edu.mass.doe.cap.candidate.CandidateEnrollment;
import edu.mass.doe.cap.dataservice.entity.Evidence;
import edu.mass.doe.cap.dataservice.entity.ObservationGroupType;
import edu.mass.doe.cap.dataservice.observation.ObservationService;
import edu.mass.doe.cap.dataservice.pojo.AssessmentInfo;
import edu.mass.doe.cap.dataservice.pojo.CapCycleInfo;
import edu.mass.doe.cap.dataservice.pojo.EvidenceInfo;
import edu.mass.doe.cap.dataservice.pojo.ObservationInfo;
import edu.mass.doe.cap.dataservice.pojo.ThreewayMeetingInfo;
import edu.mass.doe.cap.model.candidate.CandidateManager;
import edu.mass.doe.cap.model.cycle.CycleManager;
import edu.mass.doe.cap.model.observation.ObservationManager;
import edu.mass.doe.cap.security.EOEUser;
import edu.mass.doe.cap.util.CAPValidationUtil;
import edu.mass.doe.cap.util.CapUtil;

/**
 * The Class ObservationController.
 */
@Controller
@RequestMapping("observation")
@SessionAttributes("observationInfo")
public class ObservationController extends BaseCycleInformation {
	private static Logger logger = LoggerFactory.getLogger(ObservationController.class);
	
	@Autowired
	private Environment env;

	/**
	 * Gets the obervation.
	 *
	 * @param cycleId the cycle id
	 * @param observationNumber the observation number
	 * @param typeCode the type code
	 * @param model the model
	 * @param authentication the authentication
	 * @return the obervation
	 * @throws Exception the exception
	 */
	@RequestMapping
	public String getObervation( @RequestParam("cycleId") Long cycleId, @RequestParam("observationNumber") Long observationNumber,@RequestParam("typeCode") String typeCode, Model model,
			Authentication authentication) throws Exception {
		
		ObservationInfo	observation=observationManager.getObservation(cycleId,observationNumber,typeCode);
		
		
		model.addAttribute("observationInfo",observation);
		model.addAttribute("pageName", observation.getObservationNumber()+observation.getObservationTypecode());
		
		processFieldAccess(observation);
		
		if(enableCompletion(observation))
			observation.setEnableCompletion(true);
		
		
		loadAssessment(observation, cycleId, model, authentication);
		
		String page = ".observationView";
		
		if(CapUtil.isCandidate()&&!observation.isCompleteObservation())
			return ".home";
		
		if(CapUtil.isCandidate())
			page = ".observationCandidateView";
		
		
		if(observation.getPsspCompleted()==null)
			observation.setPsspCompleted('B');
	
		
		if (cycleManager.getCycleInfo(cycleId).getCycleStatusCode() != null)
			return page;
		
		
		if(CapUtil.isSupervisor()||CapUtil.isManager()||CapUtil.ispractitioner()){
		Long personId=	((EOEUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
			.getPersonId();
			
			if(CapUtil.ispractitioner() &&observation.getPsspCompleted().equals('P')&&!observation.isCompleteObservation())
				page=".observation";
			else if((CapUtil.isSupervisor()||CapUtil.isManager()) &&observation.getPsspCompleted().equals('S')&&!observation.isCompleteObservation())
				page=".observation";
			else if(observation.getPsspCompleted().equals('B')&&!observation.isCompleteObservation()&&!(observation.getCompletedBy()!=null&&observation.getCompletedBy().equals(personId)))
				page=".observation";
			
		}
			
		
		
		return page;
		
	}
	
	/**
	 * Inits the observation.
	 *
	 * @param cycleId the cycle id
	 * @param observationNumber the observation number
	 * @param typeCode the type code
	 * @param model the model
	 * @param authentication the authentication
	 * @throws Exception the exception
	 */
	@ModelAttribute
	public void initObservation( @RequestParam("cycleId") Long cycleId, @RequestParam("observationNumber") Long observationNumber,@RequestParam("typeCode") String typeCode, Model model,
			Authentication authentication) throws Exception{
		
		
		CapCycleInfo cycleInfo =  cycleManager.getCycleInfo(cycleId);
			
		model.addAttribute("cycleInfo",cycleInfo);
		
		
	}
	
	/**
	 * Load assessment.
	 *
	 * @param observationInfo the observation info
	 * @param cycleId the cycle id
	 * @param model the model
	 * @param authentication the authentication
	 * @throws Exception the exception
	 */
	public void loadAssessment(ObservationInfo observationInfo, Long cycleId, Model model, Authentication authentication)
			throws Exception {
		
		CapCycleInfo cycleInfo = cycleManager.getCycleInfo(cycleId);
		boolean displayEdit = false;

		displayEdit = cycleInfo.getCycleStatusCode() == null;
				
		if(displayEdit){
			if(CapUtil.ispractitioner() && ((observationInfo.getPsspCompleted() != null) && observationInfo.getPsspCompleted().equals('S')) ){
				displayEdit=false;
			}else if(CapUtil.ispractitioner() && ((observationInfo.getPsspCompleted() != null) && observationInfo.getPsspCompleted().equals('P'))){
				displayEdit=false;
			}
		}

		
		if(observationInfo.getObservationId()==null)
			displayEdit=false;
		model.addAttribute("displayEdit", displayEdit);
		
	}
	
	/**
	 * Save.
	 *
	 * @param request the request
	 * @param observationInfo the observation info
	 * @param model the model
	 * @param result the result
	 * @return the string
	 * @throws Exception the exception
	 */
	@PostMapping
	public String save(HttpServletRequest request,@ModelAttribute("observationInfo") ObservationInfo observationInfo,Model model, BindingResult result) throws Exception{
		
		
		if(request.getParameter("Cancel")!=null)
			return "redirect:/home";
		
		if(request.getParameter("edit")!=null){
			observationManager.unlockObserrvation(observationInfo.getObservationId());
			return "redirect:/observation?cycleId="+observationInfo.getCycleId()+"&observationNumber="+observationInfo.getObservationNumber()+"&typeCode="+observationInfo.getObservationTypecode();

		}
			
		
		if(validateObservation(observationInfo, result)){		
			
		try{
			observationManager.saveObservation(observationInfo);
		}catch(Exception e){
			observationManager.saveObservation(observationInfo);
		}
		
		return "redirect:/observation?cycleId="+observationInfo.getCycleId()+"&observationNumber="+observationInfo.getObservationNumber()+"&typeCode="+observationInfo.getObservationTypecode();
		}
		
		
		
		CapCycleInfo cycleInfo =  cycleManager.getCycleInfo(observationInfo.getCycleId());
		model.addAttribute("cycleInfo",cycleInfo);
		
		return ".observation";
				
	}
	
	/**
	 * Change observation conductor.
	 *
	 * @param request the request
	 * @param observationInfo the observation info
	 * @param model the model
	 * @param result the result
	 * @return the string
	 * @throws Exception the exception
	 */
	@PostMapping("changeObservationConductor")
	public String changeObservationConductor(HttpServletRequest request,
			@ModelAttribute("observationInfo") ObservationInfo observationInfo, Model model, BindingResult result)
			throws Exception {
		
			/*for (EvidenceInfo evidence : observationInfo.getEvidences()) {
				evidence.setSpEvidence(null);
				evidence.setPsEvidence(null);
			}
			observationInfo.setSpRefineFeedback(null);
			observationInfo.setPsRefineFeedback(null);
			observationInfo.setSpReinFeedback(null);
			observationInfo.setPsReinFeedback(null);*/
		
		return ".observation";
	}	
	
	/**
	 * Process field access.
	 *
	 * @param observation the observation
	 */
	public void processFieldAccess(ObservationInfo	observation) {
		
		if(CapUtil.isCandidate()){			
			observation.setViewOnly(true);
		}else if(CapUtil.ispractitioner()){
			observation.setPractitioner(true);
		}else if(CapUtil.isSupervisor()){
			observation.setSupervisor(true);
		}else if(CapUtil.isManager()){
			observation.setSupervisor(true);
		}
		
	}
	
	
	/**
	 * Gets the group types.
	 *
	 * @return the group types
	 */
	@ModelAttribute("groupTypes")
	public Map<String,String> getGroupTypes() {
		
		Map<String,String>  map = new LinkedHashMap<String,String>();
		
		for(ObservationGroupType groupType: observationManager.getGroupTypes()){
			map.put(groupType.getGroupCode(), groupType.getGroupDesc());
			
		}
		
	    return  map;
	}
	
	/**
	 * Enable completion.
	 *
	 * @param observation the observation
	 * @return true, if successful
	 */
	public boolean enableCompletion(ObservationInfo observation) {
		boolean result = true;

		result =observation.getPsspCompleted()!=null;
		
		result = result&&observation.getObservationDate()!=null;
		result = result&&observation.getStartTime()!=null;
		result = result&&observation.getEndTime()!=null;
		result = result&&observation.getGroupCode()!=null;
		
		for (EvidenceInfo evidence : observation.getEvidences()) {

			if (!evidence.getFocusElement().equals(1))
				continue;

			/*if(observation.isPractitioner())
				result=result&&evidence.getSpEvidence()!=null&&evidence.getSpEvidence().trim().length()>0;*/
			

				/*if(observation.isSupervisor())
				result=result&&evidence.getPsEvidence()!=null&&evidence.getPsEvidence().trim().length()>0;*/
				
				result=result&&evidence.getCalEvidence()!=null&&evidence.getCalEvidence().trim().length()>0;

		}
		
		
		/*if(observation.isPractitioner())
			result=result&&observation.getSpRefineFeedback()!=null&&observation.getSpRefineFeedback().trim().length()>0;*/
			
			/*if(observation.isSupervisor())
			result=result&&observation.getPsRefineFeedback()!=null&&observation.getPsRefineFeedback().trim().length()>0;*/
			
			result=result&&observation.getCalRefineFeedback()!=null&&observation.getCalRefineFeedback().trim().length()>0;
			
		
			/*if(observation.isPractitioner())
			result=result&&observation.getSpReinFeedback()!=null&&observation.getSpReinFeedback().trim().length()>0;
			
			if(observation.isSupervisor())
			result=result&&observation.getPsReinFeedback()!=null&&observation.getPsReinFeedback().trim().length()>0;*/

			result=result&&observation.getCalReinFeedback()!=null&&observation.getCalReinFeedback().trim().length()>0;
		
		return result;
	}
	
	
	/**
	 * Validate observation.
	 *
	 * @param observation the observation
	 * @param bindingResult the binding result
	 * @return true, if successful
	 */
	public boolean validateObservation(ObservationInfo observation, BindingResult bindingResult) {

		boolean result = true;

		result = CAPValidationUtil.validateRequired(observation.getPsspCompleted(), "psspCompleted", bindingResult);
		result = CAPValidationUtil.validateRequired(observation.getObservationDate(), "observationDate", bindingResult)
				&& result;
		result = CAPValidationUtil.validateRequired(observation.getStartTime(), "startTime", bindingResult) && result;
		result = CAPValidationUtil.validateRequired(observation.getEndTime(), "endTime", bindingResult) && result;
		String groupSize = (!observation.getGroupCode().isEmpty())?observation.getGroupCode().get(0):null;
		result = CAPValidationUtil.validateRequired(groupSize, "groupCode", bindingResult) && result;
		
		
		Date cycleStartDate = cycleManager.findCycle(observation.getCycleId()).getStartDate();
		ThreewayMeetingInfo threewayMeetingInfo = threewayMeetingManager.getMeetingsByCycleId(observation.getCycleId());
		Date thirdThreewayMeetingDate = threewayMeetingInfo.getMeetingDate3();
		DateTime startDate = new DateTime(cycleStartDate);
		DateTime tempendDate = startDate.plusDays(365);
		
		Date endDate=thirdThreewayMeetingDate!=null?thirdThreewayMeetingDate:new Date(tempendDate.getMillis());

		SimpleDateFormat sdf=new SimpleDateFormat("MM/dd/yyyy");
		result = result && CAPValidationUtil.validateCycleStartDate(observation.getObservationDate(),
				cycleStartDate, endDate, "observationDate", bindingResult,
				new Object[] { "Date of Observation", "Cycle Start Date" ,sdf.format(endDate)});
		
		threewayMeetingManager.getMeetingsByCycleId(observation.getCycleId());
		
		result = result && CAPValidationUtil.validatObservationTime(observation.getEndTime(),
				observation.getStartTime(), "endTime", bindingResult,
				new Object[] { "Start Time", "End Time" });
		

		if (observation.isCompleteObservation()) {

			for (EvidenceInfo evidence : observation.getEvidences()) {

				if (!evidence.getFocusElement().equals(1))
					continue;

				/*if (observation.isPractitioner())
					CAPValidationUtil.validateRequired(evidence.getSpEvidence(),
							"evidences[" + observation.getEvidences().indexOf(evidence) + "].spEvidence",
							"evidences.spEvidence", bindingResult, evidence.getElementDesc());

				if (observation.isSupervisor())
				CAPValidationUtil.validateRequired(evidence.getPsEvidence(),
						"evidences[" + observation.getEvidences().indexOf(evidence) + "].psEvidence",
						"evidences.psEvidence", bindingResult, evidence.getElementDesc());*/

				CAPValidationUtil.validateRequired(evidence.getCalEvidence(),
						"evidences[" + observation.getEvidences().indexOf(evidence) + "].calEvidence",
						"evidences.calEvidence", bindingResult, evidence.getElementDesc());

			}

			/*if (observation.isPractitioner())
				CAPValidationUtil.validateRequired(observation.getSpRefineFeedback(), "spRefineFeedback",
						bindingResult);

			if (observation.isSupervisor())
			CAPValidationUtil.validateRequired(observation.getPsRefineFeedback(), "psRefineFeedback", bindingResult);*/

			CAPValidationUtil.validateRequired(observation.getCalRefineFeedback(), "calRefineFeedback", bindingResult);

			
			/*if (observation.isPractitioner())
				CAPValidationUtil.validateRequired(observation.getSpReinFeedback(), "spReinFeedback", bindingResult);

			if (observation.isSupervisor())
			CAPValidationUtil.validateRequired(observation.getPsReinFeedback(), "psReinFeedback", bindingResult);*/

			CAPValidationUtil.validateRequired(observation.getCalReinFeedback(), "calReinFeedback", bindingResult);

		}

		return result;
	}
	

	/**
	 * Gets the observation manager.
	 *
	 * @return the observation manager
	 */
	public ObservationManager getObservationManager() {
		return observationManager;
	}

	/**
	 * Sets the observation manager.
	 *
	 * @param observationManager the new observation manager
	 */
	public void setObservationManager(ObservationManager observationManager) {
		this.observationManager = observationManager;
	}

}
