package lupe.companion.hunt.weapons;

import jakarta.persistence.*;
import lupe.companion.hunt.ammos.Ammunitions;

import java.util.Set;

@Entity
@Table(name = "weapons")
public class Weapons {
    @Id
    @GeneratedValue
    private long ID;
    private int weaponID;
    private String name;
    private int slots;
    private int bloodlineRank;
    private int ammoSlots;
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "weapons_ammunitions_connection",
            joinColumns = {@JoinColumn(name = "weapons_weaponID")},
            inverseJoinColumns = {@JoinColumn(name = "ammunitions_availableFor")}
    )
    private Set<Ammunitions> ammoSet;

    public Weapons() {
    }

    public Weapons(long ID, int weaponID, String name, int slots, int bloodlineRank, int ammoSlots, Set<Ammunitions> ammoSet) {
        this.ID = ID;
        this.weaponID = weaponID;
        this.name = name;
        this.slots = slots;
        this.bloodlineRank = bloodlineRank;
        this.ammoSlots = ammoSlots;
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
}
