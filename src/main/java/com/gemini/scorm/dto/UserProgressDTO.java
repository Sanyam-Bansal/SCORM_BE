package com.gemini.scorm.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class UserProgressDTO {

	private Long userId;
	private Long courseId;
	private String completionStatus;
	private int score;
	private String totalTime;
	private LocalDateTime lastAccessed;
}
