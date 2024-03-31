package com.fm.cars.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.fm.cars.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
