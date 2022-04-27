package com.qa.food_review_project.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.qa.food_review_project.dto.NewReviewDTO;
import com.qa.food_review_project.dto.ReviewDTO;
import com.qa.food_review_project.dto.UpdateReviewDTO;
import com.qa.food_review_project.entity.Review;
import com.qa.food_review_project.repo.ReviewRepo;



@Service
public class ReviewService {

	private ReviewRepo reviewRepo;
	private ModelMapper modelMapper;

	public ReviewService(ReviewRepo reviewRepo, ModelMapper modelMapper) {
		super();
		this.reviewRepo = reviewRepo;
		this.modelMapper = modelMapper;
	}

	private ReviewDTO toDTO(Review review) {
		return modelMapper.map(review, ReviewDTO.class);
	}

	public List<ReviewDTO> getReviews() {
		List<Review> reviews = reviewRepo.findAll();
		List<ReviewDTO> DTOs = new ArrayList<>();

		for (Review review : reviews) {
			DTOs.add(this.toDTO(review));
		}
		return DTOs;
	}

	public List<ReviewDTO> getReviewsByRestaurantId(int id) {
		List<Review> reviews = reviewRepo.findByRestaurantId(id);
		List<ReviewDTO> DTOs = new ArrayList<>();

		for (Review review : reviews) {
			DTOs.add(this.toDTO(review));
		}
		return DTOs;
	}

	public ReviewDTO getReview(int id) {
		Optional<Review> review = reviewRepo.findById(id);

		if (review.isPresent()) {
			return this.toDTO(review.get());
		}
		throw new EntityNotFoundException("Review not found with id " + id);
	}

	public ReviewDTO createReview(NewReviewDTO review) {
		Review toSave = this.modelMapper.map(review, Review.class);
		Review newReview = reviewRepo.save(toSave);
		return this.toDTO(newReview);
	}

	public ReviewDTO updateReview(UpdateReviewDTO reviewDTO, int id) {
		if (reviewRepo.existsById(id)) {
			Review savedReview = reviewRepo.getById(id);
			savedReview.setTaste(reviewDTO.getTaste());
			savedReview.setAppearance(reviewDTO.getAppearance());
			savedReview.setRating(reviewDTO.getRating());
			return this.toDTO(reviewRepo.save(savedReview));
		}
		throw new EntityNotFoundException("Review not found with id " + id);
	}

	public void deleteReview(int id) {
		if (reviewRepo.existsById(id)) {
			reviewRepo.deleteById(id);
			return;
		}
		throw new EntityNotFoundException("Review not found with id " + id);
	}

}
