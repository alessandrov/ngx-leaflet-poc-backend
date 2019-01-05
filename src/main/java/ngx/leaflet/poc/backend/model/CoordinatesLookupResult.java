package ngx.leaflet.poc.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CoordinatesLookupResult {

    // no other fields needed for the purposes of this application

    String lat;

    String lon;

    public CoordinatesLookupResult() {
    }

    public CoordinatesLookupResult(String lat, String lon) {
        this.lat = lat;
        this.lon = lon;
    }

}
