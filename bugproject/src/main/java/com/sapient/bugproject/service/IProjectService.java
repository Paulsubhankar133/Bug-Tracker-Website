package com.sapient.bugproject.service;

import java.util.List;
import java.util.Optional;

import com.sapient.bugproject.entity.Project;

public interface IProjectService {
	String save(Project project);

	List<Project> findAll();

	Optional<Project> find(String id);

	void update(Project project);
}
