package com.economix.economy.Controllers;

import com.economix.economy.DTOS.TransactionDTO;
import com.economix.economy.Models.Client;
import com.economix.economy.Models.TransactionType;
import com.economix.economy.Models.Transactions;
import com.economix.economy.Records.TransactionRecord;
import com.economix.economy.Repositories.ClientRepository;
import com.economix.economy.Repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/eco")
public class TransactionController {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private ClientRepository clientRepository;



    @GetMapping
    public Set<TransactionDTO> getAllTransactions() {
        List<Transactions> transactionsList = transactionRepository.findAll();
        return transactionsList.stream().filter(transactions -> transactions.isActive()).map(transactions -> new TransactionDTO(transactions)).collect(Collectors.toSet());
    }

    @PostMapping
    public ResponseEntity<?> newTransaction(@RequestBody TransactionRecord transactionRecord, Authentication authentication) {

        if (transactionRecord.description().isEmpty()){
            return new ResponseEntity<>("Description missing", HttpStatus.FORBIDDEN);
        }
        if (transactionRecord.amount().isNaN()){
            return new ResponseEntity<>("Please complete the amount with a valid number", HttpStatus.FORBIDDEN);
        }
        if(transactionRecord.amount() == 0){
            return new ResponseEntity<>("the amount cant be 0", HttpStatus.FORBIDDEN);
        }
        Transactions transaction = new Transactions(transactionRecord.description(), transactionRecord.amount(), LocalDateTime.now(), true);

        Client client = clientRepository.findClientByEmail(authentication.getName());
        if (transactionRecord.amount()  >   0){
            client.setTotalAmount(client.getTotalAmount() + transaction.getAmount());
        }
        if (transactionRecord.amount() < 0 ){
            client.setTotalAmount(client.getTotalAmount() - transaction.getAmount());
        }
        client.addTransaction(transaction);
        clientRepository.save(client);
        transactionRepository.save(transaction);

        return new ResponseEntity<>("client created success!!!!!!", HttpStatus.CREATED);

    }
}


