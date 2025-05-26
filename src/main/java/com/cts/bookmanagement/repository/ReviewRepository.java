package com.cts.bookmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.bookmanagement.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long>{

}
