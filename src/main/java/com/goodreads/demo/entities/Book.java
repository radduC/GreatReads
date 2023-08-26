package com.goodreads.demo.entities;

import com.goodreads.demo.entities.users.Author;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "books")
@Getter
@Setter
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    private String genre;

    private String description;

    private LocalDate publishedDate;
}
