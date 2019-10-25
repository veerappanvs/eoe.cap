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

import edu.mass.doe.cap.dataservice.entity.CapCycle;
import edu.mass.doe.cap.dataservice.entity.PracticumType;

/**
 * The Class PracticumTypeService.
 */
@Service
public class PracticumTypeService {
	
	
	private static Logger logger = LoggerFactory.getLogger(CandidateService.class);

	
	@PersistenceContext(unitName="capPeristanceUnit")
	EntityManager entityManager;
	
	@Autowired
	private Environment env;
	
	
	@Autowired
	private PracticumTypeRepository practicumTypeRepository;

	
	
	
	/**
	 * Find all.
	 *
	 * @return the list
	 */
	public List<PracticumType> findAll() {
		return practicumTypeRepository.findAll();
	}
	
	

	/**
	 * Find by primary key.
	 *
	 * @param primaryKey the primary key
	 * @return the practicum type
	 */
	public PracticumType findByPrimaryKey(String primaryKey) {
		return entityManager.find(PracticumType.class, primaryKey);
	}
	

}
