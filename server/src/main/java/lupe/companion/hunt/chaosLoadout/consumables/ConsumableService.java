package lupe.companion.hunt.chaosLoadout.consumables;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ConsumableService {
    private final ConsumableRepository consumableRepository;
    public List<Consumable> getRandomConsumables(int bloodlineRank) {
        return null;
    }
}
