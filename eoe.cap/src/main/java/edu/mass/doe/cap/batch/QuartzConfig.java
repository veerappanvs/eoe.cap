package edu.mass.doe.cap.batch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.quartz.CronTrigger;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobDetail;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.spi.JobFactory;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.configuration.JobLocator;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.support.JobRegistryBeanPostProcessor;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * The Class QuartzConfig.
 */
@Configuration
@PropertySources({@PropertySource("classpath:application_${us.ma.state.edu.env_name}.properties")})
@DisallowConcurrentExecution
public class QuartzConfig {

	@Autowired
	private JobLauncher jobLauncher;
	
	@Autowired
	private JobLocator jobLocator;

	@Autowired
    @Qualifier(value = "capDataSource") 
    DataSource dataSource;
	
	@Value("${cap.daily.notification}")
    private String dailyNotifiction;
	
	@Value("${cap.daily.autoclose}")
    private String dailyAutoClose;
	
	@Value("${cap.annual.notification}")
    private String annualNotifiction;
	
	@Value("${cap.daily.notification.controlid}")
    private String dailyNotifictionCtrlId;
	
	@Value("${cap.daily.annualnotifiction.controlid}")
    private String annualNotifictionCtrlId;
	
	@Value("${cap.daily.autoclose.controlid}")
    private String dailyAutocloseCtrlId;
	
	@Autowired
	private Environment environment;
	
	List<CronTriggerFactoryBean> triggerFactories = new ArrayList<CronTriggerFactoryBean>();
	
 
	/**
	 * Job registry bean post processor.
	 *
	 * @param jobRegistry the job registry
	 * @return the job registry bean post processor
	 */
	@Bean
	public JobRegistryBeanPostProcessor jobRegistryBeanPostProcessor(JobRegistry jobRegistry) {
		JobRegistryBeanPostProcessor jobRegistryBeanPostProcessor = new JobRegistryBeanPostProcessor();
		jobRegistryBeanPostProcessor.setJobRegistry(jobRegistry);
		return jobRegistryBeanPostProcessor;

	}

 
	 /**
 	 * Daily notification quartz job.
 	 *
 	 * @return the job detail factory bean
 	 */
 	@Bean(name="dailyNotificationQuartzJob")
	 public JobDetailFactoryBean dailyNotificationQuartzJob() {
	  JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
	  jobDetailFactoryBean.setDurability(true);
	  jobDetailFactoryBean.setJobClass(QuartzJobLauncher.class);
	  Map<String, Object> map = new HashMap<String, Object>();  
	  map.put("jobName", "dailyNotificationJob");
	  map.put("jobControlId", dailyNotifictionCtrlId);
	  jobDetailFactoryBean.setName("dailyNotificationQuartzJob");
	  jobDetailFactoryBean.setJobDataAsMap(map);
	  return jobDetailFactoryBean;
	 }
	 
	 /**
 	 * Daily auto close quartz job.
 	 *
 	 * @return the job detail factory bean
 	 */
 	@Bean(name="dailyAutoCloseQuartzJob")
	 public JobDetailFactoryBean dailyAutoCloseQuartzJob() {
	  JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
	  jobDetailFactoryBean.setDurability(true);
	  jobDetailFactoryBean.setJobClass(QuartzJobLauncher.class);
	  Map<String, Object> map = new HashMap<String, Object>();  
	  map.put("jobName", "dailyAutoCloseJob");
	  map.put("jobControlId", dailyAutocloseCtrlId);
	  jobDetailFactoryBean.setName("dailyAutoCloseQuartzJob");
	  jobDetailFactoryBean.setJobDataAsMap(map);
	  return jobDetailFactoryBean;
	 }
 
	 /**
 	 * Annual notification quartz job.
 	 *
 	 * @return the job detail factory bean
 	 */
 	@Bean(name="annualNotificationQuartzJob")
	 public JobDetailFactoryBean annualNotificationQuartzJob() {
	  JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
	  jobDetailFactoryBean.setDurability(true);
	  jobDetailFactoryBean.setJobClass(QuartzJobLauncher.class);
	  Map<String, Object> map = new HashMap<String, Object>();  
	  map.put("jobName", "annualNotificationJob");
	  map.put("jobControlId", annualNotifictionCtrlId);
	  jobDetailFactoryBean.setName("annualNotificationQuartzJob");
	  jobDetailFactoryBean.setJobDataAsMap(map);
	  return jobDetailFactoryBean;
	 }
 

	 /**
 	 * Daily notification job trigger.
 	 *
 	 * @param jobDetailFactoryBean the job detail factory bean
 	 * @return the cron trigger factory bean
 	 */
 	@Bean(name="dailyNotificationJobTrigger")
	 public CronTriggerFactoryBean dailyNotificationJobTrigger(@Qualifier(value="dailyNotificationQuartzJob")JobDetailFactoryBean jobDetailFactoryBean) {
	  CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
	  cronTriggerFactoryBean.setJobDetail(jobDetailFactoryBean.getObject());
	  cronTriggerFactoryBean.setCronExpression(dailyNotifiction);
	  cronTriggerFactoryBean.setName("dailyNotificationJobTrigger");
	  cronTriggerFactoryBean.setMisfireInstruction(CronTrigger.MISFIRE_INSTRUCTION_DO_NOTHING);
	  triggerFactories.add(cronTriggerFactoryBean);
	  return cronTriggerFactoryBean;
	 }
	 
	 /**
 	 * Daily auto close job trigger.
 	 *
 	 * @param jobDetailFactoryBean the job detail factory bean
 	 * @return the cron trigger factory bean
 	 */
 	@Bean(name="dailyAutoCloseJobTrigger")
	 public CronTriggerFactoryBean dailyAutoCloseJobTrigger(@Qualifier(value="dailyAutoCloseQuartzJob")JobDetailFactoryBean jobDetailFactoryBean) {
	  CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
	  cronTriggerFactoryBean.setJobDetail(jobDetailFactoryBean.getObject());
	  cronTriggerFactoryBean.setCronExpression(dailyAutoClose);
	  cronTriggerFactoryBean.setName("dailyAutoCloseJobTrigger");
	  cronTriggerFactoryBean.setMisfireInstruction(CronTrigger.MISFIRE_INSTRUCTION_DO_NOTHING);
	  triggerFactories.add(cronTriggerFactoryBean);
	  return cronTriggerFactoryBean;
	 }
	 
	 /**
 	 * Annual notification job trigger.
 	 *
 	 * @param jobDetailFactoryBean the job detail factory bean
 	 * @return the cron trigger factory bean
 	 */
 	@Bean(name="annualNotificationJobTrigger")
	 public CronTriggerFactoryBean annualNotificationJobTrigger(@Qualifier(value="annualNotificationQuartzJob")JobDetailFactoryBean jobDetailFactoryBean) {
	  CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
	  cronTriggerFactoryBean.setJobDetail(jobDetailFactoryBean.getObject());
	  cronTriggerFactoryBean.setCronExpression(annualNotifiction);
	  cronTriggerFactoryBean.setName("annualNotificationJobTrigger");
	  cronTriggerFactoryBean.setMisfireInstruction(CronTrigger.MISFIRE_INSTRUCTION_FIRE_ONCE_NOW);
	  triggerFactories.add(cronTriggerFactoryBean);
	  return cronTriggerFactoryBean;
	 }
 
	
 
	 /**
 	 * Quartz properties.
 	 *
 	 * @return the properties
 	 * @throws IOException Signals that an I/O exception has occurred.
 	 */
 	@Bean
		public Properties quartzProperties() throws IOException {
			PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
			propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
			propertiesFactoryBean.afterPropertiesSet();
			return propertiesFactoryBean.getObject();
		}
	 
	 /**
 	 * Job factory.
 	 *
 	 * @param applicationContext the application context
 	 * @return the job factory
 	 */
 	@Bean
		public JobFactory jobFactory(ApplicationContext applicationContext) {
			AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();
			jobFactory.setApplicationContext(applicationContext);
			return jobFactory;
		}
	
	 
	 /**
 	 * Scheduler factory bean.
 	 *
 	 * @param jobFactory the job factory
 	 * @return the scheduler factory bean
 	 * @throws Exception the exception
 	 */
 	@Bean
	 public SchedulerFactoryBean schedulerFactoryBean(JobFactory jobFactory ) throws Exception {
	  SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
	  schedulerFactoryBean.setQuartzProperties(quartzProperties());
	  schedulerFactoryBean.setAutoStartup(true);	
	  schedulerFactoryBean.setOverwriteExistingJobs(true);
	  Map<String, Object> map = new HashMap<String, Object>();  
	  map.put("jobLauncher", jobLauncher);
	  map.put("jobLocator", jobLocator);
	  schedulerFactoryBean.setSchedulerContextAsMap(map);
	  schedulerFactoryBean.setJobFactory(jobFactory);	  
	  schedulerFactoryBean.setDataSource(dataSource);
	  schedulerFactoryBean.afterPropertiesSet();
  
	  List<Trigger> triggers = new ArrayList<Trigger>();
	  for(CronTriggerFactoryBean cronTriggerFactoryBean:triggerFactories){
	  triggers.add(cronTriggerFactoryBean.getObject());
	  }
		 
	  if (triggers != null && !triggers.isEmpty()) {
	      schedulerFactoryBean.setTriggers(triggers.toArray(new Trigger[triggers.size()]));
	 }
	  
	 
	  return schedulerFactoryBean;
	 }
} 