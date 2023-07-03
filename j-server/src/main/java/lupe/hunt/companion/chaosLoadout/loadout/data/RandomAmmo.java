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
public class RandomAmmo implements PriceAble {
    private String name;
    private String typeOfAmmo;
    private int price;
}
