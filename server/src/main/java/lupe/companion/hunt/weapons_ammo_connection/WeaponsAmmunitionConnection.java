package lupe.companion.hunt.weapons_ammo_connection;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Generated;

@Entity
@Table(name = "weapons_ammunition_connection")
public class WeaponsAmmunitionConnection {
    @Id
    @GeneratedValue
    private long ID;
    private int weaponID;
    private String name;
    private int slots;
    private int bloodlineRank;
    private int ammoSlots;
    private String ammo;

    public WeaponsAmmunitionConnection() {
    }

    public WeaponsAmmunitionConnection(long ID, int weaponID, String name, int slots, int bloodlineRank, int ammoSlots, String ammo) {
        this.ID = ID;
        this.weaponID = weaponID;
        this.name = name;
        this.slots = slots;
        this.bloodlineRank = bloodlineRank;
        this.ammoSlots = ammoSlots;
        this.ammo = ammo;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public int getWeaponID() {
        return weaponID;
    }

    public void setWeaponID(int weaponID) {
        this.weaponID = weaponID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public void setAmmoSlots(int ammoSlots) {
        this.ammoSlots = ammoSlots;
    }

    public String getAmmo() {
        return ammo;
    }

    public void setAmmo(String ammo) {
        this.ammo = ammo;
    }
}
