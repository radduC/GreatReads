package com.goodreads.demo.dto;

import com.goodreads.demo.entities.Book;

import java.time.LocalDate;

public record BookDTO(
        String title,
        String author,
        String genre,
        String description,
        LocalDate publishedDate
) {
    public BookDTO(Book book) {
        this(book.getTitle(), book.getAuthor().getFirstName() + " " + book.getAuthor().getLastName(),
                book.getGenre(), book.getDescription(), book.getPublishedDate());
    }
}
