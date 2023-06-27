package lupe.companion.hunt.chaosLoadout.loadout.data;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class RandomConsumable {
    private String name;
    private int bloodlineRank;
    private int price;
    private String type;
}
