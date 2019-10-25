package edu.mass.doe.cap.batch;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
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

import edu.mass.doe.cap.model.email.EmailManager;
import edu.mass.doe.cap.dataservice.entity.CapCycle;
import edu.mass.doe.cap.dataservice.pojo.CapCycleInfo;
import edu.mass.doe.cap.dataservice.pojo.CapManagerInfo;
import edu.mass.doe.cap.dataservice.pojo.EmailGenerationControlInfo;
import edu.mass.doe.cap.model.job.BatchJobManager;

/**
 * The Class CycleItemProcessor.
 */
public class CycleItemProcessor implements Tasklet, StepExecutionListener  {

    private static final Logger LOGGER = LoggerFactory.getLogger(CycleItemProcessor.class);
    
	
    List<CapCycleInfo> list;
    
	@Autowired
	BatchJobManager batchMnager;
	
	@Autowired
	EmailManager emailManager;
	
	EmailGenerationControlInfo emailGenerationControlInfo;
	
	int annualEmailCount;
	   

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
		if (emailGenerationControlInfo.getEgnId().equals(2L)) {
			stepExecution.setWriteCount(annualEmailCount);
		}else
			stepExecution.setReadCount(list.size());
        return ExitStatus.COMPLETED;

	}
	
	
	/* (non-Javadoc)
	 * @see org.springframework.batch.core.step.tasklet.Tasklet#execute(org.springframework.batch.core.StepContribution, org.springframework.batch.core.scope.context.ChunkContext)
	 */
	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		String jobInfo="Job Name :"+chunkContext.getStepContext().getJobName()+" Job Id :"+chunkContext.getStepContext().getId();

		if (emailGenerationControlInfo.getEgnId().equals(2L)) {
			
			for (CapCycleInfo capCycleInfo : list) {
				List<CapManagerInfo> managers = batchMnager
						.getManagers(emailGenerationControlInfo.getEmailQuery(), capCycleInfo.getOrgId());
				capCycleInfo.setManagers(managers);
				annualEmailCount=annualEmailCount+managers.size();
			}

			emailManager.annualAutoCloseNotification(list, emailGenerationControlInfo.getTypeCode(),jobInfo);
		}

		else {

			for (CapCycleInfo capCycleInfo : list) {
				try {
					if (capCycleInfo.getAutoCloseIndictor().equals("E")) {
						List<CapManagerInfo> managers = batchMnager
								.getManagers(emailGenerationControlInfo.getEmailQuery(), capCycleInfo.getOrgId());
						capCycleInfo.setManagers(managers);
						emailManager.cycleAutoCloseNotification(capCycleInfo.getCycleId(), capCycleInfo,
								emailGenerationControlInfo.getTypeCode(),jobInfo);

					} else if (capCycleInfo.getAutoCloseIndictor().equals("A")) {
						emailManager.cycleAutoClose(capCycleInfo.getCycleId(), capCycleInfo,
								emailGenerationControlInfo.getTypeCode(),jobInfo);
					}
				} catch (Throwable e) {
					LOGGER.error("Cap Cycle Id :" + capCycleInfo.getCycleId(), e);
				}
			}

		}
		return RepeatStatus.FINISHED;
	}


	
   
}
