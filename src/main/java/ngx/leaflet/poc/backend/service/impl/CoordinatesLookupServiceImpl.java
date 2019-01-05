package ngx.leaflet.poc.backend.service.impl;

import ngx.leaflet.poc.backend.exception.DataProcessingException;
import ngx.leaflet.poc.backend.integration.openstreetmap.CoordinatesLookupEngine;
import ngx.leaflet.poc.backend.model.CoordinatesLookupResult;
import ngx.leaflet.poc.backend.service.CoordinatesLookupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoordinatesLookupServiceImpl implements CoordinatesLookupService {

    @Autowired
    private CoordinatesLookupEngine coordinatesLookupEngine;

    @Override
    public List<CoordinatesLookupResult> locate(String node, String way, String relation) throws DataProcessingException {

        coordinatesLookupEngine.init();

        List<CoordinatesLookupResult> result = coordinatesLookupEngine.getCoordinates(node, way, relation);

        return result;
    }
}
