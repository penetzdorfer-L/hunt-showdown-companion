package lupe.companion.hunt.chaosLoadout.ammunitions;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lupe.companion.hunt.chaosLoadout.weapons.Weapons;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ammunitions")
public class Ammunitions {
    @Id
    private String ammoID;
    private String name;
    @ManyToMany(mappedBy = "ammoSet")
    @JsonIgnore
    private List<Weapons> availableFor;
    private int price;
}
