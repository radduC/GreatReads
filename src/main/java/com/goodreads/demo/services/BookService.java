package com.goodreads.demo.services;

import com.goodreads.demo.dto.BookDTO;
import com.goodreads.demo.entities.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookService {

    Page<BookDTO> getAllBooks(String genre, Pageable pageable);

    void addBook(Book book);
}
