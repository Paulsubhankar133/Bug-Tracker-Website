package com.sapient.bugproject.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.sapient.bugproject.entity.Bug;

/**
 * 
 * @author suvpaul
 *
 */
public interface BugRepository extends MongoRepository<Bug, String> {
	Page<Bug> findByProjectIdContaining(String projectId, Pageable paging);

	Page<Bug> findByProjectIdAndNameIgnoreCaseContaining(String projectId, String name, Pageable paging);
}
