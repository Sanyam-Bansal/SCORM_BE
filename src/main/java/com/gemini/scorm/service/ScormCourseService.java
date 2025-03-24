package com.gemini.scorm.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.gemini.scorm.model.ScormCourse;

public interface ScormCourseService {

	public ScormCourse saveCourse(ScormCourse course);
	
	public List<ScormCourse> getAllCourses();
	
	public Optional<ScormCourse> getCourseById(Long id);
	
	public boolean deleteCourse(Long id);
	
	public ScormCourse saveAndExtractScorm(MultipartFile file) throws IOException;
}
