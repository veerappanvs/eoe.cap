package edu.mass.doe.cap.dataservice.assessment;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import edu.mass.doe.cap.dataservice.entity.ElementRating;
import edu.mass.doe.cap.dataservice.entity.ElementType;

/**
 * The Class ElementTypeService.
 */
@Repository
public class ElementTypeService {
	

	
	
	private static Logger logger = LoggerFactory.getLogger(ElementTypeService.class);

	@PersistenceContext(unitName = "capPeristanceUnit")
	EntityManager entityManager;

	@Autowired
	private Environment env;
	
	@Autowired
	private ElementTypeRepository elementTypeRepository;
	
	/**
	 * Find all.
	 *
	 * @return the list
	 */
	public List<ElementType> findAll(){
		return elementTypeRepository.findAll();
	}
	

	
	/**
	 * Find by primary key.
	 *
	 * @param primaryKey the primary key
	 * @return the element type
	 */
	public ElementType findByPrimaryKey(String primaryKey) {
		return entityManager.find(ElementType.class, primaryKey);
	}
	
	

	/**
	 * Creates the.
	 *
	 * @param obj the obj
	 * @return the element type
	 */
	@Transactional(value=TxType.REQUIRED)
	public ElementType create(ElementType obj) {
		entityManager.persist(obj);
		return obj;

	}

	/**
	 * Update.
	 *
	 * @param obj the obj
	 * @return the element type
	 */
	@Transactional(value=TxType.REQUIRED)
	public ElementType update(ElementType obj) {
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
		ElementType observation = entityManager.find(ElementType.class, primaryKey);
		entityManager.remove(observation);

	}



}
