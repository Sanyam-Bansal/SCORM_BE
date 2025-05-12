package com.gemini.scorm.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gemini.scorm.dto.UserProgressDTO;
import com.gemini.scorm.model.UserProgress;
import com.gemini.scorm.repository.UserProgressRepository;

@Service
public class UserProgressServiceImpl implements UserProgressService {

	@Autowired
	private UserProgressRepository userProgressRepository;

	@Override
	public UserProgress saveProgress(UserProgressDTO dto) {
		UserProgress progress = userProgressRepository.findByUserIdAndCourseId(dto.getUserId(), dto.getCourseId());

		if (progress == null) {
			progress = new UserProgress();
		}

		progress.setUserId(dto.getUserId());
		progress.setCourseId(dto.getCourseId());
		progress.setCompletionStatus(dto.getCompletionStatus());
		progress.setScore(dto.getScore());
		progress.setTotalTime(dto.getTotalTime());
		progress.setPercentage(dto.getPercentage());
		progress.setPassFail(dto.getPassFail());
		progress.setCompletionDate(dto.getCompletionStatus().equals("completed") ? LocalDateTime.now() : null);
		progress.setLastAccessed(LocalDateTime.now());

		return userProgressRepository.save(progress);
	}

	@Override
	public List<UserProgress> getProgressByCourseId(Long courseId) {
		return userProgressRepository.findByCourseId(courseId);
	}

	@Override
	public UserProgress getProgressByUserIdAndCourseId(Long userId, Long courseId) {
		return userProgressRepository.findByUserIdAndCourseId(userId, courseId);
	}
}

//package com.gemini.scorm.service;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.gemini.scorm.dto.UserProgressDTO;
//import com.gemini.scorm.model.UserProgress;
//import com.gemini.scorm.repository.UserProgressRepository;
//
//@Service
//public class UserProgressServiceImpl implements UserProgressService {
//
//	@Autowired
//	private UserProgressRepository userProgressRepository;
//
//	@Override
//	public UserProgress saveProgress(UserProgressDTO dto) {
//		UserProgress progress = userProgressRepository.findByUserIdAndCourseId(dto.getUserId(), dto.getCourseId());
//
//		if (progress == null)
//			progress = new UserProgress();
//
//		progress.setUserId(dto.getUserId());
//		progress.setCourseId(dto.getCourseId());
//		progress.setCompletionStatus(dto.getCompletionStatus());
//		progress.setScore(dto.getScore());
//		progress.setTotalTime(dto.getTotalTime());
//		progress.setLastAccessed(LocalDateTime.now());
//
//		return userProgressRepository.save(progress);
//	}
//
//	@Override
//	public List<UserProgress> getProgressByCourseId(Long courseId) {
//		return userProgressRepository.findByCourseId(courseId);
//	}
//
//}
