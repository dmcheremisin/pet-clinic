package info.cheremisin.petclinic.service.springdatajpa;

import info.cheremisin.petclinic.model.Owner;
import info.cheremisin.petclinic.repositories.OwnerRepository;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OwnerSDJpaServiceTest {

    public static final String SMITH = "Smith";
    public static final Long ID = 1L;
    @Mock
    OwnerRepository ownerRepository;

    @InjectMocks
    OwnerSDJpaService service;

    Owner owner;

    @BeforeEach
    void setUp() {
        owner = Owner.builder().id(ID).lastName(SMITH).build();
    }

    @Test
    void findByLastName() {
        when(ownerRepository.findByLastName(any())).thenReturn(owner);
        Owner byLastName = service.findByLastName(SMITH);

        assertEquals(SMITH, byLastName.getLastName());

        verify(ownerRepository).findByLastName(any());
    }

    @Test
    void findAll() {
        Set<Owner> owners = new HashSet<>();
        owners.add(Owner.builder().id(1L).build());
        owners.add(Owner.builder().id(2L).build());

        when(ownerRepository.findAll()).thenReturn(owners);

        Set<Owner> all = service.findAll();
        assertNotNull(all);;
        assertEquals(2, all.size());;
    }

    @Test
    void findById() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(owner));

        Owner owner = service.findById(ID);
        assertNotNull(owner);
        assertEquals(ID, owner.getId());
    }

    @Test
    void findByIdNotFound() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.empty());

        Owner owner = service.findById(ID);
        assertNull(owner);
    }

    @Test
    void save() {
        when(ownerRepository.save(any())).thenReturn(owner);
        Owner savedOwner = service.save(owner);
        assertNotNull(savedOwner);
        verify(ownerRepository).save(any());
    }

    @Test
    void delete() {
        service.delete(owner);
        verify(ownerRepository).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(ID);
        verify(ownerRepository).deleteById(anyLong());
    }
}