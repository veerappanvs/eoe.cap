/**
 * 
 */
package edu.mass.doe.cap.dataservice.correspondence;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;


import edu.mass.doe.cap.controllers.CorrespondenceHistoryController;
import edu.mass.doe.cap.dataservice.pojo.CandidateInfo;
import edu.mass.doe.cap.dataservice.pojo.CorrespondenceInfo;
import edu.mass.doe.cap.dataservice.pojo.SponsoringOrg;
import edu.mass.doe.cap.util.MapValue;

/**
 * @author vsubramaniyan
 *
 */
@Repository
public class CorrespondenceService {

	private static Logger logger = LoggerFactory.getLogger(CorrespondenceService.class);


	@PersistenceContext(unitName="capPeristanceUnit")
	EntityManager entityManager;	
	@Autowired
	private Environment env;
	
	
	
public List<CorrespondenceInfo> getCorrespondenceHistoryForCycle(Long cycleId){
		
		Query q = entityManager.createNativeQuery(env.getProperty("correspondenceHistoryForCycle"));
		q.setParameter("cycleid", cycleId);
		
		List<Object[]> correspondences = q.getResultList();
		List<CorrespondenceInfo> correspondenceList = new ArrayList<CorrespondenceInfo>();

		for (Object[] correspondence : correspondences) {
			CorrespondenceInfo correspondenceInfo =new CorrespondenceInfo();
			correspondenceInfo.setCapEmailId(((BigDecimal)correspondence[0]).longValue());
			correspondenceInfo.setCycleId(((BigDecimal)correspondence[1]).longValue());
			correspondenceInfo.setToEmailAddr((String)correspondence[2]);
			correspondenceInfo.setLastUdateDate((Date)correspondence[3]);
			correspondenceInfo.setFullName((String)correspondence[4]);
			correspondenceInfo.setProgramName((String)correspondence[5]);
			correspondenceInfo.setEmailSubject((String)correspondence[6]);
			//correspondenceInfo.setEmailBody((String)correspondence[7]);
			correspondenceList.add(correspondenceInfo);
			
			}
		
		logger.info(correspondenceList.toString());
		return correspondenceList;
	}
	
		
	public String getEmailBodyForCommunicaitonId(Long communicationId) {
		Query q = entityManager.createNativeQuery(env.getProperty("correspondenceBody"));
		q.setParameter("communicationId", communicationId);
		List<Object> bodies = q.getResultList();
		String emailBody="";
		for (Object body : bodies) {
			emailBody = (String)bodies.get(0);
		}
		return  emailBody;
	}
	
}
