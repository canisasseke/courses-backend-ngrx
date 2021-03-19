package com.example.demo.business;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.CourseNameAlreadyExistException;
import com.example.demo.exception.CourseNotFoundException;
import com.example.demo.models.Course;
import com.example.demo.repository.CourseRepository;

@Service
public class CourseBusiness implements ICourseBusiness {

	@Autowired
	CourseRepository courseRepository;
	
	@Override
	public List<Course> getAllCourses() {
		return courseRepository.findAll();
	}

	@Override
	public Course getCourseById(Long courseid) {
		Optional<Course> optCourse= courseRepository.findById(courseid);
		if(!optCourse.isPresent()) throw new CourseNotFoundException("CourseNotFoundException");
		return optCourse.get();
	}

	@Override
	public Course getCourseByName(String name) {
		Optional<Course> optCourse= courseRepository.findByName(name);
		if(!optCourse.isPresent()) throw new CourseNotFoundException("CourseNotFoundException");
		return optCourse.get();
	}

	@Override
	public Course createCourse(@Valid Course course) {
		checkUnicityCourseName(course);
		return courseRepository.save(course);
	}

	@Override
	public Course updateCourse(@Valid Course course, Long courseid) {
		Optional<Course> optCourse= courseRepository.findById(courseid);
		if(!optCourse.isPresent()) throw new CourseNotFoundException("CourseNotFoundException");
		if(!course.getName().equals(optCourse.get().getName())) checkUnicityCourseName(course);
		course.setId(courseid);
		return courseRepository.save(course);
	}

	@Override
	public void deleteCourse(Long courseid) {
		Optional<Course> optCourse= courseRepository.findById(courseid);
		if(!optCourse.isPresent()) throw new CourseNotFoundException("CourseNotFoundException");
		courseRepository.deleteById(courseid);
	}

	private void checkUnicityCourseName(Course course) {
		Optional<Course> optCourse= courseRepository.findByName(course.getName());
		if(optCourse.isPresent()) throw new CourseNameAlreadyExistException("CourseNameAlreadyExistException");
	}
}
