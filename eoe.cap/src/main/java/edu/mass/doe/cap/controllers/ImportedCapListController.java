package edu.mass.doe.cap.controllers;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.mass.doe.EOEAuthorization.DirAdminAccessUtil;
import edu.mass.doe.EOEAuthorization.exception.NesterException;
import edu.mass.doe.cap.dataservice.pojo.CapYear;
import edu.mass.doe.cap.dataservice.pojo.ImportedCapInfo;
import edu.mass.doe.cap.model.batch.BatchManager;
import edu.mass.doe.cap.model.util.CAPUtil;
import edu.mass.doe.cap.security.EOEUser;
import edu.mass.doe.cap.util.CapUtil;

/**
 * The Class ImportedCapListController.
 */
@Controller
@RequestMapping("importedCapList")
public class ImportedCapListController {
	
	@Autowired
	private BatchManager batchManager;
	
	@Autowired
	private CAPUtil capUtil;
	
	/**
	 * Load cap info.
	 *
	 * @param year the year
	 * @param model the model
	 * @return the string
	 * @throws Exception the exception
	 */
	@RequestMapping
	public String loadCapInfo(@RequestParam(value="year",required=false) String year,@RequestParam(value="status",required=false) String status, Model model) throws Exception {
	
	year=year!=null?year:"0";	
	status=status!=null?status:"0";
	EOEUser userContext = (EOEUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	Long orgId = userContext.getOrgId();
	String orgCode = CapUtil.isAdmin()?"1": DirAdminAccessUtil.getOrgInfoByOrgId(orgId.intValue()).getOrgCode();
	List<ImportedCapInfo> importedCapInfos=	batchManager.getImportedCapReport(orgCode,year,status);
	model.addAttribute("importedCapInfos",importedCapInfos);
	model.addAttribute("selectedYear",year);
	model.addAttribute("selectedStatus",status);
	
	return ".importedCapList";
		
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

}
