package edu.mass.doe.cap.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import edu.mass.doe.EOEAuthorization.DirAdminAccessUtil;
import edu.mass.doe.cap.dataservice.pojo.DimensionInfo;
import edu.mass.doe.cap.dataservice.pojo.ImportedCapInfo;
import edu.mass.doe.cap.model.assessment.AssessmentManager;
import edu.mass.doe.cap.model.batch.BatchManager;
import edu.mass.doe.cap.model.message.MessageManager;
import edu.mass.doe.cap.security.EOEUser;
import edu.mass.doe.cap.util.CapUtil;

/**
 * The Class ImportedCapController.
 */
@Controller
@RequestMapping("importedCapInfo")
@SessionAttributes("importedCapInfo")
public class ImportedCapController {

	@Autowired
	private BatchManager batchManager;

	@Autowired
	private AssessmentManager assessmentManager;

	/**
	 * Load cap info.
	 *
	 * @param importId the import id
	 * @param model the model
	 * @return the string
	 * @throws Exception the exception
	 */
	@RequestMapping
	public String loadCapInfo(@RequestParam("importId") Long importId, Model model) throws Exception {
		EOEUser userContext = (EOEUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Long orgId = userContext.getOrgId();
		String orgCode = CapUtil.isAdmin()?"1": DirAdminAccessUtil.getOrgInfoByOrgId(orgId.intValue()).getOrgCode();
		ImportedCapInfo importedCapInfo = batchManager.getUploadedDataInfo(orgCode,importId);
		model.addAttribute("importedCapInfo", importedCapInfo);
		List<DimensionInfo> dimensions = assessmentManager.getAlldimensions();
		model.addAttribute("dimensions", dimensions);
		return ".importedCapInfo";

	}

	/**
	 * Delete cap info.
	 *
	 * @param request the request
	 * @param importId the import id
	 * @param model the model
	 * @return the string
	 * @throws Exception the exception
	 */
	@PostMapping
	@PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_SUPERVISOR')")
	public String deleteCapInfo(HttpServletRequest request, @RequestParam("importId") Long importId, Model model)
			throws Exception {

		if (request.getParameter("Delete") != null) {
			batchManager.deleteUploadedData(importId);
		}		
		
		return "redirect:/importedCapList";
	}

}
