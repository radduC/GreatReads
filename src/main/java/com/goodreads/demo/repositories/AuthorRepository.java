package com.goodreads.demo.repositories;

import com.goodreads.demo.entities.users.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
