package com.adpguima.aircraft.config;

import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Component
@EnableTransactionManagement
@PropertySource(value = { "classpath:application.properties" })
public class AppConfig {

	@Autowired
	private Environment env;

	/**
	 * Datasource will connect with database
	 * 
	 * @return Datasource
	 * @throws NamingException for Error
	 */
	@Bean(destroyMethod = "")
	public DataSource dataSource() throws NamingException {

		final DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUsername(env.getProperty("mysql.user"));
		dataSource.setPassword(env.getProperty("mysql.password"));
		dataSource.setUrl(env.getProperty("mysql.url"));
		dataSource.setDriverClassName(env.getProperty("mysql.driver"));
		return dataSource;
	}

	/**
	 * Manager for database transactions
	 * 
	 * @param emf EntityManagerFactory
	 * @return transaction manager
	 */
	@Bean
	public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
		return new JpaTransactionManager(emf);
	}
}