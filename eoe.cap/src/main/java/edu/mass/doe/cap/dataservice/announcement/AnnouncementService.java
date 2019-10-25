package edu.mass.doe.cap.dataservice.announcement;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import edu.mass.doe.cap.dataservice.entity.Announcement;
import edu.mass.doe.cap.dataservice.entity.Message;

/**
 * The Class AnnouncementService.
 */
@Service
public class AnnouncementService {
	
	private static Logger logger = LoggerFactory.getLogger(AnnouncementService.class);

	@PersistenceContext(unitName = "capPeristanceUnit")
	EntityManager entityManager;

	@Autowired
	private Environment env;
	
	@Autowired
	private AnnouncementRepository announcementRepository;
	
	
	/**
	 * Gets the announcement.
	 *
	 * @return the announcement
	 */
	public Announcement getAnnouncement(){
		return announcementRepository.findAnnouncement();
	}
	

	
	/**
	 * Creates the.
	 *
	 * @param obj the obj
	 * @return the announcement
	 */
	@Transactional(value = TxType.REQUIRED)
	public Announcement create(Announcement obj) {
		entityManager.persist(obj);
		return obj;
	}

	/**
	 * Update.
	 *
	 * @param obj the obj
	 * @return the announcement
	 */
	@Transactional(value = TxType.REQUIRED)
	public Announcement update(Announcement obj) {
		entityManager.merge(obj);
		return obj;
	}

	/**
	 * Delete.
	 *
	 * @param primaryKey the primary key
	 */
	@Transactional(value = TxType.REQUIRED)
	public void delete(Long primaryKey) {
		Announcement announcement = entityManager.find(Announcement.class, primaryKey);
		entityManager.remove(announcement);
	}
}
