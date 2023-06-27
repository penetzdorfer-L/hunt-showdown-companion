package lupe.companion.hunt.chaosLoadout.loadout;

import jakarta.persistence.*;
import lombok.*;
import lupe.companion.hunt.chaosLoadout.consumables.Consumable;
import lupe.companion.hunt.chaosLoadout.tools.Tool;
import lupe.companion.hunt.chaosLoadout.weapons.Weapon;

import java.util.List;
import java.util.Map;
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
    @JoinColumn(name = "primaryWeapon")
    private List<Weapon> primary;
    @OneToMany
    @JoinColumn(name = "secondaryWeapon")
    private List<Weapon> secondary;
    @OneToMany
    @JoinColumn(name = "tools_toolID", referencedColumnName = "ID")
    private Set<Tool> tools;
    @OneToMany
    @JoinColumn(name = "consumables_consumableID", referencedColumnName = "ID")
    private List<Consumable> consumables;
    private int totalPrice;
}
