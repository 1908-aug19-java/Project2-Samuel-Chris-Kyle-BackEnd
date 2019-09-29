package com.revature.gamesgalore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@ServletComponentScan
//@PropertySource("file:/usr/tmp/application.properties")
//@PropertySource("file:C:\\Users\\Samuel\\properties\\application.properties"")
@PropertySource("file:"+ GamesgaloreApplication.PROPETIES_FILE)
public class GamesgaloreApplication extends SpringBootServletInitializer{

	public static final String PROPETIES_FILE = "/usr/tmp/application.properties";
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(GamesgaloreApplication.class);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(GamesgaloreApplication.class, args);
	}

}
