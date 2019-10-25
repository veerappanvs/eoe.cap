package edu.mass.doe.cap.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsByNameServiceWrapper;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.security.web.authentication.preauth.RequestHeaderAuthenticationFilter;

import edu.mass.doe.cap.handler.CapAuthenticationSuccessHandler;
import edu.mass.doe.cap.handler.EOEAccessDeniedHandler;
import edu.mass.doe.cap.handler.EOEAuthenticationEntryPoint;
import edu.mass.doe.cap.security.EOELoginFilter;
import edu.mass.doe.cap.security.EOEUserDetailsService;

/**
 * The Class SecurityConfig.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final static Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

	
	@Value("${login.header.name}")
	private String headerName;
	
	@Value("${app.name}")
	private String appName;
	
	@Autowired
	private Environment env;
	
	/* (non-Javadoc)
	 * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.web.builders.HttpSecurity)
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {

				http.authorizeRequests().antMatchers("**/views/pages/errors/**","**/errors/**").permitAll()
				.and().securityContext()
				.and().addFilterAt((requestFilter()), RequestHeaderAuthenticationFilter.class)
				.csrf().disable()
				.authorizeRequests().anyRequest().authenticated()
				.and().exceptionHandling().authenticationEntryPoint(authenticationEntryPoint())				
				.accessDeniedHandler(accessDeniedHandler())
				.and().logout().logoutSuccessHandler(new EOELogoutSuccessHandler()).logoutUrl("/logout")
				.invalidateHttpSession(true).permitAll();
		
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.web.builders.WebSecurity)
	 */
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("**/resources/**", "**/static/**", "**/css/**", "**/script/**","**/webfonts/**","**/images/**");
	}

	/**
	 * Configure global.
	 *
	 * @param auth the auth
	 * @throws Exception the exception
	 */
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(preauthAuthProvider());
	}

	/**
	 * User details service wrapper.
	 *
	 * @return the user details by name service wrapper
	 */
	@Bean
	public UserDetailsByNameServiceWrapper<PreAuthenticatedAuthenticationToken> userDetailsServiceWrapper() {
		UserDetailsByNameServiceWrapper<PreAuthenticatedAuthenticationToken> wrapper = new UserDetailsByNameServiceWrapper<PreAuthenticatedAuthenticationToken>();
		wrapper.setUserDetailsService(new EOEUserDetailsService(appName,env));
		return wrapper;
	}

	/**
	 * Preauth auth provider.
	 *
	 * @return the pre authenticated authentication provider
	 */
	@Bean
	public PreAuthenticatedAuthenticationProvider preauthAuthProvider() {
		PreAuthenticatedAuthenticationProvider preauthAuthProvider = new PreAuthenticatedAuthenticationProvider();
		preauthAuthProvider.setPreAuthenticatedUserDetailsService(userDetailsServiceWrapper());
		return preauthAuthProvider;
	}

	/**
	 * Request filter.
	 *
	 * @return the EOE login filter
	 * @throws Exception the exception
	 */
	@Bean
	public EOELoginFilter requestFilter() throws Exception {
		EOELoginFilter filter = new EOELoginFilter(headerName);
		filter.setAuthenticationManager(super.authenticationManager());
		filter.setAuthenticationSuccessHandler(authenticationSuccessHandler());
		return filter;
	}
	
	/**
	 * Authentication success handler.
	 *
	 * @return the authentication success handler
	 */
	@Bean
	public AuthenticationSuccessHandler authenticationSuccessHandler() {
		return new CapAuthenticationSuccessHandler();
	}

	/**
	 * Access denied handler.
	 *
	 * @return the access denied handler
	 */
	@Bean
	public AccessDeniedHandler accessDeniedHandler() {
		return new EOEAccessDeniedHandler();
	}

	/**
	 * Authentication entry point.
	 *
	 * @return the authentication entry point
	 */
	@Bean
	public AuthenticationEntryPoint authenticationEntryPoint() {
		return new EOEAuthenticationEntryPoint();
	}

}
