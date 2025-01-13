package com.pwr.model;

import com.pwr.model.Request;

import java.util.Date;

public class Review {

	private int id;
	private String issueDescription;
	private int rate;
	private String opinion;
	private Date date;
	private Request request;

	public Review(){

	}

	public Review(int rate, String opinion, Request request){
		this.rate = rate;
		this.opinion = opinion;
		this.request = request;
	}

	public Review(int reviewId, String issueDescription, int rate, String opinion, Date date, Request request){
		this.id = reviewId;
		this.issueDescription = issueDescription;
		this.rate = rate;
		this.opinion = opinion;
		this.date = date;
		this.request = request;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIssueDescription() {
		return this.issueDescription;
	}

	public void setIssueDescription(String issueDescription) {
		this.issueDescription = issueDescription;
	}

	public int getRate() {
		return this.rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Request getRequest() {
		return this.request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

}