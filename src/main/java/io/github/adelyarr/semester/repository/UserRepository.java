package io.github.adelyarr.semester.repository;

import io.github.adelyarr.semester.entity.AuthType;
import io.github.adelyarr.semester.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User save(User user);
    Optional<User> findByEmail(String email);
    Optional<User> findByOauth2Id(String id);
}