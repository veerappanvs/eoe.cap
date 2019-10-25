package edu.mass.doe.cap.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.RequestHeaderAuthenticationFilter;

/**
 * The Class EOELoginFilter.
 */
public class EOELoginFilter extends RequestHeaderAuthenticationFilter {

	private static Logger logger = LoggerFactory.getLogger(EOELoginFilter.class);


	private String sCookie;
	private String orgRequestHeader;
	
	/**
	 * Instantiates a new EOE login filter.
	 *
	 * @param headerName the header name
	 */
	public EOELoginFilter(String headerName)  {
		super();
		this.setPrincipalRequestHeader(headerName);
		this.setExceptionIfHeaderMissing(false);

	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		super.doFilter(request, response, chain);
		
		
	}

	/**
	 * Sets the org request header.
	 *
	 * @param sOrgRequestHeader the new org request header
	 */
	public void setOrgRequestHeader(String sOrgRequestHeader) {
		this.orgRequestHeader = sOrgRequestHeader;
	}

	/**
	 * Gets the org request header.
	 *
	 * @return the org request header
	 */
	public String getOrgRequestHeader() {
		return orgRequestHeader;
	}

	/**
	 * Gets the user info from cookie.
	 *
	 * @param request the request
	 * @return the user info from cookie
	 */
	private String getUserInfoFromCookie(HttpServletRequest request) {
		String user = null;
		logger.info("request.getCookies() " + request.getCookies() + "::" + sCookie);

		Cookie[] cookies = request.getCookies();
		for (int i = 0; i < cookies.length; i++) {
			logger.info("Cookies " + cookies[i].getName());
			if (cookies[i].getName().equalsIgnoreCase(sCookie)) {
				user = cookies[i].getValue();
				break;
			}
		}
		return user;

	}

}
