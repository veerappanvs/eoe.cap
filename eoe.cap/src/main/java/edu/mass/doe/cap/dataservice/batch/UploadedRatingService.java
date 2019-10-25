package edu.mass.doe.cap.dataservice.batch;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.stereotype.Repository;

import edu.mass.doe.cap.dataservice.entity.UploadedRating;

/**
 * The Class UploadedRatingService.
 */
@Repository
public class UploadedRatingService {

	@PersistenceContext(unitName = "capPeristanceUnit")
	EntityManager entityManager;

	/**
	 * Find by primary key.
	 *
	 * @param primaryKey the primary key
	 * @return the uploaded rating
	 */
	public UploadedRating findByPrimaryKey(Long primaryKey) {
		return entityManager.find(UploadedRating.class, primaryKey);
	}

	/**
	 * Creates the.
	 *
	 * @param obj the obj
	 * @return the uploaded rating
	 */
	@Transactional(value = TxType.REQUIRED)
	public UploadedRating create(UploadedRating obj) {
		entityManager.persist(obj);
		return obj;

	}

	/**
	 * Update.
	 *
	 * @param obj the obj
	 * @return the uploaded rating
	 */
	@Transactional(value = TxType.REQUIRED)
	public UploadedRating update(UploadedRating obj) {
		entityManager.merge(obj);
		return obj;
	}

	/**
	 * Delete.
	 *
	 * @param primaryKey the primary key
	 */
	@Transactional(value = TxType.REQUIRED)
	public void delete(Long primaryKey) {

		UploadedRating uploadedRating = entityManager.find(UploadedRating.class, primaryKey);
		entityManager.remove(uploadedRating);

	}

}
