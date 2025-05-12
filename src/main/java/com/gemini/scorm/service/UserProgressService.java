package com.gemini.scorm.service;

import java.util.List;

import com.gemini.scorm.dto.UserProgressDTO;
import com.gemini.scorm.model.UserProgress;

public interface UserProgressService {

	public UserProgress saveProgress(UserProgressDTO dto);
	
	public List<UserProgress> getProgressByCourseId(Long courseId);
	
	public UserProgress getProgressByUserIdAndCourseId(Long userId, Long courseId);
}
