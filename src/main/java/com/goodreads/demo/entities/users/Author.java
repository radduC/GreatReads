package com.goodreads.demo.entities.users;

import com.goodreads.demo.entities.Book;
import com.goodreads.demo.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "authors")
@Getter
@Setter
@NoArgsConstructor
public class Author extends User {

    @Enumerated(EnumType.STRING)
    private final Role role = Role.AUTHOR;

    @OneToMany(mappedBy = "author", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<Book> publishedBooks = new ArrayList<>();
}
