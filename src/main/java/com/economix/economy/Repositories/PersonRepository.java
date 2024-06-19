package com.economix.economy.Repositories;

import com.economix.economy.Models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface PersonRepository extends JpaRepository<Person, String> {
    Person findByEmail(String email);
}
