package com.eventu.event_service.services;

import com.eventu.event_service.model.Location;
import com.eventu.event_service.model.gateway.LocationRepository;
import com.eventu.event_service.services.interfaces.LocationService;
import com.eventu.event_service.services.request.LocationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LocationServiceImplementation implements LocationService {

    @Autowired
    private LocationRepository locationRepository;

    @Override
    public Location createLocation(LocationRequest request) {
        Location createdLocation = new Location();
        createdLocation.setAddressLine1(request.getAddressLine1());
        createdLocation.setLocationNumber(UUID.randomUUID().toString());
        createdLocation.setCity(request.getCity());
        createdLocation.setCountry(request.getCountry());
        createdLocation.setCapacity(request.getCapacity());
        createdLocation.setAddressLine2(request.getAddressLine2());
        createdLocation.setDescription(request.getDescription());
        createdLocation.setFacilities(request.getFacilities());
        createdLocation.setLatitude(request.getLatitude());
        createdLocation.setLongitude(request.getLongitude());
        createdLocation.setName(request.getName());
        createdLocation.setOnline(request.isOnline());
        createdLocation.setOnlineUrl(request.getOnlineUrl());
        createdLocation.setTimezone(request.getTimezone());
        return locationRepository.save(createdLocation);
    }

    @Override
    public Location updateLocation(Long id, LocationRequest request) throws Exception {
        Location location = findLocationById(id);
        if(request.getAddressLine1()!=null){
            location.setAddressLine1(request.getAddressLine1());
        }
        if(request.getCity()!=null){
            location.setCity(request.getCity());
        }
        if(request.getCountry()!=null){
            location.setCountry(request.getCountry());
        }
        if(request.getCapacity()!=0){
            location.setCapacity(request.getCapacity());
        }
        if(request.getAddressLine2()!=null){
            location.setAddressLine2(request.getAddressLine2());
        }
        if(request.getDescription()!=null){
            location.setDescription(request.getDescription());
        }
        if(request.getFacilities()!=null){
            location.setFacilities(request.getFacilities());
        }
        if(request.getLatitude()!=null){
            location.setLatitude(request.getLatitude());
        }
        if(request.getLongitude()!=null){
            location.setLongitude(request.getLongitude());
        }
        if(request.getName()!=null){
            location.setName(request.getName());
        }
        if(request.getOnlineUrl()!=null){
            location.setOnlineUrl(request.getOnlineUrl());
        }
        if(request.getTimezone()!=null){
            location.setTimezone(request.getTimezone());
        }
        return locationRepository.save(location);
    }

    @Override
    public void deleteLocation(Long id) throws Exception {
        Location location = findLocationById(id);
        locationRepository.delete(location);
    }

    @Override
    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    @Override
    public Location findLocationById(Long location) throws Exception {
        Optional<Location> opt = locationRepository.findById(location);
        if(opt.isEmpty()){
            throw new Exception("Ubicaci[on no encontrada");
        }
        return opt.get();
    }
}
