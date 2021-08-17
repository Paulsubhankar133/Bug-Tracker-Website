package com.sapient.bugproject.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

import com.sapient.bugproject.entity.Bug;
import com.sapient.bugproject.service.IBugService;

/**
 * 
 * @author suvpaul
 *
 */
@RestController
@org.springframework.web.bind.annotation.CrossOrigin
public class BugController {

	@Autowired
	IBugService ibugService;

	/**
	 * Post Method for Bug Creation
	 */
	@PostMapping("/api/bug")
	public String create(@RequestBody Bug bug) {
		bug.setCreatedDate(LocalDate.now());
		return ibugService.save(bug);
	}

	/**
	 * Get Method To get All Bugs
	 */
	@GetMapping("/api/bug")
	public List<Bug> getAllBug() {
		return ibugService.getAll();
	}

	@GetMapping("/api/bug/project/{projectid}")
	public Page<Bug> getAllBugsByProjectId(@PathVariable("projectid") String projectid,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "2") int size) {
		return ibugService.getAllBugsByProjectId(projectid, page, size);
	}

	/**
	 * Get Method to find a bug by id
	 */
	@GetMapping("/api/bug/{id}")
	public Optional<Bug> findBugById(@PathVariable String id) {
		return ibugService.find(id);
	}

	// TODO Performance Testing
	@PutMapping("/api/bug/{id}")
	public void updateBug(@RequestBody Bug bug, @PathVariable String id) {
		bug.setId(id);
		ibugService.update(bug);
	}

	@GetMapping("/api/bug/filter/{name}")
	public List<Bug> filterBug(@PathVariable String name, @RequestParam(defaultValue = "0") int pageNo,
			@RequestParam(defaultValue = "1") int pageSize) {
		return ibugService.filterBug(name, pageNo, pageSize);
	}

	@GetMapping("/api/bug/filter3/{projectId}/{name}")
	public List<Bug> filterBugByProjectIdAndName(@PathVariable(name = "projectId") String projectId,
			@PathVariable(name = "name") String name) {
		return ibugService.filterBugByProjectIdAndName(projectId, name);
	}

	@GetMapping("/api/bug/paging")
	public List<Bug> getPaginatedBugs(@RequestParam(defaultValue = "0") int pageNo,
			@RequestParam(defaultValue = "2") int pageSize) {

		return ibugService.findPaginated(pageNo, pageSize);
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
