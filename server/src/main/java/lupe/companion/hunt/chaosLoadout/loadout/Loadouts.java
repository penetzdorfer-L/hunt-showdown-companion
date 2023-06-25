package lupe.companion.hunt.chaosLoadout.loadout;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lupe.companion.hunt.chaosLoadout.consumables.Consumables;
import lupe.companion.hunt.chaosLoadout.tools.Tools;
import lupe.companion.hunt.chaosLoadout.weapons.Weapons;

import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "loadouts")
public class Loadouts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "loadouts")
    private List<Weapons> primary;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "loadouts")
    private List<Weapons> secondary;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "loadouts")
    private Set<Tools> tools;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "loadouts")
    private List<Consumables> consumables;
    private int totalPrice;
}
