package com.goodreads.demo.repositories;

import com.goodreads.demo.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
