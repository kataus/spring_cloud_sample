package ru.otus.scs.dl.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.otus.scs.dl.service.TaskService;
import ru.otus.scs.dto.Task;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaskServiceImpl implements TaskService {
    private final RestTemplate restTemplate;
    private final LoadBalancerClient loadBalancerClient;

    @Override
    public Task find( String id ) {
        var serviceUri = loadBalancerClient.choose( "tasks-service" ).getUri();

        var request = new RequestEntity( null, HttpMethod.GET
                , UriComponentsBuilder.fromUri( serviceUri )
                .path( "/tasks/{id}" )
                .buildAndExpand( id )
                .toUri()
        );

        var response
                = restTemplate.exchange( request, Task.class );

        if ( ! response.getStatusCode().is2xxSuccessful() ) {
            log.error( "Can't get users info, request {}, response status {}"
                    , request.getUrl().getQuery(), response.getStatusCode() );
            throw new RuntimeException( "Can't get users info" );
        }
        return response.getBody();
    }

    @Override
    public List<Task> findAll() {
        var serviceUri = loadBalancerClient.choose( "tasks-service" ).getUri();

        var request = new RequestEntity( null, HttpMethod.GET
                , UriComponentsBuilder.fromUri( serviceUri )
                .path( "/tasks" )
                .build()
                .toUri()
        );

        var response
                = restTemplate.exchange( request, new ParameterizedTypeReference<List<Task>>() {
        } );

        if ( ! response.getStatusCode().is2xxSuccessful() ) {
            log.error( "Can't get users info, request {}, response status {}"
                    , request.getUrl().getQuery(), response.getStatusCode() );
            throw new RuntimeException( "Can't get users info" );
        }
        return response.getBody();
    }

    @Override
    public Task save( Task task ) {
        var serviceUri = loadBalancerClient.choose( "tasks-service" ).getUri();

        var request = new RequestEntity( task, HttpMethod.POST
                , UriComponentsBuilder.fromUri( serviceUri )
                .path( "/tasks/add" )
                .build()
                .toUri()
        );

        var response
                = restTemplate.exchange( request, Task.class );

        if ( ! response.getStatusCode().is2xxSuccessful() ) {
            log.error( "Can't get users info, request {}, response status {}"
                    , request.getUrl().getQuery(), response.getStatusCode() );
            throw new RuntimeException( "Can't save task info" );
        }
        return response.getBody();
    }
}
