package com.sapient.bugproject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.sapient.bugproject.entity.Bug;

/**
 * 
 * @author suvpaul
 *
 */
public interface IBugService {

	String save(Bug bug);

	List<Bug> getAll();

	Optional<Bug> find(String id);

	Page<Bug> getAllBugsByProjectId(String projectId, int page, int size);

	List<Bug> findPaginated(int pageNo, int pageSize);
	void update(Bug bug);

	List<Bug> filterBugByProjectIdAndName(String projectId, String name);

	List<Bug> filterBug(String name, int pageNo, int pageSize);
}
