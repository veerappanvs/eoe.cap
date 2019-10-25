package edu.mass.doe.cap.dataservice.candidate;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import edu.mass.doe.cap.dataservice.entity.ObservationGroupLink;
import edu.mass.doe.cap.model.util.CAPUtil;

@Repository
public class ObservationGroupLinkService {

	
	private static Logger logger = LoggerFactory.getLogger(ObservationGroupLinkService.class);

	@PersistenceContext(unitName="capPeristanceUnit")
	EntityManager entityManager;
	
	@Autowired
	private CAPUtil capUtil;
	
	@Autowired
	private Environment env;
	
	@Autowired
	private ObservationGroupLinkRepository observationGroupLinkRepository;
	
	
	public ObservationGroupLink findByPrimaryKey(Long primaryKey) {
		return entityManager.find(ObservationGroupLink.class, primaryKey);
	}
	
	public List<ObservationGroupLink> findGroupsByObservation(Long observationId) {
		return observationGroupLinkRepository.findAll(observationId);
	}
	
	/**
	 * Creates the.
	 *
	 * @param obj the obj
	 * @return the cap cycle
	 */
	@Transactional(value=TxType.REQUIRED)
	public ObservationGroupLink create(ObservationGroupLink obj) {
		entityManager.persist(obj);
		return obj;
		
	}
		
	/**
	 * Update.
	 *
	 * @param obj the obj
	 * @return the cap cycle
	 */
	@Transactional(value=TxType.REQUIRED)
	public ObservationGroupLink update(ObservationGroupLink obj) {
		entityManager
		.merge(obj);		
		return obj;
	}
	
	/**
	 * Delete.
	 *
	 * @param primaryKey the primary key
	 */
	@Transactional(value=TxType.REQUIRED)
	public void delete(Long primaryKey) {
		
		ObservationGroupLink observationGroupLink = 
				entityManager
				.find(ObservationGroupLink.class, primaryKey);
		entityManager
		.remove(observationGroupLink);
		
	}
	
	public List<String> getAllGroupCodeForObservationid(Long observationId){
		
		Query q = entityManager.createNativeQuery(env.getProperty("group.code.list"));
		q.setParameter("observationId", observationId);
		List<String> results=   q.getResultList();
		List<String> lists= new ArrayList<String>();
		
		for(String result:results){
			lists.add(result);
		}
		return lists;
		
	}

}
