package lupe.hunt.companion.chaosLoadout.loadout;

import lombok.AllArgsConstructor;
import lupe.hunt.companion.chaosLoadout.PriceAble;
import lupe.hunt.companion.chaosLoadout.ammunitions.Ammunition;
import lupe.hunt.companion.chaosLoadout.ammunitions.AmmunitionService;
import lupe.hunt.companion.chaosLoadout.consumables.Consumable;
import lupe.hunt.companion.chaosLoadout.consumables.ConsumableService;
import lupe.hunt.companion.chaosLoadout.loadout.data.AmmunitionDTO;
import lupe.hunt.companion.chaosLoadout.loadout.data.ConsumableDTO;
import lupe.hunt.companion.chaosLoadout.loadout.data.WeaponDTO;
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
        List<WeaponDTO> primary = weaponService.getRandomPrimary(request);
        List<WeaponDTO> secondary = weaponService.getRandomSecondary(request, getSlotsAfterPrimary(primary));
        Set<Tool> tools = toolService.getRandomTools(request);
        List<ConsumableDTO> consumables = consumableService.getRandomConsumables(request);
        Set<Ammunition> primaryAvailableAmmo = weaponService.filterOutAmmo(primary);
        Set<Ammunition> secondaryAvailableAmmo = weaponService.filterOutAmmo(secondary);
        List<AmmunitionDTO> primaryAmmo = ammunitionService.getRandomAmmo(primaryAvailableAmmo, request);
        List<AmmunitionDTO> secondaryAmmo = ammunitionService.getRandomAmmo(secondaryAvailableAmmo ,request);
//        List<AmmunitionDTO> primaryAmmo = ammunitionService.fillWithNothing();
//        List<AmmunitionDTO> secondaryAmmo = ammunitionService.fillWithNothing();
        int totalPrice = getTotalPrice(primary, secondary, tools, consumables, primaryAmmo, secondaryAmmo);
        return constructLoadout(primary, secondary, tools, consumables, totalPrice, primaryAmmo, secondaryAmmo);
    }

    private Loadout constructLoadout(List<WeaponDTO> primary, List<WeaponDTO> secondary, Set<Tool> tools, List<ConsumableDTO> consumables, int totalPrice, List<AmmunitionDTO> primaryAmmo, List<AmmunitionDTO> secondaryAmmo) {
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

    private int getTotalPrice(List<WeaponDTO> primary, List<WeaponDTO> secondary, Set<Tool> tools, List<ConsumableDTO> consumables, List<AmmunitionDTO> primaryAmmo, List<AmmunitionDTO> secondaryAmmo) {
        List<PriceAble> priceAbles = convertToPriceAble(primary, secondary, tools.stream().toList(), consumables, primaryAmmo, secondaryAmmo);
        return priceAbles.stream().reduce(0, (currentValue, item) -> currentValue + item.getPrice(), Integer::sum);
    }

    private List<PriceAble> convertToPriceAble(List<WeaponDTO> primary, List<WeaponDTO> secondary, List<Tool> tools, List<ConsumableDTO> consumables, List<AmmunitionDTO> primaryAmmo, List<AmmunitionDTO> secondaryAmmo) {
        List<PriceAble> priceAbles = new ArrayList<>();
        primary.stream().map(el -> (PriceAble) el).forEach(priceAbles::add);
        secondary.stream().map(el -> (PriceAble) el).forEach(priceAbles::add);
        tools.stream().map(el -> (PriceAble) el).forEach(priceAbles::add);
        consumables.stream().map(el -> (PriceAble) el).forEach(priceAbles::add);
        primaryAmmo.stream().map(el -> (PriceAble) el).forEach(priceAbles::add);
        secondaryAmmo.stream().map(el -> (PriceAble) el).forEach(priceAbles::add);
        return priceAbles;
    }

    private Integer getSlotsAfterPrimary(List<WeaponDTO> primary) {
        return primary.stream()
                .reduce(0, (accumulator, weapon) -> accumulator + weapon.getSlots(), Integer::sum);
    }
}
