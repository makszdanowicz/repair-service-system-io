package com.pwr.model;

import com.pwr.model.Review;
import com.pwr.model.ReviewDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class ReviewDAOImp implements ReviewDAO {
	private Connection connection;

	public ReviewDAOImp(Connection connection){
		this.connection = connection;
	}

	public void addReview(Review review) {
		// TODO - implement ReviewDAOImp.addReview
		String insertQuery = "INSERT INTO reviews (rate, request_id, opinion) VALUES (?,?,?)";
		try{
			PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
			preparedStatement.setInt(1,review.getRate());
			preparedStatement.setInt(2,review.getRequest().getId());
			preparedStatement.setString(3,review.getOpinion());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Can't add review to db " + e.getMessage());
		}
	}

	public void updateReview(Review updatedReview) {
		// TODO - implement ReviewDAOImp.updateReview
		throw new UnsupportedOperationException();
	}

	public Review getReviewById(int reviewId) {
		String getReviewQuery = "SELECT review_id, rate, request_id, date, opinion WHERE review_id = ?";
		try{
			PreparedStatement preparedStatement = connection.prepareStatement(getReviewQuery);
			preparedStatement.setInt(1,reviewId);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next() && reviewId == resultSet.getInt("review_id")){
				int rate = resultSet.getInt("rate");
				int requestId = resultSet.getInt("request_id");
				Date date = resultSet.getTimestamp("date");
				String opinion = resultSet.getString("opinion");
				//Request request = reviewDAOImp.getRequestById(requestId);
			}
		} catch (SQLException e) {
			System.out.println("Can't get Review from DataBase " + e.getMessage());
		}
		return review;
	}

	public List<Review> getAllReviews() {
		// TODO - implement ReviewDAOImp.getAllReviews
		throw new UnsupportedOperationException();
	}

	public void deleteReview(Review review) {
		// TODO - implement ReviewDAOImp.deleteReview
		throw new UnsupportedOperationException();
	}

}