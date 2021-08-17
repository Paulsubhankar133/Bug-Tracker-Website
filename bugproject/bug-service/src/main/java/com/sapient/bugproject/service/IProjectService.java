package com.sapient.bugproject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sapient.bugproject.entity.Project;

/**
 * 
 * @author suvpaul
 *
 */
public interface IProjectService {
	String save(Project project);

	Page<Project> findAll(Pageable pageable);

	Optional<Project> findById(String id);

	Optional<Project> find(String id);

	void update(Project project);

	List<Project> filterProject(String name);

	List<Project> findAll();
}
