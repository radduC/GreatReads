package com.goodreads.demo.entities.users;

import com.goodreads.demo.enums.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "administrators")
@Getter
@Setter
@NoArgsConstructor
public class Administrator extends User {

    @Enumerated(EnumType.STRING)
    private final Role role = Role.ADMINISTRATOR;
}
