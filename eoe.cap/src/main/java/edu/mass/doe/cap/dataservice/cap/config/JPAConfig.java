package edu.mass.doe.cap.dataservice.cap.config;

import java.net.MalformedURLException;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.jndi.JndiTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * The Class JPAConfig.
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {
		"edu.mass.doe.cap.dataservice" }, entityManagerFactoryRef = "capEntityManagerFactory", transactionManagerRef = "transactionManager", namedQueriesLocation = "classpath:jpa-named-queries.properties")
public class JPAConfig {

	@Autowired
	private Environment environment;

	/**
	 * Entity manager factory.
	 *
	 * @return the local container entity manager factory bean
	 * @throws NamingException the naming exception
	 */
	@Primary
	@Bean(name = "capEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws NamingException {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(capDataSource());
		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setPackagesToScan(new String[] { "edu.mass.doe.cap.dataservice" });
		em.setPersistenceUnitName("capPeristanceUnit");
		em.setJpaVendorAdapter(vendorAdapter);
		em.setJpaProperties(hibernateProperties());

		return em;
	}

	/**
	 * Gets the transaction manager.
	 *
	 * @param emf the emf
	 * @return the transaction manager
	 */
	@Primary
	@Bean(name = "transactionManager")
	public PlatformTransactionManager getTransactionManager(EntityManagerFactory emf) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(emf);
		transactionManager.setGlobalRollbackOnParticipationFailure(true);
		transactionManager.setRollbackOnCommitFailure(true);
		return transactionManager;
	}
	

	/**
	 * Cap data source.
	 *
	 * @return the data source
	 * @throws NamingException the naming exception
	 */
	@Primary
	@Bean(name = "capDataSource", destroyMethod = "")
	public DataSource capDataSource() throws NamingException {
		JndiDataSourceLookup dslookup = new JndiDataSourceLookup();

		Properties jndiProps = new Properties();
		jndiProps.setProperty("java.naming.provider.url", "t3://localhost:7001");
		jndiProps.setProperty("java.naming.factory.initial", "weblogic.jndi.WLInitialContextFactory");
		dslookup.setJndiEnvironment(jndiProps);
		DataSource dataSource = dslookup.getDataSource("CAPPool");
		return dataSource;

	}
	
	
	
	
	/*@Bean(name="springDataSource", destroyMethod="")
	public DataSource dataSource() throws NamingException {
		JndiDataSourceLookup   dslookup = new JndiDataSourceLookup();
		Properties jndiProps = new Properties();
		jndiProps.setProperty("java.naming.provider.url", "t3://localhost:7001");
		jndiProps.setProperty("java.naming.factory.initial", "weblogic.jndi.WLInitialContextFactory");
		dslookup.setJndiEnvironment(jndiProps);
		DataSource dataSource =  dslookup.getDataSource("CapBatchPool");
		return dataSource;
	}*/
	
	


	/**
	 * Exception translation.
	 *
	 * @return the persistence exception translation post processor
	 */
	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

	/**
	 * Hibernate properties.
	 *
	 * @return the properties
	 */
	private Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
		properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
		properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
		properties.put("hibernate.transaction.jta.platform",
				environment.getRequiredProperty("hibernate.transaction.jta.platform"));
		properties.put("hibernate.transaction.manager_lookup_class",
				environment.getRequiredProperty("hibernate.transaction.manager_lookup_class"));
		properties.put("hibernate.archive.autodetection",
				environment.getRequiredProperty("hibernate.archive.autodetection"));
		properties.put("hibernate.default_batch_fetch_size",
				environment.getRequiredProperty("hibernate.default_batch_fetch_size"));
		properties.put("hibernate.use_identifier_rollback",
				environment.getRequiredProperty("hibernate.use_identifier_rollback"));
		properties.put("hibernate.jdbc.fetch_size", environment.getRequiredProperty("hibernate.jdbc.fetch_size"));
		properties.put("hibernate.jdbc.batch_size", environment.getRequiredProperty("hibernate.jdbc.batch_size"));
		properties.put("hibernate.use_sql_comments", environment.getRequiredProperty("hibernate.use_sql_comments"));
		// properties.put("hibernate.default_schema",
		// environment.getRequiredProperty("hibernate.default_schema"));

		return properties;
	}

}
