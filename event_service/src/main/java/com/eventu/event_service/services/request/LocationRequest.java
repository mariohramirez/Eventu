package com.eventu.event_service.services.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class LocationRequest {
    private Long id;
    private String name;
    private String description;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String country;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private boolean isOnline;
    private String onlineUrl;
    private String timezone;
    private int capacity;
    private String[] facilities;

}
