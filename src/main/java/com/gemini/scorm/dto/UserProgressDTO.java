package com.gemini.scorm.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class UserProgressDTO {

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
