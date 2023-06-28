package lupe.companion.hunt.chaosLoadout.loadout;

import lombok.AllArgsConstructor;
import lupe.companion.hunt.chaosLoadout.PriceAble;
import lupe.companion.hunt.chaosLoadout.consumables.Consumable;
import lupe.companion.hunt.chaosLoadout.consumables.ConsumableService;
import lupe.companion.hunt.chaosLoadout.tools.Tool;
import lupe.companion.hunt.chaosLoadout.tools.ToolService;
import lupe.companion.hunt.chaosLoadout.weapons.Weapon;
import lupe.companion.hunt.chaosLoadout.weapons.WeaponService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        List<Consumable> consumables = consumableService.getRandomConsumables(request);
        int totalPrice = getTotalPrice(primary, secondary, tools, consumables);
        return constructLoadout(primary, secondary, tools, consumables, totalPrice);
    }

    private Loadout constructLoadout(List<Weapon> primary, List<Weapon> secondary, Set<Tool> tools, List<Consumable> consumables, int totalPrice) {
        Loadout randomLoadout = new Loadout();
        randomLoadout.setPrimary(primary);
        randomLoadout.setSecondary(secondary);
        randomLoadout.setTools(tools);
        randomLoadout.setConsumables(consumables);
        randomLoadout.setTotalPrice(totalPrice);
        return randomLoadout;
    }
    private int getTotalPrice(List<Weapon> primary, List<Weapon> secondary, Set<Tool> tools, List<Consumable> consumables) {
        List<PriceAble> items = getPriceAbleList(primary, secondary, tools.stream().toList(), consumables);
        return items.stream().reduce(0, (currentPrice, item) -> currentPrice + item.getPrice(), Integer::sum);
    }
    private List<PriceAble> getPriceAbleList(List<Weapon> primary, List<Weapon> secondary, List<Tool> toollist, List<Consumable> consumables) {
        List<PriceAble> items = new ArrayList<>();
        items.addAll(primary);
        items.addAll(secondary);
        items.addAll(toollist);
        items.addAll(consumables);
        return items;
    }
    private Integer getSlotsAfterPrimary(List<Weapon> primary) {
        return primary.stream()
                .reduce(0, (accumulator, weapon) -> accumulator + weapon.getSlots(), Integer::sum);
    }
}
