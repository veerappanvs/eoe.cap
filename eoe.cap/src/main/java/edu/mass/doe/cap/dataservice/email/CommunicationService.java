package edu.mass.doe.cap.dataservice.email;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;


import edu.mass.doe.cap.dataservice.entity.Communication;

/**
 * The Class CommunicationService.
 */
@Repository
public class CommunicationService {
	
	private static Logger logger = LoggerFactory.getLogger(CommunicationService.class);

	@PersistenceContext(unitName = "capPeristanceUnit")
	EntityManager entityManager;

	@Autowired
	private Environment env;
	
	
	
	
	
	/**
	 * Find by primary key.
	 *
	 * @param primaryKey the primary key
	 * @return the communication
	 */
	public Communication findByPrimaryKey(Long primaryKey) {
		return entityManager.find(Communication.class, primaryKey);
	}
	
	/**
	 * Creates the.
	 *
	 * @param obj the obj
	 * @return the communication
	 */
	@Transactional(value=TxType.REQUIRED)
	public Communication create(Communication obj) {
		entityManager.persist(obj);
		return obj;

	}

	/**
	 * Update.
	 *
	 * @param obj the obj
	 * @return the communication
	 */
	@Transactional(value=TxType.REQUIRED)
	public Communication update(Communication obj) {
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
		Communication communication = entityManager.find(Communication.class, primaryKey);
		entityManager.remove(communication);

	}





}
