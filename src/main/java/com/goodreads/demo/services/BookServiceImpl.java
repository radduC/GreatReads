package com.goodreads.demo.services;

import com.goodreads.demo.dto.BookDTO;
import com.goodreads.demo.entities.Book;
import com.goodreads.demo.entities.User;
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
    public Page<BookDTO> getAllBooks(String genre, Pageable pageable) {
        Specification<Book> specification = BookSpecifications.hasGenre(genre);
        Page<Book> books = bookRepository.findAll(specification, pageable);
        return books.map(BookDTO::new);
    }

    @Override
    @Transactional
    public void addBook(Book book) {
        User author = book.getAuthor();
        author.getPublishedBooks().add(book);
//        bookRepository.save(book);
    }
}
