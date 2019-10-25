package edu.mass.doe.cap.mail;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;


/**
 * The Class Mail.
 */
@Component
public class Mail {

    private String from;
    private String to;
    private String subject;
    private List<Object> attachments;
    private Map<String, Object> model;
    private String body;

    /**
     * Instantiates a new mail.
     */
    public Mail() {

    }

    /**
     * Gets the from.
     *
     * @return the from
     */
    public String getFrom() {
        return from;
    }

    /**
     * Sets the from.
     *
     * @param from the new from
     */
    public void setFrom(String from) {
        this.from = from;
    }

    /**
     * Gets the to.
     *
     * @return the to
     */
    public String getTo() {
        return to;
    }

    /**
     * Sets the to.
     *
     * @param to the new to
     */
    public void setTo(String to) {
        this.to = to;
    }

    /**
     * Gets the subject.
     *
     * @return the subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Sets the subject.
     *
     * @param subject the new subject
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * Gets the attachments.
     *
     * @return the attachments
     */
    public List<Object> getAttachments() {
        return attachments;
    }

    /**
     * Sets the attachments.
     *
     * @param attachments the new attachments
     */
    public void setAttachments(List<Object> attachments) {
        this.attachments = attachments;
    }

    /**
     * Gets the model.
     *
     * @return the model
     */
    public Map<String, Object> getModel() {
        return model;
    }

    /**
     * Sets the model.
     *
     * @param model the model
     */
    public void setModel(Map<String, Object> model) {
        this.model = model;
    }

    
	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((attachments == null) ? 0 : attachments.hashCode());
		result = prime * result + ((body == null) ? 0 : body.hashCode());
		result = prime * result + ((from == null) ? 0 : from.hashCode());
		result = prime * result + ((model == null) ? 0 : model.hashCode());
		result = prime * result + ((subject == null) ? 0 : subject.hashCode());
		result = prime * result + ((to == null) ? 0 : to.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Mail other = (Mail) obj;
		if (attachments == null) {
			if (other.attachments != null)
				return false;
		} else if (!attachments.equals(other.attachments))
			return false;
		if (body == null) {
			if (other.body != null)
				return false;
		} else if (!body.equals(other.body))
			return false;
		if (from == null) {
			if (other.from != null)
				return false;
		} else if (!from.equals(other.from))
			return false;
		if (model == null) {
			if (other.model != null)
				return false;
		} else if (!model.equals(other.model))
			return false;
		if (subject == null) {
			if (other.subject != null)
				return false;
		} else if (!subject.equals(other.subject))
			return false;
		if (to == null) {
			if (other.to != null)
				return false;
		} else if (!to.equals(other.to))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Mail [from=" + from + ", to=" + to + ", subject=" + subject + ", attachments=" + attachments
				+ ", model=" + model + ", body=" + body + "]";
	}
	
	
}
