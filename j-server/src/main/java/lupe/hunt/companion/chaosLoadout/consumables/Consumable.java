package lupe.hunt.companion.chaosLoadout.consumables;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lupe.companion.hunt.chaosLoadout.PriceAble;
import lupe.companion.hunt.chaosLoadout.loadout.Loadout;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "consumables")
public class Consumable implements PriceAble {
    @Id
    private String consumableID;
    private String name;
    private int bloodlineRank;
    private int price;
    private String type;
}
