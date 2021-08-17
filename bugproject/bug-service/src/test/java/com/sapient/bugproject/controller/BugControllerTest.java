package com.sapient.bugproject.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.Mockito.doNothing;
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

import com.sapient.bugproject.entity.Bug;
import com.sapient.bugproject.service.IBugService;

/**
 * 
 * @author suvpaul
 *
 */
@ExtendWith(MockitoExtension.class)
class BugControllerTest {
	@InjectMocks
	BugController bugController;

	@Mock
	IBugService ibugService;

	@Test
	void testCreateBug() {
		Bug bug = new Bug();
		when(ibugService.save(bug)).thenReturn("bugsavedid");
		String response = bugController.create(bug);
		assertEquals("bugsavedid", response);
	}

	@Test
	void testGetAllBugs() {
		List<Bug> response = new ArrayList<Bug>();
		response.add(new Bug());
		response.add(new Bug());
		response.add(new Bug());
		when(ibugService.getAll()).thenReturn(response);
		List<Bug> actual = bugController.getAllBug();
		assertIterableEquals(response, actual);
	}

	@Test
	void testFindBugById() {
		Optional<Bug> bug = Optional.ofNullable(new Bug());
		String id = "123abcds";
		when(ibugService.find(id)).thenReturn(bug);
		assertEquals(bug, bugController.findBugById(id));
	}

	@Test
	void testUpdateBug() {
		Bug bug = new Bug();
		String id = "123abc";
		doNothing().when(ibugService).update(bug);
		bugController.updateBug(bug, id);
		verify(ibugService, times(1)).update(bug);
	}

	@Test
	void testFilterBug() {
		List<Bug> bug = new ArrayList<>();
		when(ibugService.filterBug("name", 0, 2)).thenReturn(bug);
		List<Bug> response = bugController.filterBug("name", 0, 2);
		assertEquals(bug, response);
	}

	@Test
	void testfilterBugByProjectIdAndName() {
		List<Bug> bug = new ArrayList<>();
		when(ibugService.filterBugByProjectIdAndName("abc123", "name")).thenReturn(bug);
		List<Bug> response = bugController.filterBugByProjectIdAndName("abc123", "name");
		assertEquals(bug, response);
	}

	@Test
	void testgetPaginatedBugs() {
		List<Bug> bug = new ArrayList<>();
		when(ibugService.findPaginated(0, 2)).thenReturn(bug);
		List<Bug> response = bugController.getPaginatedBugs(0, 2);
		assertEquals(bug, response);
	}

	@Test
	void testgetAllBugsByProjectID() {
		List<Bug> bug = new ArrayList<>();
		when(ibugService.getAllBugsByProjectId("abc123")).thenReturn(bug);
		List<Bug> response = bugController.getAllBugsByProjectId("abc123");
		assertEquals(bug, response);
	}
}
