package com.pwr.model;

import com.pwr.model.Request;

import java.util.Date;

public class Review {

	private int id;
	private String issueDescription;
	private int rate;
	private Date date;
	private Request request;


	public int getId() {
		return this.id;
	}

	/**
	 * 
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	public String getIssueDescription() {
		return this.issueDescription;
	}

	/**
	 * 
	 * @param issueDescription
	 */
	public void setIssueDescription(String issueDescription) {
		this.issueDescription = issueDescription;
	}

	public int getRate() {
		return this.rate;
	}

	/**
	 * 
	 * @param rate
	 */
	public void setRate(int rate) {
		this.rate = rate;
	}

	public Date getDate() {
		return this.date;
	}

	/**
	 * 
	 * @param date
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	public Request getRequest() {
		return this.request;
	}

	/**
	 * 
	 * @param request
	 */
	public void setRequest(Request request) {
		this.request = request;
	}

}