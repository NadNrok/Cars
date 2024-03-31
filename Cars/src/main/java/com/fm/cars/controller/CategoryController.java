package com.fm.cars.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.fm.cars.model.Category;
import com.fm.cars.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import org.springframework.security.access.prepost.PreAuthorize;
import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@SecurityRequirement(name = "bearerAuth")
@SecurityScheme(name = "bearerAuth", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "JWT")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@PostMapping
	@PreAuthorize("hasAuthority('SCOPE_write:messages')")
	@Operation(summary = "Create a new category")
	@SecurityRequirement(name = "bearerAuth")
	public Category createCategory(@RequestBody Category category) {
		return categoryService.createCategory(category);
	}

	@GetMapping
	@Operation(summary = "Get all categories")
	public List<Category> getAllCategories() {
		return categoryService.getAllCategories();
	}

	@GetMapping("/{categoryId}")
	@Operation(summary = "Get a category by ID")
	public Category getCategoryById(@PathVariable Long categoryId) {
		return categoryService.getCategoryById(categoryId);
	}

	@PutMapping("/{categoryId}")
	@Operation(summary = "Update a category")
	public Category updateCategory(@PathVariable Long categoryId, @RequestBody Category categoryDetails) {
		return categoryService.updateCategory(categoryId, categoryDetails);
	}

	@DeleteMapping("/{categoryId}")
	@Operation(summary = "Delete a category")
	public void deleteCategory(@PathVariable Long categoryId) {
		categoryService.deleteCategory(categoryId);
	}
}
