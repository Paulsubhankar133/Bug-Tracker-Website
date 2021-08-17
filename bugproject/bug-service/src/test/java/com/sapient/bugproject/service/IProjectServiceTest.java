package com.sapient.bugproject.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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
import com.sapient.bugproject.repository.ProjectRepository;

/**
 * 
 * @author suvpaul
 *
 */
@ExtendWith(MockitoExtension.class)
class IProjectServiceTest {
	@InjectMocks
	private ProjectServiceImpl projectServiceImpl;

	@Mock
	ProjectRepository projectRepository;

	@Test
	void testSave() {
		Project project = new Project();
		project.setId("001");
		when(projectRepository.save(project)).thenReturn(project);
		String projectReturned = projectServiceImpl.save(project);
		assertEquals(projectReturned, project.getId());
	}

	@Test
	void testFindAll() {
		List<Project> projectList = new ArrayList<>();
		when(projectRepository.findAll()).thenReturn(projectList);
		List<Project> projectListReturned = projectServiceImpl.findAll();
		assertIterableEquals(projectList, projectListReturned);
	}

	@Test
	void testFind() {
		Optional<Project> project = Optional.ofNullable(new Project());
		String id = "abcde";
		when(projectRepository.findById(id)).thenReturn(project);
		Optional<Project> projectReturned = projectServiceImpl.find(id);
		assertEquals(project, projectReturned);
	}

	@Test
	void updateProjectTest() {
		Project project = new Project();
		when(projectRepository.save(project)).thenReturn(project);
		projectServiceImpl.update(project);
		verify(projectRepository, times(1)).save(project);
	}
}
