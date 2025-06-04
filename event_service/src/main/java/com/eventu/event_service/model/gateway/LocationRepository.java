package com.eventu.event_service.model.gateway;

import com.eventu.event_service.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
