package edu.mass.doe.cap.mail;


import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import edu.mass.doe.cap.dataservice.pojo.CorrespondenceInfo;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * The Class EmailService.
 */
@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private FreemarkerConfig markerConfig;
    
    @Autowired
	private Environment env;

    /**
     * Send simple message.
     *
     * @param mail the mail
     * @param template the template
     * @return the string
     * @throws MessagingException the messaging exception
     * @throws IOException Signals that an I/O exception has occurred.
     * @throws TemplateException the template exception
     */
    private String sendSimpleMessage(Mail mail,String template) throws MessagingException, IOException, TemplateException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());


        Template t = markerConfig.getConfig(). getTemplate(template+".ftl");
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, mail.getModel());

        helper.setTo(mail.getTo());
        helper.setText(html, true);
        helper.setSubject(mail.getSubject());
        helper.setFrom(mail.getFrom());

       emailSender.send(message);
        
        return html;
    }
    
    
    
	/**
	 * 
	 * @param correspondenceInfo
	 * @return
	 * @throws MessagingException
	 * @throws IOException
	 * @throws TemplateException
	 */
    public  boolean sendSimpleMessage(Mail mail) throws MessagingException, IOException, TemplateException {
        MimeMessage message = emailSender.createMimeMessage();
        
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());

        String emailTo=env.getProperty("cap.mail.to");

		String from=env.getProperty("cap.mail.from");
		
		boolean isProd= System.getProperty("us.ma.state.edu.env_name").equals("prod");
		if(isProd)
			emailTo=mail.getTo();
        
        helper.setTo(emailTo);
        helper.setText(mail.getBody(), true);
        helper.setSubject(mail.getSubject());
        helper.setFrom(from);

        emailSender.send(message);
        
        return true;
    }
    
    
    /**
     * Gets the text message.
     *
     * @param emailModel the email model
     * @param template the template
     * @return the text message
     * @throws TemplateException the template exception
     * @throws TemplateNotFoundException the template not found exception
     * @throws MalformedTemplateNameException the malformed template name exception
     * @throws ParseException the parse exception
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public String getTextMessage(Map<String, Object> emailModel,String template)throws TemplateException, TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException {
        Template t = markerConfig.getConfig(). getTemplate(template+".ftl");
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, emailModel);
        return html;
    }
    
    
    
    /**
     * Email.
     *
     * @param emailModel the email model
     * @param subject the subject
     * @param to the to
     * @param template the template
     * @return the string
     * @throws MessagingException the messaging exception
     * @throws IOException Signals that an I/O exception has occurred.
     * @throws TemplateException the template exception
     */
    public String email(Map<String, Object> emailModel,String subject,String to,String template ) throws MessagingException, IOException, TemplateException{
    	
        Mail mail = new Mail();
        
        String emailTo=env.getProperty("cap.mail.to");

		String from=env.getProperty("cap.mail.from");
		boolean isProd= System.getProperty("us.ma.state.edu.env_name").equals("prod");
		if(isProd)
			emailTo=to;
        
        mail.setFrom(from);
        mail.setTo(emailTo);
        mail.setSubject(subject);
        mail.setModel(emailModel);
        return  sendSimpleMessage(mail,template);
    }
    

}
