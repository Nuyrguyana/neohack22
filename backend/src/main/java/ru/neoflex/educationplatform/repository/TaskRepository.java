package ru.neoflex.educationplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.neoflex.educationplatform.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
}