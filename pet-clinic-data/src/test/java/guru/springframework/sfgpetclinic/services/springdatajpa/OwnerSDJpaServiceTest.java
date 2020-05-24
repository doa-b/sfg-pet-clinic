package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.Repositories.OwnerRepository;
import guru.springframework.sfgpetclinic.Repositories.PetRepository;
import guru.springframework.sfgpetclinic.Repositories.PetTypeRepository;
import guru.springframework.sfgpetclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

// We want to do transactional tests
@ExtendWith(MockitoExtension.class)
class OwnerSDJpaServiceTest {

    public static final String LAST_NAME = "Smith";
    @Mock
    OwnerRepository ownerRepository;
    @Mock
    PetRepository petRepository;
    @Mock
    PetTypeRepository petTypeRepository;

    @InjectMocks
    OwnerSDJpaService service;
    // injects mocks and instantiates service

    Owner returnOwner;

    @BeforeEach
    void setUp() {
        returnOwner = Owner.builder()
                .id(1L)
                .lastName(LAST_NAME)
                .build();
    }

    @Test
    void findByLastName() {

        // Set up the Mock
        // when findByLastName() is called, return that owner
        // any() is a Mockito argument matcher stating any String can go in there
        when(ownerRepository.findByLastName((any()))).thenReturn(returnOwner);

        Owner smith = service.findByLastName(LAST_NAME);

        assertEquals(LAST_NAME, smith.getLastName());

        verify(ownerRepository).findByLastName(any());
    }

    @Test
    void findAll() {

        // Create a set of 3 owners
        Set<Owner> returnOwnerSet = new HashSet<>();
        returnOwnerSet.add(Owner.builder().id(1L).build());
        returnOwnerSet.add(Owner.builder().id(2L).build());

        // Set up the mock
        // when findAll() is called, return that ownerSet
        when(ownerRepository.findAll()).thenReturn(returnOwnerSet);

        // Call the service that implements our mock repository from above & store result
        Set<Owner> owners = service.findAll();

        // check if we get a set of 2 owners
        assertNotNull(owners);
        assertEquals(2, owners.size());
    }

    @Test
    void findById() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(returnOwner));

        Owner owner = service.findById(2L);

        // check if we get an owner back
        assertNotNull(owner);
    }

    @Test
    void findByIdNotFound() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.empty());

        Owner owner = service.findById(2L);

        // check if we get Null back
        assertNull(owner);
    }

    @Test
    void save() {
        // create an owner to save
        Owner ownerToSave = Owner.builder().id(1L).build();

        // Set up the mock to return returnOwner
        when(ownerRepository.save(any())).thenReturn(returnOwner);

        // Call service
        Owner savedOwner = service.save(ownerToSave);

        // Make sure the save method was called and returned something
        assertNotNull(savedOwner);

        // verify mock interaction does occur (redundant) once
        verify(ownerRepository).save(any());
    }

    @Test
    void delete() {
        service.delete(returnOwner);
        // the method we want to test does not return a value, so we can only
        // test if ownerRepository delete method is called once(default)
        verify(ownerRepository, times(1)).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(returnOwner.getId());

        verify(ownerRepository).deleteById(anyLong());
    }
}