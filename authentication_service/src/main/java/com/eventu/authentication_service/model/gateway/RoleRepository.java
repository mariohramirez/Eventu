package com.eventu.authentication_service.model.gateway;

import com.eventu.authentication_service.model.Role;
import com.eventu.authentication_service.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    public Role findByName(RoleName name);
}
