package edu.mass.doe.cap.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * The Class EOEAccessDeniedHandler.
 */
public class EOEAccessDeniedHandler implements AccessDeniedHandler {

	public static final Logger LOG = LoggerFactory.getLogger(EOEAccessDeniedHandler.class);

	/* (non-Javadoc)
	 * @see org.springframework.security.web.access.AccessDeniedHandler#handle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.access.AccessDeniedException)
	 */
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException exc)
			throws IOException, ServletException {
		response.sendRedirect(request.getContextPath()+"/errors/accessDenied.jsp");
	    
	}

}
