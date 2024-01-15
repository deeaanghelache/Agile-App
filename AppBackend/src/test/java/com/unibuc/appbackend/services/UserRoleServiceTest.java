package com.unibuc.appbackend.services;

import com.unibuc.appbackend.embeddedIds.UserRoleEmbeddedId;
import com.unibuc.appbackend.entities.Role;
import com.unibuc.appbackend.entities.User;
import com.unibuc.appbackend.entities.UserRole;
import com.unibuc.appbackend.enums.RoleName;
import com.unibuc.appbackend.interfaces.UserRoleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserRoleServiceTest {

    @InjectMocks
    private UserRoleService userRoleService;

    @Mock
    private UserRoleRepository userRoleRepository;

    @Mock
    private RoleService roleService;

    @Test
    void addRoleForUser_shouldSaveUserRole() {
        User user = new User(UUID.randomUUID(), "John", "Doe", "john.doe@example.com", "password");
        Role userRole = new Role(UUID.randomUUID(), RoleName.USER);

        UserRoleEmbeddedId userRoleEmbeddedId = new UserRoleEmbeddedId(userRole.getRoleId(), user.getUserId());
        UserRole expectedUserRole = new UserRole(userRoleEmbeddedId, user, userRole);

        when(roleService.getRoleByName(RoleName.USER)).thenReturn(userRole);

        userRoleService.addRoleForUser(user);

        verify(roleService).getRoleByName(RoleName.USER);
        verify(userRoleRepository).save(expectedUserRole);
    }
}
