package edu.mass.doe.cap.handler;

import java.io.IOException;
import java.util.Collection;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import edu.mass.doe.cap.security.EOEUser;


/**
 * The Class CapAuthenticationSuccessHandler.
 */
@Component
public class CapAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	

	@Autowired
	private Environment env;
	
	
	protected Logger logger = LoggerFactory.getLogger(CapAuthenticationSuccessHandler.class);
	
	 
	
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		int sessionTimeoutinSec=Integer.valueOf(env.getProperty("server.servlet.session.timeout"))*60;
		int sessionTimeoutCountdown=Integer.valueOf(env.getProperty("cap.session.timeout.countdown"));
		HttpSession session=request.getSession();
		session.setMaxInactiveInterval(sessionTimeoutinSec);
		session.setAttribute("sessionTimeoutCountdown", sessionTimeoutCountdown);
		session.setAttribute("CAP_LOGIN_REDIRECT", true);
		response.setStatus(HttpServletResponse.SC_OK);
		clearAuthenticationAttributes(request);
	}

	/**
	 * Removes temporary authentication-related data which may have been stored in the
	 * session during the authentication process.
	 */
	protected final void clearAuthenticationAttributes(HttpServletRequest request) {
		HttpSession session = request.getSession(false);

		if (session == null) {
			return;
		}

		session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
	}
	
	
 
   
}
