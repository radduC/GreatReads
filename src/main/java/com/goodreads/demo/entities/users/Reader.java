package com.goodreads.demo.entities.users;

import com.goodreads.demo.entities.Book;
import com.goodreads.demo.entities.Review;
import com.goodreads.demo.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "readers")
@Getter
@Setter
@NoArgsConstructor
public class Reader extends User {

    @Enumerated(EnumType.STRING)
    private final Role role = Role.READER;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
        name = "readers_books",
        joinColumns = @JoinColumn(name = "reader_id"),
        inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private List<Book> readBooks = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
        name = "readers_wishlist",
        joinColumns = @JoinColumn(name = "reader_id"),
        inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private List<Book> wishList = new ArrayList<>();

    @OneToMany(mappedBy = "reviewAuthor", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();

    public Reader(int id, String email, String password, String firstName, String lastName) {
        super(id, email, password, firstName, lastName);
    }
}
