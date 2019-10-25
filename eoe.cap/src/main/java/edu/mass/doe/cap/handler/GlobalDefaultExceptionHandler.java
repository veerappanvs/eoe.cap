package edu.mass.doe.cap.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * The Class GlobalDefaultExceptionHandler.
 */
@ControllerAdvice
public class GlobalDefaultExceptionHandler  {

	public static final String DEFAULT_ERROR_VIEW = "errors/errorPage";
	private static Logger logger = LoggerFactory.getLogger(GlobalDefaultExceptionHandler.class);


	/**
	 * Default error handler.
	 *
	 * @param req the req
	 * @param e the e
	 * @return the model and view
	 * @throws Exception the exception
	 */
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = Throwable.class)
	public ModelAndView defaultErrorHandler(HttpServletRequest req, Throwable e) throws Exception {
		logger.error("defaultErrorHandler",e);
		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", e);
		mav.addObject("url", req.getRequestURL());
		mav.setViewName("errors/errorPage");
		return mav;
	}
	
	/**
	 * Access denied handler.
	 *
	 * @param req the req
	 * @param res the res
	 * @param e the e
	 * @return the model and view
	 * @throws Exception the exception
	 */
	@ResponseStatus(value = HttpStatus.FORBIDDEN)
	@ExceptionHandler(value = AccessDeniedException.class)
	public ModelAndView accessDeniedHandler(HttpServletRequest req,HttpServletResponse res, Throwable e) throws Exception { 
		logger.error("accessDeniedHandler",e);
		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", e);
		mav.addObject("url", req.getRequestURL());
		mav.setViewName("errors/accessDenied");
		
		return mav;
	}

}
