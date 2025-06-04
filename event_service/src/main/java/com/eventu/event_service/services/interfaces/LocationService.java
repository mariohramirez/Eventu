package com.eventu.event_service.services.interfaces;

import com.eventu.event_service.model.Location;
import com.eventu.event_service.services.request.LocationRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LocationService {

    public Location createLocation(LocationRequest request);

    public Location updateLocation(Long id, LocationRequest request) throws Exception;

    public void deleteLocation(Long id)throws Exception;

    public List<Location> getAllLocations();

    public Location findLocationById(Long location) throws Exception;

}
