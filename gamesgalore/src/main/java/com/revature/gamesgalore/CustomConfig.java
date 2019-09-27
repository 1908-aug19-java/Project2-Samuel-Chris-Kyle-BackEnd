package com.revature.gamesgalore;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class CustomConfig {

	@Bean
	@Primary
	public DataSource dataSource() {
	    return DataSourceBuilder
	        .create()
	        .username(System.getenv("GG_USERNAME"))
	        .password(System.getenv("GG_PASS"))
	        .url(System.getenv("GG_URL"))
	        .driverClassName(System.getenv("GG_DRIVER"))
	        .build();
	}
}
