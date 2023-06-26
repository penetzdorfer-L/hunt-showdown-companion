package lupe.companion.hunt.chaosLoadout.weapons;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class WeaponService {
    private final WeaponRepository weaponRepository;

    public List<Weapon> getRandomPrimary(int bloodlineRank, boolean dualWield, boolean quarterMaster, boolean specialAmmo) {
        return null;
    }

    public List<Weapon> getRandomSecondary(int bloodlineRank, boolean dualWield, boolean quarterMaster, boolean specialAmmo, int slotsUsed) {
        return null;
    }
}
