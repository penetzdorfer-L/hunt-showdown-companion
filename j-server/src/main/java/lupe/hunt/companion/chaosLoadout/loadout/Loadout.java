package lupe.hunt.companion.chaosLoadout.loadout;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lupe.hunt.companion.chaosLoadout.ammunitions.Ammunition;
import lupe.hunt.companion.chaosLoadout.consumables.Consumable;
import lupe.hunt.companion.chaosLoadout.loadout.data.AmmunitionDTO;
import lupe.hunt.companion.chaosLoadout.loadout.data.ConsumableDTO;
import lupe.hunt.companion.chaosLoadout.loadout.data.WeaponDTO;
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
    @ElementCollection(targetClass = WeaponDTO.class, fetch = FetchType.EAGER)
    private List<WeaponDTO> primary;
    @ElementCollection(targetClass = WeaponDTO.class, fetch = FetchType.EAGER)
    private List<WeaponDTO> secondary;
    @OneToMany
    @JoinColumn(name = "tools_toolID", referencedColumnName = "ID")
    private Set<Tool> tools;
    @ElementCollection(targetClass = ConsumableDTO.class, fetch = FetchType.EAGER)
    private List<ConsumableDTO> consumables;
    @ElementCollection(targetClass = AmmunitionDTO.class, fetch = FetchType.EAGER)
    private List<AmmunitionDTO> ammoForPrimary;
    @ElementCollection(targetClass = AmmunitionDTO.class, fetch = FetchType.EAGER)
    private List<AmmunitionDTO> ammoForSecondary;
    private int totalPrice;
}
