package com.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.model.Task;
import com.service.TaskService;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

	@Autowired
	private TaskService taskService;

	@GetMapping
	public ResponseEntity<?> findAll(Pageable pageable) {
		return new ResponseEntity<Page<Task>>(taskService.findAll(pageable), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		Optional<Task> taskOpt = taskService.findById(id);

		if (taskOpt.isPresent()) {
			return new ResponseEntity<Task>(taskOpt.get(), HttpStatus.OK);
		}

		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/search")
	public ResponseEntity<?> findByCriteria(@RequestParam(name = "criteria", required = true) String criteria,
			@RequestParam(name = "searchItem", required = true) String searchItem) {
		return new ResponseEntity<List<Task>>(taskService.findByCriteria(criteria, searchItem), HttpStatus.OK);
	}

	@PostMapping("/add")
	public ResponseEntity<?> add(@Valid @RequestBody Task task) {
		taskService.add(task);

		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Optional<Task> optTask = taskService.delete(id);

		if (optTask.isPresent()) {
			return new ResponseEntity<Task>(optTask.get(), HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}

	@PutMapping("/update")
	public ResponseEntity<?> update(@RequestBody Task task) {
		Optional<Task> optTask = taskService.update(task);

		if (optTask.isPresent()) {
			return new ResponseEntity<Task>(optTask.get(), HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}

}
