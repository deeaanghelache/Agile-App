package com.unibuc.appbackend.interfaces;

import com.unibuc.appbackend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
