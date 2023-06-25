package lupe.companion.hunt.chaosLoadout.weapons;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lupe.companion.hunt.chaosLoadout.ammunitions.Ammunitions;
import lupe.companion.hunt.chaosLoadout.loadout.Loadouts;

import java.util.Set;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "weapons")
public class Weapons {
    @Id
    private String weaponID;
    private String name;
    private int slots;
    private int bloodlineRank;
    private int ammoSlots;
    private boolean dualwielable;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "weapons_ammunition_connection",
            joinColumns = {@JoinColumn(name = "weapons_weaponID")},
            inverseJoinColumns = {@JoinColumn(name = "ammunitions_ammoID")}
    )
    private Set<Ammunitions> ammoSet;
    @ManyToOne
    @JoinColumn(name = "loadouts_id")
    private Loadouts loadouts;
    private int price;
}
