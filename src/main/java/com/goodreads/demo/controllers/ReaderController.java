package com.goodreads.demo.controllers;

import com.goodreads.demo.dto.BookDTO;
import com.goodreads.demo.entities.Book;
import com.goodreads.demo.entities.User;
import com.goodreads.demo.repositories.UserRepository;
import com.goodreads.demo.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class ReaderController {

    private final BookService bookService;
    private final UserRepository userRepository;

    @GetMapping("/get")
    @PreAuthorize("hasRole('READER')")
    public Page<Book> getBooksByGenre(
        @RequestParam(name = "genre") String genre,
        @RequestParam(name = "page", defaultValue = "0") int page,
        @RequestParam(name = "size", defaultValue = "10") int size) {

        PageRequest pageable = PageRequest.of(page, size);
        return bookService.getAllBooks(genre, pageable);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addBook(@RequestBody BookDTO bookDTO) {
        User author = userRepository.findByEmail(bookDTO.author()).orElseThrow(
            () -> new UsernameNotFoundException("Author not found"));

        Book book = Book.builder()
            .author(author)
            .title(bookDTO.title())
            .genre(bookDTO.genre())
            .description(bookDTO.description())
            .publishedDate(bookDTO.publishedDate())
            .build();

        bookService.addBook(book);
        return ResponseEntity.ok("Book added");
    }
}
