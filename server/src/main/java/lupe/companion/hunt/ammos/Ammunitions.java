package lupe.companion.hunt.ammos;

import jakarta.persistence.*;
import lupe.companion.hunt.weapons.Weapons;

import java.util.Set;

@Entity
@Table(name = "ammunitions")
public class Ammunitions {
    @Id
    @GeneratedValue
    private final long ID;
    private final String type;
    @ManyToOne
    @JoinColumn()
    private Set<Weapons> availableFor;
}
