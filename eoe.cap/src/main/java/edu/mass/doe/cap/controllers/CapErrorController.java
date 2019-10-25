package edu.mass.doe.cap.controllers;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * The Class CapErrorController.
 */
@Controller
@RequestMapping("error")
public class CapErrorController implements ErrorController {

	private static final String PATH = "/error";
	private static Logger logger = LoggerFactory.getLogger(ErrorController.class);


	/**
	 * Default error handler.
	 *
	 * @param req the req
	 * @param e the e
	 * @return the model and view
	 * @throws Exception the exception
	 */
	@RequestMapping
	public ModelAndView defaultErrorHandler(HttpServletRequest req, Throwable e) throws Exception {
	   logger.error("defaultErrorHandler",e);
		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", e);
		mav.addObject("url", req.getRequestURL());
		mav.setViewName("errors/errorPage");
		return mav;
	}

	/* (non-Javadoc)
	 * @see org.springframework.boot.autoconfigure.web.ErrorController#getErrorPath()
	 */
	@Override
	public String getErrorPath() {
		// TODO Auto-generated method stub
		return PATH;
	}

}
