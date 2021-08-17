package com.sapient.bugproject.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.sapient.bugproject.entity.Project;

@Repository
public interface ProjectRepository extends MongoRepository<Project, String> {

}
