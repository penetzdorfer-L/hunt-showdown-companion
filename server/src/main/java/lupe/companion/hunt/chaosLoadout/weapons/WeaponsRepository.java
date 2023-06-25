package lupe.companion.hunt.chaosLoadout.weapons;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeaponsRepository extends JpaRepository<Weapons, Long> {
    List<Weapons> findWeaponsByNameIsContainingIgnoreCase(String parameter);
}
