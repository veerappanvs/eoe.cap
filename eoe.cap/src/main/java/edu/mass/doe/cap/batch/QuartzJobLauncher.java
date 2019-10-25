package edu.mass.doe.cap.batch;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.configuration.JobLocator;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.NoSuchJobException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import edu.mass.doe.cap.config.RequestInterceptor;

/**
 * The Class QuartzJobLauncher.
 */
public class QuartzJobLauncher extends QuartzJobBean {
	
	private static Logger log = LoggerFactory.getLogger(QuartzJobLauncher.class);


	private String jobName;
	private String jobControlId;
	private JobLauncher jobLauncher;
	private JobLocator jobLocator;
	
	/**
	 * Gets the job name.
	 *
	 * @return the job name
	 */
	public String getJobName() {
		return jobName;
	}
	
	/**
	 * Sets the job name.
	 *
	 * @param jobName the new job name
	 */
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	
	/**
	 * Gets the job launcher.
	 *
	 * @return the job launcher
	 */
	public JobLauncher getJobLauncher() {
		return jobLauncher;
	}
	
	/**
	 * Gets the job control id.
	 *
	 * @return the job control id
	 */
	public String getJobControlId() {
		return jobControlId;
	}
	
	/**
	 * Sets the job control id.
	 *
	 * @param jobControlId the new job control id
	 */
	public void setJobControlId(String jobControlId) {
		this.jobControlId = jobControlId;
	}
	
	/**
	 * Sets the job launcher.
	 *
	 * @param jobLauncher the new job launcher
	 */
	public void setJobLauncher(JobLauncher jobLauncher) {
		this.jobLauncher = jobLauncher;
	}
	
	/**
	 * Gets the job locator.
	 *
	 * @return the job locator
	 */
	public JobLocator getJobLocator() {
		return jobLocator;
	}
	
	/**
	 * Sets the job locator.
	 *
	 * @param jobLocator the new job locator
	 */
	public void setJobLocator(JobLocator jobLocator) {
		this.jobLocator = jobLocator;
	}
	
	


	/* (non-Javadoc)
	 * @see org.springframework.scheduling.quartz.QuartzJobBean#executeInternal(org.quartz.JobExecutionContext)
	 */
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		try {
			
			
			Job job = jobLocator.getJob(jobName);
			JobExecution jobExecution = jobLauncher.run(job,newExecution(context.getScheduler().getSchedulerInstanceId()));
			
			
		} catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException | JobParametersInvalidException | NoSuchJobException | SchedulerException e) {
			try {
				context.getScheduler().unscheduleJob(context.getTrigger().getKey()) ; 
			} catch (SchedulerException e1) {
				log.error("SchedulerException",e1);
			}
		}
		
		

	}
	
	/**
	 * Gets the host name.
	 *
	 * @return the host name
	 */
	public String getHostName() {
	    String hostName = "";
	    try {
	        java.net.InetAddress addr = InetAddress.getLocalHost();
	        hostName = addr.getHostName();
	    } catch (UnknownHostException ex) {
	    }
	    return hostName; }

	
	
	/**
	 * New execution.
	 *
	 * @param schedulerInstanceId the scheduler instance id
	 * @return the job parameters
	 */
	private JobParameters newExecution(String schedulerInstanceId) {
        Map<String, JobParameter> parameters = new HashMap<String, JobParameter>();
        JobParameter parameter = new JobParameter(new Date());
        parameters.put("currentTime", parameter);
        parameter = new JobParameter(jobControlId);
        parameters.put("jobControlId", parameter);
        parameter = new JobParameter(schedulerInstanceId);
        parameters.put("schedulerInstanceId", parameter);
        parameter = new JobParameter(getHostName());
        parameters.put("hostName", parameter);
        return new JobParameters(parameters);
    }
	
	

}