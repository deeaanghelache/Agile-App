package com.unibuc.appbackend.entities;

import com.unibuc.appbackend.enums.RoleName;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    @NotNull
    private UUID roleId;

    @Column(columnDefinition = "varchar(15)", unique = true)
    @NotNull
    @Enumerated(EnumType.STRING)
    private RoleName roleName;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserRole> userRoles;

    public Role(UUID roleId, RoleName roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }
}
