package com.goodreads.demo.specification;

import com.goodreads.demo.entities.Book;
import org.springframework.data.jpa.domain.Specification;

public class BookSpecifications {

    public static Specification<Book> hasGenre(String genre) {

        return (root, query, criteriaBuilder) -> {
            if (genre == null || genre.isEmpty()) {
                return criteriaBuilder.conjunction();
            }

            return criteriaBuilder.equal(root.get("genre"), genre);
        };
    }
}
