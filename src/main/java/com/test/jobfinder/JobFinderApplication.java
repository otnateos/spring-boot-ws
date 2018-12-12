package com.test.jobfinder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.test.jobfinder")
public class JobFinderApplication {
	private static final Logger log = LoggerFactory.getLogger(JobFinderApplication.class);

	public static void main(String[] args) {
		log.info("Initialising JobFinder Application");
		SpringApplication.run(JobFinderApplication.class, args);
	}
}
