package com.economix.economy;

import com.economix.economy.Models.Admin;
import com.economix.economy.Models.Client;
import com.economix.economy.Models.TransactionType;
import com.economix.economy.Models.Transactions;
import com.economix.economy.Repositories.AdminRepository;
import com.economix.economy.Repositories.ClientRepository;
import com.economix.economy.Repositories.PersonRepository;
import com.economix.economy.Repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@SpringBootApplication
public class EconomyApplication {

	public static void main(String[] args) {
		SpringApplication.run(EconomyApplication.class, args);
	}

	@Autowired
	private PasswordEncoder passwordEncoder;


	@Bean
	public CommandLineRunner initData(PersonRepository personRepository, ClientRepository clientRepository, AdminRepository adminRepository, TransactionRepository transactionRepository) {
		return args -> {
			System.out.println("app launching");

			Client client1 = new Client("Melba", "Morel", "melba@mindhub.com", passwordEncoder.encode("123"), 0.00);

			Admin admin1 = new Admin("Lucky", "Sapio", "123@123.com", passwordEncoder.encode("123"), 0.00);




			Transactions transaction1 = new Transactions("Buying Ram", -13000.00, LocalDateTime.now().minusDays(15), true);
			Transactions transactions2 = new Transactions("Materitas Web", 7000.00, LocalDateTime.now(), true);

			client1.addTransaction(transaction1);
			client1.addTransaction(transactions2);

//			saves
			//client
			clientRepository.save(client1);
			adminRepository.save(admin1);
//			transactions


			transactionRepository.save(transaction1);
			transactionRepository.save(transactions2);
		};
	}
}