package lupe.hunt.companion.chaosLoadout.ammunitions;

import lombok.AllArgsConstructor;
import lupe.companion.hunt.chaosLoadout.loadout.LoadoutRequest;
import lupe.companion.hunt.chaosLoadout.loadout.data.RandomAmmo;
import lupe.companion.hunt.chaosLoadout.loadout.data.RandomWeapon;
import lupe.companion.hunt.logic.HelperFunctions;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class AmmunitionService {
    private final AmmunitionRepository ammunitionRepository;
    private final HelperFunctions helperFunctions;
    public List<RandomAmmo> getRandomAmmo(List<RandomWeapon> weaponList, LoadoutRequest request) {
        return new ArrayList<>();
        //TODO: work with repository weapon to get desired lists
//        List<RandomAmmo> randomAmmos = new ArrayList<>();
//        List<RandomAmmo> collectedAmmosFromWeapon = weaponList.stream().findFirst().get().getAmmoSet().stream().toList();
//        return delegateForDualSlotWeapon(randomAmmos, collectedAmmosFromWeapon, weaponList, request);
    }
    private List<RandomAmmo> delegateForDualSlotWeapon(List<RandomAmmo> randomAmmos, List<RandomAmmo> collectedAmmosFromWeapon, List<RandomWeapon> weaponList, LoadoutRequest request) {
        RandomWeapon weapon = weaponList.stream().findFirst().get();
        if (weapon.getAmmoSlots() > 1) {
            return getFilledDualSlotWeapon(randomAmmos, collectedAmmosFromWeapon ,request);
        }
        return getFilledSingleSlotWeapon(randomAmmos, collectedAmmosFromWeapon, request, weapon);
    }

    private List<RandomAmmo> getFilledSingleSlotWeapon(List<RandomAmmo> randomAmmoList, List<RandomAmmo> collectedAmmosFromWeapon, LoadoutRequest request, RandomWeapon weapon) {
        if (!(request.isSpecialAmmo())) {
            return fillWithStandardAmmo(randomAmmoList, collectedAmmosFromWeapon);
        }
        return delegateIfSpecial(randomAmmoList, collectedAmmosFromWeapon, weapon);
    }

    private List<RandomAmmo> delegateIfSpecial(List<RandomAmmo> randomAmmoList, List<RandomAmmo> collectedAmmosFromWeapon, RandomWeapon weapon) {
        if (!(helperFunctions.isSpecialAmmo())) {
            return fillWithStandardAmmo(randomAmmoList, collectedAmmosFromWeapon);
        }
        return filterSpecialAmmo(randomAmmoList, collectedAmmosFromWeapon, weapon);
    }
    private List<RandomAmmo> filterSpecialAmmo(List<RandomAmmo> randomAmmoList, List<RandomAmmo> collectedAmmosFromWeapon, RandomWeapon weapon) {
        int ammoSlots = weapon.getAmmoSlots();
        List<RandomAmmo> filteredAmmosFromWeapon = new ArrayList<>();
        for (int i = 0; i < ammoSlots; i++) {
            RandomAmmo randomAmmo = collectedAmmosFromWeapon.get(helperFunctions.getRandomIndex(0, collectedAmmosFromWeapon.size()));
            filteredAmmosFromWeapon.add(randomAmmo);
        }
        return fillWithSpecialAmmo(randomAmmoList, collectedAmmosFromWeapon);
    }
    private List<RandomAmmo> fillWithStandardAmmo(List<RandomAmmo> randomAmmoList, List<RandomAmmo> collectedAmmosFromWeapon) {
        List<RandomAmmo> standard = collectedAmmosFromWeapon.stream().filter(el -> el.getTypeOfAmmo().equalsIgnoreCase("standard")).toList();
        return fillListWithAmmos(randomAmmoList, standard);
    }
    private List<RandomAmmo> fillWithSpecialAmmo(List<RandomAmmo> randomAmmoList, List<RandomAmmo> filteredAmmo) {
        return fillListWithAmmos(randomAmmoList, filteredAmmo);
    }
    private List<RandomAmmo> fillListWithAmmos(List<RandomAmmo> randomAmmoList, List<RandomAmmo> filteredAmmo) {
        for (RandomAmmo ammunition : filteredAmmo) {
            RandomAmmo randomAmmo = new RandomAmmo();
            randomAmmo.setName(ammunition.getName());
            randomAmmo.setPrice(ammunition.getPrice());
            randomAmmo.setTypeOfAmmo(ammunition.getTypeOfAmmo());
            randomAmmoList.add(randomAmmo);
        }
        return randomAmmoList;
    }
    private List<RandomAmmo> getFilledDualSlotWeapon(List<RandomAmmo> randomAmmoList, List<RandomAmmo> collectedAmmos, LoadoutRequest request) {
        // TODO: find a way to implement a generic way to filter out different kind ammo(long, compact, shotgun, medium)
        return fillWithStandardAmmo(randomAmmoList, collectedAmmos);
    }
}
