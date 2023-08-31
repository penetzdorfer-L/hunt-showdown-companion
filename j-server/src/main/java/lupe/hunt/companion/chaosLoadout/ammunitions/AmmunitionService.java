package lupe.hunt.companion.chaosLoadout.ammunitions;

import lombok.AllArgsConstructor;
import lupe.hunt.companion.chaosLoadout.loadout.LoadoutRequest;
import lupe.hunt.companion.chaosLoadout.loadout.data.AmmunitionDTO;
import lupe.hunt.companion.chaosLoadout.loadout.data.WeaponDTO;
import lupe.hunt.companion.logic.HelperFunctions;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

@Service
@AllArgsConstructor
public class AmmunitionService {
    private final AmmunitionRepository ammunitionRepository;
    private final HelperFunctions helperFunctions;
    private final ModelMapper mapper;

    public List<AmmunitionDTO> getAmmo(List<WeaponDTO> DTOweapons, Set<Ammunition> ammoForWeapon, LoadoutRequest request) {
        WeaponDTO weapon = DTOweapons.stream().findFirst().get();
        if (ammoForWeapon.isEmpty() || !(request.isSpecialAmmo())) {
            return handleVanillaOrEmpty(ammoForWeapon, weapon);
        }
        return getRandomAmmo(weapon, ammoForWeapon);
    }

    private List<AmmunitionDTO> handleVanillaOrEmpty(Set<Ammunition> ammoForWeapon, WeaponDTO weapon) {
        if (ammoForWeapon.isEmpty()) {
            return new ArrayList<>();
        }
        List<Ammunition> standard = ammoForWeapon.stream()
                .filter(el -> el.getTypeOfAmmo().equalsIgnoreCase("standard"))
                .toList();
        return getVanillaDTO(standard, weapon);
    }

    private List<AmmunitionDTO> getVanillaDTO(List<Ammunition> standard, WeaponDTO weapon) {
        if (standard.size() > 1) {
            List<AmmunitionDTO> ammunition = new ArrayList<>();
            standard.forEach(el -> ammunition.add(mapper.map(el, AmmunitionDTO.class)));
            return ammunition;
        }
        Ammunition ammunition = standard.stream().findFirst().get();
        return fillWithVanillaAmmo(ammunition, weapon);
    }

    private List<AmmunitionDTO> fillWithVanillaAmmo(Ammunition standard, WeaponDTO weapon) {
        ArrayList<AmmunitionDTO> ammunitions = new ArrayList<>();
        IntStream.of(weapon.getAmmoSlots()).forEach(el -> ammunitions.add(mapper.map(standard, AmmunitionDTO.class)));
        return (List.of(mapper.map(standard, AmmunitionDTO.class)));
    }

    private List<AmmunitionDTO> getRandomAmmo(WeaponDTO weapon, Set<Ammunition> ammoForWeapon) {
        List<AmmunitionDTO> ammunitionDTOS = checkForDualTypeWeapons(ammoForWeapon);
        if (!(ammunitionDTOS.isEmpty())) {
            return ammunitionDTOS;
        }
        return getFilledRandomAmmoList(weapon, ammoForWeapon, ammunitionDTOS);
    }

    private List<AmmunitionDTO> checkForDualTypeWeapons(Set<Ammunition> ammoForWeapon) {
        List<Ammunition> vanillaAmmo = ammoForWeapon.stream()
                .filter(el -> el.getTypeOfAmmo().equalsIgnoreCase("standard"))
                .toList();
        if (vanillaAmmo.size() > 1) {
            //TODO: Think of dual Type Weapons Solution
            List<AmmunitionDTO> vanillaAmmoForNow = new ArrayList<>();
            vanillaAmmo.forEach(el -> vanillaAmmoForNow.add(mapper.map(el, AmmunitionDTO.class)));
            return vanillaAmmoForNow;
        }
        return new ArrayList<>();
    }
    private List<AmmunitionDTO> getFilledRandomAmmoList(WeaponDTO weapon, Set<Ammunition> ammoForWeapon, List<AmmunitionDTO> ammunitionDTOS) {
        List<Ammunition> ammoConvertedToList = ammoForWeapon.stream().toList();
        while(ammunitionDTOS.size() < weapon.getAmmoSlots()){
            int randomIndex = helperFunctions.getRandomIndex(0, ammoConvertedToList.size());
            ammunitionDTOS.add(mapper.map(ammoConvertedToList.get(randomIndex), AmmunitionDTO.class));
        }
        return ammunitionDTOS;
    }
}
