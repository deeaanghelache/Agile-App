package com.unibuc.appbackend.interfaces;

import com.unibuc.appbackend.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String> {
}
