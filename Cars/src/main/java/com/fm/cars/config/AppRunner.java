package com.fm.cars.config;

import jakarta.annotation.PostConstruct;

import javax.sql.DataSource;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fm.cars.repository.CarRepository;

@Component
public class AppRunner {
	private final DataSource dataSource;
	private final CarRepository carRepository;

	@Autowired
	public AppRunner(DataSource dataSource, CarRepository carRepository) {
		this.dataSource = dataSource;
		this.carRepository = carRepository;
	}

	@PostConstruct
	private void migrateAndGenerateData() {
		migrateDatabase();
		generateTestDataIfNeeded();
	}

	private void migrateDatabase() {
		Flyway flyway = Flyway.configure().dataSource(dataSource).load();
		flyway.migrate();
	}

	private void generateTestDataIfNeeded() {
		if (!databaseHasData()) {
			createCars();
		}
	}

	private boolean databaseHasData() {
		Long totalCars = carRepository.getCount();
		long totalCount = totalCars;
		return totalCount > 0;
	}

	private void createCars() {

	}
}
