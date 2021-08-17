package com.sapient.bugproject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;

import com.sapient.bugproject.entity.Bug;
import com.sapient.bugproject.repository.BugRepository;

/**
 * 
 * @author suvpaul
 *
 */
@Service
public class BugServiceImpl implements IBugService {

	@Autowired
	BugRepository bugRepository;

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public String save(Bug bug) {
		Bug saved = bugRepository.save(bug);
		return saved.getId();
	}

	@Override
	public List<Bug> getAll() {
		return bugRepository.findAll();
	}

	@Override
	public Optional<Bug> find(String id) {
		return bugRepository.findById(id);
	}

	@Override
	public void update(Bug bug) {
		bugRepository.save(bug);
	}

	@Override
	public List<Bug> filterBugByProjectIdAndName(String projectId, String name) {
		Pageable paging = PageRequest.of(0, 2);
		return bugRepository.findByProjectIdAndNameIgnoreCaseContaining(projectId, name, paging).toList();
	}

	@Override
	public Page<Bug> getAllBugsByProjectId(String projectId, int page, int size) {
		Pageable paging = PageRequest.of(page, size);
		return bugRepository.findByProjectIdContaining(projectId, paging);
	}

	@Override
	public List<Bug> filterBug(String name, int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		Query query = new Query().with(pageable);
		query.addCriteria(Criteria.where("name").regex(name, "i"));
		List<Bug> list = mongoTemplate.find(query, Bug.class);
		Page<Bug> pageBug = PageableExecutionUtils.getPage(list, pageable,
				() -> mongoTemplate.count(Query.of(query).limit(-1).skip(-1), Bug.class));
		List<Bug> bugsList = pageBug.getContent();
		return bugsList;
	}

	@Override
	public List<Bug> findPaginated(int pageNo, int pageSize) {
		Pageable paging = PageRequest.of(pageNo, pageSize);
		Page<Bug> pagedResult = bugRepository.findAll(paging);

		return pagedResult.toList();
	}
}
