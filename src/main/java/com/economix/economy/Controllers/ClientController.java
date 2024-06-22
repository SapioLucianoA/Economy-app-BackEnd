package com.economix.economy.Controllers;

import com.economix.economy.DTOS.ClientDTO;
import com.economix.economy.Models.Client;
import com.economix.economy.Records.ClientRecord;
import com.economix.economy.Repositories.ClientRepository;
import com.economix.economy.Repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/eco")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private TransactionRepository transactionRepository;

    private final Lock requestLock = new ReentrantLock();
    @Autowired
    private PasswordEncoder passwordEncoder;

//    GETS
    @GetMapping("/all/clients")
    public Set<ClientDTO> getAllClients(){
        List<Client> clients = clientRepository.findAll();
         Set<ClientDTO> clientDTOS = clients.stream().map(client -> new ClientDTO(client)).collect(Collectors.toSet());

         return clientDTOS;

    }
    @GetMapping("/client")
    public ClientDTO getClient(Authentication authentication){
        Client client = clientRepository.findClientByEmail(authentication.getName());

        return new ClientDTO(client);
    }


//POST

    @PostMapping("client")
    public ResponseEntity<?> newClient (@RequestBody ClientRecord clientRecord){
        try {
            if (requestLock.tryLock()) {
                if (clientRepository.existsClientByEmail(clientRecord.email())) {
                    return new ResponseEntity<>("email already in use", HttpStatus.FORBIDDEN);
                }

                if (clientRecord.name().isBlank()) {
                    return new ResponseEntity<>("Missing Name or have spaces", HttpStatus.FORBIDDEN);
                }
                if (clientRecord.lastName().isBlank()) {
                    return new ResponseEntity<>("Missing last name or have spaces", HttpStatus.FORBIDDEN);
                }
                if (clientRecord.password().isBlank()) {
                    return new ResponseEntity<>("Missing password or have spaces", HttpStatus.FORBIDDEN);
                }

                if (clientRecord.email().isBlank()) {
                    return new ResponseEntity<>("Missing email or have spaces", HttpStatus.FORBIDDEN);
                }

                Client client = new Client(clientRecord.name(), clientRecord.lastName(), clientRecord.email(), passwordEncoder.encode(clientRecord.password()),0.00);

                clientRepository.save(client);

                return new ResponseEntity<>("client created success!!!!!!", HttpStatus.CREATED);
            }
            else {
                return new ResponseEntity<>("Request in progress. Please wait.", HttpStatus.TOO_MANY_REQUESTS);
            }
        } finally {
            requestLock.unlock();
        }
    }


}
