package lupe.companion.hunt.weapons;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeaponsRepository extends JpaRepository<Weapons, Long> {
}
