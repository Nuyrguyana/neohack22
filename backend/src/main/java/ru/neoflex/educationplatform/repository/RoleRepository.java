package ru.neoflex.educationplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.neoflex.educationplatform.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
}