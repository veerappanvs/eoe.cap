package edu.mass.doe.cap.dataservice.epims.config;

import java.util.Properties;

import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "epimsEntityManagerFactory", transactionManagerRef = "epimsUserTransactionManager")
public class EPIMSJpaConfig {



	@Autowired
	private Environment environment;

	@Bean(name="epimsEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean epimsEntityManagerFactory() throws NamingException {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(epimsDataSource());
		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setPackagesToScan(new String[] { "edu.mass.doe.cap.dataservice" });
		em.setPersistenceUnitName("epimsPerssistanceUnit");
		em.setJpaVendorAdapter(vendorAdapter);
		em.setJpaProperties(hibernateProperties());
		return em;
	}

	
	@Bean(name="epimsUserTransactionManager")
	public PlatformTransactionManager epimsUserTransactionManager(EntityManagerFactory emf) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(emf);
		return transactionManager;
	}

	@Bean(name="epimsDataSource", destroyMethod="")
	public DataSource epimsDataSource() throws NamingException {
		JndiDataSourceLookup   dslookup = new JndiDataSourceLookup();
		
		Properties jndiProps = new Properties();
		jndiProps.setProperty("java.naming.provider.url", "t3://localhost:7001");
		jndiProps.setProperty("java.naming.factory.initial", "weblogic.jndi.WLInitialContextFactory");
		dslookup.setJndiEnvironment(jndiProps);
		DataSource dataSource =  dslookup.getDataSource("EPIMSPool");
		return dataSource;

	}

	

	private Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
		properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
		properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
		properties.put("hibernate.transaction.jta.platform", environment.getRequiredProperty("hibernate.transaction.jta.platform"));
		properties.put("hibernate.transaction.manager_lookup_class", environment.getRequiredProperty("hibernate.transaction.manager_lookup_class"));
		properties.put("hibernate.archive.autodetection", environment.getRequiredProperty("hibernate.archive.autodetection"));
		properties.put("hibernate.default_batch_fetch_size", environment.getRequiredProperty("hibernate.default_batch_fetch_size"));
		properties.put("hibernate.use_identifier_rollback", environment.getRequiredProperty("hibernate.use_identifier_rollback"));
		properties.put("hibernate.jdbc.fetch_size", environment.getRequiredProperty("hibernate.jdbc.fetch_size"));
		properties.put("hibernate.jdbc.batch_size", environment.getRequiredProperty("hibernate.jdbc.batch_size"));
		properties.put("hibernate.use_sql_comments", environment.getRequiredProperty("hibernate.use_sql_comments"));
		
		
		return properties;
	}

	



}
