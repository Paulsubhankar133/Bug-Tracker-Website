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
@Document(collection = "bug")
public class Bug {
	private String id;
	@Size(min = 24, max = 24)
	private String projectId;
	@NotBlank(message = "Name is mandatory")
	private String name;
	@NotBlank(message = "Owner Name is mandatory")
	private String ownerName;
	private String ownerEmail;
	@Size(min = 5, max = 255)
	private String description;
	private LocalDate createdDate;
	private LocalDate completedDate;
	private BUG_STATUS status;
	private BUG_PRIORITY priority;
}
