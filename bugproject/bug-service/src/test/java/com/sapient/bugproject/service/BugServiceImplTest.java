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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.sapient.bugproject.entity.Bug;
import com.sapient.bugproject.repository.BugRepository;

/**
 * 
 * @author suvpaul
 *
 */
@ExtendWith(MockitoExtension.class)
class BugServiceImplTest {

	@InjectMocks
	private BugServiceImpl bugServiceImpl;

	@Mock
	BugRepository bugRepository;

	@Test
	void testSave() {
		Bug bug = new Bug();
		bug.setId("001");
		when(bugRepository.save(bug)).thenReturn(bug);
		String bugReturned = bugServiceImpl.save(bug);
		assertEquals(bugReturned, bug.getId());

	}

	@Test
	void testGetAll() {
		List<Bug> bugList = new ArrayList<>();
		when(bugRepository.findAll()).thenReturn(bugList);
		List<Bug> bugListReturned = bugServiceImpl.getAll();
		assertIterableEquals(bugList, bugListReturned);
	}

	@Test
	void testFind() {
		Optional<Bug> bug = Optional.ofNullable(new Bug());
		String id = "abcde";
		when(bugRepository.findById(id)).thenReturn(bug);
		Optional<Bug> bugReturned = bugServiceImpl.find(id);
		assertEquals(bug, bugReturned);
	}

	@Test
	void testUpdate() {
		Bug bug = new Bug();
		when(bugRepository.save(bug)).thenReturn(bug);
		bugServiceImpl.update(bug);
		verify(bugRepository, times(1)).save(bug);
	}

	@Test
	void testfilterBugByProjectIdAndName() {
		List<Bug> bug = new ArrayList<>();
		Pageable paging = PageRequest.of(0, 2);
		when(bugRepository.findByProjectIdAndNameIgnoreCaseContaining("abc123", "name", paging).toList())
				.thenReturn(bug);
		List<Bug> response = bugServiceImpl.filterBugByProjectIdAndName("abc123", "name");
		assertEquals(bug, response);
	}

	@Test
	void testgetAllBugsByProjectId() {
		Page<Bug> page = new PageImpl<Bug>(new ArrayList<Bug>());
		List<Bug> bug = new ArrayList<>();
		Pageable paging = PageRequest.of(0, 2);
		when(bugRepository.findByProjectIdContaining("abc123", paging)).thenReturn(bug);
		List<Bug> response = bugServiceImpl.getAllBugsByProjectId("abc123");
		assertEquals(bug, response);
	}

	@Test
	void testfindPaginated() {
		Page<Bug> page = new PageImpl<Bug>(new ArrayList<Bug>());
		Pageable paging = PageRequest.of(0, 2);
		when(bugRepository.findAll(paging)).thenReturn(page);
		List<Bug> response = bugServiceImpl.findPaginated(0, 2);
		assertEquals(response, page.toList());
	}
}
