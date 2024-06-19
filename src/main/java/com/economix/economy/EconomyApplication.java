package com.economix.economy;

import com.economix.economy.Models.Admin;
import com.economix.economy.Models.Client;
import com.economix.economy.Repositories.AdminRepository;
import com.economix.economy.Repositories.ClientRepository;
import com.economix.economy.Repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class EconomyApplication {

	public static void main(String[] args) {
		SpringApplication.run(EconomyApplication.class, args);
	}

	@Autowired
	private PasswordEncoder passwordEncoder;


	@Bean
	public CommandLineRunner initData(PersonRepository personRepository, ClientRepository clientRepository, AdminRepository adminRepository) {
		return args -> {
			System.out.println("app launching");

			Client client1 = new Client("Melba", "Morel", "melba@mindhub.com", passwordEncoder.encode("123"));

			Admin admin1 = new Admin("Lucky", "Sapio", "123@123.com", passwordEncoder.encode("123"));

			clientRepository.save(client1);
			adminRepository.save(admin1);


		};
	}
}