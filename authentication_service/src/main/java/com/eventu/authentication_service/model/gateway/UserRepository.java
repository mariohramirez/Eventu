package com.eventu.authentication_service.model.gateway;

import com.eventu.authentication_service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    public User findByEmail(String username);

}
