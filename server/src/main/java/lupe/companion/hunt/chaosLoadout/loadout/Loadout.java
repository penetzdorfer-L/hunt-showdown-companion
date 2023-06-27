package lupe.companion.hunt.chaosLoadout.loadout;

import jakarta.persistence.*;
import lombok.*;
import lupe.companion.hunt.chaosLoadout.consumables.Consumable;
import lupe.companion.hunt.chaosLoadout.tools.Tool;
import lupe.companion.hunt.chaosLoadout.weapons.Weapon;

import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "loadouts")
@AllArgsConstructor
@NoArgsConstructor
public class Loadout {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long ID;
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "loadout")
    @OneToMany
    private List<Weapon> primary;
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "loadout")
    @OneToMany
    private List<Weapon> secondary;
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "loadout")
    @OneToMany
    private Set<Tool> tools;
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "loadout")
    @OneToMany
    private List<Consumable> consumables;
    private int totalPrice;
}
