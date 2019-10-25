package edu.mass.doe.cap.dataservice.assessment;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.mass.doe.cap.dataservice.entity.ElementType;
import edu.mass.doe.cap.dataservice.entity.RubricMap;
import edu.mass.doe.cap.dataservice.pojo.EvidenceSource;

/**
 * The Class RubricMapService.
 */
@Repository
public class RubricMapService {
	

	
	
	private static Logger logger = LoggerFactory.getLogger(RubricMapService.class);

	@PersistenceContext(unitName = "capPeristanceUnit")
	EntityManager entityManager;

	@Autowired
	private Environment env;
	
	@Autowired
	private RubricMapRepository rubricMapRepository;

	
	/**
	 * Find by primary key.
	 *
	 * @param primaryKey the primary key
	 * @return the rubric map
	 */
	public RubricMap findByPrimaryKey(Long primaryKey) {
		return entityManager.find(RubricMap.class, primaryKey);
	}
	

	/**
	 * Creates the.
	 *
	 * @param obj the obj
	 * @return the rubric map
	 */
	@Transactional(value=TxType.REQUIRED)
	public RubricMap create(RubricMap obj) {
		entityManager.persist(obj);
		return obj;

	}

	/**
	 * Update.
	 *
	 * @param obj the obj
	 * @return the rubric map
	 */
	@Transactional(value=TxType.REQUIRED)
	public RubricMap update(RubricMap obj) {
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
		RubricMap rubricMap = entityManager.find(RubricMap.class, primaryKey);
		entityManager.remove(rubricMap);
	}


	 /**
 	 * Find rubric map by assessment.
 	 *
 	 * @param assessmentId the assessment id
 	 * @param dimensionCode the dimension code
 	 * @param elementCode the element code
 	 * @return the long
 	 */
 	public Long findRubricMapByAssessment(Long assessmentId,String dimensionCode,String elementCode) {
			
			Query q = entityManager.createNativeQuery(env.getProperty("RubricMap.findRubricMapByAssessment"));
			q.setParameter("assessmentId", assessmentId);
			q.setParameter("dimensionCode", dimensionCode);
			q.setParameter("elementCode", elementCode);

			List<Object> results= q.getResultList();
			
			Long mapId=null;		
			
			if(results.size()>0)	
				mapId=((BigDecimal) results.get(0)).longValue();
				
		
			return mapId;

		}



}
