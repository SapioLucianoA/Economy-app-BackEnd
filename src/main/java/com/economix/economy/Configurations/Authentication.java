package com.economix.economy.Configurations;


import com.economix.economy.Models.Admin;
import com.economix.economy.Models.Client;
import com.economix.economy.Models.Person;
import com.economix.economy.Repositories.AdminRepository;
import com.economix.economy.Repositories.PersonRepository;
import com.economix.economy.Repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


@Configuration
public class Authentication extends GlobalAuthenticationConfigurerAdapter {
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private ClientRepository clientRepository;


    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(inputName-> {
            Person person = personRepository.findByEmail(inputName);
            if (person instanceof Admin){
                return new User(person.getEmail(), person.getPassword(), AuthorityUtils.createAuthorityList("ADMIN"));
            }
            if (person instanceof Client){
                return new User(person.getEmail(), person.getPassword(), AuthorityUtils.createAuthorityList("Client"));
            }
            throw new UsernameNotFoundException("User mail not found: " + inputName);
        });


    }


        @Bean
        public PasswordEncoder passwordEncoder() {

            return PasswordEncoderFactories.createDelegatingPasswordEncoder();

        }
    }