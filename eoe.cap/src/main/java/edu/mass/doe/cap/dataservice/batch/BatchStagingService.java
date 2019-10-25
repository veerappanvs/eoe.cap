package edu.mass.doe.cap.dataservice.batch;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.stereotype.Repository;

import edu.mass.doe.cap.dataservice.entity.BatchStaging;


/**
 * The Class BatchStagingService.
 */
@Repository
public class BatchStagingService {
	@PersistenceContext(unitName="capPeristanceUnit")
	EntityManager entityManager;
	
	/**
	 * Find by primary key.
	 *
	 * @param primaryKey the primary key
	 * @return the batch staging
	 */
	public BatchStaging findByPrimaryKey(Long primaryKey) {
		return entityManager.find(BatchStaging.class, primaryKey);
	}
	
	
	/**
	 * Creates the.
	 *
	 * @param obj the obj
	 * @return the batch staging
	 */
	@Transactional(value=TxType.REQUIRED)
	public BatchStaging create(BatchStaging obj) {
		entityManager.persist(obj);
		return obj;
		
	}
		
	/**
	 * Update.
	 *
	 * @param obj the obj
	 * @return the batch staging
	 */
	@Transactional(value=TxType.REQUIRED)
	public BatchStaging update(BatchStaging obj) {
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
		
		BatchStaging batch = 
				entityManager
				.find(BatchStaging.class, primaryKey);
		entityManager
		.remove(batch);
		
	}

}
