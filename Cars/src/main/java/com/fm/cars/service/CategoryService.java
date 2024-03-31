package com.fm.cars.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fm.cars.model.Category;
import com.fm.cars.repository.CategoryRepository;

import java.util.List;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	public Category createCategory(Category category) {
		return categoryRepository.save(category);
	}

	public List<Category> getAllCategories() {
		return categoryRepository.findAll();
	}

	public Category getCategoryById(Long categoryId) {
		return categoryRepository.findById(categoryId)
				.orElseThrow(() -> new RuntimeException("Category not found with id: " + categoryId));
	}

	public Category updateCategory(Long categoryId, Category categoryDetails) {
		Category category = getCategoryById(categoryId);
		category.setCategory(categoryDetails.getCategory());
		return categoryRepository.save(category);
	}

	public void deleteCategory(Long categoryId) {
		Category category = getCategoryById(categoryId);
		categoryRepository.delete(category);
	}
}
