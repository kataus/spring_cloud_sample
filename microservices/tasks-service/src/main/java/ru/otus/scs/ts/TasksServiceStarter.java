package ru.otus.scs.ts;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@Slf4j
public class TasksServiceStarter {

    public static void main( String[] args ) {
        ConfigurableApplicationContext context = SpringApplication.run( TasksServiceStarter.class );

        String mongodDbHost = context.getEnvironment().getProperty( "spring.data.mongodb.host" );
        String mongodDbPort = context.getEnvironment().getProperty( "spring.data.mongodb.port" );
        log.info( "Connected to MongoDb: " + mongodDbHost + ":" + mongodDbPort );
    }
}
