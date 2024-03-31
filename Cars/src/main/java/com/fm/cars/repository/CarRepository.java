package com.fm.cars.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.fm.cars.model.Car;

public interface CarRepository extends JpaRepository<Car, Long> {
	@Query("SELECT COUNT(c) FROM Car c")
	Long getCount();

	List<Car> findByMakeAndModelAndYear(String make, String model, int year);

	@Query("SELECT c FROM Car c JOIN c.categories ct WHERE (c.make IS NULL OR c.make = :make) "
			+ "AND (c.model IS NULL OR c.model = :model) "
			+ "AND (c.year IS NULL OR (c.year >= :minYear AND c.year <= :maxYear)) "
			+ "AND (ct.category IS NULL OR ct.category = :category)")
	List<Car> searchCarByParameters(@Param("make") String make, @Param("model") String model,
			@Param("minYear") Integer minYear, @Param("maxYear") Integer maxYear, @Param("category") String category);
}
