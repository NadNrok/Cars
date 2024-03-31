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
import com.fm.cars.model.Category;
import com.fm.cars.service.CategoryService;

@ExtendWith(MockitoExtension.class)
public class CategoryControllerTest {

	@Mock
	private CategoryService categoryService;

	@InjectMocks
	private CategoryController categoryController;

	@Test
	public void testCreateCategory() {
		Category category = new Category();
		category.setCategory("SUV");
		when(categoryService.createCategory(any(Category.class))).thenReturn(category);

		Category createdCategory = categoryController.createCategory(category);

		assertNotNull(createdCategory);
		assertEquals(category.getCategory(), createdCategory.getCategory());
	}

	@Test
	public void testGetAllCategories() {
		List<Category> categories = new ArrayList<>();
		Category category1 = new Category();
		category1.setCategory("SUV");
		Category category2 = new Category();
		category2.setCategory("Sedan");
		categories.add(category1);
		categories.add(category2);
		when(categoryService.getAllCategories()).thenReturn(categories);

		List<Category> result = categoryController.getAllCategories();

		assertNotNull(result);
		assertEquals(categories.size(), result.size());
		assertEquals(categories, result);
	}

	@Test
	public void testGetCategoryById() {
		Long categoryId = 1L;
		Category category = new Category();
		category.setCategory("SUV");
		when(categoryService.getCategoryById(categoryId)).thenReturn(category);

		Category result = categoryController.getCategoryById(categoryId);

		assertNotNull(result);
		assertEquals(category, result);
	}

	@Test
	public void testUpdateCategory() {
		Long categoryId = 1L;
		Category category = new Category();
		category.setCategory("SUV");
		when(categoryService.updateCategory(categoryId, category)).thenReturn(category);

		Category updatedCategory = categoryController.updateCategory(categoryId, category);

		assertNotNull(updatedCategory);
		assertEquals(category, updatedCategory);
	}

	@Test
	public void testDeleteCategory() {
		Long categoryId = 1L;

		categoryController.deleteCategory(categoryId);

		verify(categoryService, times(1)).deleteCategory(categoryId);
	}
}
