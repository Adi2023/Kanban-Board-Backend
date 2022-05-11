package com.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.model.Task;
import com.repository.TaskRepository;
import com.service.TaskService;

@Service
@Transactional
public class TaskServiceImpl implements TaskService {

	@Autowired
	private TaskRepository taskRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Page<Task> findAll(Pageable pageable) {
		return taskRepository.findAll(pageable);
	}

	@Override
	public Optional<Task> findById(Long id) {
		return taskRepository.findById(id);
	}

	@Override
	public void add(Task task) {
		task.setPassword(passwordEncoder.encode(task.getPassword()));
		taskRepository.save(task);
	}

	@Override
	public Optional<Task> update(Task task) {
		Optional<Task> userOpt = taskRepository.findById(task.getId());

		if (userOpt.isPresent()) {
			Task existingUser = userOpt.get();

			if (task.getUsername() != null) {
				existingUser.setUsername(task.getUsername());
			}

			if (task.getPassword() != null) {
				existingUser.setPassword(passwordEncoder.encode(task.getPassword()));
			}

			if (task.getTaskName() != null) {
				existingUser.setTaskName(task.getTaskName());
			}

			if (task.getTaskStatus() != null) {
				existingUser.setTaskStatus(task.getTaskStatus());
			}

			if (task.getNoOfTask() != null) {
				existingUser.setNoOfTask(task.getNoOfTask());
			}

			if (task.getTaskDetails() != null) {
				existingUser.setTaskDetails(task.getTaskDetails());
			}

			taskRepository.save(existingUser);

			return Optional.of(existingUser);
		}

		return Optional.empty();
	}

	@Override
	public Optional<Task> delete(Long id) {
		Optional<Task> taskOpt = taskRepository.findById(id);

		if (taskOpt.isPresent()) {
			taskRepository.delete(taskOpt.get());
			return taskOpt;
		}

		return Optional.empty();
	}

	@Override
	public List<Task> findByCriteria(String criteria, String searchItem) {

		switch (criteria) {
		case "username":
			return this.taskRepository.findByUsername(searchItem);
		case "taskName":
			return this.taskRepository.findByTaskName(searchItem);
		case "taskStatus":
			return this.taskRepository.findByTaskStatus(searchItem);
		case "noOfTask":
			try {
				Integer noOfTask = Integer.valueOf(searchItem);
				return this.taskRepository.findByNoOfTask(noOfTask);
			} catch (NumberFormatException e) {
				System.out.println("Could not convert noOfTask to number...");
			}
			return new ArrayList<>();
		case "taskDetails":
			return this.taskRepository.findByTaskDetails(searchItem);
		}
		return new ArrayList<>();
	}

}
