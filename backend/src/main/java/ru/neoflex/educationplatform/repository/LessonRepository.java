package ru.neoflex.educationplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.neoflex.educationplatform.model.Lesson;

public interface LessonRepository extends JpaRepository<Lesson, Long> {
}