package com.qa.food_review_project.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qa.food_review_project.entity.Review;

public interface ReviewRepo extends JpaRepository<Review, Integer> {

	List<Review> findByRestaurantId(int id);

}
