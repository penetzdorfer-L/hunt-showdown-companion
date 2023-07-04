package lupe.hunt.companion.chaosLoadout.tools;

import lombok.AllArgsConstructor;
import lupe.hunt.companion.chaosLoadout.loadout.LoadoutRequest;
import lupe.hunt.companion.logic.HelperFunctions;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class ToolService {
    private final ToolRepository toolRepository;
    private final HelperFunctions helperFunctions;

    public Set<Tool> getRandomTools(LoadoutRequest request) {
        Set<Tool> randomTools = new HashSet<>();
        requestChecker(request, randomTools);
        List<Tool> toolList = toolRepository.findToolsByBloodlineRankIsLessThanEqual(request.getBloodlineRank());
        addToolsToList(randomTools, toolList);
        return randomTools;
    }

    private void addToolsToList(Set<Tool> randomTools, List<Tool> toolList) {
        while (!(randomTools.size() == 4)) {
            Tool tool = toolList.get(helperFunctions.getRandomIndex(0, toolList.size()));
            randomTools.add(tool);
        }
    }
    private void requestChecker(LoadoutRequest request, Set<Tool> randomTools) {
        if (request.isForceMelee()) {
            Tool firstAidKit = toolRepository.findToolsByToolID("first_aid_kit");
            randomTools.add(firstAidKit);
        }
        if (request.isForceMelee()) {
            List<Tool> toolList = toolRepository.findToolsByTypeIsIgnoreCase("melee");
            Tool tool = toolList.get(helperFunctions.getRandomIndex(0, toolList.size()));
            randomTools.add(tool);
        }
    }
}
