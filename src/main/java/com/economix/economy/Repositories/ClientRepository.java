package com.economix.economy.Repositories;

import com.economix.economy.Models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ClientRepository extends JpaRepository<Client,String> {
    Client findClientByEmail(String email);

    boolean existsClientByEmail(String email);
}
