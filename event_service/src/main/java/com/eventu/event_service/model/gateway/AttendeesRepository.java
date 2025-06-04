package com.eventu.event_service.model.gateway;

import com.eventu.event_service.model.Attendees;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendeesRepository extends JpaRepository<Attendees, Integer> {
}
