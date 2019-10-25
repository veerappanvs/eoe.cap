package edu.mass.doe.cap.dataservice.entity;

import java.io.Serializable;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import edu.mass.doe.cap.security.EOEUser;

public class SimpleAuditIdentity  implements AuditIdentity, Serializable  {

	private static final long serialVersionUID = -3426657714422211111L;
	private Long personId;

    public SimpleAuditIdentity() {
    	
    	
    	
    };

    /**
     * Returns the string to be logged in the create_user and/or modify_user audit columns of GMAS DB tables.
     * @return String - The audit identity String 
     */
    public Long getAuditIdentity(){
        return personId;
    }

    public SimpleAuditIdentity(Long personId) {
    	personId =this.personId;
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((personId == null) ? 0 : personId.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof SimpleAuditIdentity))
			return false;
		SimpleAuditIdentity other = (SimpleAuditIdentity) obj;
		if (personId == null) {
			if (other.personId != null)
				return false;
		} else if (!personId.equals(other.personId))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SimpleAuditIdentity [personId=" + personId + "]";
	} 

}
