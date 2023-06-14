package lupe.companion.hunt.ammos;

import jakarta.persistence.*;
import lupe.companion.hunt.weapons.Weapons;

import java.lang.invoke.TypeDescriptor;
import java.util.List;

@Entity
@Table(name = "ammunitions")
public class Ammunitions {
    @Id
    @GeneratedValue
    private long ID;
    @ManyToMany(mappedBy = "ammoSet")
    private List<Weapons> availableFor;
    private String type;

    public Ammunitions() {
    }
    public Ammunitions(long id, List<Weapons> availableFor, String type) {
        ID = id;
        this.availableFor = availableFor;
        this.type = type;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public List<Weapons> getAvailableFor() {
        return availableFor;
    }

    public void setAvailableFor(List<Weapons> availableFor) {
        this.availableFor = availableFor;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
