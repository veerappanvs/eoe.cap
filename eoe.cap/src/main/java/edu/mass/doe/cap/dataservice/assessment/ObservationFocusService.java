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
import edu.mass.doe.cap.dataservice.entity.ObservationFocus;

/**
 * The Class ObservationFocusService.
 */
@Repository
public class ObservationFocusService {
	

	
	
	private static Logger logger = LoggerFactory.getLogger(ObservationFocusService.class);

	@PersistenceContext(unitName = "capPeristanceUnit")
	EntityManager entityManager;

	@Autowired
	private Environment env;
	
	@Autowired
	private ObservationFocusRepository observationFocusRepository;
	
	/**
	 * Find all.
	 *
	 * @return the list
	 */
	public List<ObservationFocus> findAll(){
		return observationFocusRepository.findAll();
	}
	


}
