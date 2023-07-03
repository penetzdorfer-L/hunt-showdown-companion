package lupe.hunt.companion.chaosLoadout.weapons;

import lombok.AllArgsConstructor;
import lupe.companion.hunt.chaosLoadout.ammunitions.Ammunition;
import lupe.companion.hunt.chaosLoadout.loadout.LoadoutRequest;
import lupe.companion.hunt.chaosLoadout.loadout.data.RandomAmmo;
import lupe.companion.hunt.chaosLoadout.loadout.data.RandomWeapon;
import lupe.companion.hunt.logic.HelperFunctions;
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

    public List<RandomWeapon> getRandomPrimary(LoadoutRequest request) {
        RandomWeapon randomWeapon = new RandomWeapon();
        List<Weapon> weaponsFiltered = weaponRepository.findWeaponsByBloodlineRankLessThanEqual(request.getBloodlineRank());
        Weapon weapon = weaponsFiltered.get(helperFunctions.getRandomIndex(0, weaponsFiltered.size()));
        initRandomWeapon(randomWeapon, weapon);
        if (randomWeapon.isDualwielable() && request.isDualWield()) {
            return flipForDualWield(randomWeapon);
        }
        return new ArrayList<>(List.of(randomWeapon));
    }
    private void initRandomWeapon(RandomWeapon randomWeapon, Weapon weapon) {
        randomWeapon.setName(weapon.getName());
        randomWeapon.setSlots(weapon.getSlots());
        randomWeapon.setBloodlineRank(weapon.getBloodlineRank());
        randomWeapon.setAmmoSlots(weapon.getAmmoSlots());
        randomWeapon.setDualwielable(weapon.isDualwielable());
        randomWeapon.setPrice(weapon.getPrice());
//        convertAmmoToAvailableAmmo(randomWeapon, weapon);
    }

//    private void convertAmmoToAvailableAmmo(RandomWeapon randomWeapon, Weapon weapon) {
//        Set<RandomAmmo> ammoSet = new HashSet<>();
//        weapon.getAmmoSet().forEach(el -> createConversion(el, ammoSet));
//        randomWeapon.setAmmoSet(ammoSet);
//    }

    private void createConversion(Ammunition el, Set<RandomAmmo> ammoSet) {
        RandomAmmo ammo = new RandomAmmo();
        ammo.setName(el.getName());
        ammo.setPrice(el.getPrice());
        ammo.setTypeOfAmmo(el.getTypeOfAmmo());
        ammoSet.add(ammo);
    }

    public List<RandomWeapon> getRandomSecondary(LoadoutRequest request, int slotsUsed) {
        int slotsAvailable = getSlotsAvailable(request, slotsUsed);
        RandomWeapon randomWeapon = new RandomWeapon();
        List<Weapon> weaponsFiltered = weaponRepository.findWeaponsByBloodlineRankLessThanEqualAndSlotsLessThanEqual(request.getBloodlineRank(), slotsAvailable);
        Weapon weapon = weaponsFiltered.get(helperFunctions.getRandomIndex(0, weaponsFiltered.size()));
        initRandomWeapon(randomWeapon, weapon);
        if (randomWeapon.isDualwielable() && request.isDualWield()) {
            return flipForDualWield(randomWeapon);
        }
        return new ArrayList<>(List.of(randomWeapon));
    }

    private List<RandomWeapon> flipForDualWield(RandomWeapon randomWeapon) {
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

    public Set<RandomAmmo> filterOutAmmo(List<RandomWeapon> primary) {
        RandomWeapon randomWeapon = primary.stream().findFirst().get();
        Weapon weapon = weaponRepository.findWeaponByNameEqualsIgnoreCase(randomWeapon.getName());
        return weapon.getAmmoSet().isEmpty() ? new HashSet<>() : convertAmmo(weapon.getAmmoSet());
    }

    private Set<RandomAmmo> convertAmmo(Set<Ammunition> weapon) {
        Set<RandomAmmo> ammoSet = new HashSet<>();
        weapon.forEach(el -> initRandomAmmo(el, ammoSet));
        return ammoSet;
    }

    private void initRandomAmmo(Ammunition el, Set<RandomAmmo> ammoSet) {
        RandomAmmo randomAmmo = new RandomAmmo();
        randomAmmo.setName(el.getName());
        randomAmmo.setPrice(el.getPrice());
        randomAmmo.setTypeOfAmmo(el.getTypeOfAmmo());
        ammoSet.add(randomAmmo);
    }
}
