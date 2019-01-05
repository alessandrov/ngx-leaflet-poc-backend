package ngx.leaflet.poc.backend.service.impl;

import ngx.leaflet.poc.backend.entity.Location;
import ngx.leaflet.poc.backend.repository.LocationRepository;
import ngx.leaflet.poc.backend.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    protected LocationRepository locationRepository;

    @Override
    public Location find(Long id) {
        Location result = null;

        Optional<Location> location = locationRepository.findById(id);

        if (location.isPresent()) {
            result = location.get();
        }

        return result;
    }

    @Override
    public List<Location> findAll() {
        return (List<Location>) locationRepository.findAll();
    }

    @Override
    public Location save(Location entity) {
        return locationRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        locationRepository.deleteById(id);
    }

    @Override
    public Location update(Location entity) {
        return locationRepository.save(entity);
    }

}
