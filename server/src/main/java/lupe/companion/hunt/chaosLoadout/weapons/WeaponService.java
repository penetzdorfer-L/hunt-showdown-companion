package lupe.companion.hunt.chaosLoadout.weapons;

import lombok.AllArgsConstructor;
import lupe.companion.hunt.chaosLoadout.loadout.LoadoutRequest;
import lupe.companion.hunt.logic.HelperFunctions;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class WeaponService {
    private final WeaponRepository weaponRepository;
    private final HelperFunctions helperFunctions;

    public List<Weapon> getRandomPrimary(LoadoutRequest request) {
        List<Weapon> weaponsLimitedByBloodline = weaponRepository.findWeaponsByBloodlineRankBefore(request.getBloodlineRank() + 1);
        Weapon randomWeapon = weaponsLimitedByBloodline.get(helperFunctions.getRandomInteger(0, weaponsLimitedByBloodline.size() + 1));
        if (randomWeapon.isDualwielable() && request.isDualWield()) {
            return flipForDualWield(randomWeapon);
        }
        return new ArrayList<>(List.of(randomWeapon));
    }
    private List<Weapon> flipForDualWield(Weapon randomWeapon) {
        if (helperFunctions.isDualwield()) {
            return new ArrayList<>(List.of(randomWeapon, randomWeapon));
        }
        return new ArrayList<>(List.of(randomWeapon));
    }

    public List<Weapon> getRandomSecondary(LoadoutRequest request, int slotsUsed) {
        return null;
    }
}
