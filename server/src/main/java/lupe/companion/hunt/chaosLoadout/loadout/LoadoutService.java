package lupe.companion.hunt.chaosLoadout.loadout;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lupe.companion.hunt.chaosLoadout.ammunitions.AmmunitionService;
import lupe.companion.hunt.chaosLoadout.consumables.ConsumableService;
import lupe.companion.hunt.chaosLoadout.loadout.data.RandomAmmo;
import lupe.companion.hunt.chaosLoadout.loadout.data.RandomConsumable;
import lupe.companion.hunt.chaosLoadout.loadout.data.RandomWeapon;
import lupe.companion.hunt.chaosLoadout.tools.Tool;
import lupe.companion.hunt.chaosLoadout.tools.ToolService;
import lupe.companion.hunt.chaosLoadout.weapons.WeaponService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class LoadoutService {

    private final LoadoutRepository loadoutRepository;
    private final WeaponService weaponService;
    private final ToolService toolService;
    private final ConsumableService consumableService;
    private final AmmunitionService ammunitionService;
    public Loadout generateLoadout(LoadoutRequest request) {
        List<RandomWeapon> primary = weaponService.getRandomPrimary(request);
        List<RandomWeapon> secondary = weaponService.getRandomSecondary(request, getSlotsAfterPrimary(primary));
        Set<Tool> tools = toolService.getRandomTools(request);
        List<RandomConsumable> consumables = consumableService.getRandomConsumables(request);
        List<RandomAmmo> primaryAmmo = ammunitionService.getRandomAmmo(primary, request);
        List<RandomAmmo> secondaryAmmo = ammunitionService.getRandomAmmo(secondary ,request);
        int totalPrice = calculatePrice(primary, secondary, tools, consumables, primaryAmmo, secondaryAmmo);
        return constructLoadout(primary, secondary, tools, consumables, totalPrice, primaryAmmo, secondaryAmmo);
    }

    private Loadout constructLoadout(List<RandomWeapon> primary, List<RandomWeapon> secondary, Set<Tool> tools, List<RandomConsumable> consumables, int totalPrice, List<RandomAmmo> primaryAmmo, List<RandomAmmo> secondaryAmmo) {
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

    private int calculatePrice(List<RandomWeapon> primary, List<RandomWeapon> secondary, Set<Tool> tools, List<RandomConsumable> consumables, List<RandomAmmo> primaryAmmo, List<RandomAmmo> secondaryAmmo) {
        int primaryValue = primary.stream().reduce(0, (currentPrice, weapon) -> currentPrice + weapon.getPrice(), Integer::sum);
        int secondaryValue = secondary.stream().reduce(0, (currentPrice, weapon) -> currentPrice + weapon.getPrice(), Integer::sum);
        int toolsValue = tools.stream().reduce(0, (currentPrice, tool) -> currentPrice + tool.getPrice(), Integer::sum);
        int consumablesValue = consumables.stream().reduce(0, (currentPrice, consumable) -> currentPrice + consumable.getPrice(), Integer::sum);
        int primaryAmmoValue = primaryAmmo.stream().reduce(0, (currentPrice, ammo) -> currentPrice + ammo.getPrice(), Integer::sum);
        int secondaryAmmoValue = secondaryAmmo.stream().reduce(0, (currentPrice, ammo) -> currentPrice + ammo.getPrice(), Integer::sum);
        return primaryValue + secondaryValue + toolsValue + consumablesValue + primaryAmmoValue + secondaryAmmoValue;
    }
    private Integer getSlotsAfterPrimary(List<RandomWeapon> primary) {
        return primary.stream()
                .reduce(0, (accumulator, weapon) -> accumulator + weapon.getSlots(), Integer::sum);
    }
}
