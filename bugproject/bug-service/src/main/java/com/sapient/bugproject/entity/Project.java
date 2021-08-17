package com.sapient.bugproject.entity;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author suvpaul
 *
 */
@Getter
@Setter
@Document(collection = "project")
public class Project {
	private String id;
	@NotBlank(message = "Name is mandatory")
	private String name;
	@NotBlank(message = "Description is mandatory")
	@Size(min = 5, max = 255)
	private String description;
	private LocalDate createdDate;
	private LocalDate completedDate;
}
