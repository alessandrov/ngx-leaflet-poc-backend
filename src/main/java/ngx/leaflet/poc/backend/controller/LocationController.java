package ngx.leaflet.poc.backend.controller;

import ngx.leaflet.poc.backend.entity.Location;
import ngx.leaflet.poc.backend.service.LocationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * Responsible for providing access to predefined locations coordinates (latitude and longitude)
 *
 */
@RestController
@RequestMapping("/location")
public class LocationController {

    private static final Logger logger = LoggerFactory.getLogger(LocationController.class);

    @Autowired
    private LocationService locationService;

    @CrossOrigin(origins = {"http://localhost:4200"})
    @GetMapping(value = "/")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<List<Location>> getAll() {

        logger.info("Predefined locations list retrieval started");

        List<Location> result = locationService.findAll();

        if (result.isEmpty()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        logger.info("Predefined locations list retrieval ended");

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
