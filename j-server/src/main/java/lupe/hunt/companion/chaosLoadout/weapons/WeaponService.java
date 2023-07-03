package lupe.hunt.companion.chaosLoadout.weapons;

import lombok.AllArgsConstructor;
import lupe.hunt.companion.chaosLoadout.ammunitions.Ammunition;
import lupe.hunt.companion.chaosLoadout.loadout.LoadoutRequest;
import lupe.hunt.companion.chaosLoadout.loadout.data.RandomAmmo;
import lupe.hunt.companion.chaosLoadout.loadout.data.RandomWeapon;
import lupe.hunt.companion.logic.HelperFunctions;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class WeaponService {
    private final WeaponRepository weaponRepository;
    private final HelperFunctions helperFunctions;

    public List<Weapon> getRandomPrimary(LoadoutRequest request) {
        List<Weapon> weaponsFiltered = weaponRepository.findWeaponsByBloodlineRankLessThanEqual(request.getBloodlineRank());
        Weapon weapon = weaponsFiltered.get(helperFunctions.getRandomIndex(0, weaponsFiltered.size()));
        if (weapon.isDualwielable() && request.isDualWield()) {
            return flipForDualWield(weapon);
        }
        return new ArrayList<>(List.of(weapon));
    }
    public List<Weapon> getRandomSecondary(LoadoutRequest request, int slotsUsed) {
        int slotsAvailable = getSlotsAvailable(request, slotsUsed);
        List<Weapon> weaponsFiltered = weaponRepository.findWeaponsByBloodlineRankLessThanEqualAndSlotsLessThanEqual(request.getBloodlineRank(), slotsAvailable);
        Weapon weapon = weaponsFiltered.get(helperFunctions.getRandomIndex(0, weaponsFiltered.size()));
        if (weapon.isDualwielable() && request.isDualWield() && slotsAvailable >= 2) {
            return flipForDualWield(weapon);
        }
        return new ArrayList<>(List.of(weapon));
    }

    private List<Weapon> flipForDualWield(Weapon weapon) {
        if (helperFunctions.isDualwield()) {
            return new ArrayList<>(List.of(weapon, weapon));
        }
        return new ArrayList<>(List.of(weapon));
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
