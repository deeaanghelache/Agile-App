package com.unibuc.appbackend.embeddedIds;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleEmbeddedId implements Serializable {
    @Column(name = "role_id", nullable = false)
    private String roleId;

    @Column(name = "user_id", nullable = false)
    private String userId;

    public UserRoleEmbeddedId(String roleId, String userId) {
        this.roleId = roleId;
        this.userId = userId;
    }
}
