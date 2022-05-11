package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

	public List<Task> findByUsername(String username);
	
	public List<Task> findByTaskName(String taskName);
	
	public List<Task> findByTaskStatus(String taskStatus);
	
	public List<Task> findByNoOfTask(Integer noOfTask);
	
	public List<Task> findByTaskDetails(String taskDetails);
}
