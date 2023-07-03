package lupe.hunt.companion.chaosLoadout.loadout;

import lombok.AllArgsConstructor;
import lupe.hunt.companion.chaosLoadout.PriceAble;
import lupe.hunt.companion.chaosLoadout.ammunitions.Ammunition;
import lupe.hunt.companion.chaosLoadout.ammunitions.AmmunitionService;
import lupe.hunt.companion.chaosLoadout.consumables.Consumable;
import lupe.hunt.companion.chaosLoadout.consumables.ConsumableService;
import lupe.hunt.companion.chaosLoadout.loadout.data.RandomAmmo;
import lupe.hunt.companion.chaosLoadout.loadout.data.RandomConsumable;
import lupe.hunt.companion.chaosLoadout.loadout.data.RandomWeapon;
import lupe.hunt.companion.chaosLoadout.tools.Tool;
import lupe.hunt.companion.chaosLoadout.tools.ToolService;
import lupe.hunt.companion.chaosLoadout.weapons.Weapon;
import lupe.hunt.companion.chaosLoadout.weapons.WeaponService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class LoadoutService {

    private final LoadoutRepository loadoutRepository;
    private final WeaponService weaponService;
    private final ToolService toolService;
    private final ConsumableService consumableService;
    private final AmmunitionService ammunitionService;
    public Loadout generateLoadout(LoadoutRequest request) {
        List<Weapon> primary = weaponService.getRandomPrimary(request);
        List<Weapon> secondary = weaponService.getRandomSecondary(request, getSlotsAfterPrimary(primary));
        Set<Tool> tools = toolService.getRandomTools(request);
        List<Consumable> consumables = consumableService.getRandomConsumables(request);
//        Set<RandomAmmo> primaryAvailableAmmo = weaponService.filterOutAmmo(primary);
//        Set<RandomAmmo> secondaryAvailableAmmo = weaponService.filterOutAmmo(secondary);
        List<Ammunition> primaryAmmo = ammunitionService.getRandomAmmo(primary, request);
        List<Ammunition> secondaryAmmo = ammunitionService.getRandomAmmo(secondary ,request);
        int totalPrice = getTotalPrice(primary, secondary, tools, consumables, primaryAmmo, secondaryAmmo);
        return constructLoadout(primary, secondary, tools, consumables, totalPrice, primaryAmmo, secondaryAmmo);
    }

    private Loadout constructLoadout(List<Weapon> primary, List<Weapon> secondary, Set<Tool> tools, List<Consumable> consumables, int totalPrice, List<Ammunition> primaryAmmo, List<Ammunition> secondaryAmmo) {
        Loadout randomLoadout = new Loadout();
        randomLoadout.setPrimary(primary);
        randomLoadout.setSecondary(secondary);
        randomLoadout.setTools(tools);
        randomLoadout.setConsumables(consumables);
        randomLoadout.setTotalPrice(totalPrice);
        randomLoadout.setAmmoForPrimary(primaryAmmo);
        randomLoadout.setAmmoForSecondary(secondaryAmmo);
        return randomLoadout;
    }

    private int getTotalPrice(List<Weapon> primary, List<Weapon> secondary, Set<Tool> tools, List<Consumable> consumables, List<Ammunition> primaryAmmo, List<Ammunition> secondaryAmmo) {
        List<PriceAble> priceAbles = convertToPriceAble(primary, secondary, tools.stream().toList(), consumables, primaryAmmo, secondaryAmmo);
        return priceAbles.stream().reduce(0, (currentValue, item) -> currentValue + item.getPrice(), Integer::sum);
    }

    private List<PriceAble> convertToPriceAble(List<Weapon> primary, List<Weapon> secondary, List<Tool> tools, List<Consumable> consumables, List<Ammunition> primaryAmmo, List<Ammunition> secondaryAmmo) {
        List<PriceAble> priceAbles = new ArrayList<>();
        primary.stream().map(el -> (PriceAble) el).forEach(priceAbles::add);
        secondary.stream().map(el -> (PriceAble) el).forEach(priceAbles::add);
        tools.stream().map(el -> (PriceAble) el).forEach(priceAbles::add);
        consumables.stream().map(el -> (PriceAble) el).forEach(priceAbles::add);
        primaryAmmo.stream().map(el -> (PriceAble) el).forEach(priceAbles::add);
        secondaryAmmo.stream().map(el -> (PriceAble) el).forEach(priceAbles::add);
        return priceAbles;
    }

    private Integer getSlotsAfterPrimary(List<Weapon> primary) {
        return primary.stream()
                .reduce(0, (accumulator, weapon) -> accumulator + weapon.getSlots(), Integer::sum);
    }
}
