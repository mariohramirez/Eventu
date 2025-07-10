package com.eventu.event_service.services.response;

import com.eventu.event_service.model.StatusName;
import com.eventu.event_service.model.VisibilityName;
import com.eventu.event_service.services.request.CategoryRequest;
import com.eventu.event_service.services.request.LocationRequest;
import com.eventu.event_service.services.request.TagRequest;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class EventResponse {
    private String title;
    private String description;
    private String shortDescription;
    private LocalDate startTime;
    private LocalDate endTime;
    private int maxAttendees;
    private int minAttendees;
    private int currentAttendees;
}
