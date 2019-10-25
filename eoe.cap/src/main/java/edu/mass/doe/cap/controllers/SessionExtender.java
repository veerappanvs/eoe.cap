package edu.mass.doe.cap.controllers;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.mass.doe.EOEAuthorization.exception.NesterException;
import edu.mass.doe.EOEAuthorization.exception.NoOrganizationFoundException;
import edu.mass.doe.cap.dataservice.pojo.CapCycleInfo;
import edu.mass.doe.cap.dataservice.pojo.CapYear;
import edu.mass.doe.cap.model.candidate.CandidateManager;
import edu.mass.doe.cap.model.util.CAPUtil;

import java.util.ArrayList;
import java.util.Calendar;

import edu.mass.doe.cap.security.EOEUser;
import edu.mass.doe.cap.util.CapUtil;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import edu.mass.doe.EOEAuthorization.DirAdminAccessUtil;
import edu.mass.doe.EOEAuthorization.exception.NesterException;
import edu.mass.doe.EOEAuthorization.exception.NoOrganizationFoundException;
import edu.mass.doe.cap.dataservice.entity.StatusReasonType;
import edu.mass.doe.cap.dataservice.pojo.CapAdminViewInfo;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/")
public class SessionExtender {
	
	
	@Autowired
	private Environment env;
	
	private static Logger logger = LoggerFactory.getLogger(SessionExtender.class);

	
	@RequestMapping("extendsession")
	@ResponseBody
	public String landingPage(HttpServletRequest request,Model model, Authentication authentication) throws NesterException, NoOrganizationFoundException {
		return "OK";
	}
}
	