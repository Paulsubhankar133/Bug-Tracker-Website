package com.sapient.bugproject.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.bugproject.entity.Project;
import com.sapient.bugproject.service.IProjectService;

@RestController
public class ProjectController {
	@Autowired
	private IProjectService projectServiceImpl;

	@PostMapping("/api/project")
	public String createProject(@Valid @RequestBody Project project) {
		return projectServiceImpl.save(project);
	}

	@GetMapping("/api/project/{id}")
	public Optional<Project> getProject(@PathVariable String id) {
		return projectServiceImpl.find(id);
	}

	@GetMapping("/api/project")
	public List<Project> getAllProjects() {
		return projectServiceImpl.findAll();
	}

	@PutMapping("/api/patch/{id}")
	public void updateProject(@RequestBody Project project, @PathVariable String id) {
		project.setId(id);
		projectServiceImpl.update(project);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return errors;
	}
}
