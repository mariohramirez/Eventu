package com.eventu.event_service.model.gateway;

import com.eventu.event_service.model.EventTagId;
import com.eventu.event_service.model.EventTags;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventTagRepository extends JpaRepository<EventTags, EventTagId> {
}
