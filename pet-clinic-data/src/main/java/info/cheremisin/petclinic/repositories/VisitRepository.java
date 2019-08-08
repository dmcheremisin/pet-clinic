package info.cheremisin.petclinic.repositories;

import info.cheremisin.petclinic.model.Visit;
import org.springframework.data.repository.CrudRepository;

public interface VisitRepository extends CrudRepository<Visit, Long> {
}
