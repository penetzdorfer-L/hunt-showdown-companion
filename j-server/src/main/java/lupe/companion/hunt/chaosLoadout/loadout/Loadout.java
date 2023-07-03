package lupe.companion.hunt.chaosLoadout.loadout;

import jakarta.persistence.*;
import lombok.*;
import lupe.companion.hunt.chaosLoadout.loadout.data.RandomAmmo;
import lupe.companion.hunt.chaosLoadout.loadout.data.RandomConsumable;
import lupe.companion.hunt.chaosLoadout.loadout.data.RandomWeapon;
import lupe.companion.hunt.chaosLoadout.tools.Tool;

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
    private List<RandomWeapon> primary;
    @ElementCollection(targetClass = RandomWeapon.class, fetch = FetchType.EAGER)
    private List<RandomWeapon> secondary;
    @OneToMany
    @JoinColumn(name = "tools_toolID", referencedColumnName = "ID")
    private Set<Tool> tools;
    @ElementCollection(targetClass = RandomConsumable.class, fetch = FetchType.EAGER)
    private List<RandomConsumable> consumables;
    @ElementCollection(targetClass = RandomAmmo.class, fetch = FetchType.EAGER)
    private List<RandomAmmo> ammoForPrimary;
    @ElementCollection(targetClass = RandomAmmo.class, fetch = FetchType.EAGER)
    private List<RandomAmmo> ammoForSecondary;
    private int totalPrice;
}
