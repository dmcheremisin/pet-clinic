package info.cheremisin.petclinic.service;

import info.cheremisin.petclinic.model.Vet;

import java.util.Set;

public interface VetService {

    Vet findById(Long id);

    Set<Vet> findAll();

    Vet save(Vet owner);
}
