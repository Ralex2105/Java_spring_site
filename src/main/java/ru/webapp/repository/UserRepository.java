package ru.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.webapp.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    User findByActivationCode(String code);
}
