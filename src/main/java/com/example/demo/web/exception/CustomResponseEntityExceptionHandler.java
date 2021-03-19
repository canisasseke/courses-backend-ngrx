package com.example.demo.web.exception;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.demo.exception.CourseNameAlreadyExistException;
import com.example.demo.exception.CourseNotFoundException;

@RestControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{

 @ExceptionHandler(CourseNotFoundException.class)
 @ResponseStatus(HttpStatus.NOT_FOUND)
 public final ResponseEntity<Object> handleUserCourseNotFoundException(CourseNotFoundException ex,
     WebRequest request) {

   Map<String, Object> body = new LinkedHashMap<String, Object>();
   body.put("timestamp", LocalDateTime.now());
   body.put("status", HttpStatus.NOT_FOUND.value());
   body.put("errors", Arrays.asList(ex.getMessage()));
   return new ResponseEntity<Object>(body, HttpStatus.NOT_FOUND);

 }

 @ExceptionHandler(CourseNameAlreadyExistException.class)
 @ResponseStatus(HttpStatus.BAD_REQUEST)
 public final ResponseEntity<Object> handleCourseNameAlreadyExistException(CourseNameAlreadyExistException ex,
     WebRequest request) {

   Map<String, Object> body = new LinkedHashMap<String, Object>();
   body.put("timestamp", LocalDateTime.now());
   body.put("status", HttpStatus.BAD_REQUEST.value());
   body.put("errors", Arrays.asList(ex.getMessage()));
   return new ResponseEntity<Object>(body, HttpStatus.NOT_FOUND);

 }
 
 @ExceptionHandler(Exception.class)
 @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
 public final ResponseEntity<Object> handle500Exception(Exception ex, WebRequest request) {

   Map<String, Object> body = new LinkedHashMap<String, Object>();
   body.put("timestamp", LocalDateTime.now());
   body.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
   body.put("errors", Arrays.asList(ex.getMessage()));

   return new ResponseEntity<Object>(body, HttpStatus.INTERNAL_SERVER_ERROR);

 }

 @Override
 protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
     HttpStatus status, WebRequest request) {

   Map<String, Object> body = new LinkedHashMap<String, Object>();
   body.put("timestamp", LocalDateTime.now());
   body.put("status", status.value());
   List<String> errors = ex.getBindingResult().getFieldErrors().stream()
       .map(x -> x.getDefaultMessage()).collect(Collectors.toList());
   body.put("errors", errors);
   return new ResponseEntity<Object>(body, headers, status);
 }
}
