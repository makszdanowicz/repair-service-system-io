package com.pwr.model;

import com.pwr.model.Review;
import com.pwr.model.ReviewDAO;

import java.sql.Connection;
import java.util.List;

public class ReviewDAOImp implements ReviewDAO {
	private Connection connection;
	public ReviewDAOImp(Connection connection){
		this.connection = connection;
	}
	/**
	 * 
	 * @param review
	 */
	public void addReview(Review review) {
		// TODO - implement ReviewDAOImp.addReview
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param updatedReview
	 */
	public void updateReview(Review updatedReview) {
		// TODO - implement ReviewDAOImp.updateReview
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param reviewId
	 */
	public Review getReviewById(int reviewId) {
		// TODO - implement ReviewDAOImp.getReviewById
		throw new UnsupportedOperationException();
	}

	public List<Review> getAllReviews() {
		// TODO - implement ReviewDAOImp.getAllReviews
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param review
	 */
	public void deleteReview(Review review) {
		// TODO - implement ReviewDAOImp.deleteReview
		throw new UnsupportedOperationException();
	}

}