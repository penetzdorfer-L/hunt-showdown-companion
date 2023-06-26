package lupe.companion.hunt.chaosLoadout.ammunitions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AmmunitionRepository extends JpaRepository<Ammunition, Long> {
    Ammunition findAmmunitionsByAmmoID(String ammoid);
}
