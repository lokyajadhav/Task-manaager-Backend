package com.example.taskManager.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.taskManager.models.TaskStatus;
import com.example.taskManager.models.Tasks;
import com.example.taskManager.models.Users;
import com.example.taskManager.repository.TasksRepository;
import com.example.taskManager.repository.UserRepository;

@Service
public class taskServiceImpl implements taskService {

	final TasksRepository tasksRepository;
	final UserRepository userRepository;
	
	@Autowired
	public taskServiceImpl(UserRepository userRepository, TasksRepository tasksRepository) {
		// TODO Auto-generated constructor stub
		this.userRepository=userRepository;
		this.tasksRepository=tasksRepository;
	}
	@Override
	public void createTask(Tasks task) {
		
		try
		{
			task.setStatus(TaskStatus.CREATED);
			
			tasksRepository.save(task);
			
		}
		catch(Exception e)
		{
			throw new RuntimeException("Failed to create tasks: " + e.getMessage(), e);
		}
	}

	@Override
	public void deleteTask(Long taskId) {
	    try {
	        Optional<Tasks> optionalTask = tasksRepository.findById(taskId);

	        if (optionalTask.isEmpty()) {
	            throw new RuntimeException("No task present with task ID: " + taskId);
	        }

	        Tasks task = optionalTask.get();

	        if (task.getStatus() == TaskStatus.IN_PROGRESS || task.getStatus() == TaskStatus.NOT_STARTED) {
	            throw new RuntimeException("Failed to delete task as the task is already assigned to a developer");
	        }

	        tasksRepository.deleteById(taskId);

	    } catch (Exception e) {
	        throw new RuntimeException("Failed to delete task: " + e.getMessage(), e);
	    }
	}


	@Override
	public void assignTask(Long taskId, Long userId) {
		try
		{
			  Optional<Tasks> optionalTask = tasksRepository.findById(taskId);
			  Optional<Users> optionalUser = userRepository.findById(userId);
		        if (optionalTask.isEmpty()) {
		            throw new RuntimeException("No task present with task ID: " + taskId);
		        }
		        if (optionalUser.isEmpty()) {
		            throw new RuntimeException("No user present with task ID: " + userId);
		        }

		       Tasks task = optionalTask.get();
		       Users user = optionalUser.get();
			List<Tasks> taskToAssign=new ArrayList<>();
			taskToAssign.add(task);
			task.setUser(user);
			user.setTasks(taskToAssign);
			tasksRepository.save(task);
			userRepository.save(user);
			this.updateTaskAStatus(taskId, "not_started");
			//System.err.println();
			
			
			
		}
		catch(Exception e)
		{
			throw new RuntimeException("Failed to assign task: " + e.getMessage(), e);
		}
		
	}

	@Override
	public List<Tasks> getAllTasks() {
		try
		{
			
			
			return tasksRepository.findAll();
			
		}
		catch(Exception e)
		{
			throw new RuntimeException("Failed to fetch tasks: " + e.getMessage(), e);
		}
	}

	@Override
	public List<Tasks> getDeveloperTasks(Long userId) {
		try
		{
			
			
			return tasksRepository.getTasksByUserId(userId);
			
		}
		catch(Exception e)
		{
			throw new RuntimeException("Failed to fetch tasks: " + e.getMessage(), e);
		}
	}

	@Override
	public List<Users> getDevelopersAvailable() {
		try
		{
			List<Users> availableDevelopers=new ArrayList<>();
			List<Users> users=  userRepository.findAll();
			for( Users user : users)
			{
				List<Tasks> taskAssigned=user.getTasks();
				List<Tasks> CurrentTasks=	taskAssigned.stream().filter(task->{
					return (task.getStatus().equals(TaskStatus.NOT_STARTED)|| task.getStatus().equals(TaskStatus.IN_PROGRESS));
					 
				}).toList();
				if(CurrentTasks.size()<3) availableDevelopers.add(user);
			}
			return availableDevelopers;
		}
		catch(Exception e)
		{
			throw new RuntimeException("Failed to fetch available developers: " + e.getMessage(), e);
		}
	}

	@Override
	public  void updateTaskAStatus(Long taskId, String status) {
		  try {
		        Optional<Tasks> optionalTask = tasksRepository.findById(taskId);

		        if (optionalTask.isEmpty()) {
		            throw new RuntimeException("No task present with task ID: " + taskId);
		        }

		        Tasks task = optionalTask.get();

		        
		        try {
		            TaskStatus taskStatus = TaskStatus.valueOf(status.toUpperCase());
		            task.setStatus(taskStatus);
		        } catch (Exception e) {
		            throw new RuntimeException("Invalid status: " + status + ". Allowed values: " +
		                Arrays.toString(TaskStatus.values()));
		        }
		        tasksRepository.save(task);

		    } catch (Exception e) {
		        throw new RuntimeException("Failed to update task status: " + e.getMessage(), e);
		    }
	}

	@Override
	public void createUsers() {
		
		
		List<Users> users=new ArrayList<>();
		try
		{
			users.add(new Users("Madhav","Manager","madhav@gmail.com","admin123"));
			users.add(new Users("Lokya","Developer","lokya@gmail.com","admin123"));
			users.add(new Users("Raju","Developer","raju@gmail.com","admin123"));
			users.add(new Users("Ravi","Developer","ravi@gmail.com","admin123"));
			users.add(new Users("Mani","Developer","mani@gmail.com","admin123"));
			userRepository.saveAll(users);
			
		}
		catch(Exception e)
		{
			throw new RuntimeException("Failed to create users: " + e.getMessage(), e);
		}
		
		
	}

}
