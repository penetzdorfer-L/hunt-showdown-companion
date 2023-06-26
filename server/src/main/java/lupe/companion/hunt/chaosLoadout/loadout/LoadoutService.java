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
        List<Weapon> primary = weaponService.getRandomPrimary(request);
        List<Weapon> secondary = weaponService.getRandomSecondary(request, getSlotsAfterPrimary(primary));
        Set<Tool> tools = toolService.getRandomTools(request);
        List<Consumable> consumables = consumableService.getRandomConsumables(request.getBloodlineRank());
        int totalPrice = calculatePrice(primary, secondary, tools, consumables);
        return new Loadout(1,primary,secondary,tools,consumables,totalPrice);
    }
    private int calculatePrice(List<Weapon> primary, List<Weapon> secondary, Set<Tool> tools, List<Consumable> consumables) {
        int primaryValue = primary.stream().reduce(0, (currentPrice, weapon) -> currentPrice + weapon.getPrice(), Integer::sum);
        int secondaryValue = secondary.stream().reduce(0, (currentPrice, weapon) -> currentPrice + weapon.getPrice(), Integer::sum);
        int toolsValue = tools.stream().reduce(0, (currentPrice, tool) -> currentPrice + tool.getPrice(), Integer::sum);
        int consumablesValue = consumables.stream().reduce(0, (currentPrice, consumable) -> currentPrice + consumable.getPrice(), Integer::sum);
        return primaryValue + secondaryValue + toolsValue + consumablesValue;
    }
    private Integer getSlotsAfterPrimary(List<Weapon> primary) {
        return primary.stream()
                .reduce(0, (accumulator, weapon) -> accumulator + weapon.getSlots(), Integer::sum);
    }
}
