package edu.mass.doe.cap.dataservice.job;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import edu.mass.doe.cap.dataservice.entity.EmailGenerationControl;
import edu.mass.doe.cap.dataservice.entity.GoalPlanElement;
import edu.mass.doe.cap.dataservice.pojo.CapManagerInfo;
import edu.mass.doe.cap.dataservice.pojo.EmailGenerationControlInfo;

/**
 * The Class ControlService.
 */
@Service
public class ControlService {
	
	@PersistenceContext(unitName = "capPeristanceUnit")
	EntityManager entityManager;

	@PersistenceContext(unitName = "dimsPerssistanceUnit")
	EntityManager dimsEntityManager;

	
	@Autowired
	private Environment env;
	

	/**
	 * Find by primary key.
	 *
	 * @param primaryKey the primary key
	 * @return the email generation control
	 */
	public EmailGenerationControl findByPrimaryKey(Long primaryKey) {
		return entityManager.find(EmailGenerationControl.class, primaryKey);
		
	}
	
	/**
	 * Find managers.
	 *
	 * @param sql the sql
	 * @param orgId the org id
	 * @return the list
	 */
	public List<CapManagerInfo> findManagers(String sql,Long orgId) {
		
	Query q = dimsEntityManager.createNativeQuery(sql);
	q.setParameter("orgId", orgId);
		
	List<Object[]>	results= q.getResultList();
	List<CapManagerInfo>	managers = new ArrayList<CapManagerInfo>();
	
	for(Object[] result:results){
		CapManagerInfo capManagerInfo= new CapManagerInfo();
		capManagerInfo.setPersonId(((BigDecimal)result[1]).longValue());
		capManagerInfo.setEmailId((String)result[8]);
		managers.add(capManagerInfo);
	}
	return managers;
		
	}
	

}
