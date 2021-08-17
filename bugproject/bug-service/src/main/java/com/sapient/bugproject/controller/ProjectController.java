package com.sapient.bugproject.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.bugproject.entity.Project;
import com.sapient.bugproject.service.IProjectService;

/**
 * 
 * @author suvpaul
 *
 * 
 */
@org.springframework.web.bind.annotation.CrossOrigin
@RestController
public class ProjectController {
	@Autowired
	private IProjectService projectServiceImpl;

	/**
	 * Post Method to create Bug project
	 */
	@PostMapping("/api/project")
	public String createProject(@Valid @RequestBody Project project) {
		project.setCreatedDate(LocalDate.now());
		return projectServiceImpl.save(project);
	}

	/**
	 * find project with project id
	 */
	@GetMapping("/api/project/{id}")
	public Optional<Project> getProject(@PathVariable String id) {
		return projectServiceImpl.find(id);
	}

	@GetMapping("/api/project/id/{id}")
	public Optional<Project> findProjectById(@PathVariable String id) {
		return projectServiceImpl.findById(id);
	}

	/*
	 * PUT request for update
	 */
	// TODO Performance Testing
	@PutMapping("/api/project/{id}")
	public void updateProject(@RequestBody Project project, @PathVariable String id) {
		project.setId(id);
		projectServiceImpl.update(project);
	}

	@GetMapping("/api/project/filter/{name}")
	public List<Project> filterProjects(@PathVariable String name) {
		return projectServiceImpl.filterProject(name);
	}

	@GetMapping("/api/project/all")
	public List<Project> getAllProjects() {
		return projectServiceImpl.findAll();
	}

	@GetMapping("/api/project")
	public Page<Project> getAllProjects(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "2") int size) {

		Pageable paging = PageRequest.of(page, size);

		Page<Project> pageTuts;
		pageTuts = projectServiceImpl.findAll(paging);
		return pageTuts;


	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach(error -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return errors;
	}
}
