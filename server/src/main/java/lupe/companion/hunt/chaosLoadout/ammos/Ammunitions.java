package lupe.companion.hunt.chaosLoadout.ammos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lupe.companion.hunt.chaosLoadout.weapons.Weapons;

import java.util.List;

@Entity
@Table(name = "ammunitions")
public class Ammunitions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;
    private String name;
    @ManyToMany(mappedBy = "ammoSet")
    @JsonIgnore
    private List<Weapons> availableFor;

    public Ammunitions() {
    }

    public Ammunitions(long ID, String name, List<Weapons> availableFor) {
        this.ID = ID;
        this.name = name;
        this.availableFor = availableFor;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Weapons> getAvailableFor() {
        return availableFor;
    }

    public void setAvailableFor(List<Weapons> availableFor) {
        this.availableFor = availableFor;
    }
}
