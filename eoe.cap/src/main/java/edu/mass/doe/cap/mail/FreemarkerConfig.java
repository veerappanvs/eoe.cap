package edu.mass.doe.cap.mail;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;

/**
 * The Class FreemarkerConfig.
 */
@Component
public class FreemarkerConfig {

	
    private Configuration config;

	
/**
 * Instantiates a new freemarker config.
 *
 * @throws IOException Signals that an I/O exception has occurred.
 * @throws TemplateException the template exception
 */
public FreemarkerConfig() throws IOException, TemplateException {
	 FreeMarkerConfigurationFactoryBean bean = new FreeMarkerConfigurationFactoryBean();
     bean.setTemplateLoaderPath("/templates/");
     config= bean.createConfiguration();
}


/**
 * Gets the config.
 *
 * @return the config
 */
public Configuration getConfig() {
	return config;
}


/**
 * Sets the config.
 *
 * @param config the new config
 */
public void setConfig(Configuration config) {
	this.config = config;
}

 
   
}
