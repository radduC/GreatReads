package com.goodreads.demo.entities;

import com.goodreads.demo.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String email;
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Enumerated(EnumType.STRING)
    private Role role;

    private boolean accountEnabled;
    private boolean accountNonLocked;
    private boolean accountNonExpired;
    private boolean credentialsNonExpired;

    public User(String email, String password, String firstName, String lastName, Role role) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }

    @OneToMany(mappedBy = "author", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<Book> publishedBooks = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "readers_books",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private List<Book> readBooks = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "readers_wishlist",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private List<Book> wishList = new ArrayList<>();

    @OneToMany(mappedBy = "reviewAuthor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();
}
