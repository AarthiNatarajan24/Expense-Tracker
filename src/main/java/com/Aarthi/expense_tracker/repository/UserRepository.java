package com.Aarthi.expense_tracker.repository;

import com.Aarthi.expense_tracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    Optional<User> findByEmail(String email);   // 👈 REQUIRED for email login

    boolean existsByEmail(String email); 
  
}
