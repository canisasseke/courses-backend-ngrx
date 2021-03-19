package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.business.ICourseBusiness;
import com.example.demo.models.Course;

@SpringBootApplication
public class CoursesBackendNgrxApplication  implements CommandLineRunner{

	@Autowired
	ICourseBusiness courseBusiness;
	public static void main(String[] args) {
		SpringApplication.run(CoursesBackendNgrxApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		courseBusiness.createCourse(new Course("Angular 11 - The complete Guide", "Master Angular and build awesome, reactive web apps"));
		courseBusiness.createCourse(new Course("Spring Boot", "Learn Spring Boot - Rapid spring Application Development"));
		courseBusiness.createCourse(new Course("React - The complete Guide", "Dive in and learn React.js from scratch!"));
		courseBusiness.createCourse(new Course("Docker - The complete Guide", "Dive in Containers from scratch!"));
	}

}
