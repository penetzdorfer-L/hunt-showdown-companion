package lupe.hunt.companion.chaosLoadout.loadout;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lupe.hunt.companion.chaosLoadout.ammunitions.Ammunition;
import lupe.hunt.companion.chaosLoadout.consumables.Consumable;
import lupe.hunt.companion.chaosLoadout.loadout.data.RandomAmmo;
import lupe.hunt.companion.chaosLoadout.loadout.data.RandomConsumable;
import lupe.hunt.companion.chaosLoadout.loadout.data.RandomWeapon;
import lupe.hunt.companion.chaosLoadout.tools.Tool;
import lupe.hunt.companion.chaosLoadout.weapons.Weapon;

import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "loadouts")
@AllArgsConstructor
@NoArgsConstructor
public class Loadout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;
    @ElementCollection(targetClass = RandomWeapon.class, fetch = FetchType.EAGER)
    private List<Weapon> primary;
    @ElementCollection(targetClass = RandomWeapon.class, fetch = FetchType.EAGER)
    private List<Weapon> secondary;
    @OneToMany
    @JoinColumn(name = "tools_toolID", referencedColumnName = "ID")
    private Set<Tool> tools;
    @ElementCollection(targetClass = RandomConsumable.class, fetch = FetchType.EAGER)
    private List<Consumable> consumables;
    @ElementCollection(targetClass = RandomAmmo.class, fetch = FetchType.EAGER)
    private List<Ammunition> ammoForPrimary;
    @ElementCollection(targetClass = RandomAmmo.class, fetch = FetchType.EAGER)
    private List<Ammunition> ammoForSecondary;
    private int totalPrice;
}
