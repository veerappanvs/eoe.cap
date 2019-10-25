package edu.mass.doe.cap.dataservice.candidate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import edu.mass.doe.cap.dataservice.entity.CapAssignment;
import edu.mass.doe.cap.dataservice.entity.OutOfStateSpAssignment;

/**
 * The Class OutOfStateSpAssignmentService.
 */
@Repository
public class OutOfStateSpAssignmentService {
	
	private static Logger logger = LoggerFactory.getLogger(OutOfStateSpAssignmentService.class);
	
	@PersistenceContext(unitName="capPeristanceUnit")
	EntityManager entityManager;
	
	
	/**
	 * Find by primary key.
	 *
	 * @param primaryKey the primary key
	 * @return the out of state sp assignment
	 */
	public OutOfStateSpAssignment findByPrimaryKey(Long primaryKey) {
		return entityManager.find(OutOfStateSpAssignment.class, primaryKey);
	}
	

	/**
	 * Creates the.
	 *
	 * @param obj the obj
	 * @return the out of state sp assignment
	 */
	@Transactional(value=TxType.REQUIRED)
	public OutOfStateSpAssignment create(OutOfStateSpAssignment obj) {
		entityManager.persist(obj);
		return obj;
		
	}
		
	/**
	 * Update.
	 *
	 * @param obj the obj
	 * @return the out of state sp assignment
	 */
	@Transactional(value=TxType.REQUIRED)
	public OutOfStateSpAssignment update(OutOfStateSpAssignment obj) {
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
		
		OutOfStateSpAssignment outOfStateSpAssignment = 
				entityManager
				.find(OutOfStateSpAssignment.class, primaryKey);
		entityManager
		.remove(outOfStateSpAssignment);
		
	}

}
