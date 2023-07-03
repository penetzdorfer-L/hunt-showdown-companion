package lupe.hunt.companion.chaosLoadout.loadout;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lupe.hunt.companion.chaosLoadout.ammunitions.Ammunition;
import lupe.hunt.companion.chaosLoadout.consumables.Consumable;
import lupe.hunt.companion.chaosLoadout.loadout.data.RandomConsumable;
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
    @OneToMany
    @JoinColumn(name = "weapons_primary")
//    @ElementCollection(targetClass = Weapon.class, fetch = FetchType.EAGER)
    private List<Weapon> primary;
    @OneToMany
    @JoinColumn(name = "weapons_secondary")
//    @ElementCollection(targetClass = Weapon.class, fetch = FetchType.EAGER)
    private List<Weapon> secondary;
    @OneToMany
    @JoinColumn(name = "tools_toolID", referencedColumnName = "ID")
    private Set<Tool> tools;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "consumables_loadout")
//    @ElementCollection(targetClass = RandomConsumable.class, fetch = FetchType.EAGER)
    private List<Consumable> consumables;
    @OneToMany
    @JoinColumn(name = "ammunitions_primary")
//    @ElementCollection(targetClass = Ammunition.class, fetch = FetchType.EAGER)
    private List<Ammunition> ammoForPrimary;
    @OneToMany
    @JoinColumn(name = "ammunitions_secondary")
//    @ElementCollection(targetClass = Ammunition.class, fetch = FetchType.EAGER)
    private List<Ammunition> ammoForSecondary;
    private int totalPrice;
}
