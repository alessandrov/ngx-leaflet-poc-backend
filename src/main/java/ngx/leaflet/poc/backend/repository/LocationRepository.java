package ngx.leaflet.poc.backend.repository;

import ngx.leaflet.poc.backend.entity.Location;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends CrudRepository<Location, Long> {

}
