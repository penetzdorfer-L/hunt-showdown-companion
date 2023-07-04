package lupe.hunt.companion.chaosLoadout.weapons;

import lombok.AllArgsConstructor;
import lupe.hunt.companion.chaosLoadout.ammunitions.Ammunition;
import lupe.hunt.companion.chaosLoadout.loadout.LoadoutRequest;
import lupe.hunt.companion.chaosLoadout.loadout.data.WeaponDTO;
import lupe.hunt.companion.logic.HelperFunctions;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class WeaponService {
    private final WeaponRepository weaponRepository;
    private final HelperFunctions helperFunctions;
    private final ModelMapper mapper;

    public List<WeaponDTO> getRandomPrimary(LoadoutRequest request) {
        List<Weapon> weaponsFiltered = weaponRepository.findWeaponsByBloodlineRankLessThanEqual(request.getBloodlineRank());
        Weapon weapon = weaponsFiltered.get(helperFunctions.getRandomIndex(0, weaponsFiltered.size()));
        WeaponDTO weaponClone = mapper.map(weapon, WeaponDTO.class);
        if (weaponClone.isDualwieldable() && request.isDualWield()) {
            return flipForDualWield(weaponClone);
        }
        return new ArrayList<>(List.of(weaponClone));
    }
    public List<WeaponDTO> getRandomSecondary(LoadoutRequest request, int slotsUsed) {
        int slotsAvailable = getSlotsAvailable(request, slotsUsed);
        List<Weapon> weaponsFiltered = weaponRepository.findWeaponsByBloodlineRankLessThanEqualAndSlotsLessThanEqual(request.getBloodlineRank(), slotsAvailable);
        Weapon weapon = weaponsFiltered.get(helperFunctions.getRandomIndex(0, weaponsFiltered.size()));
        WeaponDTO weaponClone = mapper.map(weapon, WeaponDTO.class);
        if (weaponClone.isDualwieldable() && request.isDualWield() && slotsAvailable >= 2) {
            return flipForDualWield(weaponClone);
        }
        return new ArrayList<>(List.of(weaponClone));
    }

    private List<WeaponDTO> flipForDualWield(WeaponDTO weaponClone) {
        if (helperFunctions.isDualwield()) {
            return new ArrayList<>(List.of(weaponClone, weaponClone));
        }
        return new ArrayList<>(List.of(weaponClone));
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

//    public Set<Ammunition> filterOutAmmo(List<WeaponDTO> primary) {
//        return primary.stream().findFirst().get().getAmmoSet();
//    }
}
