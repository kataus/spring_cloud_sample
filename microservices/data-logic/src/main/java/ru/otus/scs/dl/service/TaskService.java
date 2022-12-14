package ru.otus.scs.dl.service;

import ru.otus.scs.dto.Task;

import java.util.List;

public interface TaskService {
    Task find( String id );

    List<Task> findAll( );

    Task save( Task task );
}
