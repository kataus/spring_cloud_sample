package ru.otus.scs.ts.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.scs.ts.model.TaskEntity;

public interface TaskRepository extends MongoRepository<TaskEntity, String> {
}
