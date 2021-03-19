package com.example.demo.web.rest;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.business.ICourseBusiness;
import com.example.demo.models.Course;

@RestController
@CrossOrigin("*")
public class CourseRestController {

	@Autowired
	ICourseBusiness courseBusiness;
	
	@GetMapping("courses")
	public List<Course> getAllCourses(){
		return courseBusiness.getAllCourses();
	}
	@GetMapping("courses/{courseid}")
	public Course getCourseById(@PathVariable(name = "courseid") Long courseid) {
		return courseBusiness.getCourseById(courseid);
	}
	@GetMapping("courses/searchby/{coursename}")
	public Course getCourseByName(@PathVariable(name = "coursename") String name) {
		return courseBusiness.getCourseByName(name);
	}
	
	@PostMapping("courses")
	public ResponseEntity<Course> createCourse (@RequestBody @Valid Course course) {
			Course courseCreated = courseBusiness.createCourse(course);
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{courseid}")
     .buildAndExpand(courseCreated.getId()).toUri();
		return ResponseEntity.created(location).body(courseCreated);
	}
	
	@PutMapping("courses/{courseid}")
	public ResponseEntity<Course> updateCourse(@RequestBody @Valid Course course, @PathVariable(name = "courseid") Long courseid) {
		Course courseUpdated= courseBusiness.updateCourse(course, courseid);
		return new ResponseEntity<Course>(courseUpdated, HttpStatus.OK);
	}
	@DeleteMapping("courses/{courseid}")
	public ResponseEntity<Void> deleteCourse(@PathVariable(name = "courseid")Long courseid) {
		courseBusiness.deleteCourse(courseid);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	
}
