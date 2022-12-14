package ru.otus.scs.gateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(AdditionalRoutesImportSelector.class)
@Slf4j
public class GatewayStarter {


    @Bean
    public RouteLocator customRouteLocator( RouteLocatorBuilder builder ) {
        return builder.routes()
                .route( r -> {
                    log.error( "psth {}", r );
                    return r.path( "/crm" )
                            .uri( "ls://data-logic" );
                        }
                )
                .build();
    }

    public static void main( String[] args ) {
        SpringApplication.run( GatewayStarter.class, args );
    }

}
