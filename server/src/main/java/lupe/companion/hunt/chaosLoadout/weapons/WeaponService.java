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
        List<Weapon> weaponsFiltered = weaponRepository.findWeaponsByBloodlineRankLessThanEqual(request.getBloodlineRank());
        Weapon randomWeapon = weaponsFiltered.get(helperFunctions.getRandomIndex(0, weaponsFiltered.size()));
        if (randomWeapon.isDualwielable() && request.isDualWield()) {
            return flipForDualWield(randomWeapon);
        }
        return new ArrayList<>(List.of(randomWeapon));
    }
    public List<Weapon> getRandomSecondary(LoadoutRequest request, int slotsUsed) {
        int slotsAvailable = getSlotsAvailable(request, slotsUsed);
        List<Weapon> weaponsFiltered = weaponRepository.findWeaponsByBloodlineRankLessThanEqualAndSlotsLessThanEqual(request.getBloodlineRank(), slotsAvailable);
        Weapon randomWeapon = weaponsFiltered.get(helperFunctions.getRandomIndex(0, weaponsFiltered.size()));
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

    private static int getSlotsAvailable(LoadoutRequest request, int slotsUsed) {
        int hasQuarterMaster = 5;
        int noQuarterMaster = 4;
        int slotsAvailable;
        if (request.isQuarterMaster()) {
            slotsAvailable = hasQuarterMaster - slotsUsed;
            return slotsAvailable;
        }
        slotsAvailable = noQuarterMaster - slotsUsed;
        return slotsAvailable;
    }
}
