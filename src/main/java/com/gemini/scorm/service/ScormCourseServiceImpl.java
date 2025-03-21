package com.gemini.scorm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gemini.scorm.model.ScormCourse;
import com.gemini.scorm.repository.ScormCourseRepository;

@Service
public class ScormCourseServiceImpl implements ScormCourseService {
	
	@Autowired
	private ScormCourseRepository repository;

	public ScormCourse saveCourse(ScormCourse course) {
		return repository.save(course);
	}

	public List<ScormCourse> getAllCourses() {
		return repository.findAll();
	}

	public Optional<ScormCourse> getCourseById(Long id) {
		return repository.findById(id);
	}

	public boolean deleteCourse(Long id) {
		if (repository.existsById(id)) {
			repository.deleteById(id);
			return true;
		}
		return false;
	}
}
