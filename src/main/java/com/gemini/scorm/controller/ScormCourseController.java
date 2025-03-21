package com.gemini.scorm.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gemini.scorm.model.ScormCourse;
import com.gemini.scorm.service.ScormCourseService;

@RestController
@RequestMapping("/api/scorm")
@CrossOrigin(origins = "http://localhost:4200")
public class ScormCourseController {

	@Autowired
	private ScormCourseService service;

	@PostMapping("/upload")
	public ResponseEntity<ScormCourse> uploadScormCourse(@RequestBody ScormCourse course) {
		ScormCourse savedCourse = service.saveCourse(course);
		return ResponseEntity.status(201).body(savedCourse);
	}

	@GetMapping("/courses")
	public ResponseEntity<List<ScormCourse>> getAllCourses() {
		List<ScormCourse> courses = service.getAllCourses();
		return courses.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(courses);
	}

	@GetMapping("/course/{id}")
	public ResponseEntity<ScormCourse> getCourseById(@PathVariable Long id) {
		Optional<ScormCourse> course = service.getCourseById(id);
		return course.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@DeleteMapping("/course/{id}")
	public ResponseEntity<String> deleteCourse(@PathVariable Long id) {
		return service.deleteCourse(id) ? ResponseEntity.ok("Course deleted successfully.")
				: ResponseEntity.status(404).body("Course not found.");
	}
}
