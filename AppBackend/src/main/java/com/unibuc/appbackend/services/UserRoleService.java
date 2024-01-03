package com.unibuc.appbackend.services;

import com.unibuc.appbackend.embeddedIds.UserRoleEmbeddedId;
import com.unibuc.appbackend.entities.Role;
import com.unibuc.appbackend.entities.User;
import com.unibuc.appbackend.entities.UserRole;
import com.unibuc.appbackend.enums.RoleName;
import com.unibuc.appbackend.interfaces.UserRoleRepository;
import org.springframework.stereotype.Service;

@Service
public class UserRoleService {

    private UserRoleRepository userRoleRepository;
    private RoleService roleService;

    public UserRoleService(UserRoleRepository userRoleRepository, RoleService roleService) {
        this.userRoleRepository = userRoleRepository;
        this.roleService = roleService;
    }

    public void addRoleForUser(User user) {
        Role role = roleService.getRoleByName(RoleName.USER);
        UserRoleEmbeddedId userRoleEmbeddedId = new UserRoleEmbeddedId(role.getRoleId(), user.getUserId());

        UserRole userRole = new UserRole(userRoleEmbeddedId, user, role);
        userRoleRepository.save(userRole);
    }
}
