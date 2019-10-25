package edu.mass.doe.cap.controllers;

import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.OptimisticLockException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import edu.mass.doe.cap.dataservice.entity.ObservationGroupType;
import edu.mass.doe.cap.dataservice.pojo.AssessmentInfo;
import edu.mass.doe.cap.dataservice.pojo.CapCycleInfo;
import edu.mass.doe.cap.dataservice.pojo.DimensionInfo;
import edu.mass.doe.cap.dataservice.pojo.ElementInfo;
import edu.mass.doe.cap.dataservice.pojo.ObservationInfo;
import edu.mass.doe.cap.util.CapUtil;

/**
 * The Class SummativeAssessmentController.
 */
@Controller
@RequestMapping("summative")
@SessionAttributes("assessmentInfo")
public class SummativeAssessmentController extends BaseCycleInformation {

	@Autowired
	private Environment env;

	/**
	 * Gets the assessment.
	 *
	 * @param cycleId the cycle id
	 * @param typeCode the type code
	 * @param scrollX the scroll X
	 * @param scrollY the scroll Y
	 * @param model the model
	 * @param authentication the authentication
	 * @return the assessment
	 * @throws Exception the exception
	 */
	@RequestMapping
	public String getAssessment(@RequestParam("cycleId") Long cycleId, @RequestParam("typeCode") String typeCode,
			@RequestParam(name="scrollX",required=false) Double scrollX, @RequestParam(name="scrollY",required=false) Double scrollY,Model model, Authentication authentication) throws Exception {

		AssessmentInfo assessmentInfo = assessmentManager.getAssessmentInfo(cycleId, "3");
		loadAssessment(assessmentInfo, cycleId, typeCode, model, authentication);
		
	
		model.addAttribute("scrollX", scrollX!=null?scrollX:0);
		model.addAttribute("scrollY", scrollY!=null?scrollY:0);
		
		String page=".assessmentSummativeView";
		
		
		if (CapUtil.isCandidate() && assessmentInfo.getCompleted().equals('N')) {
			return "redirect:.home";
		}

		if (cycleManager.getCycleInfo(cycleId).getCycleStatusCode() != null)
			return page;

		if ((CapUtil.isSupervisor()||CapUtil.isManager()) && assessmentInfo.getCompleted().equals('N')
				&& assessmentInfo.getPsApproved().equals('N')) {
			return ".assessmentSummative";
		}

		if (CapUtil.ispractitioner() && assessmentInfo.getCompleted().equals('N')
				&& assessmentInfo.getSpApproved().equals('N')) {
			return ".assessmentSummative";
		}

		return page;
	}

	/**
	 * Load assessment.
	 *
	 * @param assessmentInfo the assessment info
	 * @param cycleId the cycle id
	 * @param typeCode the type code
	 * @param model the model
	 * @param authentication the authentication
	 * @throws Exception the exception
	 */
	public void loadAssessment(AssessmentInfo assessmentInfo, Long cycleId, String typeCode, Model model,
			Authentication authentication) throws Exception {

		CapCycleInfo cycleInfo = cycleManager.getCycleInfo(cycleId);
		assessmentInfo.setReadyToTeach(cycleInfo.getReadyToTeach());
		
		List<DimensionInfo> dimensions = assessmentManager.getAlldimensions();
		ObservationInfo observationInfo=observationManager.getObservation(cycleId, 1L, "01");
		if(!observationInfo.isCompleteObservation())
			observationInfo.setObservationDate(null);
		assessmentInfo.setAnnouncedObservation1(observationInfo);
		
		observationInfo=observationManager.getObservation(cycleId, 2L, "01");
		if(!observationInfo.isCompleteObservation())
			observationInfo.setObservationDate(null);
		assessmentInfo.setAnnouncedObservation2(observationInfo);
		
		observationInfo=observationManager.getObservation(cycleId, 1L, "02");
		if(!observationInfo.isCompleteObservation())
			observationInfo.setObservationDate(null);
		assessmentInfo.setUnAnnouncedObservation(observationInfo);
		
		observationInfo=observationManager.getObservation(cycleId, 2L, "02");
		if(!observationInfo.isCompleteObservation())
			observationInfo.setObservationDate(null);
		assessmentInfo.setUnAnnouncedObservation2(observationInfo);
		
		boolean displayEdit = false;

		displayEdit = cycleInfo.getCycleStatusCode() == null;
		model.addAttribute("displayEdit", displayEdit);
		

		assessmentInfo.setCanSignOff(canSignOffAssessment(assessmentInfo));
		model.addAttribute("assessmentInfo", assessmentInfo);
		model.addAttribute("cycleInfo", cycleInfo);
		model.addAttribute("dimensions", dimensions);

	}

	/**
	 * Can sign off assessment.
	 *
	 * @param assessmentInfo the assessment info
	 * @return true, if successful
	 */
	public boolean canSignOffAssessment(AssessmentInfo assessmentInfo) {
		boolean canSignassessment = assessmentInfo.getElements().stream()
				.allMatch(temp -> temp.getCompleted().equals('Y'));
		
		canSignassessment = canSignassessment && assessmentInfo.getEvidenceFeedback() != null
				&& !assessmentInfo.getEvidenceFeedback().isEmpty();
		canSignassessment = canSignassessment && assessmentInfo.getRecommendedfocus() != null
				&& !assessmentInfo.getRecommendedfocus().isEmpty();

		if (canSignassessment) {
			if ((CapUtil.isSupervisor() ||CapUtil.isManager()) && assessmentInfo.getPsApproved().equals('Y')) {
				canSignassessment = false;
			}

			if (CapUtil.ispractitioner() && assessmentInfo.getSpApproved().equals('Y')) {
				canSignassessment = false;
			}
		}
		return canSignassessment;

	}

	/**
	 * Update assessment.
	 *
	 * @param request the request
	 * @param elementCode the element code
	 * @param scrollX the scroll X
	 * @param scrollY the scroll Y
	 * @param assessmentInfo the assessment info
	 * @param model the model
	 * @param authentication the authentication
	 * @return the string
	 * @throws Exception the exception
	 */
	@PostMapping
	@PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_SUPERVISOR','ROLE_PRACTITIONER')")
	public String updateAssessment(HttpServletRequest request, @RequestParam("elementCode") String elementCode,
			@RequestParam("scrollX") double scrollX, @RequestParam("scrollY") double scrollY,
			@ModelAttribute("assessmentInfo") AssessmentInfo assessmentInfo, Model model, Authentication authentication)
			throws Exception {
		
		
		if(request.getParameter("cancel")!=null){
			return "redirect:/home";
		}
		
		if(request.getParameter("edit")!=null){
			assessmentManager.unlockAsssessment(assessmentInfo.getAssessmentId());
			return "redirect:/summative?typeCode=3&cycleId="+assessmentInfo.getCycleId();
		
		}
		
		

		assessmentInfo.getElements().forEach(elementInfo -> {

			elementInfo.setCompleted('Y');
			elementInfo.getDimensions().forEach(dimensionInfo -> {
				if (elementInfo.getCompleted().equals('Y') && ((dimensionInfo.getRatingCode() == null
						|| dimensionInfo.getRatingCode().isEmpty())
						|| (elementInfo.getRationale() == null || elementInfo.getRationale().trim().isEmpty()))) {
					elementInfo.setCompleted('N');
				}

			});

		});

		if (assessmentInfo.isCanSignOff() && assessmentInfo.isAssignmentSigned()
				&& canSignOffAssessment(assessmentInfo)) {
			if (CapUtil.isSupervisor()||CapUtil.isManager()) {
				assessmentInfo.setPsApproved('Y');
				assessmentInfo.setPsApprovedDate(new Date());
				assessmentInfo.setSpApproved('Y');
				assessmentInfo.setSpApprovedDate(new Date());
			}

			if (CapUtil.ispractitioner()) {
				assessmentInfo.setSpApproved('Y');
				assessmentInfo.setSpApprovedDate(new Date());
			}
		}
		
		if(assessmentInfo.getPsApproved().equals('Y') && assessmentInfo.getSpApproved().equals('Y')){
			assessmentInfo.setCompleted('Y');
			assessmentInfo.setLocked('Y');
		}
		
		
		try {
			assessmentManager.save(assessmentInfo, elementCode.equals("ALL") ? null : elementCode);
		} catch (OptimisticLockException oe) {
			model.addAttribute("cannotSave", true);
			return ".assessmentSummative";
		} catch (Exception e) {
			try {
				assessmentManager.save(assessmentInfo, elementCode.equals("ALL") ? null : elementCode);
			} catch (OptimisticLockException oe) {
				model.addAttribute("cannotSave", true);
				return ".assessmentSummative";
			}
		}
		
		model.addAttribute("scrollX", scrollX);
		model.addAttribute("scrollY", scrollY);


		if (assessmentInfo.getCompleted() != null && assessmentInfo.getCompleted().equals('Y')
				|| (CapUtil.isSupervisor()|| CapUtil.isManager())&& assessmentInfo.getPsApproved().equals('Y')
				|| CapUtil.ispractitioner() && assessmentInfo.getSpApproved().equals('Y'))
			return "redirect:/summative?cycleId=" + assessmentInfo.getCycleId()  + "&typeCode="
					+ assessmentInfo.getTypeCode();

		return "redirect:/summative?cycleId="+assessmentInfo.getCycleId() + "&typeCode="+ assessmentInfo.getTypeCode()+"&scrollX="+scrollX+"&scrollY="+scrollY;

	}

	/**
	 * Gets the group types.
	 *
	 * @return the group types
	 */
	@ModelAttribute("ratingTypes")
	public Map<String, String> getGroupTypes() {
		return assessmentManager.getAllRatingTypes();
	}

}
