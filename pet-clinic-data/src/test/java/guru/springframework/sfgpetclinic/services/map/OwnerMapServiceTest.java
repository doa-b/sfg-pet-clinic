package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OwnerMapServiceTest {

    OwnerMapService ownerMapService;
    final Long testOwnerId = 1L;
    final String lastName = "Doe";

    @BeforeEach
    void setUp() {
     // initialize ownerMapService. We don't need Mackito because it uses a simple HashMap, no resources like dB
        ownerMapService = new OwnerMapService(new PetTypeMapService(), new PetMapService());

        // Put one owner Object into it
        ownerMapService.save(Owner.builder()
                .id(testOwnerId)
                .lastName(lastName)
                .build());
    }

    @Test
    void findAll() {
        Set<Owner> ownerSet = ownerMapService.findAll();

        assertEquals(1, ownerSet.size());
    }

    @Test
    void findById() {
        Owner owner = ownerMapService.findById(testOwnerId);

        assertEquals(testOwnerId, owner.getId());
    }

    @Test
    void saveExistingId() {
        // save a new owner
        Long owner2Id = 2L;
        Owner owner2 = Owner.builder().id(owner2Id).build();
        Owner savedOwner = ownerMapService.save(owner2);

        assertEquals(owner2Id, savedOwner.getId());
    }

    @Test
    void saveNoId() {
        // save a new owner without id
        Owner owner3 = Owner.builder().build();
        Owner savedOwner = ownerMapService.save(owner3);

        // test if this owner is saved
       assertNotNull(savedOwner);
       // test if this owner got an id
       assertNotNull(savedOwner.getId());
    }

    @Test
    void delete() {
        // delete the owner by object using testOwnerId (which we created in setUp)
        ownerMapService.delete(ownerMapService.findById(testOwnerId));

        // check if testOwner is deleted
        assertEquals(0, ownerMapService.findAll().size());
    }

    @Test
    void deleteById() {
        ownerMapService.deleteById(testOwnerId);

        assertEquals(0, ownerMapService.findAll().size());
    }

    @Test
    void findByLastName() {
        // find our testOwner by lastName
        Owner Doe = ownerMapService.findByLastName(lastName);

       // check if it is returned with the right id
        assertNotNull(Doe);
       assertEquals(testOwnerId, Doe.getId());
    }

    @Test
    void findByLastNameNotFound() {
       // find an owner that does not exist
        Owner Doe = ownerMapService.findByLastName("foo");

        // check if we get null in return
        assertNull(Doe);
    }
}