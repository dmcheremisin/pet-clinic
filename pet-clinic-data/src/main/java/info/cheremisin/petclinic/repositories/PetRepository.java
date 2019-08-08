package info.cheremisin.petclinic.repositories;

import info.cheremisin.petclinic.model.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<Pet, Long> {
}
