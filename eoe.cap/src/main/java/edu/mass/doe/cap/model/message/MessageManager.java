package edu.mass.doe.cap.model.message;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import edu.mass.doe.EOEAuthorization.DirAdminAccessUtil;
import edu.mass.doe.cap.dataservice.entity.Message;
import edu.mass.doe.cap.dataservice.message.MessageService;
import edu.mass.doe.cap.dataservice.pojo.MessageInfo;
import edu.mass.doe.cap.model.cycle.CycleManager;
import edu.mass.doe.cap.model.email.EmailManager;
import edu.mass.doe.cap.security.EOEUser;
import freemarker.template.TemplateException;

/**
 * The Class MessageManager.
 */
@Component
public class MessageManager {
	
	private static Logger logger = LoggerFactory.getLogger(MessageManager.class);

	@Autowired(required = true)
	private MessageService messageService;
	
	@Autowired(required = true)
	private EmailManager emailManager;
	
	@Autowired(required = true)
	private CycleManager cycleManager;
	
	@Autowired
	public Environment env;
	
	/**
	 * Gets the message.
	 *
	 * @param cycleId the cycle id
	 * @return the message
	 */
	public List<MessageInfo> getMessage(Long cycleId) {
		
		
		List<Message> messages =messageService.findMessages(cycleId);
		List<MessageInfo> messageInfos = new ArrayList<MessageInfo>();
		
		messages.stream().forEach(message->{
			MessageInfo messageInfo= new MessageInfo();
			messageInfo.setCycleId(cycleId);
			messageInfo.setMessageId(message.getMessageId());
			messageInfo.setMessage(message.getMessage());
			messageInfo.setDaPersonId(message.getDaPersonId());
			messageInfo.setMessageDate(message.getMessageDate());
			
			try {
				String userRoleCode =DirAdminAccessUtil.getUserInfo(DirAdminAccessUtil.getUserIDByDAPersonID(message.getDaPersonId().intValue()), env.getProperty("app.name")).getUserRoles()[0];
				if(userRoleCode.equals("3"))
					messageInfo.setBgColor("#dce4ef");
				
				if(userRoleCode.equals("723"))
					messageInfo.setBgColor("#8ba6ca");
				
				if(userRoleCode.equals("724"))
					messageInfo.setBgColor("#4773aa");
					
			} catch (Exception e1) {
				logger.error("Error " ,e1);
			}

			
			try {
				Map<String, String> userName=DirAdminAccessUtil.getUserName(message.getDaPersonId());
				messageInfo.setMessageFrom(userName.get("firstName")+" "+userName.get("lastName"));
			} catch (Exception e) {
				logger.error("error " , e);
			}
			messageInfos.add(messageInfo);
		});
		
		
		return messageInfos;
	}
	
	/**
	 * Save.
	 *
	 * @param messageInfo the message info
	 * @throws MessagingException the messaging exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws TemplateException the template exception
	 * @throws Exception the exception
	 */
	public void save(MessageInfo messageInfo) throws MessagingException, IOException, TemplateException, Exception{
		Message message= new Message();
		message.setCycleId(messageInfo.getCycleId());
		message.setMessageDate(new Date());
		message.setEffDate(new Date());
		message.setTypeCode("1");
		message.setDaPersonId(
				((EOEUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getPersonId());
		message.setMessage(messageInfo.getMessage());
		messageService.create(message);
		emailManager.newMessage(messageInfo.getCycleId(), cycleManager.getCycleInfo(messageInfo.getCycleId()));
		
	}
	
	
	

	

}
