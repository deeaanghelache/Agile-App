package com.unibuc.appbackend.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.unibuc.appbackend.embeddedIds.UserProjectEmbeddedId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProject {
    @EmbeddedId
    private UserProjectEmbeddedId userProjectEmbeddedId;

    @MapsId("userId")
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @MapsId("projectId")
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;
}
