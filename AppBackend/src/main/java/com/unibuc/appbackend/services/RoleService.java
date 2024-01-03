package com.unibuc.appbackend.services;

import com.unibuc.appbackend.entities.Role;
import com.unibuc.appbackend.enums.RoleName;
import com.unibuc.appbackend.exceptions.UserRoleNotFoundException;
import com.unibuc.appbackend.interfaces.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {

    private RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role getRoleByName(RoleName name) {
        Optional<Role> role = roleRepository.findByRoleName(name);
        if (role.isPresent()) {
            return role.get();
        } else {
            throw new UserRoleNotFoundException();
        }
    }
}
