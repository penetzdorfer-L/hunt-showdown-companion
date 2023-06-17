package lupe.companion.hunt.chaosLoadout.weapons;

import jakarta.persistence.*;
import lombok.*;
import lupe.companion.hunt.chaosLoadout.ammunitions.Ammunitions;

import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "weapons")
public class Weapons {
    @Id
    private int weaponID;
    private String name;
    private int slots;
    private int bloodlineRank;
    private int ammoSlots;
    private boolean dualwielable;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "weapons_ammunitions_connection",
            joinColumns = {@JoinColumn(name = "weapons_weaponID")},
            inverseJoinColumns = {@JoinColumn(name = "ammunitions_ammoID")}
    )
    private Set<Ammunitions> ammoSet;
    private int price;
}
