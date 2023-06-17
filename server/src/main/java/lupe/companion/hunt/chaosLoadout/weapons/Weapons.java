package lupe.companion.hunt.chaosLoadout.weapons;

import jakarta.persistence.*;
import lupe.companion.hunt.chaosLoadout.ammunitions.Ammunitions;

import java.util.Set;

@Entity
@Table(name = "weapons")
public class Weapons {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;
    private int weaponID;
    private String name;
    private int slots;
    private int bloodlineRank;
    private int ammoSlots;
    private boolean dualwielable;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "weapons_ammunitions_connection", joinColumns = {@JoinColumn(name = "weapons_ID")}, inverseJoinColumns = {@JoinColumn(name = "ammunitions_ID")})
    private Set<Ammunitions> ammoSet;

    public Weapons() {
    }

    public Weapons(long ID, int weaponID, String name, int slots, int bloodlineRank, int ammoSlots, boolean dualwielable, Set<Ammunitions> ammoSet) {
        this.ID = ID;
        this.weaponID = weaponID;
        this.name = name;
        this.slots = slots;
        this.bloodlineRank = bloodlineRank;
        this.ammoSlots = ammoSlots;
        this.dualwielable = dualwielable;
        this.ammoSet = ammoSet;
    }

    public long getID() {
        return ID;
    }

    public int getWeaponID() {
        return weaponID;
    }

    public String getName() {
        return name;
    }

    public int getSlots() {
        return slots;
    }

    public void setSlots(int slots) {
        this.slots = slots;
    }

    public int getBloodlineRank() {
        return bloodlineRank;
    }

    public void setBloodlineRank(int bloodlineRank) {
        this.bloodlineRank = bloodlineRank;
    }

    public int getAmmoSlots() {
        return ammoSlots;
    }

    public void setAmmoSlots(int specialAmmoSlots) {
        this.ammoSlots = specialAmmoSlots;
    }

    public Set<Ammunitions> getAmmoSet() {
        return ammoSet;
    }

    public boolean isDualwielable() {
        return dualwielable;
    }

    public void setDualwielable(boolean dualwielable) {
        this.dualwielable = dualwielable;
    }
}
