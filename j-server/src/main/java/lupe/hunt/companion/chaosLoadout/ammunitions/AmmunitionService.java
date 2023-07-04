package lupe.hunt.companion.chaosLoadout.ammunitions;

import lombok.AllArgsConstructor;
import lupe.hunt.companion.chaosLoadout.loadout.LoadoutRequest;
import lupe.hunt.companion.chaosLoadout.loadout.data.AmmunitionDTO;
import lupe.hunt.companion.chaosLoadout.loadout.data.WeaponDTO;
import lupe.hunt.companion.chaosLoadout.weapons.Weapon;
import lupe.hunt.companion.logic.HelperFunctions;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class AmmunitionService {
    private final AmmunitionRepository ammunitionRepository;
    private final HelperFunctions helperFunctions;
    public List<AmmunitionDTO> getRandomAmmo(Set<Ammunition> ammoForWeapon, LoadoutRequest request) {
        return new ArrayList<>();
        //TODO: work with repository weapon to get desired lists
//        List<RandomAmmo> randomAmmos = new ArrayList<>();
//        List<RandomAmmo> collectedAmmosFromWeapon = weaponList.stream().findFirst().get().getAmmoSet().stream().toList();
//        return delegateForDualSlotWeapon(randomAmmos, collectedAmmosFromWeapon, weaponList, request);
    }
    private List<AmmunitionDTO> delegateForDualSlotWeapon(List<AmmunitionDTO> ammunitionDTOS, List<AmmunitionDTO> collectedAmmosFromWeapon, List<WeaponDTO> weaponList, LoadoutRequest request) {
        WeaponDTO weapon = weaponList.stream().findFirst().get();
        if (weapon.getAmmoSlots() > 1) {
            return getFilledDualSlotWeapon(ammunitionDTOS, collectedAmmosFromWeapon ,request);
        }
        return getFilledSingleSlotWeapon(ammunitionDTOS, collectedAmmosFromWeapon, request, weapon);
    }

    private List<AmmunitionDTO> getFilledSingleSlotWeapon(List<AmmunitionDTO> ammunitionDTOList, List<AmmunitionDTO> collectedAmmosFromWeapon, LoadoutRequest request, WeaponDTO weapon) {
        if (!(request.isSpecialAmmo())) {
            return fillWithStandardAmmo(ammunitionDTOList, collectedAmmosFromWeapon);
        }
        return delegateIfSpecial(ammunitionDTOList, collectedAmmosFromWeapon, weapon);
    }

    private List<AmmunitionDTO> delegateIfSpecial(List<AmmunitionDTO> ammunitionDTOList, List<AmmunitionDTO> collectedAmmosFromWeapon, WeaponDTO weapon) {
        if (!(helperFunctions.isSpecialAmmo())) {
            return fillWithStandardAmmo(ammunitionDTOList, collectedAmmosFromWeapon);
        }
        return filterSpecialAmmo(ammunitionDTOList, collectedAmmosFromWeapon, weapon);
    }
    private List<AmmunitionDTO> filterSpecialAmmo(List<AmmunitionDTO> ammunitionDTOList, List<AmmunitionDTO> collectedAmmosFromWeapon, WeaponDTO weapon) {
        int ammoSlots = weapon.getAmmoSlots();
        List<AmmunitionDTO> filteredAmmosFromWeapon = new ArrayList<>();
        for (int i = 0; i < ammoSlots; i++) {
            AmmunitionDTO ammunitionDTO = collectedAmmosFromWeapon.get(helperFunctions.getRandomIndex(0, collectedAmmosFromWeapon.size()));
            filteredAmmosFromWeapon.add(ammunitionDTO);
        }
        return fillWithSpecialAmmo(ammunitionDTOList, collectedAmmosFromWeapon);
    }
    private List<AmmunitionDTO> fillWithStandardAmmo(List<AmmunitionDTO> ammunitionDTOList, List<AmmunitionDTO> collectedAmmosFromWeapon) {
        List<AmmunitionDTO> standard = collectedAmmosFromWeapon.stream().filter(el -> el.getTypeOfAmmo().equalsIgnoreCase("standard")).toList();
        return fillListWithAmmos(ammunitionDTOList, standard);
    }
    private List<AmmunitionDTO> fillWithSpecialAmmo(List<AmmunitionDTO> ammunitionDTOList, List<AmmunitionDTO> filteredAmmo) {
        return fillListWithAmmos(ammunitionDTOList, filteredAmmo);
    }
    private List<AmmunitionDTO> fillListWithAmmos(List<AmmunitionDTO> ammunitionDTOList, List<AmmunitionDTO> filteredAmmo) {
        for (AmmunitionDTO ammunition : filteredAmmo) {
            AmmunitionDTO ammunitionDTO = new AmmunitionDTO();
            ammunitionDTO.setName(ammunition.getName());
            ammunitionDTO.setPrice(ammunition.getPrice());
            ammunitionDTO.setTypeOfAmmo(ammunition.getTypeOfAmmo());
            ammunitionDTOList.add(ammunitionDTO);
        }
        return ammunitionDTOList;
    }
    private List<AmmunitionDTO> getFilledDualSlotWeapon(List<AmmunitionDTO> ammunitionDTOList, List<AmmunitionDTO> collectedAmmos, LoadoutRequest request) {
        // TODO: find a way to implement a generic way to filter out different kind ammo(long, compact, shotgun, medium)
        return fillWithStandardAmmo(ammunitionDTOList, collectedAmmos);
    }
}
