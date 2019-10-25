package edu.mass.doe.cap.batch;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

import edu.mass.doe.cap.dataservice.entity.CapCycle;
import edu.mass.doe.cap.dataservice.pojo.CapCycleInfo;
import edu.mass.doe.cap.dataservice.pojo.EmailGenerationControlInfo;
import edu.mass.doe.cap.model.email.EmailManager;
import edu.mass.doe.cap.model.job.BatchJobManager;

/**
 * The Class CycleItemWriter.
 */
public class CycleItemWriter implements Tasklet, StepExecutionListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(CycleItemWriter.class);

	 	List<CapCycleInfo> list;
		
	    
		@Autowired
		BatchJobManager batchMnager;
		
		@Autowired
		EmailManager emailManager;
		
		EmailGenerationControlInfo emailGenerationControlInfo;
		   
	/* (non-Javadoc)
	 * @see org.springframework.batch.core.StepExecutionListener#beforeStep(org.springframework.batch.core.StepExecution)
	 */
	@Override
	public void beforeStep(StepExecution stepExecution) {
		list=(List<CapCycleInfo>) stepExecution.getJobExecution().getExecutionContext().get("cycles");
		emailGenerationControlInfo=(EmailGenerationControlInfo) stepExecution.getJobExecution().getExecutionContext().get("emailGenerationControlInfo");

	}

	/* (non-Javadoc)
	 * @see org.springframework.batch.core.StepExecutionListener#afterStep(org.springframework.batch.core.StepExecution)
	 */
	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		stepExecution.setReadCount(list.size());
		return ExitStatus.COMPLETED;

	}

	/* (non-Javadoc)
	 * @see org.springframework.batch.core.step.tasklet.Tasklet#execute(org.springframework.batch.core.StepContribution, org.springframework.batch.core.scope.context.ChunkContext)
	 */
	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		
		Iterator<CapCycleInfo> iterator= list.iterator();
		
		
		while (iterator.hasNext()) {
			CapCycleInfo capCycleInfo = (CapCycleInfo) iterator.next();
			try{
			
			if(capCycleInfo.getAutoCloseIndictor().equals("E"))
				capCycleInfo.setAutoCloseFirstEmailDate(new Date());		
			if(capCycleInfo.getAutoCloseIndictor().equals("A"))
				capCycleInfo.setAutoCloseEmailDate(new Date());
			batchMnager.updateCycle(capCycleInfo);
			}catch (Exception e) {
				LOGGER.error("Cap Cycle Id :" + capCycleInfo.getCycleId(), e);
			}
		}
		
		
		
		return RepeatStatus.FINISHED;
	}

}
