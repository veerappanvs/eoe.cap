package edu.mass.doe.cap.controllers;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.mass.doe.EOEAuthorization.exception.NesterException;
import edu.mass.doe.EOEAuthorization.exception.NoOrganizationFoundException;
import edu.mass.doe.cap.dataservice.pojo.CapCycleInfo;
import edu.mass.doe.cap.dataservice.pojo.CapYear;
import edu.mass.doe.cap.model.candidate.CandidateManager;
import edu.mass.doe.cap.model.util.CAPUtil;

import java.util.ArrayList;
import java.util.Calendar;

import edu.mass.doe.cap.security.EOEUser;
import edu.mass.doe.cap.util.CapUtil;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import edu.mass.doe.EOEAuthorization.DirAdminAccessUtil;
import edu.mass.doe.EOEAuthorization.exception.NesterException;
import edu.mass.doe.EOEAuthorization.exception.NoOrganizationFoundException;
import edu.mass.doe.cap.dataservice.entity.StatusReasonType;
import edu.mass.doe.cap.dataservice.pojo.CapAdminViewInfo;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * The Class HomeController.
 */
@Controller
@RequestMapping("/")
public class HomeController {
	
	
	@Autowired
	private Environment env;
	
	@Autowired(required = true)
	private CandidateManager candidateManager;
	
	@Autowired(required = true)
	private CAPUtil capUtil;
	
	private static Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	boolean isAdminManager = false;
    boolean isManager = false;
    boolean isSupervisor = false;
    boolean ispractitioner = false;
    boolean isCandidate = false;
    
	/**
	 * Landing page.
	 *
	 * @param year the year
	 * @param status the status
	 * @param request the request
	 * @param model the model
	 * @param authentication the authentication
	 * @return the string
	 * @throws NesterException the nester exception
	 * @throws NoOrganizationFoundException the no organization found exception
	 */
	@RequestMapping("home")
	public String landingPage(@RequestParam(value = "selectedYear",required=false)Integer year,@RequestParam(value = "selectedStatus",required=false)String status ,HttpServletRequest request,Model model, Authentication authentication) throws NesterException, NoOrganizationFoundException {
		
		if((boolean) request.getSession().getAttribute("CAP_LOGIN_REDIRECT")){
		request.getSession().setAttribute("CAP_LOGIN_REDIRECT",false);
		request.getSession().setAttribute("timeoutUserNotice",true);
		}
		
		CapYear capYear=capUtil.getCurrSchoolYear();		
		int currYear=capYear.getSchoolYear();
		status=status!=null?status:"Open";
		year =year!=null?year:(status.equals("Open")&&!CapUtil.isAdmin()?0: currYear);

		model.addAttribute("selectedYear", year);
		model.addAttribute("selectedStatus", status);
		model.addAttribute("currentYear", currYear);

		List<CapCycleInfo> capCycles = new ArrayList<CapCycleInfo>();
		List<CapAdminViewInfo> capAdminView = new ArrayList<CapAdminViewInfo>();
		
		authentication = SecurityContextHolder.getContext().getAuthentication();
		EOEUser userContext = (EOEUser) authentication.getPrincipal();
		
		Long orgId = userContext.getOrgId();
		Long personId = userContext.getPersonId();
		String orgCode = "";
		String page= ".home";
		
		orgCode = DirAdminAccessUtil.getOrgInfoByOrgId(orgId.intValue()).getOrgCode();	
		
		if(CapUtil.isManager()){			
			logger.info("I AM A CAP MANAGER FOR ORGCODE = " + orgCode + " AND ORGID = " + orgId + " AND MY PERSONID = " + personId);
			capCycles=candidateManager.fetchAllCapCycles(((EOEUser)authentication.getPrincipal()).getOrgId(),year,status);
			page=".managerHome";
		}
		else if(CapUtil.isSupervisor()){			
			logger.info("I AM A PROGRAM SUPERVISOR FOR ORGCODE = " + orgCode + " AND ORGID = " + orgId + " AND MY PERSONID = " + personId);
			capCycles=candidateManager.fetchAllPsCapCycles(orgCode, personId,year,status);
		}
		else if(CapUtil.ispractitioner()){			
			logger.info("I AM A SUPERVISING PRACTITIONER FOR ORGCODE = " + orgCode + " AND ORGID = " + orgId + " AND MY PERSONID = " + personId);
			capCycles=candidateManager.fetchAllSpCapCycles(orgCode, personId,year,status);
		}
		else if(CapUtil.isCandidate()){
			logger.info("I AM A TEACHER CANDIDATE FOR ORGCODE = " + orgCode + " AND ORGID = " + orgId + " AND MY PERSONID = " + personId);
			capCycles=candidateManager.fetchAllTcCapCycles(orgCode, personId,year,status);
		}
		else if(CapUtil.isAdmin()){
			logger.info("I AM A CAP ADMIN");
			capAdminView=candidateManager.fetchCapAdminView(year);
			logger.info("Records to be returned = " + capAdminView.size());
			page=".capAdminHome";
		}
		
		List<Long> cycles = new ArrayList<Long>();
		capCycles.stream().forEach(temp->{
			cycles.add(temp.getCycleId());
		});
		
		request.getSession().setAttribute("cycles",cycles);

		model.addAttribute("capCycles", capCycles);
		model.addAttribute("capAdminView", capAdminView);
		
		
		return page;
	}
	
	/**
	 * Load ps view.
	 *
	 * @param year the year
	 * @param status the status
	 * @param request the request
	 * @param model the model
	 * @param authentication the authentication
	 * @return the string
	 * @throws NesterException the nester exception
	 * @throws NoOrganizationFoundException the no organization found exception
	 */
	@RequestMapping("home/getPsView")
	public String loadPsView(@RequestParam(value = "selectedYear",required=false)Integer year,@RequestParam(value = "selectedStatus",required=false)String status,HttpServletRequest request,Model model, Authentication authentication) throws NesterException, NoOrganizationFoundException {
		List<CapCycleInfo> capCycles = new ArrayList<CapCycleInfo>();
		
		authentication = SecurityContextHolder.getContext().getAuthentication();
		EOEUser userContext = (EOEUser) authentication.getPrincipal();
		
		Long orgId = userContext.getOrgId();
		Long personId = 1L;
		String orgCode = "";
		String page= ".psViewHome";
		
		CapYear capYear=capUtil.getCurrSchoolYear();		
		int currYear=capYear.getSchoolYear();
				
		status=status!=null?status:"Open";
		year =year!=null?year:(status.equals("Open")?0: currYear);

		model.addAttribute("selectedYear", year);
		model.addAttribute("selectedStatus", status);
		model.addAttribute("currentYear", currYear);
				
		
		orgCode = DirAdminAccessUtil.getOrgInfoByOrgId(orgId.intValue()).getOrgCode();	
	
		if(CapUtil.isManager()){			
			logger.info("I AM A CAP MANAGER FOR ORGCODE = " + orgCode + " AND ORGID = " + orgId + " AND MY PERSONID = " + personId);
			capCycles=candidateManager.fetchAllPsCapCycles(orgCode, personId,year,status);
		}
		
		List<Long> cycles = new ArrayList<Long>();
		capCycles.stream().forEach(temp->{
			cycles.add(temp.getCycleId());
		});
		
		
		request.getSession().setAttribute("cycles",cycles);
		
		model.addAttribute("capCycles", capCycles);
		
		return page;
	}
	
	/**
	 * Login.
	 *
	 * @param request the request
	 * @param response the response
	 * @param authentication the authentication
	 * @return the string
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@GetMapping
	protected String login(HttpServletRequest request, 
		      HttpServletResponse response, Authentication authentication)
		      throws IOException {
		  
		        String targetUrl = determineTargetUrl(authentication,request);
		 return "redirect:"+targetUrl;
		        
		        
		    }
	
	
	/**
	 * Determine target url.
	 *
	 * @param authentication the authentication
	 * @param request the request
	 * @return the string
	 */
	protected String determineTargetUrl(Authentication authentication,HttpServletRequest request) {
      isAdminManager = false;
      isManager = false;
      isSupervisor = false;
      ispractitioner = false;
      isCandidate = false;
      
      Collection<? extends GrantedAuthority> authorities
       = authentication.getAuthorities();
      for (GrantedAuthority grantedAuthority : authorities) { 	 
          if (request.isUserInRole("ROLE_ADMIN")){
          	isAdminManager = true;
              break;
          } else if (request.isUserInRole("ROLE_MANAGER")){
          	isManager = true;
              break;
          }else if (request.isUserInRole("ROLE_SUPERVISOR")) {
          	isSupervisor = true;
              break;
          }else if (request.isUserInRole("ROLE_PRACTITIONER")){
          	ispractitioner = true;
              break;
          }else if (request.isUserInRole("ROLE_CANDIDATE")) {
          	isCandidate = true;
              break;
          }
      }

      if (isAdminManager||isManager||isSupervisor||ispractitioner||isCandidate) {
          return "/home";
      } else {
          throw new IllegalStateException();
      }
  }

	/**
	 * Load cap admin cycles.
	 *
	 * @param year the year
	 * @param status the status
	 * @param orgCode the org code
	 * @param request the request
	 * @param model the model
	 * @return the string
	 * @throws NesterException the nester exception
	 * @throws NoOrganizationFoundException the no organization found exception
	 */
	@RequestMapping("listcamcycles")
	public String loadCapAdminCycles(@RequestParam(value = "selectedYear",required=false)Integer year,@RequestParam(value = "selectedStatus",required=false)String status,@RequestParam("orgCode") String orgCode,HttpServletRequest request, Model model) throws NesterException, NoOrganizationFoundException{	
		logger.info("loadCapAdminCycles for ORGCODE = " + orgCode);
		List<CapCycleInfo> capAdminCycles = new ArrayList<CapCycleInfo>();
		
		year =year!=null?year: Calendar.getInstance().get(Calendar.YEAR);
		status=status!=null?status:"Open";
		model.addAttribute("selectedYear", year);
		model.addAttribute("selectedStatus", status);	
		
		capAdminCycles=candidateManager.fetchAllCapAdminCapCycles(orgCode,year,status);
		
		model.addAttribute("capAdminCycles", capAdminCycles);
		model.addAttribute("orgCode", orgCode);
		
		
		List<Long> cycles = new ArrayList<Long>();
		capAdminCycles.stream().forEach(temp->{
			cycles.add(temp.getCycleId());
		});
		
		request.getSession().setAttribute("cycles",cycles);
		
		
		return ".capAdminCapCycles";
	}
	
	/**
	 * Load year N status.
	 *
	 * @param reqOrgCode the req org code
	 * @param model the model
	 * @throws NesterException the nester exception
	 * @throws NoOrganizationFoundException the no organization found exception
	 */
	@ModelAttribute
	public void loadYearNStatus(@RequestParam(value = "orgCode",required=false)String reqOrgCode,Model model) throws NesterException, NoOrganizationFoundException {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		EOEUser userContext = (EOEUser) authentication.getPrincipal();
		
		Long orgId = userContext.getOrgId();
		Long personId = userContext.getPersonId();
		
		String orgCode = DirAdminAccessUtil.getOrgInfoByOrgId(orgId.intValue()).getOrgCode();	
		
		List<Integer> capSchoolYears=null;
		
		if(CapUtil.isManager()){			
			capSchoolYears=candidateManager.fetchDistinctSchoolYearCapCycles(orgCode);
		}
		else if(CapUtil.isSupervisor()){			
			capSchoolYears=candidateManager.fetchDistinctSchoolYearPsCapCycles(orgCode, personId);
		}
		else if(CapUtil.ispractitioner()){			
			capSchoolYears=candidateManager.fetchDistinctSchoolYearSpCapCycles(orgCode, personId);
		}
		else if(CapUtil.isCandidate()){
			capSchoolYears=candidateManager.fetchDistinctSchoolYearTcCapCycles(personId);
		}
		else if(CapUtil.isAdmin()){
			if(reqOrgCode!=null)
				capSchoolYears=candidateManager.fetchDistinctSchoolYearCapCycles(reqOrgCode);
			else
				capSchoolYears=candidateManager.fetchDistinctSchoolYearCapCycles();
		}
		
		
		List<CapYear> capYears=capUtil.getSchoolYears();
		Map<Integer, String> years = new HashMap<Integer, String>();
		
		capSchoolYears.add(capUtil.getCurrSchoolYear().getSchoolYear());
		
			for(CapYear capYear:capYears){
				if(capSchoolYears.contains(capYear.getSchoolYear()))
					years.put(capYear.getSchoolYear(), capYear.getDesc());
			}
	
	
			Map<String, String> types = new LinkedHashMap<String, String>();

			for (StatusReasonType statusReasonType : candidateManager.getStatusReasonTypes()) {
				types.put(statusReasonType.getTypeCode(), statusReasonType.getTypeDesc());
			}
			
		
			model.addAttribute("statusTypes", types);
			model.addAttribute("years", years);
	} 
}
