package com.smoothstack.avalanche.lmssecure.db;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.smoothstack.avalanche.lmssecure.entity.User;

import java.util.Arrays;
import java.util.List;

/*
 * Creates and populates table to store user login information
 */
@Service
public class DbInit implements CommandLineRunner {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public DbInit(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        this.userRepository.deleteAll();

        User admin = new User("admin",passwordEncoder.encode("adminpass"),"ADMIN","");
        User borrower = new User("borrower",passwordEncoder.encode("borpass"),"BORROWER","");
        User librarian = new User("librarian",passwordEncoder.encode("libpass"),"LIBRARIAN","");

        List<User> users = Arrays.asList(admin, librarian, borrower);

        this.userRepository.saveAll(users);
    }
}
