package com.revature.gamesgalore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ServletComponentScan
@ImportResource({"classpath*:hibernate.cfg.xml"})
public class GamesgaloreApplication extends SpringBootServletInitializer{

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(GamesgaloreApplication.class);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(GamesgaloreApplication.class, args);
	}

}
