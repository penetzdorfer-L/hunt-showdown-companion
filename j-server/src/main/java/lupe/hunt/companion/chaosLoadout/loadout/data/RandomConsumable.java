package lupe.hunt.companion.chaosLoadout.loadout.data;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lupe.companion.hunt.chaosLoadout.PriceAble;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class RandomConsumable implements PriceAble {
    private String name;
    private int bloodlineRank;
    private int price;
    private String type;
}
