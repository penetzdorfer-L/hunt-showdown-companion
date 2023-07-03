package lupe.hunt.companion.chaosLoadout.weapons;

import lombok.AllArgsConstructor;
import lupe.hunt.companion.chaosLoadout.loadout.LoadoutRequest;
import lupe.hunt.companion.logic.HelperFunctions;
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
        Weapon weapon = weaponsFiltered.get(helperFunctions.getRandomIndex(0, weaponsFiltered.size()));
        if (weapon.isDualwieldable() && request.isDualWield()) {
            return flipForDualWield(weapon);
        }
        return new ArrayList<>(List.of(weapon));
    }
    public List<Weapon> getRandomSecondary(LoadoutRequest request, int slotsUsed) {
        int slotsAvailable = getSlotsAvailable(request, slotsUsed);
        List<Weapon> weaponsFiltered = weaponRepository.findWeaponsByBloodlineRankLessThanEqualAndSlotsLessThanEqual(request.getBloodlineRank(), slotsAvailable);
        Weapon weapon = weaponsFiltered.get(helperFunctions.getRandomIndex(0, weaponsFiltered.size()));
        if (weapon.isDualwieldable() && request.isDualWield() && slotsAvailable >= 2) {
            return flipForDualWield(weapon);
        }
        return new ArrayList<>(List.of(weapon));
    }

    private List<Weapon> flipForDualWield(Weapon weapon) {
        if (helperFunctions.isDualwield()) {
            List<Weapon> weaponList = new ArrayList<>();
            weaponList.add(weapon);
            weaponList.add(weapon);
            return weaponList;
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
