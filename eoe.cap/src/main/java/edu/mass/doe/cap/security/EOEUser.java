package edu.mass.doe.cap.security;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;


/**
 * The Class EOEUser.
 */
public class EOEUser implements UserDetails {
	

	 
	 private static final long serialVersionUID = 1924132530173910545L;
	 
	 private Collection authorities;
	 private String password;
	 private String username;
	 private Long orgId;
	 private String name;
	 private Long personId;
	 private String orgName;
	 
	 /**
 	 * Gets the org id.
 	 *
 	 * @return the org id
 	 */
 	public Long getOrgId() {
		return orgId;
	}

	/**
	 * Sets the org id.
	 *
	 * @param orgId the new org id
	 */
	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	/**
	 * Instantiates a new EOE user.
	 */
	public EOEUser() {}
	 
	 /**
 	 * Instantiates a new EOE user.
 	 *
 	 * @param username the username
 	 * @param password the password
 	 */
 	public EOEUser(String username, String password) {
	  this.username = username;
	  this.password = password;
	 }
	 
	 /**
 	 * Instantiates a new EOE user.
 	 *
 	 * @param username the username
 	 * @param password the password
 	 * @param authorities the authorities
 	 */
 	public EOEUser(String username, String password, Collection authorities) {
	  this.username = username;
	  this.password = password;
	  this.authorities = authorities;
	 }

	 /* (non-Javadoc)
 	 * @see org.springframework.security.core.userdetails.UserDetails#getAuthorities()
 	 */
 	@Override
	 public Collection getAuthorities() {
	  return authorities;
	 }

	 /* (non-Javadoc)
 	 * @see org.springframework.security.core.userdetails.UserDetails#getPassword()
 	 */
 	@Override
	 public String getPassword() {
	  return this.password;
	 }

	 /* (non-Javadoc)
 	 * @see org.springframework.security.core.userdetails.UserDetails#getUsername()
 	 */
 	@Override
	 public String getUsername() {
	  return this.username;
	 }

	 //NOTE If any of these things can be retrieved from your SSO Authentiation point, you may want to use them.

	 /* (non-Javadoc)
 	 * @see org.springframework.security.core.userdetails.UserDetails#isAccountNonExpired()
 	 */
 	@Override
	 public boolean isAccountNonExpired() {
	  return true;
	 }

	 /* (non-Javadoc)
 	 * @see org.springframework.security.core.userdetails.UserDetails#isAccountNonLocked()
 	 */
 	@Override
	 public boolean isAccountNonLocked() {
	  return true;
	 }

	 /* (non-Javadoc)
 	 * @see org.springframework.security.core.userdetails.UserDetails#isCredentialsNonExpired()
 	 */
 	@Override
	 public boolean isCredentialsNonExpired() {
	  return true;
	 }

	 /* (non-Javadoc)
 	 * @see org.springframework.security.core.userdetails.UserDetails#isEnabled()
 	 */
 	@Override
	 public boolean isEnabled() {
	  return true;
	 }

	/**
	 * Gets the person id.
	 *
	 * @return the person id
	 */
	public Long getPersonId() {
		return personId;
	}

	/**
	 * Sets the authorities.
	 *
	 * @param authorities the new authorities
	 */
	public void setAuthorities(Collection authorities) {
		this.authorities = authorities;
	}

	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Sets the username.
	 *
	 * @param username the new username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Sets the person id.
	 *
	 * @param personId the new person id
	 */
	public void setPersonId(Long personId) {
		this.personId = personId;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the org name.
	 *
	 * @return the org name
	 */
	public String getOrgName() {
		return orgName;
	}

	/**
	 * Sets the org name.
	 *
	 * @param orgName the new org name
	 */
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "EOEUser [authorities=" + authorities + ", password=" + password + ", username=" + username + ", orgId="
				+ orgId + ", name=" + name + ", personId=" + personId + ", orgName=" + orgName + "]";
	}

	
}
