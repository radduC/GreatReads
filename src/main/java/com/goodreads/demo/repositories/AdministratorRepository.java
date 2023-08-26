package com.goodreads.demo.repositories;

import com.goodreads.demo.entities.users.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministratorRepository extends JpaRepository<Administrator, Long> {
}
