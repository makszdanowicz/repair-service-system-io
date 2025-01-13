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
		try(PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)){
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
		// Przygotowanie zapytania
		String getReviewQuery = "SELECT review_id, rate, request_id, date, opinion FROM reviews WHERE review_id = ?";
		try(PreparedStatement preparedStatement = connection.prepareStatement(getReviewQuery)){
			preparedStatement.setInt(1,reviewId);
			// Wykonanie zapytania i uzyskanie wynikow
			try(ResultSet resultSet = preparedStatement.executeQuery()){
				// Sprawdzenie, czy istnieje wierz wynikowy
				if(resultSet.next()){
					return mapToReview(resultSet);
				}
			}
		} catch (SQLException e) {
			System.out.println("Can't get Review from DataBase " + e.getMessage());
		}
		return null;
	}

	public List<Review> getAllReviews(){
		List<Review> reviews = new ArrayList<>();
		// Przygotowanie zapytania;
		String getAllReviewQuery = "SELECT * FROM reviews";
		try(PreparedStatement preparedStatement = connection.prepareStatement(getAllReviewQuery)) {
			// Wykonanie zapytania
			try(ResultSet resultSet = preparedStatement.executeQuery()) {
				// Iteracja przez wyniki i tworzenie obiektow Reviews
				while (resultSet.next()){
					Review review = mapToReview(resultSet);
					reviews.add(review);
				}
			}
		} catch (SQLException e) {
			System.out.println("Can't get all Reviews from DataBase: " + e.getMessage());
		}
		return reviews;
	}

	private Review mapToReview(ResultSet resultSet){
		try{
			// Pobranie danych z bazy
			int reviewId = resultSet.getInt("review_id");
			int rate = resultSet.getInt("rate");
			int requestId = resultSet.getInt("request_id");
			Date date = resultSet.getTimestamp("date");
			String opinion = resultSet.getString("opinion");

			// Pobranie obiektu Request z innego DAO
			RequestDAOImp requestDAOImp = new RequestDAOImp(connection);
			Request request = requestDAOImp.getRequestById(requestId);
			String issueDescription = request.getIssueDescription();
			return new Review(reviewId,issueDescription,rate,opinion,date,request);
		} catch (SQLException e) {
			System.out.println("Can't map result Set to Review: " + e.getMessage());
		}
		return null;
	}

	public boolean isReviewExist(int requestId){
		String isReviewExistQuery = "SELECT review_id FROM reviews WHERE request_id = ?";
		try(PreparedStatement preparedStatement = connection.prepareStatement(isReviewExistQuery)){
			preparedStatement.setInt(1,requestId);
			ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
			throw new RuntimeException(e);
			//System.out.println("Can't check if review exist in DataBase: " + e.getMessage());
		}
	}


	public void deleteReview(Review review) {
		// TODO - implement ReviewDAOImp.deleteReview
		throw new UnsupportedOperationException();
	}

}