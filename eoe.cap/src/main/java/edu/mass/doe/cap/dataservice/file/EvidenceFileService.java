package edu.mass.doe.cap.dataservice.file;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
import org.springframework.stereotype.Repository;

import edu.mass.doe.cap.dataservice.entity.ElementType;
import edu.mass.doe.cap.dataservice.entity.EvidenceFile;
import edu.mass.doe.cap.dataservice.entity.ThreewayMeeting;
import edu.mass.doe.cap.dataservice.pojo.EvidenceSource;


/**
 * The Class EvidenceFileService.
 */
@Repository
public class EvidenceFileService {
	
	private static Logger logger = LoggerFactory.getLogger(EvidenceFileService.class);

	@PersistenceContext(unitName = "capPeristanceUnit")
	EntityManager entityManager;
	
	@Autowired
	private EvidencefileRepository evidencefileRepository;  
	
	@Autowired
	private Environment env;
	
	public BigDecimal folderSizeInMb(Long cycleId,long maxSize){
		Query q = entityManager.createNativeQuery(env.getProperty("cap.folder.size"));
		q.setParameter("cycleId", cycleId);
		BigDecimal result = (BigDecimal) q.getSingleResult();
		result=result.setScale(2,RoundingMode.DOWN);
		return  result;
	}
	
	/**
	 * Find by primary key.
	 *
	 * @param primaryKey the primary key
	 * @return the evidence file
	 */
	public EvidenceFile findByPrimaryKey(Long primaryKey) {
		return entityManager.find(EvidenceFile.class, primaryKey);
	}
	
	
	/**
	 * Find files by cycle.
	 *
	 * @param cycleId the cycle id
	 * @return the list
	 */
	public List<EvidenceFile> findFilesByCycle(Long cycleId)
	{
		return evidencefileRepository.findFilesByCycle(cycleId);
	}
	
	
	
	/**
	 * Creates the.
	 *
	 * @param obj the obj
	 * @return the evidence file
	 */
	@Transactional(value=TxType.REQUIRED)
	public EvidenceFile create(EvidenceFile obj) {
		entityManager.persist(obj);
		return obj;

	}

	/**
	 * Update.
	 *
	 * @param obj the obj
	 * @return the evidence file
	 */
	@Transactional(value=TxType.REQUIRED)
	public EvidenceFile update(EvidenceFile obj) {
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

		EvidenceFile evidenceFile = entityManager.find(EvidenceFile.class, primaryKey);
		entityManager.remove(evidenceFile);

	}

	

}
