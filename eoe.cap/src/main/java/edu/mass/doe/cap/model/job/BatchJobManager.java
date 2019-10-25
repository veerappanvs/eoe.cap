package edu.mass.doe.cap.model.job;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.mass.doe.cap.dataservice.candidate.CycleService;
import edu.mass.doe.cap.dataservice.entity.CapCycle;
import edu.mass.doe.cap.dataservice.entity.EmailGenerationControl;
import edu.mass.doe.cap.dataservice.job.ControlService;
import edu.mass.doe.cap.dataservice.pojo.CapCycleInfo;
import edu.mass.doe.cap.dataservice.pojo.CapManagerInfo;
import edu.mass.doe.cap.dataservice.pojo.EmailGenerationControlInfo;
import edu.mass.doe.cap.model.cycle.CycleManager;
import edu.mass.doe.cap.model.email.EmailManager;

/**
 * The Class BatchJobManager.
 */
@Component
public class BatchJobManager {
	
	private static Logger logger = LoggerFactory.getLogger(BatchJobManager.class);

	
	@Autowired
	private ControlService controlService;
	
	@Autowired	
	private CycleService cycleService;
	
	@Autowired
	private CycleManager cycleManager;
	
	
	/**
	 * Gets the email gen control.
	 *
	 * @param controlId the control id
	 * @return the email gen control
	 */
	public EmailGenerationControlInfo getEmailGenControl(Long controlId){
		EmailGenerationControl emailGenerationControl=controlService.findByPrimaryKey(controlId);
		EmailGenerationControlInfo emailGenerationControlinfo=new EmailGenerationControlInfo();
		emailGenerationControlinfo.setEgnId(emailGenerationControl.getEgnId());
		emailGenerationControlinfo.setSlectionQuery(emailGenerationControl.getSlectionQuery());
		emailGenerationControlinfo.setTypeCode(emailGenerationControl.getTypeCode());
		emailGenerationControlinfo.setEmailQuery(emailGenerationControl.getEmailQuery());
		emailGenerationControlinfo.setParams(emailGenerationControl.getParams());
		emailGenerationControlinfo.setUpdateQuery(emailGenerationControl.getUpdateQuery());
		emailGenerationControlinfo.setUpdateParams(emailGenerationControl.getUpdateParams());
		return emailGenerationControlinfo;
		
	}
	
	
	/**
	 * Gets the cap cycles.
	 *
	 * @param sql the sql
	 * @return the cap cycles
	 * @throws Exception the exception
	 */
	public List<CapCycleInfo> getCapCycles(String sql) throws Exception{
		List<CapCycleInfo>  capCycleInfos = new ArrayList<CapCycleInfo>();
		List<Long> cycleIds=cycleService.findCycleIds(sql);
		for(Long cycleId:cycleIds){
			try{
			capCycleInfos.add(cycleManager.getCycleInfo(cycleId));
			}catch(Exception e){
				logger.error("cycleId :"+cycleId,e);
			}
		}
		
		return capCycleInfos;
	}
	
	
	/**
	 * Gets the managers.
	 *
	 * @param sql the sql
	 * @param orgId the org id
	 * @return the managers
	 */
	public List<CapManagerInfo> getManagers(String sql,Long orgId){
		return controlService.findManagers(sql, orgId);
	}
	
	/**
	 * Update cycle.
	 *
	 * @param capCycleInfo the cap cycle info
	 */
	public void updateCycle(CapCycleInfo capCycleInfo){
	CapCycle capCycle=	cycleService.findByPrimaryKey(capCycleInfo.getCycleId());
	capCycle.setAutoCloseEmailDate(capCycleInfo.getAutoCloseEmailDate());
	capCycle.setAutoCloseFirstEmailDate(capCycleInfo.getAutoCloseFirstEmailDate());
		
	}

}
