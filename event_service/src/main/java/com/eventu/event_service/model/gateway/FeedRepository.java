package com.eventu.event_service.model.gateway;

import com.eventu.event_service.model.Feed;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedRepository extends JpaRepository<Feed, Long> {
}
