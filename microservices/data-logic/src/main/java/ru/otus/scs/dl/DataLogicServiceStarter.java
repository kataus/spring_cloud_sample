package ru.otus.scs.dl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@Slf4j
public class DataLogicServiceStarter {

    public static void main( String[] args ) {
        ConfigurableApplicationContext context = SpringApplication.run( DataLogicServiceStarter.class );
    }
}
