package lupe.companion.hunt.chaosLoadout.loadout.data;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class RandomWeapon {
    private String name;
    private int slots;
    private int bloodlineRank;
    private int ammoSlots;
    private boolean dualwielable;
    private int price;
//    private Set<RandomAmmo> ammoSet;
}
