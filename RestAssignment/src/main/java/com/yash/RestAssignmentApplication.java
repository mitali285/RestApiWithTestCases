package com.yash;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.hateoas.ResourceSupport;

@SpringBootApplication
public class RestAssignmentApplication  extends ResourceSupport  {

	public static void main(String[] args) {
		SpringApplication.run(RestAssignmentApplication.class, args);
	}

}
