package com.eventu.event_service.controllers.request;

import com.eventu.event_service.model.StatusName;
import com.eventu.event_service.model.VisibilityName;
import lombok.Data;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
public class EventRequest {
    private String title;
    private String description;
    private String shortDescription;
    private LocalDate starTime;
    private LocalDate endTime;
    private Long organizerId;
    private StatusName status;
    private VisibilityName visibility;
    private int maxAttendees;
    private int minAttendees;
    private int currentAttendees;
    private LocalDate publishedAt;
    //private Set<EventCategories> categories = new HashSet<>();
    //private Set<EventTags> eventTags = new HashSet<>();
}
