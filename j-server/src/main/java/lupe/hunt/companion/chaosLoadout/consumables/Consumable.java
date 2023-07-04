package lupe.hunt.companion.chaosLoadout.consumables;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lupe.hunt.companion.chaosLoadout.PriceAble;
import lupe.hunt.companion.chaosLoadout.loadout.Loadout;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "consumables")
@Embeddable
public class Consumable implements PriceAble {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String consumableID;
    private String name;
    private int bloodlineRank;
    private int price;
    private String type;
    @ManyToOne
    private Loadout loadout;
}
