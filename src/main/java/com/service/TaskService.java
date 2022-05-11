package com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.model.Task;

public interface TaskService {

	public Page<Task> findAll(Pageable pageable);

	public Optional<Task> findById(Long id);
	
	public List<Task> findByCriteria(String criteria, String searchItem);
	
	public void add(Task user);
	
	public Optional<Task> update(Task task);
	
	public Optional<Task> delete(Long id);
}
