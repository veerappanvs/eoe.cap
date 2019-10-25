package edu.mass.doe.cap.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import edu.mass.doe.EOEAuthorization.DirAdminAccessUtil;
import edu.mass.doe.EOEAuthorization.EOEAdminOrgInfo;
import edu.mass.doe.EOEAuthorization.EOEAdminUserInfo;
import edu.mass.doe.EOEAuthorization.exception.NesterException;


/**
 * The Class EOEUserDetailsService.
 */
public class EOEUserDetailsService implements UserDetailsService {

	private static Logger logger = LoggerFactory.getLogger(EOEUserDetailsService.class);
	

	private String appName;
	
	@Autowired
	private Environment env;
	
	
/**
 * Instantiates a new EOE user details service.
 *
 * @param appName the app name
 * @param env the env
 */
public EOEUserDetailsService( String appName,Environment env) {
	this.appName=appName;
	this.env=env;
}
	
	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		
		EOEUser user = null;

		EOEAdminUserInfo eoeUserInfo;
		EOEAdminOrgInfo eOEAdminOrgInfo;
		try {
			eoeUserInfo = DirAdminAccessUtil.getUserInfo(username, appName);
			logger.info("eoeUserInfo "+eoeUserInfo);
			List orgs= (List) eoeUserInfo.getRolesOrg().get(eoeUserInfo.getUserRoles()[0]);
			eOEAdminOrgInfo =(edu.mass.doe.EOEAuthorization.EOEAdminOrgInfo) orgs.get(0);
		} catch (NesterException e) {
			throw new UsernameNotFoundException(e.getMessage(),e);
		}
		user = new EOEUser(eoeUserInfo.getUserID(), "password", getGrantedAuthorities(eoeUserInfo));
		user.setOrgId(eOEAdminOrgInfo.getOrgID());
		user.setPersonId(new Long(eoeUserInfo.getDaPersonID()));
		user.setName(eoeUserInfo.getName());
		user.setOrgName(eOEAdminOrgInfo.getOrgName());
		
		return user;
	}

	
	

	/**
	 * Gets the granted authorities.
	 *
	 * @param eoeUserInfo the eoe user info
	 * @return the granted authorities
	 */
	public Collection<SimpleGrantedAuthority> getGrantedAuthorities(EOEAdminUserInfo eoeUserInfo) {

		Collection<SimpleGrantedAuthority> clc = new ArrayList<SimpleGrantedAuthority>();
		SimpleGrantedAuthority grt1 = null;
		String[] userRole = eoeUserInfo.getUserRoles();
		for (String role : userRole) {
			grt1 = new SimpleGrantedAuthority(env.getProperty("role.code."+role));
			clc.add(grt1);
		}
		
		return clc;
	}


	/**
	 * Gets the app name.
	 *
	 * @return the app name
	 */
	public String getAppName() {
		return appName;
	}




	/**
	 * Sets the app name.
	 *
	 * @param appName the new app name
	 */
	public void setAppName(String appName) {
		this.appName = appName;
	}
	
}