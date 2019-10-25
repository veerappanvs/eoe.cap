package edu.mass.doe.cap.dataservice.observation;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import edu.mass.doe.cap.dataservice.entity.ObservationType;

/**
 * The Class ObservationTypeService.
 */
@Service
public class ObservationTypeService {
	
	
	private static Logger logger = LoggerFactory.getLogger(ObservationGroupTypeService.class);

	@PersistenceContext(unitName = "capPeristanceUnit")
	EntityManager entityManager;


	/**
	 * Find by primary key.
	 *
	 * @param primaryKey the primary key
	 * @return the observation type
	 */
	public ObservationType findByPrimaryKey(String primaryKey) {
		return entityManager.find(ObservationType.class, primaryKey);
	}


}
