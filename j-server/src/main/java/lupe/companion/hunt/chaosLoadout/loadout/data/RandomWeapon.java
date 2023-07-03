package lupe.companion.hunt.chaosLoadout.loadout.data;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lupe.companion.hunt.chaosLoadout.PriceAble;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class RandomWeapon implements PriceAble {
    private String name;
    private int slots;
    private int bloodlineRank;
    private int ammoSlots;
    private boolean dualwielable;
    private int price;
//    private Set<RandomAmmo> ammoSet;
}
