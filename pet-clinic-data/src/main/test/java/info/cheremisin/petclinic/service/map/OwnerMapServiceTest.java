package info.cheremisin.petclinic.service.map;

import info.cheremisin.petclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OwnerMapServiceTest {


    public static final long ID = 1L;
    public static final String SMITH = "Smith";
    private OwnerMapService ownerMapService;

    @BeforeEach
    void setUp() {
        Owner owner = new Owner();
        owner.setId(ID);
        owner.setLastName(SMITH);

        ownerMapService = new OwnerMapService(new PetTypeMapService(), new PetMapService());
        ownerMapService.save(owner);
    }

    @Test
    void findAll() {
        Set<Owner> owners = ownerMapService.findAll();
        assertNotNull(owners);
        assertEquals(1, owners.size());
    }

    @Test
    void findById() {
        Owner owner = ownerMapService.findById(ID);
        assertNotNull(owner);
    }

    @Test
    void deleteById() {
        ownerMapService.deleteById(ID);
        Owner owner = ownerMapService.findById(ID);
        assertNull(owner);
        Set<Owner> all = ownerMapService.findAll();
        assertEquals(0, all.size());
    }

    @Test
    void delete() {
        ownerMapService.delete(ownerMapService.findById(ID));
        Owner owner = ownerMapService.findById(ID);
        assertNull(owner);
        Set<Owner> all = ownerMapService.findAll();
        assertEquals(0, all.size());
    }

    @Test
    void saveExistingId() {
        Long id = 2L;
        Owner owner = new Owner();
        owner.setId(id);

        Owner saveOwner = ownerMapService.save(owner);
        assertEquals(id, saveOwner.getId());
    }

    @Test
    void saveNoId() {
        Owner saveOwner = ownerMapService.save(new Owner());
        assertNotNull(saveOwner.getId());
    }

    @Test
    void findByLastName() {
        Owner owner = ownerMapService.findByLastName(SMITH);
        assertNotNull(owner);
        assertEquals(SMITH, owner.getLastName());

    }
}