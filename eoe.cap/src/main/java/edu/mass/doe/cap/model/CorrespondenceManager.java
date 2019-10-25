package edu.mass.doe.cap.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.mass.doe.cap.dataservice.correspondence.CorrespondenceService;
import edu.mass.doe.cap.dataservice.pojo.CorrespondenceInfo;
import edu.mass.doe.cap.mail.EmailService;
import edu.mass.doe.cap.util.MapValue;

/**
 * 
 * @author vsubramaniyan
 *
 */
@Component
public class CorrespondenceManager {

	@Autowired(required=true)
	CorrespondenceService  correspondenceService;
	

	@Autowired(required=true)
	EmailService emailService;
	 
	
	/**
	 * get All coreespondences for the given cycle id.
	 * @param cycleId
	 * @return
	 */
	public List<CorrespondenceInfo> getCorrespondenceHistoryForCycle(Long cycleId){
		
		List<CorrespondenceInfo>  correspondences = new ArrayList<CorrespondenceInfo>();
			correspondences =  correspondenceService.getCorrespondenceHistoryForCycle(cycleId);
		return correspondences;

	}
	

	/**
	 * Get email body for the given commmunicaiton id. 
	 * @param communicationId
	 * @return
	 */
	public String  getEmailBodyForCommunicaitonId(Long communicationId) {
		return correspondenceService.getEmailBodyForCommunicaitonId(communicationId);
	}
	
}
