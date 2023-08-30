package com.goodreads.demo.services;

import com.goodreads.demo.entities.Book;
import com.goodreads.demo.repositories.BookRepository;
import com.goodreads.demo.specification.BookSpecifications;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public Page<Book> getAllBooks(String genre, Pageable pageable) {
        Specification<Book> specification = BookSpecifications.hasGenre(genre);
        return bookRepository.findAll(specification, pageable);
    }

    @Override
    @Transactional
    public void addBook(Book book) {
        bookRepository.save(book);
    }
}
