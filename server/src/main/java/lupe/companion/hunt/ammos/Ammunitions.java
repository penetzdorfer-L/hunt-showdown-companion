package lupe.companion.hunt.ammos;

import jakarta.persistence.*;

@Entity
@Table(name = "ammunitions")
public class Ammunitions {
    @Id
    @GeneratedValue
    private final long ID;
    private final String type;

    public Ammunitions(long id, String type) {
        ID = id;
        this.type = type;
    }
    public long getID() {
        return ID;
    }
    public String getType() {
        return type;
    }
}
