package com.qa.food_review_project.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.food_review_project.dto.NewRestaurantDTO;
import com.qa.food_review_project.dto.RestaurantDTO;
import com.qa.food_review_project.dto.ReviewDTO;
import com.qa.food_review_project.entity.Restaurant;
import com.qa.food_review_project.repo.RestaurantRepo;

@Service
public class RestaurantService {

	private RestaurantRepo restaurantRepo;
	private ReviewService reviewService;
	private ModelMapper modelMapper;

	@Autowired
	public RestaurantService(RestaurantRepo restaurantRepo, ReviewService reviewService, ModelMapper modelMapper) {
		super();
		this.restaurantRepo = restaurantRepo;
		this.reviewService = reviewService;
		this.modelMapper = modelMapper;
	}

	private RestaurantDTO toDTO(Restaurant restaurant) {
		return modelMapper.map(restaurant, RestaurantDTO.class);
	}

	public List<RestaurantDTO> getRestaurant() {
		List<Restaurant> restaurants = restaurantRepo.findAll();
		List<RestaurantDTO> DTOs = new ArrayList<>();

		for (Restaurant restaurant : restaurants) {
			DTOs.add(this.toDTO(restaurant));
		}
		return DTOs;
	}

	public RestaurantDTO getRestaurant(int id) {
		Optional<Restaurant> restaurant = restaurantRepo.findById(id);

		if (restaurant.isPresent()) {
			return this.toDTO(restaurant.get());
		}
		throw new EntityNotFoundException("Restaurant not found with id " + id);
	}

	public RestaurantDTO createRestaurant(NewRestaurantDTO restaurant) {
		Restaurant toSave = this.modelMapper.map(restaurant, Restaurant.class);
		Restaurant newRestaurant = restaurantRepo.save(toSave);
		return this.toDTO(newRestaurant);
	}

	public RestaurantDTO updateRestaurant(NewRestaurantDTO restaurant, int id) {
		if (restaurantRepo.existsById(id)) {
			Restaurant savedRestaurant = restaurantRepo.getById(id);
			savedRestaurant.setRestaurant(restaurant.getRestaurant());
			savedRestaurant.setFoodType(restaurant.getFoodType());
			savedRestaurant.setFood(restaurant.getFood());
			savedRestaurant.setPrice(restaurant.getPrice());

			restaurantRepo.save(savedRestaurant);

			return this.toDTO(savedRestaurant);
		}
		throw new EntityNotFoundException("Resturant not found with id " + id);
	}

	public boolean deleteRestaurant(int id) {
		if (restaurantRepo.existsById(id)) {
			restaurantRepo.deleteById(id);
			return !this.restaurantRepo.existsById(id);
		}
		throw new EntityNotFoundException("Restaurant not found with id " + id);
	}

	public List<ReviewDTO> getRestaurantReviews(int restaurantId) {
		RestaurantDTO restaurant = this.getRestaurant(restaurantId);
		List<ReviewDTO> reviews = reviewService.getReviewsByRestaurantId(restaurantId);
		reviews.forEach(review -> review.setRestaurantDTO(restaurant));
		return reviews;
	}
}