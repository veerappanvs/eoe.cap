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
 * The Class ElementRatingService.
 */
@Repository
public class ElementRatingService {
	
	
	private static Logger logger = LoggerFactory.getLogger(ElementRatingService.class);

	@PersistenceContext(unitName = "capPeristanceUnit")
	EntityManager entityManager;

	@Autowired
	private Environment env;
	
	@Autowired
	private ElementRatingRepository elementRatingRepository;
	
	
	
	/**
	 * Find all by element code.
	 *
	 * @param elementCode the element code
	 * @return the list
	 */
	public List<ElementRating> findAllByElementCode(String elementCode){
		return elementRatingRepository.findAllByElementCode(elementCode);
	}
	
	/**
	 * Find all.
	 *
	 * @return the list
	 */
	public List<ElementRating> findAll(){
		return elementRatingRepository.findAll();
	}
	
	
	/**
	 * Find by element and rating.
	 *
	 * @param elementCode the element code
	 * @param ratingCode the rating code
	 * @return the element rating
	 */
	public ElementRating findByElementAndRating(String elementCode,String ratingCode){
		return elementRatingRepository.findLinkId(elementCode, ratingCode);
	}
	

	
	/**
	 * Find by primary key.
	 *
	 * @param primaryKey the primary key
	 * @return the element rating
	 */
	public ElementRating findByPrimaryKey(Long primaryKey) {
		return entityManager.find(ElementRating.class, primaryKey);
	}
	
	

	/**
	 * Creates the.
	 *
	 * @param obj the obj
	 * @return the element rating
	 */
	@Transactional(value=TxType.REQUIRED)
	public ElementRating create(ElementRating obj) {
		entityManager.persist(obj);
		return obj;

	}

	/**
	 * Update.
	 *
	 * @param obj the obj
	 * @return the element rating
	 */
	@Transactional(value=TxType.REQUIRED)
	public ElementRating update(ElementRating obj) {
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
		ElementRating observation = entityManager.find(ElementRating.class, primaryKey);
		entityManager.remove(observation);

	}

}
