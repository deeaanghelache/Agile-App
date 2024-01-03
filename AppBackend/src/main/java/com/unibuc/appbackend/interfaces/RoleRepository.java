package com.unibuc.appbackend.interfaces;

import com.unibuc.appbackend.entities.Role;
import com.unibuc.appbackend.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, String> {

    Optional<Role> findByRoleName(RoleName name);
}
