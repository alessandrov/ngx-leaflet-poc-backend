package ngx.leaflet.poc.backend.service;

import ngx.leaflet.poc.backend.exception.DataProcessingException;
import ngx.leaflet.poc.backend.model.CoordinatesLookupResult;

import java.util.List;

public interface CoordinatesLookupService {

    List<CoordinatesLookupResult> locate(String node, String way, String relation) throws DataProcessingException;

}
