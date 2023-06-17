package lupe.companion.hunt.chaosLoadout.tools;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lupe.companion.hunt.chaosLoadout.loadout.Loadouts;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tools")
public class Tools {
    @Id
    private int toolID;
    private String name;
    private int bloodlineRank;
    private int price;
    @ManyToOne
    @JoinColumn(name = "loadouts_id")
    private Loadouts loadouts;

}
