package ru.otus.sc.us;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@Slf4j
public class UsersServiceStarter {

    public static void main( String[] args ) {
        ConfigurableApplicationContext context = SpringApplication.run( UsersServiceStarter.class );

    }
}
