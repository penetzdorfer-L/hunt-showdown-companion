package lupe.companion.hunt.chaosLoadout.loadout;

import jakarta.persistence.*;
import lupe.companion.hunt.chaosLoadout.consumables.Consumables;
import lupe.companion.hunt.chaosLoadout.tools.Tools;
import lupe.companion.hunt.chaosLoadout.weapons.Weapons;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "loadouts")
public class Loadouts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;
    @ManyToOne
    private Weapons primary;
    @ManyToOne
    private Weapons secondary;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "loadouts")
    private Set<Tools> tools;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "loadouts")
    private List<Consumables> consumables;

    public Loadouts() {
    }
    public Loadouts(long ID, Weapons primary, Weapons secondary, Set<Tools> tools, List<Consumables> consumables) {
        this.ID = ID;
        this.primary = primary;
        this.secondary = secondary;
        this.tools = tools;
        this.consumables = consumables;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public Weapons getPrimary() {
        return primary;
    }

    public void setPrimary(Weapons primary) {
        this.primary = primary;
    }

    public Weapons getSecondary() {
        return secondary;
    }

    public void setSecondary(Weapons secondary) {
        this.secondary = secondary;
    }

    public Set<Tools> getTools() {
        return tools;
    }

    public void setTools(Set<Tools> tools) {
        this.tools = tools;
    }

    public List<Consumables> getConsumables() {
        return consumables;
    }

    public void setConsumables(List<Consumables> consumables) {
        this.consumables = consumables;
    }
}
