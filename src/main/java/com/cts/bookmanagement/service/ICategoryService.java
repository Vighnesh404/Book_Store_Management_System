package com.cts.bookmanagement.service;

import java.util.List;

import com.cts.bookmanagement.dto.BookDto;
import com.cts.bookmanagement.dto.CategoryDto;

public interface ICategoryService {
	CategoryDto addCategory(CategoryDto categoryDto);
	List<CategoryDto> viewAllCategories();
	CategoryDto getCategoryById(Long catId);
	CategoryDto updateCategoryById(Long catId, CategoryDto categoryDto);
	void deleteCategoryById(Long catId);
	List<BookDto> getBooksByCatId(Long catId);
}
