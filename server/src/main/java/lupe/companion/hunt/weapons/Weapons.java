package lupe.companion.hunt.weapons;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lupe.companion.hunt.ammos.Ammunitions;

import java.util.Set;

@Entity
@Table(name = "weapons")
public class Weapons {
    @Id
    @GeneratedValue
    private final long ID;
    private final int weaponID;
    private final String name;
    private int slots;
    private int bloodlineRank;
    private int specialAmmoSlots;
    @OneToMany(mappedBy = "ammunitions")
    private final Set<Ammunitions> ammoSet;

    public Weapons(long ID, int weaponID, String name, int slots, int bloodlineRank, int specialAmmoSlots, Set<Ammunitions> ammoSet) {
        this.ID = ID;
        this.weaponID = weaponID;
        this.name = name;
        this.slots = slots;
        this.bloodlineRank = bloodlineRank;
        this.specialAmmoSlots = specialAmmoSlots;
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

    public int getSpecialAmmoSlots() {
        return specialAmmoSlots;
    }

    public void setSpecialAmmoSlots(int specialAmmoSlots) {
        this.specialAmmoSlots = specialAmmoSlots;
    }

    public Set<Ammunitions> getAmmoSet() {
        return ammoSet;
    }
}
