package com.mynew.auth.user.repository;

import com.mynew.auth.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
