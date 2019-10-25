package edu.mass.doe.cap.dataservice.candidate;

import java.math.BigDecimal;
import java.util.ArrayList;
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

import edu.mass.doe.cap.dataservice.entity.Assessment;
import edu.mass.doe.cap.dataservice.entity.AuditIdentity;
import edu.mass.doe.cap.dataservice.entity.CapAssignment;
import edu.mass.doe.cap.dataservice.entity.CapCycle;
import edu.mass.doe.cap.dataservice.pojo.SupervisorInfo;

/**
 * The Class AssignmentService.
 */
@Repository
public class AssignmentService{
	
	
	private static Logger logger = LoggerFactory.getLogger(AssignmentService.class);

	@PersistenceContext(unitName="capPeristanceUnit")
	EntityManager entityManager;
	
	@Autowired
	private Environment env;
	
	

	/**
	 * Find by primary key.
	 *
	 * @param primaryKey the primary key
	 * @return the cap assignment
	 */
	public CapAssignment findByPrimaryKey(Long primaryKey) {
		return entityManager.find(CapAssignment.class, primaryKey);
	}
	
	/**
	 * Creates the.
	 *
	 * @param obj the obj
	 * @return the cap assignment
	 */
	@Transactional(value=TxType.REQUIRED)
	public CapAssignment create(CapAssignment obj) {
		entityManager.persist(obj);
		return obj;
		
	}
		
	/**
	 * Update.
	 *
	 * @param obj the obj
	 * @return the cap assignment
	 */
	@Transactional(value=TxType.REQUIRED)
	public CapAssignment update(CapAssignment obj) {
		entityManager
		.merge(obj);		
		return obj;
	}
	
	/**
	 * Delete.
	 *
	 * @param primaryKey the primary key
	 */
	@Transactional(value=TxType.REQUIRED)
	public void delete(Long primaryKey) {
		
		CapAssignment capAssignment = 
				entityManager
				.find(CapAssignment.class, primaryKey);
		entityManager
		.remove(capAssignment);
		
	}
	
	/**
	 * Findpractitioner by MEPID.
	 *
	 * @param mepid the mepid
	 * @param schoolOrgId the school org id
	 * @param districtOrgId the district org id
	 * @return the list
	 */
	public List<SupervisorInfo> findpractitionerByMEPID(Long mepid,Long schoolOrgId,Long districtOrgId) {
		
		List<SupervisorInfo> supervisorList = new ArrayList<SupervisorInfo>();
		
		Query q = entityManager.createNativeQuery(env.getProperty("practitionerByMEPID"));
		q.setParameter("mepid", mepid);
		q.setParameter("schoolOrgId", schoolOrgId);
		q.setParameter("districtOrgId", districtOrgId==null?1:districtOrgId);

		List<Object[]> supervisors = q.getResultList();
		
		
		for (Object[] supervisor : supervisors) {
			SupervisorInfo supervisorInfo = new SupervisorInfo();
			supervisorInfo.setMepid(((BigDecimal) supervisor[0]).longValue());
			supervisorInfo.setDistrictName((String)supervisor[1]);
			supervisorInfo.setDistrictOrgId(((BigDecimal) supervisor[2]).longValue());
			supervisorInfo.setSchoolName((String)supervisor[3]);
			supervisorInfo.setSchoolOrgId(((BigDecimal) supervisor[4]).longValue());
			supervisorInfo.setName(supervisor[5] + ", "+ supervisor[6]);
			supervisorInfo.setFirstName((String)supervisor[5]);
			supervisorInfo.setLastName((String)supervisor[6]);
			supervisorList.add(supervisorInfo);
		}
		
		return supervisorList;
	}
	


}
