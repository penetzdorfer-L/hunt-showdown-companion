package lupe.hunt.companion.chaosLoadout.weapons;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeaponRepository extends JpaRepository<Weapon, Long> {
    Weapon findWeaponsByWeaponID(String id);
    Weapon findWeaponByNameEqualsIgnoreCase(String weaponName);
    List<Weapon> findWeaponsBySlotsEquals(int slots);
    List<Weapon> findWeaponsByBloodlineRankLessThanEqual(int bloodline);
    List<Weapon> findWeaponsByBloodlineRankLessThanEqualAndSlotsLessThanEqual(int bloodline, int slots);
}
