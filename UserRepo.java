package com.miniProject.miniProjectTask.repo;

import com.miniProject.miniProjectTask.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUserId(String userId);

}
