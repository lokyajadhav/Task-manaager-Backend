package com.example.taskManager.contollers;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.taskManager.models.Tasks;
import com.example.taskManager.models.Users;
import com.example.taskManager.services.taskService;

@RestController
public class TaskController {
	
	final taskService taskService;
	HashMap<String,String> response=new HashMap<>();
	
	
	@Autowired
	public TaskController(taskService taskService) {
		
		this.taskService=taskService;
	}
	
	
	@PostMapping("/create-task")
	ResponseEntity<HashMap<String,String>> createTask(@RequestBody Tasks task)
	{
		try
		{
			
			taskService.createTask(task);
			response.put("response", "Task has been create successfully");
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
		catch(Exception e){
			
			response.put("response", e.getLocalizedMessage());
			return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
			
		}
	}
	@GetMapping("/delete-task/{taskId}")
	ResponseEntity<HashMap<String,String>> deleteTask(@PathVariable Long taskId)
	{
		try
		{
			
			taskService.deleteTask(taskId);
			response.put("response", "Task has been deleted successfully");
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
		catch(Exception e){
			
			response.put("response", e.getLocalizedMessage());
			return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
			
		}
	}
	
	@GetMapping("/create-all-users") //will be create 5 developers and 1 manager.
	ResponseEntity<HashMap<String,String>> createUsers()
	{
		try
		{
			
			taskService.createUsers();
			response.put("response", "Users has been created successfully");
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
		catch(Exception e){
			
			response.put("response", e.getLocalizedMessage());
			return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
			
		}
	}
	
	
	@GetMapping("/assign-task/{taskId}/{userId}")
	ResponseEntity<HashMap<String,String>> assignTask(@PathVariable Long taskId,@PathVariable Long userId)
	{
		try
		{
			
			taskService.assignTask(taskId,userId);
			response.put("response", "Task has been assigned successfully");
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
		catch(Exception e){
			
			response.put("response", e.getLocalizedMessage());
			return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
			
		}
	}
	
	@GetMapping("/get-all-tasks")
	ResponseEntity<Object> getAllTasks()
	{
		try
		{
			
			List<Tasks> allTasks= taskService.getAllTasks();
			
			return ResponseEntity.status(HttpStatus.OK).body(allTasks);
		}
		catch(Exception e){
			
			response.put("response", e.getLocalizedMessage());
			return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
			
		}
	}
	@GetMapping("/get-develper-tasks/{developerId}")
	ResponseEntity<Object> getDeveloperAllTasks(@PathVariable Long developerId)
	{
		try
		{
			
			List<Tasks> allTasks= taskService.getDeveloperTasks(developerId);
			
			return ResponseEntity.status(HttpStatus.OK).body(allTasks);
		}
		catch(Exception e){
			
			response.put("response", e.getLocalizedMessage());
			return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
			
		}
	}
	
	@GetMapping("/update-task/{taskId}/{status}")
	ResponseEntity<HashMap<String,String>> updateTask(@PathVariable Long taskId,@PathVariable String status)
	{
		try
		{
			
			taskService.updateTaskAStatus(taskId,status);
			response.put("response", "Task status has been changed successfully");
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
		catch(Exception e){
			
			response.put("response", e.getLocalizedMessage());
			return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
			
		}
	}
	
	@GetMapping("/get-developers-with-bandwidth") // Considering a developer can be assigned with maximum of 3 task at a time
	ResponseEntity<Object> getDeveloperWithBandwidth()
	{
		try
		{
			
			List<Users> devlopers= taskService.getDevelopersAvailable();
			
			return ResponseEntity.status(HttpStatus.OK).body(devlopers);
		}
		catch(Exception e){
			
			response.put("response", e.getLocalizedMessage());
			return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
			
		}
	}
	
	
	
	
	
	
	
	

}
