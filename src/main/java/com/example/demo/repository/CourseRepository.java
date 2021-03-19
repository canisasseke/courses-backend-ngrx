package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

	Optional<Course> findByName(String name);
}
