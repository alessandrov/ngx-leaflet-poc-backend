package ngx.leaflet.poc.backend;

import ngx.leaflet.poc.backend.entity.Location;
import ngx.leaflet.poc.backend.repository.LocationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 *
 * Responsible for storing 20 predefined location as per documentation
 *
 */

@Component
public class DataLoader implements ApplicationRunner {

    private static final Logger logger = LoggerFactory.getLogger(DataLoader.class);

    private LocationRepository locationRepository;

    @Autowired
    public DataLoader(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public void run(ApplicationArguments args) {
        logger.info("Started storing predefined locations");

        storeLocations();

        logger.info("Finished storing predefined locations");
    }

    private void storeLocations() {
        locationRepository.save(new Location("41.890", "12.500"));
        locationRepository.save(new Location("45.480", "9.190"));
        locationRepository.save(new Location("45.080", "7.680"));
        locationRepository.save(new Location("44.420", "8.930"));
        locationRepository.save(new Location("44.500", "11.340"));
        locationRepository.save(new Location("43.780", "11.240"));
        locationRepository.save(new Location("45.430", "12.330"));
        locationRepository.save(new Location("45.440", "10.990"));
        locationRepository.save(new Location("45.650", "13.770"));
        locationRepository.save(new Location("45.410", "11.870"));
        locationRepository.save(new Location("45.550", "10.220"));
        locationRepository.save(new Location("44.650", "10.920"));
        locationRepository.save(new Location("43.890", "11.090"));
        locationRepository.save(new Location("44.810", "10.320"));
        locationRepository.save(new Location("43.110", "12.390"));
        locationRepository.save(new Location("43.550", "10.300"));
        locationRepository.save(new Location("44.710", "10.630"));
        locationRepository.save(new Location("44.420", "12.210"));
        locationRepository.save(new Location("45.550", "11.540"));
        locationRepository.save(new Location("42.570", "12.650"));
    }

}