package com.goodreads.demo.dto;

import java.time.LocalDate;

public record BookDTO(
    String title,
    String author,
    String genre,
    String description,
    LocalDate publishedDate
) {
}
