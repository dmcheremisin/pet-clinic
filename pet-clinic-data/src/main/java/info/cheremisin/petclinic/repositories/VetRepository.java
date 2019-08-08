package info.cheremisin.petclinic.repositories;

import info.cheremisin.petclinic.model.Vet;
import org.springframework.data.repository.CrudRepository;

public interface VetRepository extends CrudRepository<Vet, Long> {
}
