package guru.springframework.sfgpetclinic.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Doa on 21-5-2019.
 */
@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "vets")
public class Vet extends Person {

    @Builder
    public Vet(Long id, String firstName, String lastName, Set<Speciality> specialties) {
        super(id, firstName, lastName);
        this.specialties = specialties;
    }

    @ManyToMany(fetch = FetchType.EAGER) // default for many to many is lazy
    @JoinTable(name = "vet_specialities", joinColumns = @JoinColumn(name = "vet_id"), // unidirectional, so no need to specify in Speciality class
    inverseJoinColumns = @JoinColumn(name = "speciality_id"))
    private Set<Speciality> specialties = new HashSet<>(); // good to initialize so we can add entities to it, without creating it first
}
