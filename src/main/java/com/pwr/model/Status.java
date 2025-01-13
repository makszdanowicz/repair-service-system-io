package com.pwr.model;

public enum Status {
	NEW("New request"),
	PENDING("Pending request"),
	ASSIGNED("Assigned request"),
	IN_PROGRESS("In progress request"),
	COMPLETED("Completed request"),
	CLOSED("Closed request");

	private String description;

	Status(String description) {
		this.description = description;
	}

	public String getDescription() {
		return this.description;
	}

}