package com.sapient.bugproject.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.sapient.bugproject.entity.Project;
import com.sapient.bugproject.service.IProjectService;

/**
 * 
 * @author suvpaul
 *
 */
@ExtendWith(MockitoExtension.class)
class ProjectControllerTest {

	@InjectMocks
	private ProjectController projectController;

	@Mock
	IProjectService projectServiceImpl;

	@Test
	void createProjectTest() {
		Project project = new Project();
		when(projectServiceImpl.save(project)).thenReturn("testidfrommongodb");
		String id = projectController.createProject(project);
		assertNotNull(id);
		assertEquals("testidfrommongodb", id);
	}

	@Test
	void getProjectTest() {
		Optional<Project> project = Optional.ofNullable(new Project());
		String id = "123abcds";
		when(projectServiceImpl.find(id)).thenReturn(project);
		assertEquals(project, projectController.getProject(id));
	}

	@Test
	void getAllProjectsTest() {
		List<Project> projectList = new ArrayList<Project>();
		projectList.add(new Project());
		projectList.add(new Project());
		when(projectServiceImpl.findAll()).thenReturn(projectList);
		assertIterableEquals(projectList, projectController.getAllProjects());
	}

}
