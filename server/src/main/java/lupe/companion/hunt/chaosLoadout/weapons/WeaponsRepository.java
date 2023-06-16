package lupe.companion.hunt.chaosLoadout.weapons;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeaponsRepository extends JpaRepository<Weapons, Long> {
}
