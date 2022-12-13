package ru.otus.sc.us.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.sc.us.model.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
