
package org.authservice;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;


@SpringBootApplication
@EnableDiscoveryClient
public class OAuth2Application
{

	public static final	Logger logger = LoggerFactory.getLogger( OAuth2Application.class );

	private	static ConfigurableApplicationContext iocContext;


	public static void main(String[] args) {
		iocContext = SpringApplication.run(  OAuth2Application.class, args );
	}


// ------------------------------------------------

	private static String[] nameBeansInContainer()
	{
		String[] beans = iocContext.getBeanDefinitionNames();

		 for( String b : beans ) {
			 logger.info( b );
		 }

		return beans;
	}

	public static Object getBean( String beanName ) {
		return iocContext.getBean( beanName );		
	}

	public static boolean isBeanInContext( String beanName ) {
		return iocContext.containsBean( beanName );
	}
// ------------------------------------------------

	/***
	 * Configuration of the DataSource
	 * @return
	 */
	@Bean
	public static DataSource dataSource()
	{
		DriverManagerDataSource ds = new DriverManagerDataSource();
	    ds.setDriverClassName( "org.mariadb.jdbc.Driver" );
	    ds.setUrl( "jdbc:mariadb://localhost:3306/contacts" );
	    ds.setUsername( "root" );
	    ds.setPassword( "123" );
	    return ds;
	}

	/***
	 * Configuration of the beans for the JPA and Hibernate
	 * @return
	 */
	@Bean
	public static JpaVendorAdapter jpaVendorAdapter()
	{
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		adapter.setDatabase( Database.MYSQL );
		adapter.setShowSql( true );
		adapter.setGenerateDdl( false );
		adapter.setDatabasePlatform( "org.hibernate.dialect.MySQLDialect" );

		return adapter;
	}
	
	@Bean
	public static LocalContainerEntityManagerFactoryBean entityManagerFactory( DataSource dataSource, JpaVendorAdapter jpaVendorAdapter )
	{
		LocalContainerEntityManagerFactoryBean  emfb = new LocalContainerEntityManagerFactoryBean();
		emfb.setDataSource( dataSource );
		emfb.setJpaVendorAdapter( jpaVendorAdapter );
		emfb.setPackagesToScan( "org.authservice" );
		return emfb;
	}
	
}