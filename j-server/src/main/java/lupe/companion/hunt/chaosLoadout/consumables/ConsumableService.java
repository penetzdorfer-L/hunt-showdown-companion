package lupe.companion.hunt.chaosLoadout.consumables;

import lombok.AllArgsConstructor;
import lupe.companion.hunt.chaosLoadout.loadout.LoadoutRequest;
import lupe.companion.hunt.chaosLoadout.loadout.data.RandomConsumable;
import lupe.companion.hunt.logic.HelperFunctions;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ConsumableService {
    private final ConsumableRepository consumableRepository;
    private final HelperFunctions helperFunctions;
    public List<RandomConsumable> getRandomConsumables(LoadoutRequest request) {
        List<RandomConsumable> randomConsumables = new ArrayList<>();
        List<Consumable> consumableList = consumableRepository.findConsumableByBloodlineRankLessThanEqual(request.getBloodlineRank());
        while (!(randomConsumables.size() == 4)) {
            Consumable consumable = consumableList.get(helperFunctions.getRandomIndex(0, consumableList.size()));
            initRandomConsumable(randomConsumables, consumable);
        }
        return randomConsumables;
    }

    private void initRandomConsumable(List<RandomConsumable> consumableList, Consumable consumable) {
        RandomConsumable randomConsumable = new RandomConsumable();
        randomConsumable.setName(consumable.getName());
        randomConsumable.setBloodlineRank(consumable.getBloodlineRank());
        randomConsumable.setPrice(consumable.getPrice());
        randomConsumable.setType(consumable.getType());
        consumableList.add(randomConsumable);
    }
}
