package com.goodreads.demo.repositories;

import com.goodreads.demo.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
