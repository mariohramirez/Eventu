package com.eventu.event_service.model.gateway;

import com.eventu.event_service.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
