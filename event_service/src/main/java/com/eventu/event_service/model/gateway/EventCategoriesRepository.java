package com.eventu.event_service.model.gateway;

import com.eventu.event_service.model.EventCategories;
import com.eventu.event_service.model.EventCategoryId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventCategoriesRepository extends JpaRepository<EventCategories, EventCategoryId> {
}
