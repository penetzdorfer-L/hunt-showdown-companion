package lupe.hunt.companion.chaosLoadout.consumables;

import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import lupe.hunt.companion.chaosLoadout.loadout.LoadoutRequest;
import lupe.hunt.companion.chaosLoadout.loadout.data.ConsumableDTO;
import lupe.hunt.companion.logic.HelperFunctions;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ConsumableService {
    private final ConsumableRepository consumableRepository;
    private final HelperFunctions helperFunctions;
    private final ModelMapper mapper;
    public List<ConsumableDTO> getRandomConsumables(LoadoutRequest request) {
        List<ConsumableDTO> randomConsumables = new ArrayList<>();
        List<Consumable> consumableList = consumableRepository.findConsumableByBloodlineRankLessThanEqual(request.getBloodlineRank());
        while (!(randomConsumables.size() == 4)) {
            Consumable consumable = consumableList.get(helperFunctions.getRandomIndex(0, consumableList.size()));
            randomConsumables.add(mapper.map(consumable, ConsumableDTO.class));
        }
        return randomConsumables;
    }

    public List<Consumable> generateDuplicates() {
        Consumable liquidFireBom = consumableRepository.findConsumableByConsumableID("liquid_fire_bomb");
        return new ArrayList<>(List.of(liquidFireBom, liquidFireBom, liquidFireBom, liquidFireBom));
    }
}
