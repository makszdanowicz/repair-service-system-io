package com.pwr.model;

import com.pwr.model.Review;

import java.util.List;

public interface ReviewDAO {

	/**
	 * 
	 * @param review
	 */
	void addReview(Review review);

	/**
	 * 
	 * @param updatedReview
	 */
	void updateReview(Review updatedReview);

	/**
	 * 
	 * @param reviewId
	 */
	Review getReviewById(int reviewId);

	List<Review> getAllReviews();
	boolean isReviewExist(int requestId);

	/**
	 * 
	 * @param review
	 */
	void deleteReview(Review review);

}