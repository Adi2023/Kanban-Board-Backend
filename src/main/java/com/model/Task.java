package com.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users")
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private String username;

	@NotNull
	private String password;

	@NotNull
	private String taskName; //firstname

	@NotNull
	private String taskStatus; //lastname

	@NotNull
	private Integer noOfTask;  //age

	@NotNull
	private String taskDetails; //country

	public Task() {
		super();
	}

	public Task( String username, String password, String taskName, String taskStatus, Integer noOfTask, String taskDetails) {
		this.username = username;
		this.password = password;
		this.taskName = taskName;
		this.taskStatus = taskStatus;
		this.noOfTask = noOfTask;
		this.taskDetails = taskDetails;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}

	public Integer getNoOfTask() {
		return noOfTask;
	}

	public void setNoOfTask(Integer noOfTask) {
		this.noOfTask = noOfTask;
	}

	public String getTaskDetails() {
		return taskDetails;
	}

	public void setTaskDetails(String taskDetails) {
		this.taskDetails = taskDetails;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Task user = (Task) o;

		if (id != null ? !id.equals(user.id) : user.id != null) return false;
		if (username != null ? !username.equals(user.username) : user.username != null) return false;
		if (password != null ? !password.equals(user.password) : user.password != null) return false;
		if (taskName != null ? !taskName.equals(user.taskName) : user.taskName != null) return false;
		if (taskStatus != null ? !taskStatus.equals(user.taskStatus) : user.taskStatus != null) return false;
		if (noOfTask != null ? !noOfTask.equals(user.noOfTask) : user.noOfTask != null) return false;
		return taskDetails != null ? taskDetails.equals(user.taskDetails) : user.taskDetails == null;
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (username != null ? username.hashCode() : 0);
		result = 31 * result + (password != null ? password.hashCode() : 0);
		result = 31 * result + (taskName != null ? taskName.hashCode() : 0);
		result = 31 * result + (taskStatus != null ? taskStatus.hashCode() : 0);
		result = 31 * result + (noOfTask != null ? noOfTask.hashCode() : 0);
		result = 31 * result + (taskDetails != null ? taskDetails.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				", taskName='" + taskName + '\'' +
				", taskStatus='" + taskStatus + '\'' +
				", noOfTask=" + noOfTask +
				", taskDetails='" + taskDetails + '\'' +
				'}';
	}
}
