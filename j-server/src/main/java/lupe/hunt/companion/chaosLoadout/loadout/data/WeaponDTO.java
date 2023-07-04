package lupe.hunt.companion.chaosLoadout.loadout.data;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lupe.hunt.companion.chaosLoadout.PriceAble;
import lupe.hunt.companion.chaosLoadout.ammunitions.Ammunition;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class WeaponDTO implements PriceAble {
    private long id;
    private String weaponID;
    private String name;
    private int slots;
    private int bloodlineRank;
    private int ammoSlots;
    private boolean dualwieldable;
    //private Set<Ammunition> ammoSet;
    private int price;
}
