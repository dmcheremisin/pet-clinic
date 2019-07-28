package info.cheremisin.petclinic.model;

/**
 * Created by Dmitrii on 28.07.2019.
 */
public class Speciality extends BaseEntity {

    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
