package com.civoranexus.eduvillage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.civoranexus.eduvillage.entity.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByEmail(String email);
}
