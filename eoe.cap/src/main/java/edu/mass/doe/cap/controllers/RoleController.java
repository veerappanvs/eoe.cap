package edu.mass.doe.cap.controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.mass.doe.EOEAuthorization.DirAdminAccessUtil;
import edu.mass.doe.EOEAuthorization.EOEAdminOrgInfo;
import edu.mass.doe.EOEAuthorization.EOEAdminUserInfo;
import edu.mass.doe.EOEAuthorization.exception.NesterException;
import edu.mass.doe.cap.security.EOEUser;

/**
 * The Class RoleController.
 */
@Controller
@RequestMapping("/role")
public class RoleController {
	
	@Autowired
	public Environment env;

	/**
	 * Gets the role.
	 *
	 * @param model the model
	 * @return the role
	 */
	@GetMapping
	public String getRole(Model model) {

		EOEUser eoeUser = (EOEUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Collection<SimpleGrantedAuthority> authorities = eoeUser.getAuthorities();

		List<String> roles = new ArrayList<String>();
		authorities.stream().forEach(simpleGrantedAuthority -> {
			roles.add(simpleGrantedAuthority.getAuthority());
		});
		model.addAttribute("roles", roles);

		if(roles.size()<=1)
			return "redirect:/home";
		
		return "roleChange";
	}

	/**
	 * Switch role.
	 *
	 * @param role the role
	 * @param model the model
	 * @return the string
	 */
	@PostMapping
	public String switchRole(@RequestParam("selectedRole") String role, Model model) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		EOEUser eoeUser = (EOEUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Collection<SimpleGrantedAuthority> authorities = eoeUser.getAuthorities();

		Iterator<SimpleGrantedAuthority> iterator = authorities.iterator();
		while (iterator.hasNext()) {
			SimpleGrantedAuthority authority = iterator.next();
			if (!authority.getAuthority().equals(role))
				iterator.remove();
		}

		eoeUser.setAuthorities(authorities);
		Authentication newAuthentication = new UsernamePasswordAuthenticationToken(
				getPrincipal(eoeUser.getUsername(), authorities), authentication.getCredentials(), authorities);
		SecurityContextHolder.getContext().setAuthentication(newAuthentication);
		return "redirect:/home";
	}
	
	
	/**
	 * Gets the principal.
	 *
	 * @param userName the user name
	 * @param authorities the authorities
	 * @return the principal
	 * @throws UsernameNotFoundException the username not found exception
	 */
	public UserDetails getPrincipal(String userName,Collection<SimpleGrantedAuthority> authorities) throws UsernameNotFoundException {
		
		
		EOEUser user = null;

		EOEAdminUserInfo eoeUserInfo;
		EOEAdminOrgInfo eOEAdminOrgInfo;
		try {
			eoeUserInfo = DirAdminAccessUtil.getUserInfo(userName, "cap");
			String roleCode=null;
			SimpleGrantedAuthority authority= authorities.iterator().next();
			String[] roles = eoeUserInfo.getUserRoles();
			for(int i=0;i<roles.length;i++){
				 roleCode =roles[i];
				 String role=	env.getProperty("role.code."+roleCode);
				if(role.equals(authority.getAuthority()))
					break;
			}
			
			List orgs= (List) eoeUserInfo.getRolesOrg().get(roleCode);
			eOEAdminOrgInfo =(edu.mass.doe.EOEAuthorization.EOEAdminOrgInfo) orgs.get(0);
		} catch (NesterException e) {
			throw new UsernameNotFoundException(e.getMessage(),e);
		}
		user = new EOEUser(eoeUserInfo.getUserID(), "password", authorities);
		user.setOrgId(eOEAdminOrgInfo.getOrgID());
		user.setPersonId(new Long(eoeUserInfo.getDaPersonID()));
		user.setName(eoeUserInfo.getName());
		user.setOrgName(eOEAdminOrgInfo.getOrgName());
		
		return user;
	}

}
