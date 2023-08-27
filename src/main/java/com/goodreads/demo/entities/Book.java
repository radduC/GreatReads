package com.goodreads.demo.entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "books")
@Data
@Builder
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User author;

    private String genre;

    private String description;

    private LocalDate publishedDate;
}
