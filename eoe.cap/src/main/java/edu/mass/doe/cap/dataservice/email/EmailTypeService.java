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

import edu.mass.doe.cap.dataservice.entity.EmailType;

/**
 * The Class EmailTypeService.
 */
@Repository
public class EmailTypeService {
	
	private static Logger logger = LoggerFactory.getLogger(EmailTypeService.class);

	@PersistenceContext(unitName = "capPeristanceUnit")
	EntityManager entityManager;

	@Autowired
	private Environment env;
	
		
	/**
	 * Find by primary key.
	 *
	 * @param primaryKey the primary key
	 * @return the email type
	 */
	public EmailType findByPrimaryKey(String primaryKey) {
		return entityManager.find(EmailType.class, primaryKey);
	}
	
	/**
	 * Creates the.
	 *
	 * @param obj the obj
	 * @return the email type
	 */
	@Transactional(value=TxType.REQUIRED)
	public EmailType create(EmailType obj) {
		entityManager.persist(obj);
		return obj;
	}

	/**
	 * Update.
	 *
	 * @param obj the obj
	 * @return the email type
	 */
	@Transactional(value=TxType.REQUIRED)
	public EmailType update(EmailType obj) {
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
		EmailType communication = entityManager.find(EmailType.class, primaryKey);
		entityManager.remove(communication);

	}


}
