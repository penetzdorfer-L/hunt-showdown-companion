package lupe.companion.hunt.chaosLoadout.consumables;

import jakarta.persistence.*;
import lupe.companion.hunt.chaosLoadout.loadout.Loadouts;

@Entity
@Table(name = "consumables")
public class Consumables {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;
    private String name;
    private int bloodlineRank;
    @ManyToOne
    @JoinColumn(name = "loadouts_id")
    private Loadouts loadouts;

    public Consumables() {
    }

    public Consumables(long ID, String name, int bloodlineRank, Loadouts loadouts) {
        this.ID = ID;
        this.name = name;
        this.bloodlineRank = bloodlineRank;
        this.loadouts = loadouts;
    }

    public Loadouts getLoadouts() {
        return loadouts;
    }

    public void setLoadouts(Loadouts loadout) {
        this.loadouts = loadout;
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

    public int getBloodlineRank() {
        return bloodlineRank;
    }

    public void setBloodlineRank(int bloodlineRank) {
        this.bloodlineRank = bloodlineRank;
    }
}
