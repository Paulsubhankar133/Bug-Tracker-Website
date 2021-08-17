package com.sapient.bugproject.entity;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Project {
	private String id;
	@NotBlank(message = "Name is mandatory")
	private String name;
	@NotBlank(message = "Description is mandatory")
	@Size(min = 1, max = 255, message = "required")
	private String description;
	private LocalDate createdDate;
	private LocalDate completedDate;
}
