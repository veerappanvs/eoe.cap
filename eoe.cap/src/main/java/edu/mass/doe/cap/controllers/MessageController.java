package edu.mass.doe.cap.controllers;

import java.io.IOException;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import edu.mass.doe.cap.dataservice.message.MessageService;
import edu.mass.doe.cap.dataservice.pojo.CapCycleInfo;
import edu.mass.doe.cap.dataservice.pojo.EvidenceFileInfo;
import edu.mass.doe.cap.dataservice.pojo.MessageInfo;
import edu.mass.doe.cap.file.io.FileSystemStorageService;
import edu.mass.doe.cap.model.message.MessageManager;
import edu.mass.doe.cap.util.CAPValidationUtil;
import freemarker.template.TemplateException;

/**
 * The Class MessageController.
 */
@Controller
@RequestMapping("message")
@SessionAttributes("messageInfo")
public class MessageController extends BaseCycleInformation{
	
	@Autowired
	private MessageManager messageManager;

	/**
	 * Load message.
	 *
	 * @param cycleId the cycle id
	 * @param model the model
	 * @return the string
	 * @throws Exception the exception
	 */
	@RequestMapping
	public String loadMessage(@RequestParam("cycleId") Long cycleId, Model model) throws Exception {

	if(	cycleManager.getCycleInfo(cycleId).getCycleStatusCode()!=null)
		return "redirect:home";
		model.addAttribute("messageInfo", getMessage());
		
	 return ".messageForm";
	}
	
	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public MessageInfo getMessage(){
		MessageInfo messageCycleInfo = new MessageInfo();
		return messageCycleInfo;
	}
	
	/**
	 * Load file.
	 *
	 * @param cycleId the cycle id
	 * @param model the model
	 * @throws Exception the exception
	 */
	@ModelAttribute
	public void loadFile(@RequestParam("cycleId") Long cycleId, Model model) throws Exception {
		
		model.addAttribute("messages", messageManager.getMessage(cycleId));
		CapCycleInfo cycleInfo = cycleManager.getCycleInfo(cycleId);
		model.addAttribute("cycleInfo", cycleInfo);
		
	}
	
	/**
	 * Save message.
	 *
	 * @param messageInfo the message info
	 * @param result the result
	 * @return the string
	 * @throws MessagingException the messaging exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws TemplateException the template exception
	 * @throws Exception the exception
	 */
	@PostMapping
	public String saveMessage(@ModelAttribute("messageInfo") MessageInfo messageInfo,
			BindingResult result) throws MessagingException, IOException, TemplateException, Exception{
		if(validate(messageInfo, result))
			messageManager.save(messageInfo);
		else return ".messageForm";
			
		return "redirect:message?cycleId="+messageInfo.getCycleId();
		
	}
	
	/**
	 * Validate.
	 *
	 * @param messageInfo the message info
	 * @param bindingResult the binding result
	 * @return true, if successful
	 */
	public boolean validate(MessageInfo messageInfo,BindingResult bindingResult){
		boolean result=true;
		
		result=CAPValidationUtil.validateMessage(messageInfo.getMessage(), "message", bindingResult, new Object[] {});
				
		return result;	
		
	}
}
