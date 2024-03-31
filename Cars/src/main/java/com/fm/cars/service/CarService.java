package com.fm.cars.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import com.fm.cars.model.Car;
import com.fm.cars.repository.CarRepository;

@Service
public class CarService {

	private final CarRepository carRepository;

	@Autowired
	public CarService(CarRepository carRepository) {
		this.carRepository = carRepository;
	}

	public Car createCar(Car car) {
		return carRepository.save(car);
	}

	public List<Car> getAllCars() {
		return carRepository.findAll();
	}

	public Car getCarById(Long carId) {
		return carRepository.findById(carId).orElseThrow(() -> new RuntimeException("Car not found with id: " + carId));
	}

	public Car updateCar(Long carId, Car carDetails) {
		Car car = getCarById(carId);
		car.setMake(carDetails.getMake());
		car.setYear(carDetails.getYear());
		car.setModel(carDetails.getModel());
		return carRepository.save(car);
	}

	public void deleteCar(Long carId) {
		Car car = getCarById(carId);
		carRepository.delete(car);
	}

	public List<Car> getCarsByMakeModelAndYear(String make, String model, int year) {
		return carRepository.findByMakeAndModelAndYear(make, model, year);
	}

	public List<Car> searchCars(String make, String model, Integer minYear, Integer maxYear, String category) {
		return carRepository.searchCarByParameters(make, model, minYear, maxYear, category);
	}

	public Page<Car> getAllCarsWithPagination(Pageable pageable) {
		return carRepository.findAll(pageable);
	}

	public List<Car> getAllCarsSorted(Sort sort) {
		return carRepository.findAll(sort);
	}
}
