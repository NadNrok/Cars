package com.fm.cars.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import com.fm.cars.model.Car;
import com.fm.cars.service.CarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.security.access.prepost.PreAuthorize;
import java.util.List;

@RestController
@RequestMapping("/api/v1/cars")
@SecurityRequirement(name = "bearerAuth")
@SecurityScheme(name = "bearerAuth", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "JWT")
public class CarController {

	@Autowired
	private CarService carService;

	@PostMapping("/{make}/models/{model}/{year}")
	@PreAuthorize("hasAuthority('SCOPE_write:messages')")
	@Operation(summary = "Create a new car")
	@SecurityRequirement(name = "bearerAuth")
	public Car createCar(@PathVariable String make, @PathVariable String model, @PathVariable int year) {
		Car car = new Car();
		car.setMake(make);
		car.setModel(model);
		car.setYear(year);
		return carService.createCar(car);
	}

	@GetMapping("/makes/{make}/models/{model}/years/{year}")
	@Operation(summary = "Get cars by make, model, and year")
	public List<Car> getCarsByMakeModelAndYear(@PathVariable String make, @PathVariable String model,
			@PathVariable int year) {
		return carService.getCarsByMakeModelAndYear(make, model, year);
	}

	@GetMapping("/search")
	@Operation(summary = "Search cars")
	public List<Car> searchCars(@RequestParam(required = false) String make,
			@RequestParam(required = false) String model, @RequestParam(required = false) Integer minYear,
			@RequestParam(required = false) Integer maxYear, @RequestParam(required = false) String category) {
		return carService.searchCars(make, model, minYear, maxYear, category);
	}

	@GetMapping
	@Operation(summary = "Get all cars")
	public List<Car> getAllCars() {
		return carService.getAllCars();
	}

	@GetMapping("/pagination")
	@Operation(summary = "Get all cars with pagination")
	public Page<Car> getAllCarsWithPagination(Pageable pageable) {
		return carService.getAllCarsWithPagination(pageable);
	}

	@GetMapping("/sorted")
	@Operation(summary = "Get all cars sorted")
	public List<Car> getAllCarsSorted(Sort sort) {
		return carService.getAllCarsSorted(sort);
	}

	@DeleteMapping("/{carId}")
	@Operation(summary = "Delete a car")
	public void deleteCar(@PathVariable Long carId) {
		carService.deleteCar(carId);
	}
}
