package edu.mass.doe.cap.model.util;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.mass.doe.cap.dataservice.pojo.CapYear;
import edu.mass.doe.cap.dataservice.util.CapService;

/**
 * The Class CAPUtil.
 */
@Component
public class CAPUtil {

	@Autowired(required = true)
	private CapService capService;

	private static Logger logger = LoggerFactory.getLogger(CAPUtil.class);

	/**
	 * Gets the school years.
	 *
	 * @return the school years
	 */
	public List<CapYear> getSchoolYears() {
		return capService.getSchoolYears();
	}
	
	/**
	 * Gets the schoolyear.
	 *
	 * @param date the date
	 * @return the schoolyear
	 */
	public CapYear getSchoolyear(Date date){
		CapYear capYear = null;
		for (CapYear cap : capService.getSchoolYears()) {
			if (cap.getStartDate().compareTo(date)<=0 && cap.getEndDate().compareTo(date)>=0) {
				capYear = cap;
				break;
			}
		}		
		return capYear;
	}

	/**
	 * Gets the curr school year.
	 *
	 * @return the curr school year
	 */
	public CapYear getCurrSchoolYear() {
		CapYear capYear = null;
		for (CapYear cap : capService.getSchoolYears()) {
			if (cap.isActive()) {
				capYear = cap;
				break;
			}
		}
		return capYear;
	}

}
