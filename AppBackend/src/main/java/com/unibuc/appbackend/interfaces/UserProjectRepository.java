package com.unibuc.appbackend.interfaces;

import com.unibuc.appbackend.embeddedIds.UserProjectEmbeddedId;
import com.unibuc.appbackend.entities.UserProject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProjectRepository extends JpaRepository<UserProject, UserProjectEmbeddedId> {
}
