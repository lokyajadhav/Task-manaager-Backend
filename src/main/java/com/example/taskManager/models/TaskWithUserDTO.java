package com.example.taskManager.models;

public class TaskWithUserDTO {
	  private Long id;
	 private String title;
	    private String description;
	    private String status;
	    private String assignedTo;

	    public TaskWithUserDTO(String title, String description, String status, String assignedTo,Long id) {
	    	 this.id=id;
	        this.title = title;
	        
	        
	        
	        this.description = description;
	        this.status = status;
	        this.assignedTo = assignedTo;
	       
	    }

	    // Getters and Setters

	    public String getTitle() {
	        return title;
	    }

	    public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public void setTitle(String title) {
	        this.title = title;
	    }

	    public String getDescription() {
	        return description;
	    }

	    public void setDescription(String description) {
	        this.description = description;
	    }

	    public String getStatus() {
	        return status;
	    }

	    public void setStatus(String status) {
	        this.status = status;
	    }

	    public String getAssignedTo() {
	        return assignedTo;
	    }

	    public void setAssignedTo(String assignedTo) {
	        this.assignedTo = assignedTo;
	    }

}
