package com.qa.food_review_project.configuration;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.qa.food_review_project.entity.Restaurant;
import com.qa.food_review_project.entity.Review;
import com.qa.food_review_project.repo.RestaurantRepo;
import com.qa.food_review_project.repo.ReviewRepo;

public class DevStartUpListener {
	@Profile("dev")
	@Configuration
	public class DevStartupListener implements ApplicationListener<ApplicationReadyEvent> {

		private RestaurantRepo restaurantRepo;
		private ReviewRepo reviewRepo;

		@Autowired
		public DevStartupListener(RestaurantRepo restaurantRepo, ReviewRepo reviewRepo) {
			this.restaurantRepo = restaurantRepo;
			this.reviewRepo = reviewRepo;
		}

		@Override
		public void onApplicationEvent(ApplicationReadyEvent event) {
			List<Restaurant> restaurants = restaurantRepo.saveAll(
					List.of(new Restaurant("Amorino", "IceCream", "Sorbet", 6.00), new Restaurant("Banadir", "Somali", "Federation", 10.00)));

			Restaurant restaurant = restaurants.stream().filter(m -> m.getRestaurant().equals("Smoothie")).findFirst().orElse(null);

			List<Review> reviews = reviewRepo.saveAll(List.of(new Review(7, 5, 7, restaurant),
					new Review(9, 9, 9, restaurant),
					new Review(2, 1, 2, restaurant)));

		}

	}
}
