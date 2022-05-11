package com.component;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.model.Task;
import com.repository.TaskRepository;

@Component
@Transactional
public class LoadTasksInDB implements CommandLineRunner {

	@Autowired
	private TaskRepository taskRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void run(String... args) throws Exception {

		if (taskRepository.count() > 0) {
			return;
		}
		
		Task task1 = new Task("adi23", UUID.randomUUID().toString(), "AgentXml", "Completed", 30, "Basic CRUD Operations");
		Task task2 = new Task("raj23", UUID.randomUUID().toString(), "Regalozone", "Completed", 24, "Basic Spring batch operations");
		Task task3 = new Task("tej23", UUID.randomUUID().toString(), "ERKA-90", "Failed", 34, "Margin-98 operations");
		Task task4 = new Task("san23", UUID.randomUUID().toString(), "Sap4hana", "Completed", 19, "Spring-Security Operations");
		Task task5 = new Task("sum23", UUID.randomUUID().toString(), "IFRS-17", "Completed", 42, "Spring-Web Operations");


		List<Task> tasksList = Arrays.asList(task1, task2, task3, task4, task5);

		tasksList = tasksList.stream().map(task -> {
			task.setPassword(passwordEncoder.encode(task.getPassword()));
			return task;
		}).collect(Collectors.toList());
		
		taskRepository.saveAll(tasksList);

	}

}
