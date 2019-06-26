package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by Doa on 23-5-2019.
 */
@Service
@Profile({"default", "map"})
public class OwnerServiceMap extends AbstractMapService<Owner, Long> implements OwnerService {

    private final PetTypeService petTypeService;
    private final PetService petService;

    public OwnerServiceMap(PetTypeService petTypeService, PetService petService) {
        this.petTypeService = petTypeService;
        this.petService = petService;
    }

    @Override
    public Set<Owner> findAll() {
        return super.findAll();
    }

    @Override
    public Owner findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Owner save(Owner object) {

        if(object != null){
            if (object.getPets() !=null) {
                object.getPets().forEach(pet -> { // a ownner can have more then one pet, so we walk over them all
                    if (pet.getPetType() != null) {
                        if(pet.getPetType().getId() == null) { // check if that pettype is not saved to our map yet
                            pet.setPetType(petTypeService.save(pet.getPetType())); // save that new pettype AND save that pettype to this owner
                        }

                    } else {
                        throw new RuntimeException("Pet Type is required");
                    }

                    if(pet.getId() == null) { // if the pet is not yet save to our map yet
                        Pet savedPet = petService.save(pet); // save that new pet
                        pet.setId(savedPet.getId()); // set that Id on pet
                    }
                });
            }
            return super.save(object);
        } else {
            return null;
        }


    }

    @Override
    public void delete(Owner object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public Owner findByLastName(String lastName) {
        return null;
    }
}
