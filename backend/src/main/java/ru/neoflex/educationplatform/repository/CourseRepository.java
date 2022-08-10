package ru.neoflex.educationplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.neoflex.educationplatform.model.Course;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query("select c from Course c where c.isPrivate = ?1 and c.status = ?2")
    List<Course> findAllByIsPrivateAndStatus(Boolean isPrivate, String status);
}