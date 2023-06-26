package lupe.companion.hunt.chaosLoadout.loadout;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lupe.companion.hunt.chaosLoadout.consumables.Consumable;
import lupe.companion.hunt.chaosLoadout.tools.Tool;
import lupe.companion.hunt.chaosLoadout.weapons.Weapon;

import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "loadouts")
public class Loadout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "loadout")
    private List<Weapon> primary;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "loadout")
    private List<Weapon> secondary;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "loadout")
    private Set<Tool> tools;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "loadout")
    private List<Consumable> consumables;
    private int totalPrice;
}
