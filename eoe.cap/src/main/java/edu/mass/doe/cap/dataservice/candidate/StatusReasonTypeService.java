package edu.mass.doe.cap.dataservice.candidate;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import edu.mass.doe.cap.dataservice.entity.StatusReasonType;

/**
 * The Class StatusReasonTypeService.
 */
@Service
public class StatusReasonTypeService {
	
	
	private static Logger logger = LoggerFactory.getLogger(CandidateService.class);

	
	@PersistenceContext(unitName="capPeristanceUnit")
	EntityManager entityManager;
	
	@Autowired
	private Environment env;
	
	
	@Autowired
	private StatusReasonTypeRepository statusReasonTypeRepository;

	
	
	
	/**
	 * Find all.
	 *
	 * @return the list
	 */
	public List<StatusReasonType> findAll() {
		return statusReasonTypeRepository.findAll();
	}
	

}
