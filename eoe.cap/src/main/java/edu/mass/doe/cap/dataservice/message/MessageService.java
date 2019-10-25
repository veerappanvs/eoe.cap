package edu.mass.doe.cap.dataservice.message;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import edu.mass.doe.cap.dataservice.entity.Message;
import edu.mass.doe.cap.dataservice.meeting.ThreewayMeetingService;

/**
 * The Class MessageService.
 */
@Service
public class MessageService {

	private static Logger logger = LoggerFactory.getLogger(MessageService.class);

	@PersistenceContext(unitName = "capPeristanceUnit")
	EntityManager entityManager;

	@Autowired
	private Environment env;
	
	@Autowired
	private MessageRepository messageRepository;
	
	
	/**
	 * Find messages.
	 *
	 * @param cycleId the cycle id
	 * @return the list
	 */
	public List<Message> findMessages(Long cycleId){
		return messageRepository.findMessages(cycleId);
	}

	/**
	 * Creates the.
	 *
	 * @param obj the obj
	 * @return the message
	 */
	@Transactional(value = TxType.REQUIRED)
	public Message create(Message obj) {
		entityManager.persist(obj);
		return obj;
	}

	/**
	 * Update.
	 *
	 * @param obj the obj
	 * @return the message
	 */
	@Transactional(value = TxType.REQUIRED)
	public Message update(Message obj) {
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
		Message message = entityManager.find(Message.class, primaryKey);
		entityManager.remove(message);
	}

}
