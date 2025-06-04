package com.eventu.event_service.services.request;

import com.eventu.event_service.model.StatusName;
import com.eventu.event_service.model.VisibilityName;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class EventRequest {
    private String title;
    private String description;
    private String shortDescription;
    private LocalDate startTime;
    private LocalDate endTime;
    private Long organizerId;
    private StatusName status;
    private VisibilityName visibility;
    private int maxAttendees;
    private int minAttendees;
    private int currentAttendees;
    private LocalDate publishedAt;
    private LocationRequest locationRequest;
    private List<CategoryRequest> categoryRequest;
    private List<TagRequest> tagRequest;
}
