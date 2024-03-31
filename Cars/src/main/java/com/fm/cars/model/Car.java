package com.fm.cars.model;

import java.util.HashSet;
import java.util.Locale.Category;
import java.util.Set;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "car")
@Schema(description = "Represents a car entity")
public class Car {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "carId")
	@Schema(description = "The unique identifier of the car")
	private Long carId;

	@Column(name = "make")
	@Schema(description = "The make of the car")
	private String make;

	@Column(name = "year")
	@Schema(description = "The year of the car")
	private int year;

	@Column(name = "model")
	@Schema(description = "The model of the car")
	private String model;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "car_category", joinColumns = { @JoinColumn(name = "car_id") }, inverseJoinColumns = {
			@JoinColumn(name = "category_id") })
	@Schema(description = "The categories associated with the car")
	private Set<Category> categories = new HashSet<>();

	public Car(long l, String string, int i, String string2) {
	}

	public Car() {
	}

	public Long getCarId() {
		return carId;
	}

	public void setCarId(Long carId) {
		this.carId = carId;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

}