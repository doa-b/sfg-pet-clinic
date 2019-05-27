package guru.springframework.sfgpetclinic.bootstrap;

import com.github.javafaker.Faker;
import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.stream.LongStream;

/**
 * Created by Doa on 27-5-2019.
 */
@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final Faker faker;

    public DataLoader(OwnerService ownerService, VetService vetService, Faker faker) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.faker = faker;
    }

    @Override
    public void run(String... args) throws Exception {

        Owner owner1 = new Owner();
        owner1.setId(1L);
        owner1.setFirstName("Michael");
        owner1.setLastName("Weston");

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setId(2L);
        owner2.setFirstName("Fiona");
        owner2.setLastName("Glenanne");

        ownerService.save(owner2);

        System.out.println("Loaded Owners....");

        Vet vet1 = new Vet();
        vet1.setId(1L);
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setId(2L);
        vet2.setFirstName("Jessie");
        vet2.setLastName("Porter");

        vetService.save(vet1);

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

        System.out.println(owner.getFirstName());

        return owner;
    }
}
