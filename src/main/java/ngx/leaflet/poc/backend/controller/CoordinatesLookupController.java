package ngx.leaflet.poc.backend.controller;

import ngx.leaflet.poc.backend.exception.DataProcessingException;
import ngx.leaflet.poc.backend.model.CoordinatesLookupResult;
import ngx.leaflet.poc.backend.service.CoordinatesLookupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Responsible for providing access to locations coordinates (latitude and longitude) based on OSM elements
 */
@RestController
@RequestMapping("/coordinates")
public class CoordinatesLookupController {

    private static final Logger logger = LoggerFactory.getLogger(CoordinatesLookupController.class);

    @Autowired
    private CoordinatesLookupService coordinatesLookupService;

    @CrossOrigin(origins = {"http://localhost:4200"})
    @GetMapping(value = "/{node}/{way}/{relation}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<List<CoordinatesLookupResult>> get(@PathVariable("node") String node,
                                                             @PathVariable("way") String way,
                                                             @PathVariable("relation") String relation) throws DataProcessingException {

        logger.info("Coordinates lookup started");

        List<CoordinatesLookupResult> response = coordinatesLookupService.locate(node, way, relation);

        logger.info("Coordinates lookup ended");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
