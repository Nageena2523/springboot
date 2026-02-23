package com.tejait.batch15;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
@EnableFeignClients
@SpringBootApplication
public class Batch15Application {
	
private static final Logger logger=LogManager.getLogger(Batch15Application.class);
	public static void main(String[] args) {
		SpringApplication.run(Batch15Application.class, args);
		
		logger.debug("Debug Level");// development stage
		logger.info("Info Level");  // production stage
		logger.warn("Warn Level"); // to show warning msgs
		logger.error("Error Level");// to show error msgs
		logger.fatal("Fatal level"); // to show sever db issue logs
		//logger.trace("Trace Level");// to show trace (each Stage) logs
	}

}
