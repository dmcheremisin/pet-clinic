package info.cheremisin.petclinic.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created by Dmitrii on 28.07.2019.
 */

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "visits")
public class Visit extends BaseEntity {

    @Builder
    public Visit(Long id) {
        super(id);
    }

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;

}
