package com.sapient.bugproject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapient.bugproject.entity.Project;
import com.sapient.bugproject.repository.ProjectRepository;

@Service
public class ProjectServiceImpl implements IProjectService {

	@Autowired
	private ProjectRepository projectRepository;

	@Override
	public String save(Project project) {
		Project saved = projectRepository.save(project);
		return saved.getId();
	}

	@Override
	public List<Project> findAll() {

		return projectRepository.findAll();
	}

	@Override
	public Optional<Project> find(String id) {
		return projectRepository.findById(id);
	}

	@Override
	public void update(Project project) {

		projectRepository.save(project);

	}

}
