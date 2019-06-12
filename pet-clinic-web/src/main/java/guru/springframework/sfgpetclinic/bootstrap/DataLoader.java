package guru.springframework.sfgpetclinic.bootstrap;

import com.github.javafaker.Faker;
import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import guru.springframework.sfgpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.stream.LongStream;

/**
 * Created by Doa on 27-5-2019.
 */
@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final Faker faker;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, Faker faker) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.faker = faker;
    }

    @Override
    public void run(String... args) throws Exception {

        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        dog.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

        PetType rabbit = new PetType();
        dog.setName("Rabbit");
        PetType savedRabbitPetType = petTypeService.save(rabbit);

        System.out.println("Loaded PetTypes....");

        Owner owner1 = new Owner();
        owner1.setFirstName("Michael");
        owner1.setLastName("Weston");
        owner1.setAddress("123 Brickerel");
        owner1.setCity("Miami");
        owner1.setTelephone("123456789");

        ownerService.save(owner1);

        Pet mikesPet = new Pet();
        mikesPet.setPetType(savedDogPetType);
        mikesPet.setOwner(owner1);
        mikesPet.setName("Rosco");
        mikesPet.setBirthDate(LocalDate.now());
        owner1.getPets().add(mikesPet);


        Owner owner2 = new Owner();
        owner2.setFirstName("Fiona");
        owner2.setLastName("Glenanne");
        owner2.setAddress("123 Brickerel");
        owner2.setCity("Miami");
        owner2.setTelephone("123456789");

        ownerService.save(owner2);

        Pet fionasCat = new Pet();
        fionasCat.setName("Just Cat");
        fionasCat.setOwner(owner2);
        fionasCat.setPetType(savedCatPetType);
        fionasCat.setBirthDate(LocalDate.now());

        System.out.println("Loaded Owners....");

        Vet vet1 = new Vet();
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Jessie");
        vet2.setLastName("Porter");

        vetService.save(vet2);

        System.out.println("Loaded Vets....");

       // add 4 more vets and owners using the Faker class

        LongStream.rangeClosed(3, 6)
                .mapToObj(this::createFakeOwner)
                .forEach(ownerService::save);
        System.out.println("Loaded owners...");

        LongStream.rangeClosed(3, 6)
                .mapToObj(this::createFakeVet)
                .forEach(vetService::save);
        System.out.println("Loaded vets...");
    }

    private Vet createFakeVet(Long id) {
        Vet vet = new Vet();

        vet.setId(id);
        vet.setFirstName(faker.name().firstName());
        vet.setLastName(faker.name().lastName());

        System.out.println(vet.getFirstName());


        return vet;
    }

    private Owner createFakeOwner(Long id) {
        Owner owner = new Owner();

        owner.setId(id);
        owner.setFirstName(faker.name().firstName());
        owner.setLastName(faker.name().lastName());
        owner.setAddress(faker.address().streetName());
        owner.setCity(faker.address().cityName());
        owner.setTelephone(faker.phoneNumber().toString());

        System.out.println(owner.getFirstName());

        return owner;
    }
}
