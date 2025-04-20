package com.example.taskManager.models;

public interface TaskDTO {
	 Long getId();
	    String getTitle();
	    String getDescription();
	    String getStatus();
	    String getAssignedTo();
}
