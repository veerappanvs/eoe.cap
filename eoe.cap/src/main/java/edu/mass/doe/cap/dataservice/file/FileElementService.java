package edu.mass.doe.cap.dataservice.file;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import edu.mass.doe.cap.dataservice.entity.EvidenceFile;
import edu.mass.doe.cap.dataservice.entity.FileElement;


/**
 * The Class FileElementService.
 */
@Repository
public class FileElementService {
	
	private static Logger logger = LoggerFactory.getLogger(FileElementService.class);

	@PersistenceContext(unitName = "capPeristanceUnit")
	EntityManager entityManager;
	
	/**
	 * Find by primary key.
	 *
	 * @param primaryKey the primary key
	 * @return the file element
	 */
	public FileElement findByPrimaryKey(Long primaryKey) {
		return entityManager.find(FileElement.class, primaryKey);
	}
	
	/**
	 * Creates the.
	 *
	 * @param obj the obj
	 * @return the file element
	 */
	@Transactional(value=TxType.REQUIRED)
	public FileElement create(FileElement obj) {
		entityManager.persist(obj);
		return obj;

	}

	/**
	 * Update.
	 *
	 * @param obj the obj
	 * @return the file element
	 */
	@Transactional(value=TxType.REQUIRED)
	public FileElement update(FileElement obj) {
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

		FileElement fileElement = entityManager.find(FileElement.class, primaryKey);
		entityManager.remove(fileElement);

	}

	

}
