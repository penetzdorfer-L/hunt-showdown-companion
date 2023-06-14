package lupe.companion.hunt.weapons_ammo_connection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeaponAmmunitionRepository extends JpaRepository<WeaponsAmmunitionConnection, Long> {
}
