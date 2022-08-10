package ru.neoflex.educationplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.neoflex.educationplatform.model.Answer;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    List<Answer> findAllByIdIn(List<Long> ids);
}
