package edu.mass.doe.cap.controllers;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.mass.doe.EOEAuthorization.exception.NesterException;
import edu.mass.doe.EOEAuthorization.exception.NoOrganizationFoundException;
import edu.mass.doe.cap.dataservice.pojo.CapYear;
import edu.mass.doe.cap.dataservice.pojo.CycleReportInfo;
import edu.mass.doe.cap.dataservice.pojo.OrganizationInfo;
import edu.mass.doe.cap.model.reports.ReportsManager;
import edu.mass.doe.cap.model.util.CAPUtil;

import java.util.ArrayList;
import edu.mass.doe.cap.security.EOEUser;
import edu.mass.doe.cap.util.CapUtil;
import edu.mass.doe.cap.util.CAPValidationUtil;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import edu.mass.doe.EOEAuthorization.DirAdminAccessUtil;
import edu.mass.doe.EOEAuthorization.exception.NesterException;
import edu.mass.doe.EOEAuthorization.exception.NoOrganizationFoundException;

import org.springframework.web.bind.annotation.RequestParam;

/**
 * The Class ReportsController.
 */
@Controller
@RequestMapping("reports")
public class ReportsController {
	
	@Autowired
	private Environment env;
	
	@Autowired(required = true)
	private ReportsManager reportsManager;
	
	@Autowired
	private CAPUtil capUtil;
	
	private static Logger logger = LoggerFactory.getLogger(ReportsController.class);
	
	boolean isAdmin = false;
    boolean isManager = false;
    
    /**
     * Show form.
     *
     * @param model the model
     * @return the string
     * @throws NesterException the nester exception
     * @throws NoOrganizationFoundException the no organization found exception
     */
    @GetMapping
    public String showForm(Model model) throws NesterException, NoOrganizationFoundException{		
  		
		CycleReportInfo cycleReportInfo = new CycleReportInfo();
		model.addAttribute("cycleReportInfo", cycleReportInfo);
		model.addAttribute("organizationMap", getOrganizations());
		
		CapYear capYear = capUtil.getCurrSchoolYear();
		cycleReportInfo.setSelectSchoolYear(String.valueOf(capYear.getSchoolYear()));
		
		String page= ".annualReport2";
		List<CycleReportInfo> reportInfos = new ArrayList<CycleReportInfo>();
		
		EOEUser userContext = (EOEUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(CapUtil.isManager()){			
			//logger.debug("I AM A CAP MANAGER TRYING TO GET ANNUAL REPORT");
			//Get Cap Manager's Org Code
			Long orgId = userContext.getOrgId();
			String orgCode = DirAdminAccessUtil.getOrgInfoByOrgId(orgId.intValue()).getOrgCode();
			cycleReportInfo.setSelectOrganization(orgCode);
			reportInfos = reportsManager.fetchAllCapManagerReportInfo(cycleReportInfo.getSelectOrganization(), Integer.valueOf(cycleReportInfo.getSelectSchoolYear()));
		}
		else if(CapUtil.isAdmin()){
			//logger.debug("I AM A CAP ADMIN TRYING TO GET ANNUAL REPORT");		
			reportInfos = reportsManager.fetchAllCapAdminReportInfo("0", Integer.valueOf(cycleReportInfo.getSelectSchoolYear()));			
		}
		model.addAttribute("reportInfoList", reportInfos);

		return page;
		
	}
    
    /**
     * Annual report page.
     *
     * @param request the request
     * @param cycleReportInfo the cycle report info
     * @param model the model
     * @param authentication the authentication
     * @param result the result
     * @return the string
     * @throws NesterException the nester exception
     * @throws NoOrganizationFoundException the no organization found exception
     */
    @PostMapping (path = "getReport")
	public String annualReportPage(HttpServletRequest request, @ModelAttribute("cycleReportInfo") CycleReportInfo cycleReportInfo,Model model, Authentication authentication, BindingResult result) throws NesterException, NoOrganizationFoundException {
		List<CycleReportInfo> reportInfos = new ArrayList<CycleReportInfo>();
		
		authentication = SecurityContextHolder.getContext().getAuthentication();
		EOEUser userContext = (EOEUser) authentication.getPrincipal();
				
		String page= ".annualReport2";
	
		if(CapUtil.isManager()){			
			//logger.debug("I AM A CAP MANAGER TRYING TO GET ANNUAL REPORT");
			
			//Get Cap Manager's Org Code
			Long orgId = userContext.getOrgId();
			String orgCode = DirAdminAccessUtil.getOrgInfoByOrgId(orgId.intValue()).getOrgCode();
			cycleReportInfo.setSelectOrganization(orgCode);
									
			reportInfos = reportsManager.fetchAllCapManagerReportInfo(cycleReportInfo.getSelectOrganization(), Integer.valueOf(cycleReportInfo.getSelectSchoolYear()));
			
		}
		else if(CapUtil.isAdmin()){
			//logger.debug("I AM A CAP ADMIN TRYING TO GET ANNUAL REPORT");		
			reportInfos = reportsManager.fetchAllCapAdminReportInfo(cycleReportInfo.getSelectOrganization(), Integer.valueOf(cycleReportInfo.getSelectSchoolYear()));			
		}
		model.addAttribute("reportInfoList", reportInfos);
		model.addAttribute("organizationMap", getOrganizations());
		model.addAttribute("wrapper_width","8000px");
		return page;
	}
	
	/**
	 * Validate input.
	 *
	 * @param cycleReportInfo the cycle report info
	 * @param bindingResult the binding result
	 * @return true, if successful
	 */
	public boolean validateInput(CycleReportInfo cycleReportInfo, BindingResult bindingResult) {
		boolean result = true;
		result = CAPValidationUtil.validateRequired(cycleReportInfo.getSelectSchoolYear(), "selectSchoolYear", bindingResult) && result;	
		return result;
	}
	
	@ModelAttribute
	public void init(Model model) throws Exception {
		model.addAttribute("activateReportsMenu", true);
		
	}
	
	
	/**
	 * Gets the cyle year.
	 *
	 * @param model the model
	 * @return the cyle year
	 * @throws NesterException the nester exception
	 */
	@ModelAttribute
	public void getCyleYear(Model model) throws NesterException {
		
		List<CapYear> capYears=capUtil.getSchoolYears();	
		Map<Integer, String> years = new HashMap<Integer, String>();
		
			for(CapYear capYear:capYears){
					years.put(capYear.getSchoolYear(), capYear.getDesc());
			}
		model.addAttribute("years", years);
	}
	
	
	
	
	
	/**
	 * Gets the organizations.
	 *
	 * @return the organizations
	 */
	public Map<String, String> getOrganizations() {
		Map<String, String> orgMap = new HashMap<String, String>();

		for (OrganizationInfo orgInfo : reportsManager.fetchOrganizationList()) { //need to replace with variable
			orgMap.put(orgInfo.getOrganizationName(),orgInfo.getOrganizationCode());
		}

		return orgMap;
	}
	
}
