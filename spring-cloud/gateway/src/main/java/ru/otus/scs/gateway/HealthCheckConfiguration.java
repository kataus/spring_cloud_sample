package ru.otus.scs.gateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.CompositeReactiveHealthContributor;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.ReactiveHealthContributor;
import org.springframework.boot.actuate.health.ReactiveHealthIndicator;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.LinkedHashMap;
import java.util.Map;

import static java.util.logging.Level.FINE;

@Configuration
public class HealthCheckConfiguration {

    private static final Logger LOG = LoggerFactory.getLogger( HealthCheckConfiguration.class );

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    private WebClient webClient;

    @Autowired
    public HealthCheckConfiguration( WebClient.Builder webClientBuilder ) {
        this.webClient = webClientBuilder.build();
    }

    @Bean
    ReactiveHealthContributor healthcheckMicroservices() {

        final Map<String, ReactiveHealthIndicator> registry = new LinkedHashMap<>();

        registry.put( "data-logic", () -> getHealth( loadBalancerClient.choose( "data-logic" ).getHost() ) );
        registry.put( "tasks-service", () -> getHealth( loadBalancerClient.choose( "tasks-service" ).getHost() ) );
        registry.put( "users-service", () -> getHealth( loadBalancerClient.choose( "users-service" ).getHost() ) );


        return CompositeReactiveHealthContributor.fromMap( registry );
    }

    private Mono<Health> getHealth( String baseUrl ) {
        String url = baseUrl + "/actuator/health";
        LOG.debug( "Setting up a call to the Health API on URL: {}", url );
        return webClient.get().uri( url ).retrieve().bodyToMono( String.class )
                .map( s -> new Health.Builder().up().build() )
                .onErrorResume( ex -> Mono.just( new Health.Builder().down( ex ).build() ) )
                .log( LOG.getName(), FINE );
    }

}
