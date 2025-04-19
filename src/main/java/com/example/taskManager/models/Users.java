package com.example.taskManager.models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Users {

	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;
	
	@Column(name = "user_name")
	private String name;
	private String email;
	
	private String designation;
	
	@Column(columnDefinition = "BOOLEAN DEFAULT TRUE")
	private boolean hasBandwidth = true;
    
    private String password;
    
	@OneToMany(mappedBy = "user")
	@JsonManagedReference
	private List<Tasks> tasks = new ArrayList<>();

	public Users(String name, String designation, String email,String password) {
		
		this.name=name;
		this.designation=designation;
		this.email=email;
		this.password=password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public boolean isHasBandwidth() {
		return hasBandwidth;
	}

	public void setHasBandwidth(boolean hasBandwidth) {
		this.hasBandwidth = hasBandwidth;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Tasks> getTasks() {
		return tasks;
	}

	public void setTasks(List<Tasks> tasks) {
		this.tasks = tasks;
	}

	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
