package com.eventu.event_service.model.gateway;

import com.eventu.event_service.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
