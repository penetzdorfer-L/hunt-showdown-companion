package lupe.hunt.companion.chaosLoadout.tools;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "tools")
public class Tool implements PriceAble {
    @Id

    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String toolID;
    private String name;
    private int bloodlineRank;
    private int price;
    private String type;
    @ManyToOne
    @JsonIgnore
    private Loadout loadout;
}
