package com.example.demo.business;

import java.util.List;

import com.example.demo.models.Course;

public interface ICourseBusiness {

	public List<Course> getAllCourses();
	
	public Course getCourseById(Long courseid);
	
	public Course getCourseByName(String name);
	
	public Course createCourse (Course course);
	
	public Course updateCourse(Course course, Long courseid);
	
	public void deleteCourse(Long courseid);
	
}
