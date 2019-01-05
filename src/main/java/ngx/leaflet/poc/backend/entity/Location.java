package ngx.leaflet.poc.backend.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "predefined_location")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Column(name = "latitude", nullable = false)
    private String latitude;

    @NotBlank
    @Column(name = "longitude", nullable = false)
    private String longitude;

    public Location(){
    }

    public Location(String latitude, String longitude){
        this.latitude = latitude;
        this.longitude = longitude;
    }

}
