package com.example.taskManager.services;

import java.util.List;

import com.example.taskManager.models.Tasks;
import com.example.taskManager.models.Users;

public interface taskService {

	void createTask(Tasks task);

	void deleteTask(Long taskId);

	void assignTask(Long taskId, Long userId);

	List<Tasks> getAllTasks();

	List<Tasks> getDeveloperTasks(Long developerId);

	List<Users> getDevelopersAvailable();

	void updateTaskAStatus(Long taskId, String status);

	void createUsers();

}
