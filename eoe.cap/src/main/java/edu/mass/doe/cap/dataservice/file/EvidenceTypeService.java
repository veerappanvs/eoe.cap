package edu.mass.doe.cap.dataservice.file;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.mass.doe.cap.dataservice.entity.EvidenceType;

/**
 * The Class EvidenceTypeService.
 */
@Repository
public class EvidenceTypeService {
	
	private static Logger logger = LoggerFactory.getLogger(EvidenceTypeService.class);
	@PersistenceContext(unitName = "capPeristanceUnit")
	EntityManager entityManager;
	
	@Autowired
	private EvidenceFileTypeRepository evidenceFileTypeRepository; 
	
	/**
	 * Find by primary key.
	 *
	 * @param primaryKey the primary key
	 * @return the evidence type
	 */
	public EvidenceType findByPrimaryKey(String primaryKey) {
		return entityManager.find(EvidenceType.class, primaryKey);
	}
	
	
	/**
	 * Find all evidences type.
	 *
	 * @return the list
	 */
	public List<EvidenceType> findAllEvidencesType() {
		return evidenceFileTypeRepository.findAllEvidencesType();
	}
}
