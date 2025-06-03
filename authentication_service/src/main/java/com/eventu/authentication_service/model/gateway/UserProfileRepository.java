package com.eventu.authentication_service.model.gateway;

import com.eventu.authentication_service.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
}
