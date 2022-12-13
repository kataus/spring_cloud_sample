package ru.otus.scs.ts.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.scs.dto.Task;
import ru.otus.scs.ts.model.TaskEntity;
import ru.otus.scs.ts.repository.TaskRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class TaskController {
    private final TaskRepository repository;

    @GetMapping("/tasks")
    public List<Task> findAll() {

        return repository.findAll().stream()
                .map( t -> new Task( t.getId(), t.getTitle(), t.getResponsibleId() ) )
                .collect( Collectors.toList() );
    }

    @PostMapping("/tasks/add")
    public Task save( Task task ) {
        var taskEntity = new TaskEntity( task.getId(), task.getTitle(), task.getResponsibleId() );
        taskEntity = repository.save( taskEntity );
        return new Task( taskEntity.getId(), taskEntity.getTitle(), taskEntity.getResponsibleId() );
    }

    @GetMapping("/tasks/{id}")
    public Task find( @PathVariable("id") String id ) {
        var taskEntity = repository.findById( id );
        return taskEntity
                .map( t -> new Task( t.getId(), t.getTitle(), t.getResponsibleId() ) )
                .orElseThrow( () -> new RuntimeException( "task not found" ) );
    }
}
