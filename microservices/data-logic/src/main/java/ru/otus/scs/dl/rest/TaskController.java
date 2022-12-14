package ru.otus.scs.dl.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.otus.scs.dl.service.TaskService;
import ru.otus.scs.dl.service.UsersService;
import ru.otus.scs.dto.Task;
import ru.otus.scs.dto.TaskInfo;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
public class TaskController {
    private final UsersService usersService;
    private final TaskService taskService;

    @GetMapping("/tasks/{taskId}")
    public TaskInfo getTask( @PathVariable("taskId") String taskId ) {
        var task = taskService.find( taskId );
        var user = usersService.get( task.getResponsibleId() );
        return new TaskInfo( task.getId(), task.getTitle(), user );
    }

    @GetMapping("/tasks")
    public List<TaskInfo> getTasks() {
        var tasks = taskService.findAll();
        return tasks.stream()
                .map( t -> new TaskInfo( t.getId(), t.getTitle(), usersService.get( t.getResponsibleId() ) ) )
                .collect( Collectors.toList() );
    }

    @PostMapping("/tasks/save")
    public TaskInfo save( @RequestBody TaskInfo taskInfo ) {
        var task = new Task( taskInfo.getTaskId(), taskInfo.getTitle(), taskInfo.getResponsible().getId() );
        log.error( "task {}, taskInfo {}", task, taskInfo );
        task = taskService.save( task );
        log.error( "task {} ", task );
        var user = usersService.get( task.getResponsibleId() );
        return new TaskInfo( task.getId(), task.getTitle(), user );
    }
}
