package com.eventu.event_service.model.gateway;

import com.eventu.event_service.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Integer> {
}
