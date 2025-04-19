package com.example.taskManager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.taskManager.models.Tasks;

@Repository
public interface TasksRepository extends JpaRepository<Tasks, Long> {
	
	@Query(value = "SELECT * FROM tasks WHERE user_id = :userId", nativeQuery = true)
	List<Tasks> getTasksByUserId(@Param("userId") Long userId);


}
