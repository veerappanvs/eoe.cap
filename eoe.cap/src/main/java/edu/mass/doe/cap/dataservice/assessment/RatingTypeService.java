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

import edu.mass.doe.cap.dataservice.entity.DimensionType;
import edu.mass.doe.cap.dataservice.entity.ElementRating;
import edu.mass.doe.cap.dataservice.entity.RatingType;



/**
 * The Class RatingTypeService.
 */
@Repository
public class RatingTypeService {
	
	
	private static Logger logger = LoggerFactory.getLogger(RatingTypeService.class);

	@PersistenceContext(unitName = "capPeristanceUnit")
	EntityManager entityManager;

	@Autowired
	private Environment env;
	
	
	@Autowired
	private RatingTypeRepository ratingTypeRepository;
	
	/**
	 * Find all.
	 *
	 * @return the list
	 */
	public List<RatingType> findAll(){
		return ratingTypeRepository.findAll();
	}

	
	/**
	 * Find by primary key.
	 *
	 * @param primaryKey the primary key
	 * @return the rating type
	 */
	public RatingType findByPrimaryKey(String primaryKey) {
		return entityManager.find(RatingType.class, primaryKey);
	}
	
	

	/**
	 * Creates the.
	 *
	 * @param obj the obj
	 * @return the rating type
	 */
	@Transactional(value=TxType.REQUIRED)
	public RatingType create(RatingType obj) {
		entityManager.persist(obj);
		return obj;

	}

	/**
	 * Update.
	 *
	 * @param obj the obj
	 * @return the rating type
	 */
	@Transactional(value=TxType.REQUIRED)
	public RatingType update(RatingType obj) {
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
		RatingType ratingType = entityManager.find(RatingType.class, primaryKey);
		entityManager.remove(ratingType);

	}

}
