package com.eventu.event_service.model.gateway;

import com.eventu.event_service.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
