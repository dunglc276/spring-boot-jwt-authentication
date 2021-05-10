package com.lcd.jwt;

import java.net.InetAddress;
import java.util.TimeZone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class SpringBootJwtAuthenticationApplication {

	 private static final Logger LOGGER = LoggerFactory.getLogger(SpringBootJwtAuthenticationApplication.class);

	  public static void main(String[] args) throws Exception {
	    SpringApplication application = new SpringApplication(SpringBootJwtAuthenticationApplication.class);
	    Environment env = application.run(args).getEnvironment();
	    LOGGER.info("\n----------------------------------------------------------\n\t" + "Application '{}' is running! Access URLs:\n\t"
	            + "External: \thttp://{}:{}\n----------------------------------------------------------\n" + "With Timezone: " + TimeZone.getDefault().getDisplayName() + " ("
	            + TimeZone.getDefault().getID() + ")", env
	            .getProperty("spring.application.name"), InetAddress.getLocalHost().getHostAddress(), env.getProperty("server.port"));
	    }

	}
