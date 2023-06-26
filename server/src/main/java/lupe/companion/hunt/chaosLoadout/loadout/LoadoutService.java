package lupe.companion.hunt.chaosLoadout.loadout;

import lombok.AllArgsConstructor;
import lupe.companion.hunt.chaosLoadout.consumables.Consumable;
import lupe.companion.hunt.chaosLoadout.consumables.ConsumableService;
import lupe.companion.hunt.chaosLoadout.tools.Tool;
import lupe.companion.hunt.chaosLoadout.tools.ToolService;
import lupe.companion.hunt.chaosLoadout.weapons.Weapon;
import lupe.companion.hunt.chaosLoadout.weapons.WeaponService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class LoadoutService {

    private LoadoutRepository loadoutRepository;
    private WeaponService weaponService;
    private ToolService toolService;
    private ConsumableService consumableService;
    public Loadout generateLoadout(LoadoutRequest request) {
        List<Weapon> primary = getRandomPrimary(
                request.getBloodlineRank(),
                request.isDualWield(),
                request.isQuarterMaster(),
                request.isSpecialAmmo()
        );
        List<Weapon> secondary = getRandomSecondary(
                request.getBloodlineRank(),
                request.isDualWield(),
                request.isQuarterMaster(),
                request.isSpecialAmmo(),
                getSlotsAfterPrimary(primary)
        );
        Set<Tool> tools = getRandomTools(request.getBloodlineRank(), request.isForceMelee(), request.isForceFirstAidKit());
        List<Consumable> consumables = getRandomConsumables(request.getBloodlineRank());
        int totalPrice = calculatePrice(primary, secondary, tools, consumables);
        return new Loadout(1,primary,secondary,tools,consumables,totalPrice);
    }
    private List<Weapon> getRandomPrimary(int bloodlineRank, boolean dualWield, boolean quarterMaster, boolean specialAmmo) {
        return null;
    }

    private List<Weapon> getRandomSecondary(int bloodlineRank, boolean dualWield, boolean quarterMaster, boolean specialAmmo, int slotsUsed) {
        return null;
    }

    private Set<Tool> getRandomTools(int bloodlineRank, boolean forceMelee, boolean forceFirstAidKit) {
        return null;
    }
    private List<Consumable> getRandomConsumables(int bloodlineRank) {
        return null;
    }
    private int calculatePrice(List<Weapon> primary, List<Weapon> secondary, Set<Tool> tools, List<Consumable> consumables) {
        int primaryValue = primary.stream().reduce(0, (currentPrice, weapon) -> currentPrice + weapon.getPrice(), Integer::sum);
        int secondaryValue = secondary.stream().reduce(0, (currentPrice, weapon) -> currentPrice + weapon.getPrice(), Integer::sum);
        int toolsValue = tools.stream().reduce(0, (currentPrice, tool) -> currentPrice + tool.getPrice(), Integer::sum);
        int consumablesValue = consumables.stream().reduce(0, (currentPrice, consumable) -> currentPrice + consumable.getPrice(), Integer::sum);
        return primaryValue + secondaryValue + toolsValue + consumablesValue;
    }
    private static Integer getSlotsAfterPrimary(List<Weapon> primary) {
        return primary.stream()
                .reduce(0, (accumulator, weapon) -> accumulator + weapon.getSlots(), Integer::sum);
    }
}
