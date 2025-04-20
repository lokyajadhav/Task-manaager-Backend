package com.example.taskManager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.taskManager.models.TaskDTO;
import com.example.taskManager.models.TaskWithUserDTO;
import com.example.taskManager.models.Tasks;

@Repository
public interface TasksRepository extends JpaRepository<Tasks, Long> {
	
	@Query(value = "SELECT * FROM tasks WHERE user_id = :userId", nativeQuery = true)
	List<Tasks> getTasksByUserId(@Param("userId") Long userId);

	
	@Query(value=" Select t.id, t.title, t.description, t.status, u.user_name as assignedTo From tasks t LEFT JOIN users u ON t.user_id = u.id",nativeQuery = true)
	List<TaskDTO> fetchAllTasksWithUserInfo();


}
