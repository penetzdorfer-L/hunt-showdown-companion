package lupe.hunt.companion.chaosLoadout.loadout.data;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lupe.hunt.companion.chaosLoadout.PriceAble;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class ConsumableDTO implements PriceAble {
    private long id;
    private String consumableID;
    private String name;
    private int bloodlineRank;
    private int price;
    private String type;
}
