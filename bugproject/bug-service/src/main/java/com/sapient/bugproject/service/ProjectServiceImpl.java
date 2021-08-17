package com.sapient.bugproject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.sapient.bugproject.entity.Project;
import com.sapient.bugproject.repository.ProjectRepository;

/**
 * 
 * @author suvpaul
 *
 */
@Service
public class ProjectServiceImpl implements IProjectService {

	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private MongoOperations mongoOperations;

	@Override
	public String save(Project project) {
		Project saved = projectRepository.save(project);
		return saved.getId();
	}

	@Override
	public Page<Project> findAll(Pageable pageable) {

		return projectRepository.findAll(pageable);
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

	@Override
	public Optional<Project> findById(String id) {
		return projectRepository.findById(id);
	}

	@Override
	public List<Project> filterProject(String name) {
//		Pageable pageable = PageRequest.of(0, 1);
//		Query query = new Query().with(pageable);
//		query.addCriteria(Criteria.where("name").regex(name, "i"));
//		List<Project> projects = mongoTemplate.find(query, Project.class);
//		Page<Project> pageBug = PageableExecutionUtils.getPage(projects, pageable,
//				() -> mongoTemplate.count(Query.of(query).limit(-1).skip(-1), Project.class));
//		return pageBug.getContent();
//		 return projectList;
//		return pageBug;
		Query query = new Query();
		query.addCriteria(Criteria.where("name").regex(name, "i"));
		List<Project> projects = mongoTemplate.find(query, Project.class);
		return projects;
	}
}
