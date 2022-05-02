package com.qa.food_review_project.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;

import com.qa.food_review_project.dto.NewReviewDTO;
import com.qa.food_review_project.dto.RestaurantDTO;
import com.qa.food_review_project.dto.ReviewDTO;
import com.qa.food_review_project.dto.UpdateReviewDTO;
import com.qa.food_review_project.entity.Restaurant;
import com.qa.food_review_project.entity.Review;
import com.qa.food_review_project.repo.ReviewRepo;

public class ReviewServiceUnitTest {
	@Mock
	private ReviewRepo reviewRepo;

	@Mock
	private ModelMapper modelMapper;

	@InjectMocks
	private ReviewService reviewService;

	private List<Restaurant> restaurants;
	private List<RestaurantDTO> restaurantDTOs;
	private List<Review> reviews;
	private List<ReviewDTO> reviewDTOs;

	@BeforeEach
	public void init() {
		restaurants = List.of(new Restaurant(1, "GBK", "Dessert", "Milkshake", 5.00),
				new Restaurant(2, "Weatherspoons", "British", "Fish&Chips", 13.00));
		restaurantDTOs = List.of(new RestaurantDTO(1, "GBK", "Dessert", "Milkshake", 5.00),
				new RestaurantDTO(2, "Weatherspoons", "British", "Fish&Chips", 13.00));

		reviews = List.of(new Review(9, 5, 8, restaurants.get(0)),
				new Review(7, 7, 7, restaurants.get(0)));
		reviewDTOs = List.of(new ReviewDTO(1, 9, 5, 8, restaurantDTOs.get(0)),
				new ReviewDTO(2,7, 7, 7, restaurantDTOs.get(1)));
	}

	@Test
	public void getAllTest() {
		
		when(reviewRepo.findAll()).thenReturn(reviews);
		when(modelMapper.map(reviews.get(0), ReviewDTO.class)).thenReturn(reviewDTOs.get(0));
		when(modelMapper.map(reviews.get(1), ReviewDTO.class)).thenReturn(reviewDTOs.get(1));

		
		List<ReviewDTO> actual = reviewService.getReviews();

		
		assertEquals(reviewDTOs, actual);
		verify(reviewRepo).findAll();
		verify(modelMapper).map(reviews.get(0), ReviewDTO.class);
		verify(modelMapper).map(reviews.get(1), ReviewDTO.class);
	}

	@Test
	public void getByIdTest() {
		// Arrange
		Review review = reviews.get(0);
		ReviewDTO reviewDTO = reviewDTOs.get(0);
		int id = review.getId();

		when(reviewRepo.findById(id)).thenReturn(Optional.of(review));
		when(modelMapper.map(review, ReviewDTO.class)).thenReturn(reviewDTO);

		
		ReviewDTO actual = reviewService.getReview(id);

		
		assertEquals(reviewDTO, actual);
		verify(reviewRepo).findById(id);
		verify(modelMapper).map(review, ReviewDTO.class);
	}

	@Test
	public void getByInvalidIdTest() {
		
		int id = 5643;
		when(reviewRepo.findById(id)).thenReturn(Optional.empty());

		
		EntityNotFoundException exception = Assertions.assertThrows(EntityNotFoundException.class, () -> {
			reviewService.getReview(id);
		});

		
		String expectedMessage = "Review not found with id " + id;
		assertEquals(expectedMessage, exception.getMessage());
		verify(reviewRepo).findById(id);
	}

	@Test
	public void createTest() {
		
		RestaurantDTO restaurantDTO = restaurantDTOs.get(0);
		Review review = reviews.get(0);
		Review newReview = new Review(review.getTaste(), review.getAppearance(),review .getRating(), review.getRestaurant());
		ReviewDTO expected = reviewDTOs.get(0);

		NewReviewDTO reviewDTO = new NewReviewDTO(review.getTaste(), review.getAppearance(), review.getRating());
		ReviewDTO createdReviewDTO = new ReviewDTO(review.getId(), review.getTaste(), review.getAppearance(),
				review.getRating(), restaurantDTO);

		when(modelMapper.map(reviewDTO, Review.class)).thenReturn(newReview);
		when(reviewRepo.save(newReview)).thenReturn(review);
		when(modelMapper.map(review, ReviewDTO.class)).thenReturn(createdReviewDTO);

		
		ReviewDTO actual = reviewService.createReview(reviewDTO);

		
		assertEquals(expected, actual);
		verify(modelMapper).map(reviewDTO, Review.class);
		verify(reviewRepo).save(newReview);
		verify(modelMapper).map(review, ReviewDTO.class);
	}

	@Test
	public void updateTest() {

		Review review = reviews.get(0);
		int id = review.getId();
		UpdateReviewDTO updateReviewDTO = new UpdateReviewDTO(review.getTaste(), review.getAppearance(), review.getRating());
		RestaurantDTO restaurantDTO = restaurantDTOs.get(0);

		ReviewDTO expected = new ReviewDTO(review.getId(), review.getTaste(), review.getAppearance(), review.getRating(), restaurantDTO);

		when(reviewRepo.existsById(id)).thenReturn(true);
		when(reviewRepo.getById(id)).thenReturn(review);
		when(reviewRepo.save(review)).thenReturn(review);
		when(modelMapper.map(review, ReviewDTO.class)).thenReturn(expected);

		ReviewDTO updated = reviewService.updateReview(updateReviewDTO, id);

		assertEquals(expected, updated);
		verify(reviewRepo).existsById(id);
		verify(reviewRepo).getById(id);
		verify(reviewRepo).save(review);
		verify(modelMapper.map(review, Review.class));

	}
}
