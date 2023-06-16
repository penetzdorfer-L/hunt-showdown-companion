package lupe.companion.hunt.chaosLoadout.tools;

import jakarta.persistence.*;
import lupe.companion.hunt.chaosLoadout.loadout.Loadouts;

@Entity
@Table(name = "tools")
public class Tools {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;

    private String name;
    private int bloodlineRank;
    @ManyToOne
    @JoinColumn(name = "loadouts_id")
    private Loadouts loadouts;

    public Tools() {
    }

    public Tools(long ID, String name, int bloodlineRank, Loadouts loadouts) {
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
