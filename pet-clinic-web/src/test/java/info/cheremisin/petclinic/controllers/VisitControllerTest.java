package info.cheremisin.petclinic.controllers;

import info.cheremisin.petclinic.model.Owner;
import info.cheremisin.petclinic.model.Pet;
import info.cheremisin.petclinic.model.PetType;
import info.cheremisin.petclinic.model.Visit;
import info.cheremisin.petclinic.service.PetService;
import info.cheremisin.petclinic.service.VisitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.HashSet;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class VisitControllerTest {

    @Mock
    PetService petService;

    @Mock
    VisitService visitService;

    @InjectMocks
    VisitController visitController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        Long petId = 1L;
        Long ownerId = 1L;
        when(petService.findById(anyLong()))
                .thenReturn(
                        Pet.builder()
                            .id(petId)
                            .birthDate(LocalDate.of(2018,11,13))
                            .name("Cutie")
                            .visits(new HashSet<>())
                            .owner(Owner.builder().id(ownerId).lastName("Doe").firstName("Joe").build())
                            .petType(PetType.builder().name("Dog").build())
                            .build()
                );

        mockMvc = MockMvcBuilders
                .standaloneSetup(visitController)
                .build();
    }

    @Test
    void initNewVisitForm() throws Exception {
        mockMvc.perform(get("/owners/1/pets/2/visits/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("pets/createOrUpdateVisitForm"))
        ;
    }

    @Test
    void processNewVisitForm() throws Exception {
        Visit visit = Visit.builder().id(1L).build();
        when(visitService.save(any())).thenReturn(visit);

        mockMvc.perform(post("/owners/1/pets/2/visits/new")
                            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                            .param("date","2018-11-11")
                            .param("description", "description"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"))
                .andExpect(model().attributeExists("visit"))
        ;
    }
}