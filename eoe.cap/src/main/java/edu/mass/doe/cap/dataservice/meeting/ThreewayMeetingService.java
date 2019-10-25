package edu.mass.doe.cap.dataservice.meeting;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.mass.doe.cap.dataservice.entity.Evidence;
import edu.mass.doe.cap.dataservice.entity.ThreewayMeeting;
import edu.mass.doe.cap.dataservice.observation.EvidenceService;

/**
 * The Class ThreewayMeetingService.
 */
@Repository
public class ThreewayMeetingService {

	private static Logger logger = LoggerFactory.getLogger(ThreewayMeetingService.class);

	@PersistenceContext(unitName = "capPeristanceUnit")
	EntityManager entityManager;

	
	
	@Autowired
	private ThreewayMeetingRepository threewayMeetingRepository;  

	/**
	 * Find by primary key.
	 *
	 * @param primaryKey the primary key
	 * @return the threeway meeting
	 */
	public ThreewayMeeting findByPrimaryKey(Long primaryKey) {
		return entityManager.find(ThreewayMeeting.class, primaryKey);
	}
	
	
	/**
	 * Find meetings by cycle id.
	 *
	 * @param cycleId the cycle id
	 * @return the list
	 */
	public List<ThreewayMeeting> findMeetingsByCycleId(Long cycleId)
	{
		return threewayMeetingRepository.findMeetingsByCycleId(cycleId);
	}
	
	/**
	 * Find meeting by cycle id.
	 *
	 * @param cycleId the cycle id
	 * @param meetingNumber the meeting number
	 * @return the threeway meeting
	 */
	public ThreewayMeeting findMeetingByCycleId(Long cycleId,Long meetingNumber){
		 return threewayMeetingRepository.findMeetingByCycleId(cycleId, meetingNumber);
	 }

	
	
	/**
	 * Creates the.
	 *
	 * @param obj the obj
	 * @return the threeway meeting
	 */
	@Transactional(value=TxType.REQUIRED)
	public ThreewayMeeting create(ThreewayMeeting obj) {
		entityManager.persist(obj);
		return obj;

	}

	/**
	 * Update.
	 *
	 * @param obj the obj
	 * @return the threeway meeting
	 */
	@Transactional(value=TxType.REQUIRED)
	public ThreewayMeeting update(ThreewayMeeting obj) {
		entityManager.merge(obj);
		return obj;
	}

	/**
	 * Delete.
	 *
	 * @param primaryKey the primary key
	 */
	@Transactional(value=TxType.REQUIRED)
	public void delete(Long primaryKey) {

		ThreewayMeeting threewayMeeting = entityManager.find(ThreewayMeeting.class, primaryKey);
		entityManager.remove(threewayMeeting);

	}

}
