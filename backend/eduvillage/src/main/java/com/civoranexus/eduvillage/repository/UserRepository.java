package com.civoranexus.eduvillage.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.civoranexus.eduvillage.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
