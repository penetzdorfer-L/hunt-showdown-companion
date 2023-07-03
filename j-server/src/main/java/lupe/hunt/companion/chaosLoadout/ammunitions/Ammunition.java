package lupe.hunt.companion.chaosLoadout.ammunitions;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lupe.companion.hunt.chaosLoadout.PriceAble;
import lupe.companion.hunt.chaosLoadout.weapons.Weapon;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ammunitions")
public class Ammunition implements PriceAble {
    @Id
    private String ammoID;
    private String name;
    private String typeOfAmmo;
    @ManyToMany(mappedBy = "ammoSet")
    @JsonIgnore
    private List<Weapon> availableFor;
    private int price;
}
