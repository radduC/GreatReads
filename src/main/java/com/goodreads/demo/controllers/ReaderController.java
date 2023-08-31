package com.goodreads.demo.controllers;

import com.goodreads.demo.dto.BookDTO;
import com.goodreads.demo.entities.Book;
import com.goodreads.demo.entities.User;
import com.goodreads.demo.security.SecurityUserDetails;
import com.goodreads.demo.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class ReaderController {

    private final BookService bookService;

    @GetMapping("/get")
    public ResponseEntity<Page<BookDTO>>  getBooksByGenre(
        @RequestParam(name = "genre") String genre,
        @RequestParam(name = "page", defaultValue = "0") int page,
        @RequestParam(name = "size", defaultValue = "10") int size) {

        PageRequest pageable = PageRequest.of(page, size);
        var books = bookService.getAllBooks(genre, pageable);
        return ResponseEntity.ok(books);
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('AUTHOR')")
    public ResponseEntity<String> addBook(@RequestBody BookDTO bookDTO,
                                     @AuthenticationPrincipal SecurityUserDetails userDetails) {

        User author = userDetails.getUserEntity();

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
