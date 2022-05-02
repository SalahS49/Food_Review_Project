package com.qa.food_review_project.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table (name = "review")
public class Review {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull
	@Min(1)
	@Max(10)
	private int taste;
	
	@Size(min = 0, max = 250)
	private int appearance;
	
	@NotNull
	@Min(1)
	@Max(10)
	private int rating;
	
	@ManyToOne(optional = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "restaurant_id", referencedColumnName = "id")
	private Restaurant restaurant;
	
	public Review(int taste, int appearance, int rating, Restaurant restaurant) {
		super();
		this.taste = taste;
		this.appearance = appearance;
		this.rating = rating;
		this.restaurant = restaurant;
	}
	
	public Review(int id, int taste, int appearance, int rating, Restaurant restaurant) {
		super();
		this.id = id;
		this.taste = taste;
		this.appearance = appearance;
		this.rating = rating;
		this.restaurant = restaurant;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTaste() {
		return taste;
	}

	public void setTaste(int taste) {
		this.taste = taste;
	}

	public int getAppearance() {
		return appearance;
	}

	public void setAppearance(int appearance) {
		this.appearance = appearance;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	@Override
	public String toString() {
		return "Review [id=" + id + ", taste=" + taste + ", appearance=" + appearance + ", rating=" + rating + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(appearance, id, rating, restaurant, taste);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Review other = (Review) obj;
		return appearance == other.appearance && id == other.id && rating == other.rating
				&& Objects.equals(restaurant, other.restaurant) && taste == other.taste;
	}
	
	
	
}
