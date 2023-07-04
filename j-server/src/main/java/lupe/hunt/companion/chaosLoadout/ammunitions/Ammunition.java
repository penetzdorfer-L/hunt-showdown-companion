package lupe.hunt.companion.chaosLoadout.ammunitions;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lupe.hunt.companion.chaosLoadout.PriceAble;
import lupe.hunt.companion.chaosLoadout.loadout.Loadout;
import lupe.hunt.companion.chaosLoadout.weapons.Weapon;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ammunitions")
@Embeddable
public class Ammunition implements PriceAble {
    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String ammoID;
    private String name;
    private String typeOfAmmo;
    @ManyToMany(mappedBy = "ammoSet")
    @JsonIgnore
    private List<Weapon> availableFor;
    private int price;
    @ManyToOne
    private Loadout primary;
    @ManyToOne
    private Loadout secondary;
}
