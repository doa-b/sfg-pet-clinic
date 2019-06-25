package guru.springframework.sfgpetclinic.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Doa on 21-5-2019.
 */
@Entity
@Table(name = "vets")
public class Vet extends Person {

    @ManyToMany(fetch = FetchType.EAGER) // default for many to many is lazy
    @JoinTable(name = "vet_specialies", joinColumns = @JoinColumn(name = "vet_id"), // unidirectional, so no need to specify in Speciality class
    inverseJoinColumns = @JoinColumn(name = "speciality_id"))
    private Set<Speciality> specialties = new HashSet<>(); // good to initialize so we can add entities to it, without creating it first

    public Set<Speciality> getSpecialities() {
        return specialties;
    }

    public void setSpecialties(Set<Speciality> specialties) {
        this.specialties = specialties;
    }
}
