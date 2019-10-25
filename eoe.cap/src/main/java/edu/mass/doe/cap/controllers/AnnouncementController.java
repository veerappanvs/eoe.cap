package edu.mass.doe.cap.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import edu.mass.doe.cap.dataservice.message.MessageService;
import edu.mass.doe.cap.dataservice.pojo.AnnouncementInfo;
import edu.mass.doe.cap.dataservice.pojo.CapCycleInfo;
import edu.mass.doe.cap.dataservice.pojo.EvidenceFileInfo;
import edu.mass.doe.cap.dataservice.pojo.MessageInfo;
import edu.mass.doe.cap.file.io.FileSystemStorageService;
import edu.mass.doe.cap.model.announcement.AnnouncementManager;
import edu.mass.doe.cap.model.message.MessageManager;
import edu.mass.doe.cap.util.CAPValidationUtil;
import edu.mass.doe.cap.util.CapUtil;

@Controller
@RequestMapping("announcement")
@SessionAttributes("announcementInfo")
public class AnnouncementController {
	
	@Autowired
	private AnnouncementManager announcementManager;

	@RequestMapping
	public String loadAnnouncement( Model model) throws Exception {
		 AnnouncementInfo  announcementInfo=	new AnnouncementInfo();
		 announcementInfo.setPrevText(announcementManager.getAnnouncement().getText());
		model.addAttribute("announcementInfo", new AnnouncementInfo());
		model.addAttribute("message", announcementManager.getAnnouncement());
	 return ".announcementForm";
	}
	
	
	
	
	@PostMapping
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public String saveAnnouncement(HttpServletRequest request,@ModelAttribute("announcementInfo") AnnouncementInfo announcementInfo, Model model,
			BindingResult result){
		
		

		if(request.getParameter("delete")!=null){
			announcementManager.delete(announcementInfo);
			return "redirect:announcement";
		}
		
		model.addAttribute("message", announcementManager.getAnnouncement());
		
		if(validate(announcementInfo, result))
			announcementManager.update(announcementInfo);
		else return ".announcementForm";
			
		return "redirect:announcement";
		
	}
	
	public boolean validate(AnnouncementInfo announcementInfo,BindingResult bindingResult){
		boolean result=true;
		
		result=CAPValidationUtil.validateMessage(announcementInfo.getText(), "text", bindingResult, new Object[] {});
				
		return result;	
		
	}
}
