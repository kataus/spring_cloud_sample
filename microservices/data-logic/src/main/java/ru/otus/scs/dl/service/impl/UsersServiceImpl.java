package ru.otus.scs.dl.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.otus.scs.dl.service.UsersService;
import ru.otus.scs.dto.User;

@Service
@RequiredArgsConstructor
@Slf4j
public class UsersServiceImpl implements UsersService {
    private final RestTemplate restTemplate;
    private final LoadBalancerClient loadBalancerClient;

    @Override
    public User get( Long id ) {
        var serviceUri = loadBalancerClient.choose( "users-service" ).getUri();
        log.error( "userId = {}", id );

        var request = new RequestEntity( null, HttpMethod.GET
                , UriComponentsBuilder.fromUri( serviceUri )
                .path( "/users/" + id )
                .build()
                .toUri()
        );

        log.error( "user request = {}", request.getUrl().getPath() );

        var response
                = restTemplate.exchange( request, User.class );

        if ( ! response.getStatusCode().is2xxSuccessful() ) {
            log.error( "Can't get users info, request {}, response status {}"
                    , request.getUrl().getQuery(), response.getStatusCode() );
            throw new RuntimeException( "Can't get users info" );
        }
        return response.getBody();
    }
}
