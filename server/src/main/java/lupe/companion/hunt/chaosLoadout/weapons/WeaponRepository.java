package lupe.companion.hunt.chaosLoadout.weapons;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeaponRepository extends JpaRepository<Weapon, Long> {
    Weapon findWeaponsByWeaponID(String parameter);
    List<Weapon> findWeaponsBySlotsEquals(int parameter);
}
