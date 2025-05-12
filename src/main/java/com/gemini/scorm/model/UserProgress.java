package com.gemini.scorm.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "progress_table")
public class UserProgress {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long userId;
	private Long courseId;
	private String completionStatus;
	private Integer score;
	private String totalTime;
	private Double percentage;
	private String passFail;
	private LocalDateTime completionDate;
	private LocalDateTime lastAccessed;

}

//package com.gemini.scorm.model;
//
//import java.time.LocalDateTime;
//
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.Table;
//import lombok.Data;
//
//@Entity
//@Data
//@Table(name = "progress_table")
//public class UserProgress {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long id;
//
//	private Long userId;
//	private Long courseId;
//	private String completionStatus;
//	private int score;
//	private String totalTime;
//	private LocalDateTime lastAccessed;
//}
