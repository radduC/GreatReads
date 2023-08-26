package com.goodreads.demo.repositories;

import com.goodreads.demo.entities.users.Reader;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReaderRepository extends JpaRepository<Reader, Long> {
}
