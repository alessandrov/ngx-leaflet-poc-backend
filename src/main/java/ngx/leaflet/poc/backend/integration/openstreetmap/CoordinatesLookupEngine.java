package ngx.leaflet.poc.backend.integration.openstreetmap;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ngx.leaflet.poc.backend.exception.DataProcessingException;
import ngx.leaflet.poc.backend.exception.WebClientCustomServerException;
import ngx.leaflet.poc.backend.model.CoordinatesLookupResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

/**
 *
 * This class is responsible for retrieving latitude and longitude coordinates from openstreetmap.org
 * through OSM elements parameters (node, way, relation)
 *
 */
@Configuration
public class CoordinatesLookupEngine {

    private static final Logger logger = LoggerFactory.getLogger(CoordinatesLookupEngine.class);

    private WebClient webClient;

    private Properties configProperties;

    public CoordinatesLookupEngine() {
    }

    public void init() throws DataProcessingException {
        Resource resource = new ClassPathResource("/config.properties");

        try {
            configProperties = PropertiesLoaderUtils.loadProperties(resource);
        } catch (IOException e) {
            logger.error("Property file not found");
            throw new DataProcessingException("No property file was found");
        }

        String nominatimOpenstreetmapURL = configProperties.getProperty("nominatim_openstreetmap_url");

        this.webClient = WebClient.builder()
                        .baseUrl(nominatimOpenstreetmapURL)
                        .build();
    }

    public List<CoordinatesLookupResult> getCoordinates(String node, String way, String relation) throws DataProcessingException {

        logger.info("About to query OSM");

        String query = getQuery(node, way, relation);

        Flux<String> response =  webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/lookup")
                        .queryParam("osm_ids", query)
                        .queryParam("format", "json")
                        .build())
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .retrieve()
                .onStatus(HttpStatus::is5xxServerError, clientResponse ->
                        Mono.error(new WebClientCustomServerException("An error occurred while retrieving data",
                                HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error", null, null, null))
                )
                .bodyToFlux(String.class);

        String result = response.blockFirst();

        ObjectMapper mapper = new ObjectMapper();

        List<CoordinatesLookupResult> coordinates = null;

        try {
            coordinates = mapper.readValue(result, new TypeReference<List<CoordinatesLookupResult>>(){});
        } catch (IOException e) {
            logger.error(e.getMessage());

            throw new DataProcessingException("An error occurred while processing retrieved data");
        }

        return coordinates;
    }

    /*
     * OSM doesn't complain if there are commas at the end of the query
     */
    private String getQuery(String node, String way, String relation) {
        StringBuffer result = new StringBuffer();

        if (!node.equals("0")){
            result.append("N" + node + ",");
        }

        if (!way.equals("0")){
            result.append("W" + way + ",");
        }

        if (!relation.equals("0")){
            result.append("R" + relation);
        }

        return result.toString();
    }

}
