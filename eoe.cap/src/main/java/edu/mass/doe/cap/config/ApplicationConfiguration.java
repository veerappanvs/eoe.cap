package edu.mass.doe.cap.config;


import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.Validator;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;


/**
 * The Class ApplicationConfiguration.
 */
@Configuration
@EnableWebMvc
@EnableBatchProcessing
@PropertySources({@PropertySource("classpath:application_${us.ma.state.edu.env_name}.properties"),@PropertySource("classpath:sql.properties")})
public class ApplicationConfiguration implements WebMvcConfigurer {

		
	@Autowired private JobBuilderFactory jobBuilderFactory;
	
    @Autowired private StepBuilderFactory steps;
    
    @Autowired
    @Qualifier(value = "capDataSource") 
    DataSource dataSource;
    
    @Autowired @Qualifier(value="transactionManager")PlatformTransactionManager transactionManager;
    
    @Autowired
    private RequestMappingHandlerAdapter requestMappingHandlerAdapter;
    
    /**
     * Inits the.
     */
    @PostConstruct
    public void init() {
        requestMappingHandlerAdapter.setIgnoreDefaultModelOnRedirect(true);
    }
 
    /**
     * Gets the job repository.
     *
     * @return the job repository
     * @throws Exception the exception
     */
    @Bean
	 public JobRepository getJobRepository() throws Exception {
	        JobRepositoryFactoryBean factory = new JobRepositoryFactoryBean();
	        factory.setDataSource(dataSource);
	        factory.setTransactionManager(transactionManager);
	        factory.setDatabaseType("Oracle");
	        factory.setIsolationLevelForCreate("ISOLATION_READ_COMMITTED");
	        return (JobRepository) factory.getObject();
	    }
	     
	  
	
	/**
	 * Configure TilesConfigurer.
	 *
	 * @return the tiles configurer
	 */
	@Bean
	public TilesConfigurer tilesConfigurer() {
		TilesConfigurer tilesConfigurer = new TilesConfigurer();
		tilesConfigurer.setDefinitions(new String[] { "/WEB-INF/views/**/tiles.xml" });
		tilesConfigurer.setCheckRefresh(true);
		return tilesConfigurer;
	}

	/**
	 * Configure ViewResolvers to deliver views.
	 *
	 * @param registry the registry
	 */
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		TilesViewResolver viewResolver = new TilesViewResolver();
		viewResolver.setViewNames(".*");
		registry.viewResolver(viewResolver);

		InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver(
				"/WEB-INF/views/pages/", ".jsp");
		registry.viewResolver(internalResourceViewResolver);
	}

	/**
	 * Message source.
	 *
	 * @return the message source
	 */
	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages");
		return messageSource;
	}

	/**
	 * Configure ResourceHandlers to serve static resources.
	 *
	 * @param registry the registry
	 */

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/css/**").addResourceLocations("/css/");
		registry.addResourceHandler("/script/**").addResourceLocations("/script/");
		registry.addResourceHandler("/webfonts/**").addResourceLocations("/webfonts/");
		registry.addResourceHandler("/images/**").addResourceLocations("/images/");
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurer#addInterceptors(org.springframework.web.servlet.config.annotation.InterceptorRegistry)
	 */
	@Override
	public void addInterceptors(final InterceptorRegistry registry) {
		registry.addInterceptor(new RequestInterceptor()).excludePathPatterns("/css/**","/script/**","/webfonts/**","/images/**");
	}
	

    


	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurer#addArgumentResolvers(java.util.List)
	 */
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> arg0) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurer#addCorsMappings(org.springframework.web.servlet.config.annotation.CorsRegistry)
	 */
	@Override
	public void addCorsMappings(CorsRegistry arg0) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurer#addFormatters(org.springframework.format.FormatterRegistry)
	 */
	@Override
	public void addFormatters(FormatterRegistry arg0) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurer#addReturnValueHandlers(java.util.List)
	 */
	@Override
	public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> arg0) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurer#addViewControllers(org.springframework.web.servlet.config.annotation.ViewControllerRegistry)
	 */
	@Override
	public void addViewControllers(ViewControllerRegistry arg0) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurer#configureAsyncSupport(org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer)
	 */
	@Override
	public void configureAsyncSupport(AsyncSupportConfigurer arg0) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurer#configureContentNegotiation(org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer)
	 */
	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer arg0) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurer#configureDefaultServletHandling(org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer)
	 */
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer arg0) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurer#configureHandlerExceptionResolvers(java.util.List)
	 */
	@Override
	public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> arg0) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurer#configureMessageConverters(java.util.List)
	 */
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> arg0) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurer#configurePathMatch(org.springframework.web.servlet.config.annotation.PathMatchConfigurer)
	 */
	@Override
	public void configurePathMatch(PathMatchConfigurer arg0) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurer#extendHandlerExceptionResolvers(java.util.List)
	 */
	@Override
	public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> arg0) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurer#extendMessageConverters(java.util.List)
	 */
	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> arg0) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurer#getMessageCodesResolver()
	 */
	@Override
	public MessageCodesResolver getMessageCodesResolver() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurer#getValidator()
	 */
	@Override
	public Validator getValidator() {
		// TODO Auto-generated method stub
		return null;
	}



	/**
	 * Gets the transaction manager.
	 *
	 * @return the transaction manager
	 */
	public PlatformTransactionManager getTransactionManager() {
		return transactionManager;
	}



	/**
	 * Sets the transaction manager.
	 *
	 * @param transactionManager the new transaction manager
	 */
	public void setTransactionManager(PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

}
