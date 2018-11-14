package com.bank.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@Configuration
@PropertySource("classpath:datasource.properties")
public class DataSourceConfig {

	@Autowired
	private Environment env;
//
//	@Bean
//	public DataSource dataSource() {
//		BasicDataSource basicDataSource = new BasicDataSource();
//		basicDataSource.setDriverClassName(env.getProperty("db.driver"));
//		basicDataSource.setUrl(env.getProperty("db.url"));
//		basicDataSource.setUsername(env.getProperty("db.user"));
//		basicDataSource.setPassword(env.getProperty("db.password"));
//		return basicDataSource;
//	}
	
	
	@Bean
	public DataSource dataSource() {
		
		// no need shutdown, EmbeddedDatabaseFactoryBean will take care of this
		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		EmbeddedDatabase db = builder
			.setType(EmbeddedDatabaseType.H2) //.H2 or .DERBY
			.addScript("create-db.sql")
			.addScript("insert-data.sql")
			.build();
		return db;
	}


}
