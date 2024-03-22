package org.rail.controller;

import lombok.RequiredArgsConstructor;
import org.rail.model.User;
import org.rail.repository.UserRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/users")
@Transactional
public class UserController implements UserOpenApi{

    RestClient restClient = RestClient.builder()
            .baseUrl("https://jsonplaceholder.typicode.com/users")
            .build();

    private final UserRepository userRepository;

    @GetMapping
    @Transactional(readOnly = true)
    @Cacheable("users")
    public List<User> getAllUsers() {
        return restClient.get()
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }

    @GetMapping("/db")
    public Iterable<User> getUsersFromDb() {
        return userRepository.findAll();
    }

    @PostMapping
    public String saveUser(@RequestBody User user) {

        userRepository.save(user);
        return "user saved";
    }

}

