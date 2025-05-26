package com.cts.bookmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.bookmanagement.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

}
