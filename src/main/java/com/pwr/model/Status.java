package com.pwr.model;

public enum Status {
	;

	public Status NEW;
	public Status PENDING;
	public Status ASSIGNED;
	public Status IN_PROGRESS;
	public Status COMPLETED;
	public Status CLOSED;
	private String description;

	/**
	 * 
	 * @param description
	 */
	Status(String description) {
		// TODO - implement Status.Status
		throw new UnsupportedOperationException();
	}

	public String getDescription() {
		return this.description;
	}

}