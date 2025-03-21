package com.gemini.scorm.service;

import java.util.List;
import java.util.Optional;

import com.gemini.scorm.model.ScormCourse;

public interface ScormCourseService {

	public ScormCourse saveCourse(ScormCourse course);
	
	public List<ScormCourse> getAllCourses();
	
	public Optional<ScormCourse> getCourseById(Long id);
	
	public boolean deleteCourse(Long id);

}
