package com.example.taskManager.services;

import java.util.List;

import com.example.taskManager.models.TaskDTO;
import com.example.taskManager.models.Tasks;
import com.example.taskManager.models.Users;

public interface taskService {

	void createTask(Tasks task);

	void deleteTask(Long taskId);

	void assignTask(Long taskId, Long userId);

	List<TaskDTO> getAllTasks();

	List<Tasks> getDeveloperTasks(Long developerId);

	List<Users> getDevelopersAvailable();

	void updateTaskAStatus(Long taskId, String status);

	void createUsers();

	Users login(String email, String password,String designation);

	List<Users> getAllUsers();

}
