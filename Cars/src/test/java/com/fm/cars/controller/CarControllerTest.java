package com.fm.cars.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import com.fm.cars.model.Car;
import com.fm.cars.service.CarService;

@ExtendWith(MockitoExtension.class)
public class CarControllerTest {

	@Mock
	private CarService carService;

	@InjectMocks
	private CarController carController;

	@Test
	public void testCreateCar() {
		String make = "Toyota";
		String model = "Camry";
		int year = 2022;
		Car car = new Car();
		car.setMake(make);
		car.setModel(model);
		car.setYear(year);
		when(carService.createCar(any(Car.class))).thenReturn(car);

		Car createdCar = carController.createCar(make, model, year);

		assertNotNull(createdCar);
		assertEquals(make, createdCar.getMake());
		assertEquals(model, createdCar.getModel());
		assertEquals(year, createdCar.getYear());
	}

	@Test
	public void testGetCarsByMakeModelAndYear() {
		String make = "Toyota";
		String model = "Camry";
		int year = 2022;
		List<Car> cars = new ArrayList<>();
		Car car1 = new Car();
		car1.setMake(make);
		car1.setModel(model);
		car1.setYear(year);
		Car car2 = new Car();
		car2.setMake(make);
		car2.setModel(model);
		car2.setYear(year);
		cars.add(car1);
		cars.add(car2);
		when(carService.getCarsByMakeModelAndYear(make, model, year)).thenReturn(cars);

		List<Car> result = carController.getCarsByMakeModelAndYear(make, model, year);

		assertNotNull(result);
		assertEquals(cars.size(), result.size());
		assertEquals(cars, result);
	}

	@Test
	public void testSearchCars() {
		String make = "Toyota";
		String model = "Camry";
		Integer minYear = 2020;
		Integer maxYear = 2022;
		String category = "Sedan";
		List<Car> cars = new ArrayList<>();
		Car car1 = new Car();
		car1.setMake(make);
		car1.setModel(model);
		car1.setYear(2020);
		Car car2 = new Car();
		car2.setMake(make);
		car2.setModel(model);
		car2.setYear(2021);
		Car car3 = new Car();
		car3.setMake(make);
		car3.setModel(model);
		car3.setYear(2022);
		cars.add(car1);
		cars.add(car2);
		cars.add(car3);
		when(carService.searchCars(make, model, minYear, maxYear, category)).thenReturn(cars);

		List<Car> result = carController.searchCars(make, model, minYear, maxYear, category);

		assertNotNull(result);
		assertEquals(cars.size(), result.size());
		assertEquals(cars, result);
	}

	@Test
	public void testGetAllCars() {
		List<Car> cars = new ArrayList<>();
		Car car1 = new Car();
		car1.setMake("Toyota");
		car1.setModel("Camry");
		car1.setYear(2022);
		Car car2 = new Car();
		car2.setMake("Honda");
		car2.setModel("Accord");
		car2.setYear(2023);
		cars.add(car1);
		cars.add(car2);
		when(carService.getAllCars()).thenReturn(cars);

		List<Car> result = carController.getAllCars();

		assertNotNull(result);
		assertEquals(cars.size(), result.size());
		assertEquals(cars, result);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testGetAllCarsWithPagination() {
		Pageable pageable = Pageable.unpaged();
		Page<Car> carPage = mock(Page.class);
		when(carService.getAllCarsWithPagination(pageable)).thenReturn(carPage);

		Page<Car> result = carController.getAllCarsWithPagination(pageable);

		assertNotNull(result);
		assertEquals(carPage, result);
	}

	@Test
	public void testGetAllCarsSorted() {
		Sort sort = Sort.unsorted();
		List<Car> cars = new ArrayList<>();
		Car car1 = new Car();
		car1.setMake("Toyota");
		car1.setModel("Camry");
		car1.setYear(2022);
		Car car2 = new Car();
		car2.setMake("Honda");
		car2.setModel("Accord");
		car2.setYear(2023);
		cars.add(car1);
		cars.add(car2);
		when(carService.getAllCarsSorted(sort)).thenReturn(cars);

		List<Car> result = carController.getAllCarsSorted(sort);

		assertNotNull(result);
		assertEquals(cars.size(), result.size());
		assertEquals(cars, result);
	}

	@Test
	public void testDeleteCar() {
		Long carId = 1L;

		carController.deleteCar(carId);

		verify(carService, times(1)).deleteCar(carId);
	}
}
