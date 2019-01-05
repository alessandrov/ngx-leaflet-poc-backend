package ngx.leaflet.poc.backend.service.common;

import java.util.List;

public interface AbstractService<T, String> {

    T find(final String id);

    List<T> findAll();

    T save(final T entity);

    T update(final T entity);

    void delete(final String entityId);

}
