package ru.otus.sc.us.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.sc.us.repository.UserRepository;
import ru.otus.scs.dto.User;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserRepository userRepository;

    @GetMapping("/users/{id}")
    public User find( @PathVariable("id") Long id ) {
        var user = userRepository.findById( id );
        log.error( "user {}", user );
        return user
                .map( u -> new User( u.getId(), u.getName() ) )
                .orElseThrow( () -> new RuntimeException( "user not found" ) );
    }

    @GetMapping("/users")
    public List<User> findAll() {
        var users = userRepository.findAll();
        return users.stream()
                .map( u -> new User( u.getId(), u.getName() ) )
                .collect( Collectors.toList() );
    }
}
