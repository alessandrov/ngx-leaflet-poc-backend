package ngx.leaflet.poc.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaAuditing
@PropertySource("classpath:config.properties")
@SpringBootApplication(scanBasePackages = {"ngx.leaflet.poc.backend"})
@EntityScan("ngx.leaflet.poc.backend.entity")
@EnableJpaRepositories("ngx.leaflet.poc.backend.repository")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
