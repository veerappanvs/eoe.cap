package edu.mass.doe.cap.dataservice.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import edu.mass.doe.cap.dataservice.pojo.CapYear;
import edu.mass.doe.cap.dataservice.pojo.OrganizationInfo;
import edu.mass.doe.cap.dataservice.reports.CycleReportsService;

/**
 * The Class CapService.
 */
@Repository
public class CapService {
	
	private static Logger logger = LoggerFactory.getLogger(CapService.class);

	@PersistenceContext(unitName="capPeristanceUnit")
	EntityManager entityManager;
	
	@Autowired
	private Environment env;
	
	/**
	 * Find user id BYMEPID.
	 *
	 * @param mepid the mepid
	 * @return the list
	 */
	public List<String> findUserIdBYMEPID(Long mepid){
		List<String> userIds=null;
		Query q = entityManager.createNativeQuery(env.getProperty("findUserIdBYMEPID"));
		q.setParameter("mepid", mepid);
		userIds= q.getResultList();
		return userIds;
	}
	
	/**
	 * Gets the school years.
	 *
	 * @return the school years
	 */
	public List<CapYear> getSchoolYears(){
		
		List<CapYear> capYears =  new ArrayList<CapYear>();
				 
		Query q = entityManager.createNativeQuery(env.getProperty("cap.year.control"));
		q.setParameter("currSchoolYear", getCurrentSchoolYear());
			
		List<Object[]> results = q.getResultList();
		
		for (Object[] result : results) {
			CapYear capYear = new CapYear();
			capYear.setSchoolYear(((BigDecimal)result[0]).intValue());
			capYear.setDesc((String) result[1]);
			capYear.setStartDate((Date)result[2]);
			capYear.setEndDate((Date)result[3]);
			capYear.setActive(((Character) result[4]).equals('Y')?true:false);
			capYears.add(capYear);
		}
		
		return capYears;
		
	}
	
	
	/**
	 * Gets the current school year.
	 *
	 * @return the current school year
	 */
	public int getCurrentSchoolYear(){
		Calendar schoolEndDate = Calendar.getInstance();
		schoolEndDate.setTime(new Date());
		schoolEndDate.set(Calendar.MONTH, Calendar.AUGUST);
		schoolEndDate.set(Calendar.DAY_OF_MONTH, 31);
		
		Calendar todaysDate = Calendar.getInstance();
		todaysDate.setTime(new Date());
		int currYear= todaysDate.get(Calendar.YEAR);
		currYear=todaysDate.compareTo(schoolEndDate)>0?currYear+1:currYear;
		
		return currYear;
	
		
	}

}
