package com.eventu.authentication_service.model.gateway;

import com.eventu.authentication_service.model.UserRole;
import com.eventu.authentication_service.model.UserRoleId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, UserRoleId> {
}
