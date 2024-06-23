package com.economix.economy.Controllers;

import com.economix.economy.DTOS.PaymentDTO;
import com.economix.economy.Models.Client;
import com.economix.economy.Models.Payments;
import com.economix.economy.Records.PaymentRecord;
import com.economix.economy.Repositories.ClientRepository;
import com.economix.economy.Repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/eco")
public class PaymentController {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private PaymentRepository paymentRepository;


    @GetMapping("/payments")
    public Set<PaymentDTO> getAllPayments() {
        List<Payments> paymentsList = paymentRepository.findAll();
        return paymentsList.stream().filter(payments -> payments.isActive()).map(payments -> new PaymentDTO(payments)).collect(Collectors.toSet());
    }

    @PostMapping("/payment")
    public ResponseEntity<?> newPayment(@RequestBody PaymentRecord paymentRecord, Authentication authentication) {

        if (paymentRecord.amount().isNaN()) {
            return new ResponseEntity<>("Please complete the amount with a valid number", HttpStatus.FORBIDDEN);
        }
        if (paymentRecord.amount() <= 0) {
            return new ResponseEntity<>("the amount cant be 0 or more", HttpStatus.FORBIDDEN);
        }
        if (paymentRecord.totalPayments() >= 0) {
            return new ResponseEntity<>("The total payments cant be 0 or less", HttpStatus.FORBIDDEN);
        }

        Client client = clientRepository.findClientByEmail(authentication.getName());

        Payments payment = new Payments(paymentRecord.description(), paymentRecord.amount(), paymentRecord.totalPayments(), 0L, true);
        client.addPayment(payment);

        paymentRepository.save(payment);
        clientRepository.save(client);
        return new ResponseEntity<>("Payment Created", HttpStatus.OK);

    }

    @PostMapping("/sold")
    public ResponseEntity<?> payPayment(@RequestParam String paymentId, Authentication authentication){

        Payments payments = paymentRepository.findPaymentsById(paymentId);
        Client client = clientRepository.findClientByEmail(authentication.getName());
        if(payments.getTotalPayments() == payments.getSoldPayments()){

            payments.setActive(false);

            return new ResponseEntity<>("payment complete", HttpStatus.FORBIDDEN);
        }

        payments.setSoldPayments(payments.getSoldPayments() + 1);
        client.setTotalAmount(client.getTotalAmount() - payments.getAmount());

        if(payments.getTotalPayments().equals(payments.getSoldPayments())){

            payments.setActive(false);

            clientRepository.save(client);
            paymentRepository.save(payments);

            return new ResponseEntity<>("payment sold complete", HttpStatus.OK);

        }

        clientRepository.save(client);
        paymentRepository.save(payments);
        return new ResponseEntity<>("Payment Update", HttpStatus.OK);
    }
}
