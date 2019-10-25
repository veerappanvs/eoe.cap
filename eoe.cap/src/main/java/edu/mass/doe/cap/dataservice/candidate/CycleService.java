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

import edu.mass.doe.cap.dataservice.entity.CapCycle;
import edu.mass.doe.cap.dataservice.pojo.CandidateInfo;
import edu.mass.doe.cap.dataservice.pojo.CapCycleInfo;

/**
 * The Class CycleService.
 */
@Repository
public class CycleService {
	
	private static Logger logger = LoggerFactory.getLogger(CycleService.class);

	@PersistenceContext(unitName="capPeristanceUnit")
	EntityManager entityManager;
	
	@Autowired
	private Environment env;
	
	
	/**
	 * Find by primary key.
	 *
	 * @param primaryKey the primary key
	 * @return the cap cycle
	 */
	public CapCycle findByPrimaryKey(Long primaryKey) {
		return entityManager.find(CapCycle.class, primaryKey);
	}
	
	/**
	 * Creates the.
	 *
	 * @param obj the obj
	 * @return the cap cycle
	 */
	@Transactional(value=TxType.REQUIRED)
	public CapCycle create(CapCycle obj) {
		entityManager.persist(obj);
		return obj;
		
	}
		
	/**
	 * Update.
	 *
	 * @param obj the obj
	 * @return the cap cycle
	 */
	@Transactional(value=TxType.REQUIRED)
	public CapCycle update(CapCycle obj) {
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
		
		CapCycle capCycle = 
				entityManager
				.find(CapCycle.class, primaryKey);
		entityManager
		.remove(capCycle);
		
	}
	
	/**
	 * Find cycle ids.
	 *
	 * @param sql the sql
	 * @return the list
	 */
	public List<Long> findCycleIds(String sql){
		
		Query q = entityManager.createNativeQuery(sql);
		
		List<Object[]> results=   q.getResultList();
		List<Long> lists= new ArrayList<Long>();
		
		for(Object[] result:results){
			lists.add(((BigDecimal) result[0]).longValue());
		}
		return lists;
		
	}
	
	public List<String> getAllgradLevel(){
		
		Query q = entityManager.createNativeQuery(env.getProperty("grad.level.list"));
		List<String> results=   q.getResultList();
		List<String> lists= new ArrayList<String>();
		
		for(String result:results){
			lists.add(result);
		}
		return lists;
		
	}
	
	public List<String> getAllgradLevelForCycle(Long cycleId){
		
		Query q = entityManager.createNativeQuery(env.getProperty("grad.level.list.cycle"));
		q.setParameter("cycleId", cycleId);
		List<String> results=   q.getResultList();
		List<String> lists= new ArrayList<String>();
		
		for(String result:results){
			lists.add(result);
		}
		return lists;
		
	}
	
	public  List<String>  getAllValidCyclesForReopening(Long cycleId){
	
		Query q = entityManager.createNativeQuery(env.getProperty("findValidCyclesReopening"));
		q.setParameter("cycleId", cycleId);
		List<Long> results=   q.getResultList();
		List<Object[]> validCycles = q.getResultList();
		List<String> lists= new ArrayList<String>();
		
		for(Object[] validCycle:validCycles){
			lists.add(((String)validCycle[1])+","+((String)validCycle[2]) );
		}
		return lists;
		
	}
	
	
}
