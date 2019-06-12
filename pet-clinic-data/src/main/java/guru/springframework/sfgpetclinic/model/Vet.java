package guru.springframework.sfgpetclinic.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Doa on 21-5-2019.
 */
public class Vet extends Person {

    private Set<Speciality> specialties = new HashSet<>(); // good to initialize so we can add entities to it, without creating it first

    public Set<Speciality> getSpecialities() {
        return specialties;
    }

    public void setSpecialties(Set<Speciality> specialties) {
        this.specialties = specialties;
    }
}
