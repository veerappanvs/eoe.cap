package edu.mass.doe.cap.dataservice.observation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import edu.mass.doe.cap.dataservice.entity.CapCycle;
import edu.mass.doe.cap.dataservice.entity.Observation;
import edu.mass.doe.cap.dataservice.entity.ObservationGroupType;
import edu.mass.doe.cap.dataservice.entity.PracticumType;
import edu.mass.doe.cap.dataservice.pojo.EvidenceInfo;
import edu.mass.doe.cap.dataservice.pojo.ObservationInfo;

/**
 * The Class ObservationGroupTypeService.
 */
@Service
public class ObservationGroupTypeService {

	private static Logger logger = LoggerFactory.getLogger(ObservationGroupTypeService.class);

	@PersistenceContext(unitName = "capPeristanceUnit")
	EntityManager entityManager;

	@Autowired
	private Environment env;

	
	@Autowired
	private ObservationGroupTypeRepository observationGroupTypeRepository;


	/**
	 * Find all.
	 *
	 * @return the list
	 */
	public List<ObservationGroupType> findAll() {
		return observationGroupTypeRepository.findAll();
	}
	
	

	/**
	 * Find by primary key.
	 *
	 * @param primaryKey the primary key
	 * @return the observation group type
	 */
	public ObservationGroupType findByPrimaryKey(Long primaryKey) {
		return entityManager.find(ObservationGroupType.class, primaryKey);
	}

	/**
	 * Creates the.
	 *
	 * @param obj the obj
	 * @return the observation group type
	 */
	@Transactional(value=TxType.REQUIRED)
	public ObservationGroupType create(ObservationGroupType obj) {
		entityManager.persist(obj);
		return obj;

	}

	/**
	 * Update.
	 *
	 * @param obj the obj
	 * @return the observation group type
	 */
	@Transactional(value=TxType.REQUIRED)
	public ObservationGroupType update(ObservationGroupType obj) {
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

		ObservationGroupType observationGroupType = entityManager.find(ObservationGroupType.class, primaryKey);
		entityManager.remove(observationGroupType);

	}
	
	
	
	public List<String> getAllGroupCodeForObservationId(Long observationId){
		
		Query q = entityManager.createNativeQuery(env.getProperty("group.code.list"));
		q.setParameter("observationId", observationId);
		List<String> results=   q.getResultList();
		List<String> lists= new ArrayList<String>();
		
		for(String result:results){
			lists.add(result);
		}
		return lists;
		
	}
	

}
