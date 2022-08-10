package ru.neoflex.educationplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.neoflex.educationplatform.model.InterestTag;

import java.util.List;

public interface InterestTagRepository extends JpaRepository<InterestTag, Long> {

    List<InterestTag> findAllByIdIn(List<Long> ids);
}