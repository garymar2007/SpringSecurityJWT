package com.gary.springsecurityjwt;

import com.gary.springsecurityjwt.entity.User;
import com.gary.springsecurityjwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class SpringSecurityJwtApplication {

    @Autowired
    private UserRepository repository;

    @PostConstruct
    public void initUsers() {
        List<User> users = Stream.of(
                new User(101, "gary", "password", "gary@example.com"),
                new User(102, "leo", "password", "leo@example.com"),
                new User(103, "carol", "password", "carol@example.com"),
                new User(104, "dave", "password", "dave@example.com")
        ).collect(Collectors.toList());
        repository.saveAll(users);

        List<User> retrievedUsers = repository.findAll();
        retrievedUsers.forEach(System.out::println);
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityJwtApplication.class, args);
    }

}
