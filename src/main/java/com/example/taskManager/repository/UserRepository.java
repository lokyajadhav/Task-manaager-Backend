package com.example.taskManager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.taskManager.models.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

}
