package edu.mass.doe.cap.batch;

import java.util.ArrayList;
import java.util.List;

import org.quartz.JobExecutionContext;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.mass.doe.cap.dataservice.entity.CapCycle;
import edu.mass.doe.cap.dataservice.pojo.CapCycleInfo;
import edu.mass.doe.cap.dataservice.pojo.EmailGenerationControlInfo;
import edu.mass.doe.cap.model.job.BatchJobManager;



/**
 * The Class CycleReader.
 */
@Component
public class CycleReader implements Tasklet, StepExecutionListener{
	
	
	List<CapCycleInfo> list;
	
	String  controlId;
	
	@Autowired
	BatchJobManager batchMnager;
	
	EmailGenerationControlInfo emailGenerationControlInfo;
	
	
	/* (non-Javadoc)
	 * @see org.springframework.batch.core.StepExecutionListener#beforeStep(org.springframework.batch.core.StepExecution)
	 */
	@Override
	public void beforeStep(StepExecution stepExecution) {
		controlId=stepExecution.getJobExecution().getJobParameters().getString("jobControlId");
		list=new ArrayList<CapCycleInfo>();
	}

	/* (non-Javadoc)
	 * @see org.springframework.batch.core.StepExecutionListener#afterStep(org.springframework.batch.core.StepExecution)
	 */
	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		stepExecution.getJobExecution().getExecutionContext().put("cycles",list);
		stepExecution.getJobExecution().getExecutionContext().put("emailGenerationControlInfo",emailGenerationControlInfo);
		stepExecution.setReadCount(list.size());
		return ExitStatus.COMPLETED;
	}

	/* (non-Javadoc)
	 * @see org.springframework.batch.core.step.tasklet.Tasklet#execute(org.springframework.batch.core.StepContribution, org.springframework.batch.core.scope.context.ChunkContext)
	 */
	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		emailGenerationControlInfo=batchMnager.getEmailGenControl(new Long(controlId));
		list.addAll(batchMnager.getCapCycles(emailGenerationControlInfo.getSlectionQuery()));
		return RepeatStatus.FINISHED;
	}

}
