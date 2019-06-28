package guru.springframework.sfgpetclinic.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * Created by Doa on 21-5-2019.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class BaseEntity implements Serializable {
    //typically used to manage ID and create/update timestamp fields

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Underlying SQL and H2 database will use auto increment field
                                                        // this setting retrieves that ID for us
    private Long id;

}
