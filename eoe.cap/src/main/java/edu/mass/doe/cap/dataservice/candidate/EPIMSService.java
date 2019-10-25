package edu.mass.doe.cap.dataservice.candidate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import edu.mass.doe.cap.dataservice.pojo.CandidateInfo;
import edu.mass.doe.cap.dataservice.pojo.SupervisorInfo;

/**
 * The Class EPIMSService.
 */
@Repository
public class EPIMSService {
	
	
	@PersistenceContext(unitName="epimsPerssistanceUnit")
	EntityManager entityManager;
	
	@Autowired
	private Environment env;

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
		q.setParameter("districtOrgId", districtOrgId);

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
	
	
/**
 * Findpractitioner by district.
 *
 * @param districtCode the district code
 * @param mepid the mepid
 * @return the list
 */
public List<SupervisorInfo> findpractitionerByDistrict(String districtCode,Long mepid) {
		
		List<SupervisorInfo> supervisorList = new ArrayList<SupervisorInfo>();
		
		Query q = entityManager.createNativeQuery(env.getProperty("practitionerByDistrict"));
		q.setParameter("districtCode", districtCode);
		q.setParameter("mepid", mepid);
		
		List<Object[]> supervisors = q.getResultList();
		
		
		for (Object[] supervisor : supervisors) {
			
			SupervisorInfo supervisorInfo = new SupervisorInfo();
			supervisorInfo.setMepid(((BigDecimal) supervisor[5]).longValue());
			supervisorInfo.setName(supervisor[4] + ", "+ supervisor[3]);
			supervisorInfo.setFirstName((String)supervisor[3]);
			supervisorInfo.setLastName((String)supervisor[4]);		
			supervisorInfo.setDistrictOrgCode((String)supervisor[0]);
			supervisorInfo.setDistrictName((String)supervisor[1]);
			supervisorInfo.setDistrictOrgId(((BigDecimal)supervisor[2]).longValue());
			supervisorInfo.setSchoolOrgId(((BigDecimal) supervisor[6]).longValue());
			supervisorInfo.setSchoolOrgCode((String)supervisor[7]);
			supervisorInfo.setSchoolName((String)supervisor[8]);
			
			supervisorList.add(supervisorInfo);
		}
		
		return supervisorList;
	}
	


}
