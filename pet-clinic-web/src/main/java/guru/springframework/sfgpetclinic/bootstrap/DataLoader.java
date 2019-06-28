package guru.springframework.sfgpetclinic.bootstrap;

import com.github.javafaker.Faker;
import guru.springframework.sfgpetclinic.model.*;
import guru.springframework.sfgpetclinic.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.stream.LongStream;

/**
 * Created by Doa on 27-5-2019.
 */
@Component
public class DataLoader implements CommandLineRunner {

    // Spring will inject one of the implementatations of these interfaces, based on the active profile
    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialityService specialityService;
    private final VisitService visitService;
    private final Faker faker;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService,
                      SpecialityService specialityService, VisitService visitService, Faker faker) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialityService = specialityService;
        this.visitService = visitService;
        this.faker = faker;
    }

    @Override
    public void run(String... args) throws Exception {

        int count = petTypeService.findAll().size();

        if (count == 0) { // check if our persistency layer already has data in it
            loadData(); // no -> populate it with our data
        }
    }

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog); // persist it to our map using the service

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

        PetType rabbit = new PetType();
        rabbit.setName("Rabbit");
        PetType savedRabbitPetType = petTypeService.save(rabbit);

        System.out.println("Loaded PetTypes....");

        Speciality radiology = new Speciality();
        radiology.setDescription("Radiology");
        Speciality savedRadiology = specialityService.save(radiology);

        Speciality surgery = new Speciality();
        surgery.setDescription("Surgery");
        Speciality savedSurgery = specialityService.save(surgery);


        Speciality dentistry = new Speciality();
        dentistry.setDescription("Dentistry");
        Speciality savedDentistry = specialityService.save(dentistry);

        System.out.println("Loaded Specialities....");

        Owner owner1 = new Owner();
        owner1.setFirstName("Michael");
        owner1.setLastName("Weston");
        owner1.setAddress("123 Brickerel");
        owner1.setCity("Miami");
        owner1.setTelephone("123456789");

//        owner1.builder()
//                .lastName("Doa")
//                .firstName("Boni");

        Pet mikesPet = new Pet();
        mikesPet.setPetType(savedDogPetType);
        mikesPet.setOwner(owner1);
        mikesPet.setName("Rosco");
        mikesPet.setBirthDate(LocalDate.now());
        owner1.getPets().add(mikesPet);

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Fiona");
        owner2.setLastName("Glenanne");
        owner2.setAddress("123 Brickerel");
        owner2.setCity("Miami");
        owner2.setTelephone("123456789");

        Pet fionasCat = new Pet();
        fionasCat.setName("Just Cat");
        fionasCat.setOwner(owner2);
        fionasCat.setPetType(savedCatPetType);
        fionasCat.setBirthDate(LocalDate.now());
        owner2.getPets().add(fionasCat);

        ownerService.save(owner2);

        Visit catVisit = new Visit();
        catVisit.setPet(fionasCat);
        catVisit.setDate(LocalDate.now());
        catVisit.setDescription("Sneezy Kitty");

        visitService.save(catVisit);

        System.out.println("Loaded Owners....");

        Vet vet1 = new Vet();
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");
        vet1.getSpecialties().add(savedRadiology);

         vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Jessie");
        vet2.setLastName("Porter");
        vet2.getSpecialties().add(savedSurgery);

        vetService.save(vet2);

        System.out.println("Loaded Vets....");

        // add 4 more vets and owners using the Faker class

        LongStream.rangeClosed(3, 6)
                .mapToObj(this::createFakeOwner)
                .forEach(ownerService::save);
        System.out.println("Loaded additional owners...");

        LongStream.rangeClosed(3, 6)
                .mapToObj(this::createFakeVet)
                .forEach(vetService::save);
        System.out.println("Loaded additional vets...");
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
        owner.setTelephone(faker.phoneNumber().phoneNumber());

        System.out.println(owner.getFirstName());

        return owner;
    }
}
