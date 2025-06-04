package com.eventu.event_service.services.request;

import lombok.Data;

@Data
public class CategoryRequest {
    private Integer id;
    private String name;
    private String description;
    private String icon;
}
