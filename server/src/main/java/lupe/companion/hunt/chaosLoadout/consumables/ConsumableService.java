package lupe.companion.hunt.chaosLoadout.consumables;

import lombok.AllArgsConstructor;
import lupe.companion.hunt.chaosLoadout.loadout.LoadoutRequest;
import lupe.companion.hunt.logic.HelperFunctions;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ConsumableService {
    private final ConsumableRepository consumableRepository;
    private final HelperFunctions helperFunctions;
    public List<Consumable> getRandomConsumables(LoadoutRequest request) {
        List<Consumable> randomConsumables = new ArrayList<>();
        List<Consumable> consumableList = consumableRepository.findConsumableByBloodlineRankLessThanEqual(request.getBloodlineRank());
        while (!(randomConsumables.size() == 4)) {
            Consumable consumable = consumableList.get(helperFunctions.getRandomIndex(0, consumableList.size() + 1));
            randomConsumables.add(consumable);
        }
        return randomConsumables;
    }
}
