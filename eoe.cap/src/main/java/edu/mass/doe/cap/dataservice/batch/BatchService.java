package edu.mass.doe.cap.dataservice.batch;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.stereotype.Repository;

import edu.mass.doe.cap.dataservice.entity.Batch;
import edu.mass.doe.cap.dataservice.entity.BatchStaging;

/**
 * The Class BatchService.
 */
@Repository
public class BatchService {
	@PersistenceContext(unitName="capPeristanceUnit")
	EntityManager entityManager;
	
	/**
	 * Find by primary key.
	 *
	 * @param primaryKey the primary key
	 * @return the batch
	 */
	public Batch findByPrimaryKey(Long primaryKey) {
		return entityManager.find(Batch.class, primaryKey);
	}
	
	/**
	 * Creates the.
	 *
	 * @param obj the obj
	 * @return the batch
	 */
	@Transactional(value=TxType.REQUIRED)
	public Batch create(Batch obj) {
		entityManager.persist(obj);
		return obj;
		
	}
	
	/**
	 * Load.
	 *
	 * @param obj the obj
	 * @return the long
	 */
	@Transactional(value=TxType.REQUIRED)
	public Long load(Batch obj) {
		entityManager.persist(obj);
		
		StoredProcedureQuery query = entityManager
			    .createStoredProcedureQuery("CAPS_UTILITIES. VALIDATE_DATA_PRO")
			    .registerStoredProcedureParameter(
			        1, Long.class, ParameterMode.IN)
			    .registerStoredProcedureParameter(
			        2, Long.class, ParameterMode.OUT)
			    .setParameter(1, obj.getUploadId());

			query.execute();

			Long errorCount = (Long) query
			    .getOutputParameterValue(2);
			
			
		return errorCount!=null?errorCount:new Long(0);		
	}
	
		
	/**
	 * Update.
	 *
	 * @param obj the obj
	 * @return the batch
	 */
	@Transactional(value=TxType.REQUIRED)
	public Batch update(Batch obj) {
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
		
		Batch batch = 
				entityManager
				.find(Batch.class, primaryKey);
		entityManager
		.remove(batch);
		
	}

}
