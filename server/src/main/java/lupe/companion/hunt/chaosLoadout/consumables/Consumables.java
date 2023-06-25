package lupe.companion.hunt.chaosLoadout.consumables;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lupe.companion.hunt.chaosLoadout.loadout.Loadouts;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "consumables")
public class Consumables {
    @Id
    private String consumableID;
    private String name;
    private int bloodlineRank;
    private int price;
    private String type;
    @ManyToOne
    @JoinColumn(name = "loadouts_id")
    private Loadouts loadouts;
}
