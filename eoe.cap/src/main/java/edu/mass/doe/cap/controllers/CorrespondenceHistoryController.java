
package edu.mass.doe.cap.controllers;

import java.io.IOException;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collector;

import javax.annotation.PostConstruct;
import javax.mail.MessagingException;
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
import org.springframework.web.bind.annotation.GetMapping;
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
import edu.mass.doe.cap.dataservice.pojo.CapCycleInfo;
import edu.mass.doe.cap.dataservice.pojo.CapYear;
import edu.mass.doe.cap.dataservice.pojo.CorrespondenceInfo;
import edu.mass.doe.cap.dataservice.pojo.ProgramInfo;
import edu.mass.doe.cap.dataservice.pojo.SponsoringOrg;
import edu.mass.doe.cap.dataservice.pojo.SupervisorInfo;
import edu.mass.doe.cap.model.CorrespondenceManager;
import edu.mass.doe.cap.model.candidate.CandidateManager;
import edu.mass.doe.cap.model.cycle.CycleManager;
import edu.mass.doe.cap.model.email.EmailManager;
import edu.mass.doe.cap.model.util.CAPUtil;
import edu.mass.doe.cap.security.EOEUser;
import edu.mass.doe.cap.util.CAPValidationUtil;
import edu.mass.doe.cap.util.CapUtil;
import edu.mass.doe.cap.util.MapValue;
import freemarker.template.TemplateException;

/**
 * @author vsubramaniyan
 * 
 */
@Controller
@RequestMapping("correspondence")
@SessionAttributes("capCycleInfo")
public class CorrespondenceHistoryController  extends BaseCycleInformation{

	private static Logger logger = LoggerFactory.getLogger(CorrespondenceHistoryController.class);

	private final Map<Integer, String> pageForms = new HashMap<Integer, String>(3);
	
	@Autowired(required = true)
	private CorrespondenceManager correspondenceManager;
	
	@Autowired(required = true)
	private EmailManager  emailManager;

	@Autowired(required = true)
	private CandidateManager candidateManager;
	
	@Autowired(required = true)
	private CycleManager cycleManager;
	
	/**
	 * Initialize.
	 */
	@PostConstruct
	public void initialize() {
		
	}


	/**
	 * Gets the all correspondences  for the given cycle id
	 * @param cycleId
	 * @param capCycleInfo
	 * @param model
	 * @param result
	 * @return
	 * @throws Exception
	 */
	@GetMapping("forcycle")
	public String getCorrespondanceHistoryForCycleId(
			@RequestParam("cycleId") Long cycleId,
			@ModelAttribute("capCycleInfo") CapCycleInfo capCycleInfo, Model model,
			BindingResult result) throws Exception {
			
		
		List<CorrespondenceInfo> correspondences = new ArrayList<CorrespondenceInfo>();
		correspondences = correspondenceManager.getCorrespondenceHistoryForCycle(cycleId);
		
		//capCycleInfo  = cycleManager.getCycleInfo(cycleId);
		capCycleInfo.setCorrespondenceInfo(correspondences);
		
		
		logger.info(capCycleInfo.toString());
		
		model.addAttribute("capCycleInfo", capCycleInfo);
		
		return ".correspondenceHistory";

	}
	


	
	@RequestMapping("resendemail")
	public String  resendEmail(@ModelAttribute("communicationId") Long communicationId, 
			@RequestParam("cycleId") Long cycleId,
			Model model, Principal user,
			BindingResult result) throws MessagingException, IOException, TemplateException {
		
		
			emailManager.resendEmail(communicationId, new Long(0));
			
			
			return ".correspondenceHistory";
			
	}
	
	
	
	@ResponseBody
	@RequestMapping("emailbody")
	public String  getEmailBody(
			@RequestParam("communicationId") Long communicationId,
			@RequestParam("cycleId") Long cycleId,
			Model model) throws NesterException, NumberFormatException, NoOrganizationFoundException {
		logger.info("retriving email body for "+communicationId);
		
		String body = correspondenceManager.getEmailBodyForCommunicaitonId(communicationId);
		
		logger.info(body);

		return body;
	}

}
