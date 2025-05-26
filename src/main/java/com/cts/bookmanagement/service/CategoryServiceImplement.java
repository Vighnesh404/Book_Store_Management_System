package com.cts.bookmanagement.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.bookmanagement.dto.BookDto;
import com.cts.bookmanagement.dto.CategoryDto;
import com.cts.bookmanagement.entity.Book;
import com.cts.bookmanagement.entity.Category;
import com.cts.bookmanagement.repository.CategoryRepository;

@Service
public class CategoryServiceImplement implements ICategoryService{
	
	@Autowired
	private CategoryRepository categoryRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDto addCategory(CategoryDto categoryDto) {
		Category category = modelMapper.map(categoryDto, Category.class);
		
		category.setCatCreatedDate(LocalDateTime.now());
		category.setCatDeleted(false);
		
		Category savedCategory = categoryRepo.save(category);
		return modelMapper.map(savedCategory, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> viewAllCategories() {
		List<Category> categories = categoryRepo.findAll();
		List<CategoryDto> categoryDtos = new ArrayList<>();
		for (Category category : categories) {
			if(!category.isCatDeleted()) {
				categoryDtos.add(modelMapper.map(category, CategoryDto.class));
			}
		}
		return categoryDtos;
	}

	@Override
	public CategoryDto getCategoryById(Long catId) {
		Category category = categoryRepo.findById(catId).get();
		if(!category.isCatDeleted()) {
			return modelMapper.map(category, CategoryDto.class);
		}
		return null;
	}
	
	@Override
	public List<BookDto> getBooksByCatId(Long catId) {
		
		Category category = categoryRepo.findById(catId).orElse(null);
		if(category != null && !category.isCatDeleted()) {
			List<BookDto> bookDtos = new ArrayList<>();
			for(Book book : category.getBooks()) {
				if(!book.isBookDeleted()) {
					bookDtos.add(modelMapper.map(book, BookDto.class));
				}
			}
			return bookDtos;
		}
		
		return new ArrayList<>();
	}
	

	@Override
	public CategoryDto updateCategoryById(Long catId, CategoryDto categoryDto) {
		Category updatedCat = categoryRepo.findById(catId).get();
		if(!updatedCat.isCatDeleted()) {
			updatedCat.setCatName(categoryDto.getCatName());
			
			Category saveCat = categoryRepo.save(updatedCat);
			return modelMapper.map(saveCat, CategoryDto.class);
		}
		return null;
	}

	@Override
	public void deleteCategoryById(Long catId) {
		Category category = categoryRepo.findById(catId).get();
		category.setCatDeleted(true);
		categoryRepo.save(category);
	}

	

}
