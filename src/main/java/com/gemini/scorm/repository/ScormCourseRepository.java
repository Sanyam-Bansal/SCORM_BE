package com.gemini.scorm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gemini.scorm.model.ScormCourse;

@Repository
public interface ScormCourseRepository extends JpaRepository<ScormCourse, Long> {

}
