package com.gemini.scorm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gemini.scorm.dto.UserProgressDTO;
import com.gemini.scorm.model.UserProgress;
import com.gemini.scorm.service.UserProgressService;

@RestController
@RequestMapping("/api/scorm")
public class UserProgressController {
	@Autowired
	private UserProgressService progressService;

	@PostMapping("/save")
	public ResponseEntity<UserProgress> saveProgress(@RequestBody UserProgressDTO progressDto) {
		UserProgress userProgress = progressService.saveProgress(progressDto);
		return ResponseEntity.status(201).body(userProgress);
	}
	
	@GetMapping("/getProgressByCourseId/{courseId}")
	public ResponseEntity<List<UserProgress>> getProgressByCourseId(@PathVariable Long courseId) {
		List<UserProgress> userProgress = progressService.getProgressByCourseId(courseId);
		return ResponseEntity.status(201).body(userProgress);
	}
	
}
