package com.gemini.scorm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gemini.scorm.model.UserProgress;

@Repository
public interface UserProgressRepository extends JpaRepository<UserProgress, Long> {

	UserProgress findByUserIdAndCourseId(Long userId, Long courseId);

	List<UserProgress> findByCourseId(Long courseId);

}
