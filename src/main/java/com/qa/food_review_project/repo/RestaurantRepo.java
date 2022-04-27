package com.qa.food_review_project.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.food_review_project.entity.Restaurant;
	
	@Repository
	public interface RestaurantRepo extends JpaRepository<Restaurant, Integer> {

	
}