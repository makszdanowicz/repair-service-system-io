package com.pwr.model;

import com.pwr.model.Review;
import com.pwr.model.ReviewDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReviewDAOImp implements ReviewDAO {
	private Connection connection;

	public ReviewDAOImp(Connection connection){
		this.connection = connection;
	}

	public void addReview(Review review) {
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
		Review review = null;
		String getReviewQuery = "SELECT review_id, rate, request_id, date, opinion FROM reviews WHERE review_id = ?";
		try{
			// Przygotowanie zapytania
			PreparedStatement preparedStatement = connection.prepareStatement(getReviewQuery);
			preparedStatement.setInt(1,reviewId);

			// Wykonanie zapytania i uzyskanie wynikow
			ResultSet resultSet = preparedStatement.executeQuery();

			// Sprawdzenie, czy istnieje wierz wynikowy
			if(resultSet.next() && reviewId == resultSet.getInt("review_id")){
				// Pobranie danych z bazy
				int rate = resultSet.getInt("rate");
				int requestId = resultSet.getInt("request_id");
				Date date = resultSet.getTimestamp("date");
				String opinion = resultSet.getString("opinion");

				// Pobranie obiektu Request z innego DAO
				RequestDAOImp requestDAOImp = new RequestDAOImp(connection);
				Request request = requestDAOImp.getRequestById(requestId);
				String issueDescription = request.getIssueDescription();
				review = new Review(reviewId,issueDescription,rate,opinion,date,request);
			}
		} catch (SQLException e) {
			System.out.println("Can't get Review from DataBase " + e.getMessage());
		}
		return review;
	}

	public List<Review> getAllReviews() {
		List<Review> reviews = new ArrayList<>();
		String getAllReviewQuery = "SELECT * FROM reviews";
		try {
			// Przygotowanie zapytania
			PreparedStatement preparedStatement = connection.prepareStatement(getAllReviewQuery);

			// Wykonanie zapytania
			ResultSet resultSet = preparedStatement.executeQuery();

			// Iteracja przez wyniki i tworzenie obiektow Reviews
			while(resultSet.next()){
				int reviewId = resultSet.getInt("review_id");
				int rate = resultSet.getInt("rate");
				int requestId = resultSet.getInt("request_id");

				// Pobranie obiektu Request z innego DAO
				RequestDAO requestDAO = new RequestDAOImp(connection);
				Request request = requestDAO.getRequestById(requestId);
				String issueDescription = request.getIssueDescription();

				Date date = resultSet.getTimestamp("date");
				String opinion = resultSet.getString("opinion");

				Review review = new Review(reviewId,issueDescription,rate,opinion,date,request);
				reviews.add(review);
			}
		} catch (SQLException e) {
			System.out.println("Can't get all Reviews from DataBase: " + e.getMessage());
		}
		return reviews;
	}

	public void deleteReview(Review review) {
		// TODO - implement ReviewDAOImp.deleteReview
		throw new UnsupportedOperationException();
	}

}